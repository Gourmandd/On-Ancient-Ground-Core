package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.recipes.QuernRecipe;
import net.gourmand.core.modonomicon.pages.BookQuernPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class BookQuernPageRenderer extends BookRecipePageRenderer<QuernRecipe, BookQuernPage> {

    public BookQuernPageRenderer(BookQuernPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 45;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<QuernRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

        Level world = Minecraft.getInstance().level;
        if (world == null) return;

        recipeY += 10;

        if (!second) {
            if (!this.page.getTitle1().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle1(), false, BookEntryScreen.PAGE_WIDTH / 2, 0);
            }
        } else {
            if (!this.page.getTitle2().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle2(), false, BookEntryScreen.PAGE_WIDTH / 2,
                        recipeY - (this.page.getTitle2().getString().isEmpty() ? 10 : 0) - 10);
            }
        }

        RenderSystem.enableBlend();
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY, 11, 71, 96, 24, 128, 256);

        var quern_recipe = recipe.value();

        this.parentScreen.renderIngredient(guiGraphics, recipeX + 4, recipeY + 4, mouseX, mouseY, quern_recipe.getIngredient());
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 40, recipeY + 4, mouseX, mouseY, TFCBlocks.QUERN.asItem().getDefaultInstance());
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, quern_recipe.getResultItem(world.registryAccess()));
    }
}
