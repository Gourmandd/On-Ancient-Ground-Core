package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class BedEntry extends EntryProvider {

    public BedEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bed.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Blocks.RED_BED))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Beds** are able to set your spawn point, and unlike **Thatch Beds** they let you to sleep the night
               \s""");

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "red_bed"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Cloth is made using a **Loom**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bed";
    }

    @Override
    protected String entryDescription() {
        return "About Beds.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Blocks.RED_BED);
    }

    @Override
    protected String entryId() {
        return "wool_bed";
    }
}
