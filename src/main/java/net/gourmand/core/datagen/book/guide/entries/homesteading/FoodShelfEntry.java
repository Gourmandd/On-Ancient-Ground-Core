package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.FLTags;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FoodShelfEntry extends EntryProvider {

    public FoodShelfEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain food shelves.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.FOOD_SHELVES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Food Shelves** can be used to store food, they are intended to be used inside of **Cellars**.
                \\
                \\
                Inside of **Cellars** the food contained in **Food Shelves** spoils slower.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/food_shelf/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The food shelf recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Food Shelves";
    }

    @Override
    protected String entryDescription() {
        return "About Food Shelves.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.FOOD_SHELVES.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "food_shelf";
    }
}
