package net.gourmand.core.modonomicon.client.pages;

import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.recipes.MixingBowlRecipe;
import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookMixingBowlPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.joml.Vector2i;

import java.util.List;
import java.util.Map;

public class BookMixingBowlPageRenderer extends BookRecipePageRenderer<MixingBowlRecipe, BookMixingBowlPage> {

    public BookMixingBowlPageRenderer(BookMixingBowlPage page) {
        super(page);
    }

    public static final Map<Integer, Vector2i> ITEM_POSITIONS = ImmutableMap.<Integer, Vector2i>builder()
            .put(0, new Vector2i(5, 16))
            .put(1, new Vector2i(29, 16))
            .put(2, new Vector2i(5, 40))
            .put(3, new Vector2i(29, 40))
            .put(4, new Vector2i(17, 64))
            .build();

    @Override
    protected int getRecipeHeight() {
        return 92;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<MixingBowlRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

        Level world = Minecraft.getInstance().level;
        if (world == null) return;

        if (!second) {
            if (!this.page.getTitle1().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle1(), false, BookEntryScreen.PAGE_WIDTH / 2, 0);
            }
        } // only rendering 1 for now

        RenderSystem.enableBlend();

        // the 5 items
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12, recipeY + 8, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 24, recipeY + 8, 11, 71, 24, 24, 128, 256);

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12, recipeY + 32, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 24, recipeY + 32, 11, 71, 24, 24, 128, 256);

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 12, recipeY + 56, 11, 71, 24, 24, 128, 256);

        // stuff to the right
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 58, recipeY + 8 + 24, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 92, recipeY + 8, 11, 71, 24, 24, 128, 256);

        // arrow and text
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 10 + 47, recipeY + 15, 38, 79, 9, 9, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 83, recipeY + 15, 38, 79, 9, 9, 128, 256);

        // items and fluids

        MixingBowlRecipe mixingBowlRecipe = recipe.value();

        List<Ingredient> ingredients = mixingBowlRecipe.getItemIngredients();

        for (int i = 0; i < ingredients.size(); i++) {
            this.parentScreen.renderIngredient(guiGraphics, ITEM_POSITIONS.get(i).x, ITEM_POSITIONS.get(i).y, mouseX, mouseY, ingredients.get(i));
        }

        if (mixingBowlRecipe.getFluidIngredient().isPresent()){
            this.parentScreen.renderFluidStack(guiGraphics, recipeX + 49, recipeY + 35, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(mixingBowlRecipe.getFluidIngredient().get().getFluids()[0]));
        }
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 50, recipeY + 11, mouseX, mouseY, new ItemStack(FLBlocks.MIXING_BOWL.asItem()));
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 84, recipeY + 12, mouseX, mouseY, mixingBowlRecipe.getResultItem(world.registryAccess()));
    }
}
