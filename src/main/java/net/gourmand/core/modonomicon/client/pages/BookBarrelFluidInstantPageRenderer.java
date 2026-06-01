package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.recipes.InstantFluidBarrelRecipe;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookBarrelInstantFluidPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public class BookBarrelFluidInstantPageRenderer extends BookRecipePageRenderer<InstantFluidBarrelRecipe, BookBarrelInstantFluidPage> {

    public BookBarrelFluidInstantPageRenderer(BookBarrelInstantFluidPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 52;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<InstantFluidBarrelRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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

        InstantFluidBarrelRecipe instantFluidBarrelRecipe = recipe.value();

        RenderSystem.enableBlend();

        // background for items and fluids
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12, recipeY + 8, 11, 71, 24, 24, 128, 256);
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 24, recipeY + 8, 11, 71, 24, 24, 128, 256);

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 68, recipeY + 8, 11, 71, 24, 24, 128, 256);

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 54, recipeY + 14, 38, 79, 9, 9, 128, 256);

        // arrow and text
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 54, recipeY + 14, 38, 79, 9, 9, 128, 256);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable("tfc.tooltip.barrel.instant"), recipeX - 12 + 54 - 10, recipeY + 32, 0x404040, false);

        // inputs
        FluidStack fluidInput = instantFluidBarrelRecipe.getInputFluid().getFluids()[(parentScreen.getTicksInBook() / 20) % instantFluidBarrelRecipe.getInputFluid().getFluids().length];
        FluidStack fluidAddedInput = instantFluidBarrelRecipe.getAddedFluid().getFluids()[(parentScreen.getTicksInBook() / 20) % instantFluidBarrelRecipe.getInputFluid().getFluids().length];

        this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 3, recipeY + 8 + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidInput.getFluid(), fluidInput.getAmount()));

        this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 20 + 7, recipeY + 8 + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidAddedInput.getFluid(), fluidAddedInput.getAmount()));

        if (!instantFluidBarrelRecipe.getOutputFluid().isEmpty()) {
            FluidStack fluidStack = instantFluidBarrelRecipe.getOutputFluid();
            this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 66 + 5, recipeY + 8 + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidStack.getFluid(), fluidStack.getAmount()));
        }
    }
}
