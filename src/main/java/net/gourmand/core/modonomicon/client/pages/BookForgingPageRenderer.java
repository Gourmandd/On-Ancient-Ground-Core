package net.gourmand.core.modonomicon.client.pages;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.recipes.AnvilRecipe;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.pages.BookForgingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Map;

public class BookForgingPageRenderer extends BookRecipePageRenderer<AnvilRecipe, BookForgingPage> {

    final static Map<Integer, Item> TIER_TO_BLOCK = ImmutableMap.<Integer, Item>builder()
            .put(0, TFCBlocks.ROCK_ANVILS.get(Rock.ANDESITE).asItem())
            .put(1, TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.ANVIL).asItem())
            .put(2, TFCBlocks.METALS.get(Metal.BRONZE).get(Metal.BlockType.ANVIL).asItem())
            .put(3, TFCBlocks.METALS.get(Metal.WROUGHT_IRON).get(Metal.BlockType.ANVIL).asItem())
            .put(4, TFCBlocks.METALS.get(Metal.STEEL).get(Metal.BlockType.ANVIL).asItem())
            .put(5, TFCBlocks.METALS.get(Metal.BLACK_STEEL).get(Metal.BlockType.ANVIL).asItem())
            .put(6, TFCBlocks.METALS.get(Metal.BLUE_STEEL).get(Metal.BlockType.ANVIL).asItem())
            .build();

    public BookForgingPageRenderer(BookForgingPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 45;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<AnvilRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

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

        var forging_recipe = recipe.value();

        this.parentScreen.renderIngredient(guiGraphics, recipeX + 4, recipeY + 4, mouseX, mouseY, forging_recipe.getInput());
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 40, recipeY + 4, mouseX, mouseY, new ItemStack(TIER_TO_BLOCK.get(forging_recipe.getMinTier())));
        this.parentScreen.renderItemStack(guiGraphics, recipeX + 76, recipeY + 4, mouseX, mouseY, forging_recipe.getResultItem(world.registryAccess()));
    }
}
