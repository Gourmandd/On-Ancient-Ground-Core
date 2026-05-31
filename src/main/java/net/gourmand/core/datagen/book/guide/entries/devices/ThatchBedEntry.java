package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.world.item.crafting.Ingredient;

public class ThatchBedEntry extends EntryProvider {

    public ThatchBedEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain thatch bed.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.THATCH_BED))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Thatch Beds** are able to set your spawn point, but not let you to sleep the night. They are too rough for that.
                \\
                \\
                To make a Thatch Bed, right click a large hide on two blocks of thatch placed next to each other.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Thatch Bed";
    }

    @Override
    protected String entryDescription() {
        return "About Thatch Beds.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.THATCH_BED);
    }

    @Override
    protected String entryId() {
        return "thatch_bed";
    }
}
