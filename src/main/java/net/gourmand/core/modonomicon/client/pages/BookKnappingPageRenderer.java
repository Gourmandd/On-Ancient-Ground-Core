package net.gourmand.core.modonomicon.client.pages;

import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.client.screen.KnappingScreen;
import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.dries007.tfc.compat.patchouli.PatchouliIntegration;
import net.dries007.tfc.util.data.KnappingPattern;
import net.gourmand.core.modonomicon.pages.BookKnappingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class BookKnappingPageRenderer extends BookRecipePageRenderer<KnappingRecipe, BookKnappingPage> {

    public BookKnappingPageRenderer(BookKnappingPage page) {
        super(page);
    }

    @Override
    protected int getRecipeHeight() {
        return 96;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<KnappingRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

        Level world = Minecraft.getInstance().level;
        if (world == null) return; // we need to get the resultItem later
        if (second) return; // Not supporting two recipes, since the recipe preview is too big.

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(recipeX, recipeY, 0);

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, PatchouliIntegration.TEXTURE);

        ItemStack[] ingredients = getIngredients(recipe);

        ItemStack input = ingredients[(parentScreen.getTicksInBook() / 20) % ingredients.length];

        render(guiGraphics, mouseX, mouseY, recipe.value(), recipe.value().getResultItem(world.registryAccess()), KnappingScreen.getHighTexture(input), KnappingScreen.getLowTexture(recipe.value().knappingType().get(), input), recipeX, recipeY);
        guiGraphics.pose().popPose();
    }

    private void render(GuiGraphics graphics, int mouseX, int mouseY, KnappingRecipe recipe, ItemStack resultStack, @Nullable ResourceLocation highTexture, @Nullable ResourceLocation lowTexture, int recipeX, int recipeY){

        int x0 = recipeX - 27;
        int y0 = recipeY - 10;

        graphics.blit(PatchouliIntegration.TEXTURE, x0, y0, 0, 0, 116, 90, 256, 256);

        final KnappingPattern pattern = recipe.getPattern();

        // If the pattern is < 5 wide in any direction, we offset it so it appears centered, rounding down
        final int offsetY = (KnappingPattern.MAX_HEIGHT - pattern.getHeight()) / 2;
        final int offsetX = (KnappingPattern.MAX_WIDTH - pattern.getWidth()) / 2;

        for (int y = 0; y < KnappingPattern.MAX_HEIGHT; y++)
        {
            for (int x = 0; x < KnappingPattern.MAX_WIDTH; x++)
            {
                if (0 <= y - offsetY && y - offsetY < pattern.getHeight() && 0 <= x - offsetX && x - offsetX < pattern.getWidth())
                {
                    // (x, y) is in-bounds, so draw based off the pattern
                    drawSquare(graphics, pattern.get(x - offsetX, y - offsetY) ? highTexture : lowTexture, x0, y0, x, y);
                }
                else
                {
                    // (x, y) is out-of-bounds, so draw the 'default' square
                    drawSquare(graphics, pattern.defaultIsOn() ? highTexture : lowTexture, x0, y0, x, y);
                }
            }
        }

        parentScreen.renderItemStack(graphics, x0 + 95, y0 + 37, mouseX - 12, mouseY, resultStack);
    }

    private static void drawSquare(GuiGraphics graphics, @Nullable ResourceLocation texture, int x0, int y0, int x, int y)
    {
        if (texture != null)
        {
            RenderSystem.setShaderTexture(0, texture);
            graphics.blit(texture, x0 + 5 + x * 16, y0 + 5 + y * 16, 0, 0, 16, 16, 16, 16);
        }
    }

    private static ItemStack[] getIngredients(RecipeHolder<KnappingRecipe> recipe){
        if (recipe.value().getIngredient() == null){
            return recipe.value().knappingType().get().inputItem().getItems();
        } else {
            return recipe.value().getIngredient().getItems();
        }
    }
}
