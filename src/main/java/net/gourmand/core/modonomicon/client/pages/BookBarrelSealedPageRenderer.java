package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.recipes.SealedBarrelRecipe;
import net.dries007.tfc.util.calendar.Calendars;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookBarrelSealedPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public class BookBarrelSealedPageRenderer extends BookRecipePageRenderer<SealedBarrelRecipe, BookBarrelSealedPage> {

    public BookBarrelSealedPageRenderer(BookBarrelSealedPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 52;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<SealedBarrelRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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

        SealedBarrelRecipe sealedBarrelRecipe = recipe.value();

        RenderSystem.enableBlend();
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY, 11, 71, 96, 24, 128, 256);
        // text
        guiGraphics.drawString(Minecraft.getInstance().font, Calendars.CLIENT.getTimeDelta(sealedBarrelRecipe.getDuration()), recipeX - 12 + 54 - 6, recipeY + 24, 0x404040, false);

        // inputs
        FluidStack fluidInput = sealedBarrelRecipe.getInputFluid().getFluids()[(parentScreen.getTicksInBook() / 20) % sealedBarrelRecipe.getInputFluid().getFluids().length];
        ItemStack itemInput = sealedBarrelRecipe.getInputItem().getItems()[(parentScreen.getTicksInBook() / 20) % sealedBarrelRecipe.getInputItem().getItems().length];

        this.parentScreen.renderFluidStack(guiGraphics, recipeX + 3, recipeY + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidInput.getFluid(), fluidInput.getAmount()));
        this.parentScreen.renderIngredient(guiGraphics, recipeX + 39, recipeY + 3, mouseX, mouseY, Ingredient.of(itemInput));


        // outputs
        if (!sealedBarrelRecipe.getResultItem(null).isEmpty()){
            this.parentScreen.renderItemStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, sealedBarrelRecipe.getResultItem());
        }

        if (!sealedBarrelRecipe.getOutputFluid().isEmpty()){
            FluidStack fluidStack = sealedBarrelRecipe.getOutputFluid();
            this.parentScreen.renderFluidStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluidStack.getFluid(), fluidStack.getAmount()));
        }
    }
}
