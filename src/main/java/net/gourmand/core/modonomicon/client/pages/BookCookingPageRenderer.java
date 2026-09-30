package net.gourmand.core.modonomicon.client.pages;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.client.gui.book.entry.BookEntryScreen;
import com.klikli_dev.modonomicon.client.render.page.BookRecipePageRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.vomiter.survivorsdelight.registry.recipe.SDCookingPotRecipe;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.modonomicon.pages.BookCookingPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import org.joml.Vector2i;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.Map;

import static net.gourmand.core.modonomicon.ModonomiconIntegration.*;

public class BookCookingPageRenderer extends BookRecipePageRenderer<CookingPotRecipe, BookCookingPage>
{

    public BookCookingPageRenderer(BookCookingPage page)
    {
        super(page);
    }

    public static final int ROW_1 = 10; //recipeX + ROW_1 is where the first row of items go
    public static final int ROW_2 = 34; //recipeX + ROW_2 etc.
    public static final int COLUMN_1 = -10; //recipeY + COLUMN_1 etc
    public static final int COLUMN_2 = 14; //recipeY + COLUMN_1 etc
    public static final int COLUMN_3 = 38; //recipeY + COLUMN_1 etc
    public static final int COLUMN_4 = 62; //recipeY + COLUMN_1 etc
    public static final int COLUMN_5 = 86; //recipeY + COLUMN_1 etc

    public static final Map<Integer, Vector2i> ITEM_POSITIONS = ImmutableMap.<Integer, Vector2i>builder()
            .put(0, new Vector2i(COLUMN_1, ROW_1))
            .put(1, new Vector2i(COLUMN_2, ROW_1))
            .put(2, new Vector2i(COLUMN_3, ROW_1))
            .put(3, new Vector2i(COLUMN_1, ROW_2))
            .put(4, new Vector2i(COLUMN_2, ROW_2))
            .put(5, new Vector2i(COLUMN_3, ROW_2))
            .build();

    @Override
    protected int getRecipeHeight()
    {
        return 78;
    }

    @Override
    protected void drawRecipe(GuiGraphics guiGraphics, RecipeHolder<CookingPotRecipe> recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {

        Level world = Minecraft.getInstance().level;
        if (world == null) return;

        if (!second) {
            if (!this.page.getTitle1().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle1(), false, BookEntryScreen.PAGE_WIDTH / 2, 0);
            }
        } else {
            if (!this.page.getTitle2().isEmpty()) {
                this.renderTitle(guiGraphics, this.page.getTitle2(), false, BookEntryScreen.PAGE_WIDTH / 2, recipeY - (this.page.getTitle2().getString().isEmpty() ? 10 : 0));
            }
        }

        RenderSystem.enableBlend();

        var cookingPotRecipe = recipe.value();
        NonNullList<Ingredient> ingredients = cookingPotRecipe.getIngredients();

        // blit needs to be called first

        // Display arrows
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX + COLUMN_4 + 4, recipeY + ROW_1 + 4, 38, 79, 9, 9, 128, 256);
        guiGraphics.blit(ModonomiconIntegration.UP_ARROW_TEXTURE, recipeX + COLUMN_5 + 4, recipeY + ROW_1 + 16, 0, 0, 9, 9, 9, 9);

        //time icon
        guiGraphics.blit(ModonomiconIntegration.COOKING_POT_ICONS_TEXTURE, recipeX + COLUMN_1 + 4, recipeY + ROW_2 + 20, 0, 0, 9, 11, 9, 20);
        //xp icon
        guiGraphics.blit(ModonomiconIntegration.COOKING_POT_ICONS_TEXTURE, recipeX + COLUMN_3 + 4, recipeY + ROW_2 + 20, 0, 11, 9, 9, 9, 20);

        for (int i = 0; i < ingredients.size(); i++)
        {
            guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX + ITEM_POSITIONS.get(i).x + ITEM_BORDER_SPACING, recipeY + ITEM_POSITIONS.get(i).y + ITEM_BORDER_SPACING, 11, 71, 24, 24, 128, 256);
        }

        if (recipe.value() instanceof SDCookingPotRecipe sdCookingPotRecipe)
        {
            if (sdCookingPotRecipe.getFluid() != null)
            {
                guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX + COLUMN_4 + FLUID_BORDER_SPACING, recipeY + ROW_2 + FLUID_BORDER_SPACING, 11, 71, 24, 24, 128, 256);
            }
        }

        // Display container
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX + COLUMN_5 + ITEM_BORDER_SPACING, recipeY + ROW_2 + ITEM_BORDER_SPACING, 11, 71, 24, 24, 128, 256);
        // Display output
        guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX + COLUMN_5 + ITEM_BORDER_SPACING, recipeY + ROW_1 + ITEM_BORDER_SPACING, 11, 71, 24, 24, 128, 256);


        // Now render items and fluids.

        // Display item ingredients 1-6
        for (int i = 0; i < ingredients.size(); i++)
        {
            this.parentScreen.renderIngredient(guiGraphics, recipeX + ITEM_POSITIONS.get(i).x, recipeY + ITEM_POSITIONS.get(i).y, mouseX, mouseY, ingredients.get(i));
        }

        // Display fluid if there is one.
        if (recipe.value() instanceof SDCookingPotRecipe sdCookingPotRecipe)
        {
            if (sdCookingPotRecipe.getFluid() != null)
            {
                Fluid fluid = sdCookingPotRecipe.getFluid().getStacks()[0].getFluid();
                int amount = sdCookingPotRecipe.getFluidAmountMb();

                this.parentScreen.renderFluidStack(guiGraphics, recipeX + COLUMN_4 - 1, recipeY + ROW_2 - 1, mouseX, mouseY, ModonomiconIntegration.getFluidHolder(fluid, amount), amount);
            }
        }

        // Display cookingTime
        drawCenteredStringNoShadow(guiGraphics, cookingPotRecipe.getCookTime() + "s", recipeX + COLUMN_2, recipeY + ROW_2 + 18, 1, 0.5f);
        // Display experience
        drawCenteredStringNoShadow(guiGraphics, cookingPotRecipe.getExperience() + "xp", recipeX + COLUMN_4, recipeY + ROW_2 + 18, 1, 0.5f);
        // Display container
        this.parentScreen.renderItemStack(guiGraphics, recipeX + COLUMN_5, recipeY + ROW_2, mouseX, mouseY, cookingPotRecipe.getOutputContainer());
        // Display output
        this.parentScreen.renderItemStack(guiGraphics, recipeX + COLUMN_5, recipeY + ROW_1, mouseX, mouseY, cookingPotRecipe.getResultItem(world.registryAccess()));

        //guiGraphics.blit(this.page.getBook().getCraftingTexture(), recipeX, recipeY, 11, 71, 96, 24, 128, 256);
    }
}



