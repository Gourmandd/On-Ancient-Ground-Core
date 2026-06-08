package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

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

public class ShelvedTraitEntry extends EntryProvider {

    public ShelvedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain shelved trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.FOOD_SHELVES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Shelved Traits** by default, modifies spoilage rate by **0.4**, **0.35**, and **0.25** depending on tier.
                \\
                \\
                It occurs when you place food in **Food Shelves** in a valid **Cellar**, the colder the ambient temperature, the higher tier of the trait it gets.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/food_shelf/oak"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The oak food shelf recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Shelved";
    }

    @Override
    protected String entryDescription() {
        return "About The Shelved Traits.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.FOOD_SHELVES.get(Wood.OAK));
    }

    @Override
    protected String entryId() {
        return "shelved_traits";
    }
}
