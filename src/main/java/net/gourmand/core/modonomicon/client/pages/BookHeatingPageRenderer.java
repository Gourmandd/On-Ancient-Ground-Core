package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.config.TemperatureDisplayStyle;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookHeatingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class BookHeatingPageRenderer extends BookRecipePageRenderer<HeatingRecipe, BookHeatingPage> {

    public BookHeatingPageRenderer(BookHeatingPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 45;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<HeatingRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12, recipeY, 11, 71, 24, 24, 128, 256); // box
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 26, recipeY+ 7, 38, 79, 9, 9, 128, 256); // arrow

        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 80, recipeY + 7, 38, 79, 9, 9, 128, 256); // arrow
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX - 12 + 92, recipeY, 11, 71, 24, 24, 128, 256); // box

        var heatingRecipe = recipe.value();
        final TemperatureDisplayStyle style = TFCConfig.CLIENT.climateTooltipStyle.get();

        this.parentScreen.renderIngredient(guiGraphics, recipeX - 12 + 4, recipeY + 4, mouseX, mouseY, heatingRecipe.getIngredient(), 2);
        guiGraphics.drawString(Minecraft.getInstance().font, Objects.requireNonNull(style.formatColored(heatingRecipe.getTemperature())), recipeX + 30, recipeY + 7, 0x404040, true);
        this.parentScreen.renderItemStack(guiGraphics, recipeX - 12 + 86 + 10, recipeY + 4, mouseX, mouseY, heatingRecipe.getResultItem(world.registryAccess()));
        this.parentScreen.renderFluidStack(guiGraphics, recipeX - 12 + 86 + 9, recipeY + 3, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(heatingRecipe.assembleFluid(heatingRecipe.getResultItem(world.registryAccess()))));
    }
}
