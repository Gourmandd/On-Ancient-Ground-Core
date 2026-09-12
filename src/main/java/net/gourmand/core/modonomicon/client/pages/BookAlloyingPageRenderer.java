package net.gourmand.core.modonomicon.client.pages;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.klikli_dev.modonomicon.fluid.NeoFluidHolder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.recipes.AlloyRecipe;
import net.gourmand.core.modonomicon.pages.BookAlloyingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import org.joml.Vector2i;

import java.util.Map;

public class BookAlloyingPageRenderer extends BookRecipePageRenderer<AlloyRecipe, BookAlloyingPage> {

    public BookAlloyingPageRenderer(BookAlloyingPage page) {
        super(page);
    }

    public static final Map<Integer, Vector2i> FLUID_POSITIONS = ImmutableMap.<Integer, Vector2i>builder()
            .put(0, new Vector2i(3, 3 + 10))
            .put(1, new Vector2i(3, 27 + 10))
            .put(2, new Vector2i(3, 51 + 10))
            .put(3, new Vector2i(3, 75 + 10))
            .build();

    @Override
    protected int getRecipeHeight() {
        return 45;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<AlloyRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

        Level world = Minecraft.getInstance().level;
        if (world == null) return;

        if (!second) {
            if (!this.page.getTitle1().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle1(), false, BookEntryScreen.PAGE_WIDTH / 2, 0);
            }
        } else {
            if (!this.page.getTitle2().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle2(), false, BookEntryScreen.PAGE_WIDTH / 2,
                        recipeY - (this.page.getTitle2().getString().isEmpty() ? 10 : 0)
                );
            }
        }

        RenderSystem.enableBlend();
        //guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY + 10, 11, 71, 96, 24, 128, 256);

        var alloying_recipe = recipe.value();

        //fluid stack 1-4
        alloying_recipe.contents().forEach(range -> {

            int count = alloying_recipe.contents().indexOf(range);

            this.parentScreen.renderFluidStack(guiGraphics, recipeX + FLUID_POSITIONS.get(count).x, recipeY + FLUID_POSITIONS.get(count).y, mouseX, mouseY, new NeoFluidHolder(new FluidStack(range.fluid(), 1000)));
            drawCenteredStringNoShadow(guiGraphics, range.min() + "%-" + range.max() + "%", recipeX + FLUID_POSITIONS.get(count).x + 39, recipeY + FLUID_POSITIONS.get(count).y - 2, 1, 0.5f);
        });
        // result
        this.parentScreen.renderFluidStack(guiGraphics, recipeX + 70, recipeY + 18, mouseX, mouseY, new NeoFluidHolder(new FluidStack(alloying_recipe.result(), 1000)));
    }
}
