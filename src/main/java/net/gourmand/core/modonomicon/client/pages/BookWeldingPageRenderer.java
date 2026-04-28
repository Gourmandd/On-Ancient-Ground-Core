package net.gourmand.core.modonomicon.client.pages;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.WeldingRecipe;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.pages.BookWeldingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Map;

public class BookWeldingPageRenderer extends BookRecipePageRenderer<WeldingRecipe, BookWeldingPage> {

    final static Map<Integer, Item> TIER_TO_BLOCK = ImmutableMap.<Integer, Item>builder()
            .put(0, TFCBlocks.ROCK_ANVILS.get(Rock.ANDESITE).asItem())
            .put(1, TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.ANVIL).asItem())
            .put(2, TFCBlocks.METALS.get(Metal.BRONZE).get(Metal.BlockType.ANVIL).asItem())
            .put(3, TFCBlocks.METALS.get(Metal.WROUGHT_IRON).get(Metal.BlockType.ANVIL).asItem())
            .put(4, TFCBlocks.METALS.get(Metal.STEEL).get(Metal.BlockType.ANVIL).asItem())
            .put(5, TFCBlocks.METALS.get(Metal.BLACK_STEEL).get(Metal.BlockType.ANVIL).asItem())
            .put(6, TFCBlocks.METALS.get(Metal.BLUE_STEEL).get(Metal.BlockType.ANVIL).asItem())
            .build();

    public BookWeldingPageRenderer(BookWeldingPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 60;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<WeldingRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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

        var weldingRecipe = recipe.value();

        // items
        this.parentScreen.renderIngredient(guiGraphics, recipeX + 4, recipeY + 4, mouseX, mouseY, weldingRecipe.getFirstInput());
        this.parentScreen.renderIngredient(guiGraphics, recipeX + 40, recipeY + 4, mouseX, mouseY, weldingRecipe.getSecondInput());
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, weldingRecipe.getResultItem(world.registryAccess()));

        // anvil + flux
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 4, recipeY + 4 + 19, mouseX, mouseY, new ItemStack(TFCItems.POWDERS.get(Powder.FLUX).asItem()));
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 40, recipeY + 4 + 19, mouseX, mouseY, new ItemStack(TIER_TO_BLOCK.get(weldingRecipe.getTier())));
    }
}
