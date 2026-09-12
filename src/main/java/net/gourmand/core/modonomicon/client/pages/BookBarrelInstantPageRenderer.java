package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.recipes.InstantBarrelRecipe;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookBarrelInstantPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public class BookBarrelInstantPageRenderer extends BookRecipePageRenderer<InstantBarrelRecipe, BookBarrelInstantPage> {

    public BookBarrelInstantPageRenderer(BookBarrelInstantPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 52;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<InstantBarrelRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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

        InstantBarrelRecipe instantBarrelRecipe = recipe.value();

        RenderSystem.enableBlend();
        //guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY, 11, 71, 96, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12, recipeY + 8, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 24, recipeY + 8, 11, 71, 24, 24, 128, 256);

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 68, recipeY + 8, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 92, recipeY + 8, 11, 71, 24, 24, 128, 256);

        // arrow and text
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 54, recipeY + 14, 38, 79, 9, 9, 128, 256);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("tfc.tooltip.barrel.instant"), recipeX - 12 + 54 - 10, recipeY + 32, 0x404040, false);

        // inputs
        FluidStack fluidInput = instantBarrelRecipe.getInputFluid().getFluids()[(parentScreen.getTicksInBook() / 20) % instantBarrelRecipe.getInputFluid().getFluids().length];
        ItemStack itemInput = instantBarrelRecipe.getInputItem().getItems()[(parentScreen.getTicksInBook() / 20) % instantBarrelRecipe.getInputItem().getItems().length];

        this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 3, recipeY + 8 + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidInput.getFluid(), fluidInput.getAmount()));

        this.parentScreen.renderIngredient(guiGraphics, recipeX - 12 + 20 + 7, recipeY + 8 + 4, mouseX, mouseY, Ingredient.of(itemInput));

        if (!instantBarrelRecipe.getResultItem(null).isEmpty()){
            this.parentScreen.renderItemStack(guiGraphics, recipeX - 12 + 66 + 5, recipeY + 8 + 3, mouseX, mouseY, instantBarrelRecipe.getResultItem());
        }

        if (!instantBarrelRecipe.getOutputFluid().isEmpty()) {
            FluidStack fluidStack = instantBarrelRecipe.getOutputFluid();
            this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 86 + 9, recipeY + 8 + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidStack.getFluid(), fluidStack.getAmount()));
        }
    }
}
