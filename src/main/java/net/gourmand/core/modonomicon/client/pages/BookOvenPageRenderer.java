package net.gourmand.core.modonomicon.client.pages;

import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.blocks.oven.OvenType;
import com.eerussianguy.firmalife.common.recipes.OvenRecipe;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.config.ItemTemperatureDisplayStyle;
import net.dries007.tfc.config.TFCConfig;
import net.gourmand.core.modonomicon.pages.BookOvenPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class BookOvenPageRenderer extends BookRecipePageRenderer<OvenRecipe, BookOvenPage>
{

    public BookOvenPageRenderer(BookOvenPage page)
    {
        super(page);
    }

    private static final int TEXT_SPACING = 20;

    @Override
    protected int getRecipeHeight()
    {
        return 60;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<OvenRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second)
    {

        Level world = Minecraft.getInstance().level;
        if (world == null) return;

        recipeY += 10;

        if (!second)
        {
            if (!this.page.getTitle1().isEmpty())
            {
                this.renderTitle(guiGraphics, this.page.getTitle1(), false, BookEntryScreen.PAGE_WIDTH / 2, 0);
            }
        }
        else
        {
            if (!this.page.getTitle2().isEmpty())
            {
                this.renderTitle(guiGraphics, this.page.getTitle2(), false, BookEntryScreen.PAGE_WIDTH / 2, recipeY - (this.page.getTitle2().getString().isEmpty() ? 10 : 0) - 10);
            }
        }

        RenderSystem.enableBlend();
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY, 11, 71, 96, 24, 128, 256);

        final ItemTemperatureDisplayStyle style = TFCConfig.CLIENT.heatTooltipStyle.get();
        OvenRecipe ovenRecipe = recipe.value();

        this.parentScreen.renderIngredient(guiGraphics, recipeX + 4, recipeY + 4, mouseX, mouseY, ovenRecipe.getIngredient());
        guiGraphics.drawString(Minecraft.getInstance().font, Objects.requireNonNull(style.formatColored(ovenRecipe.getTemperature())), recipeX, recipeY + 4 + TEXT_SPACING, 0x404040, true);

        this.parentScreen.renderItemStack(guiGraphics, recipeX + 40, recipeY + 4, mouseX, mouseY, FLBlocks.CURED_OVEN_TOP.get(OvenType.BRICK).asItem().getDefaultInstance());

        this.parentScreen.renderItemStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, ovenRecipe.getResult().stack());
        guiGraphics.drawString(Minecraft.getInstance().font, ovenRecipe.getDuration() + "s", recipeX + 72, recipeY + 4 + TEXT_SPACING, 0x404040, true);
    }
}
