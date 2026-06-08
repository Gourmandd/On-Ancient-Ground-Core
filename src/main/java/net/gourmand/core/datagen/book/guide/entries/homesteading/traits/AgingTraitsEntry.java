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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class AgingTraitsEntry extends EntryProvider {

    public AgingTraitsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain aging traits.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.CHEESE_WHEELS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Fresh**, **Aged** and **Vintage** traits by default, modifies spoilage rates by **1.1**, **0.9** and **0.6** respectfully.
                \\
                \\
                It occurs when you create and age **Cheese**.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/cheddar_wheel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The cheddar wheel recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Aging";
    }

    @Override
    protected String entryDescription() {
        return "About The Aging Traits.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.CHEDDAR_WHEEL);
    }

    @Override
    protected String entryId() {
        return "aging_traits";
    }
}
