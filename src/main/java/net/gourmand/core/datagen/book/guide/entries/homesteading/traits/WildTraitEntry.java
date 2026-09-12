package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.world.item.crafting.Ingredient;

public class WildTraitEntry extends EntryProvider {

    public WildTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain wild trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.PUMPKIN))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Wild Trait** by default, modifies spoilage rate by **0.5**.
                \\
                \\
                It occurs to wild pumpkins and melons, so that they last a reasonable amount of time before you find them.
               """);
    }

    @Override
    protected String entryName() {
        return "Wild";
    }

    @Override
    protected String entryDescription() {
        return "About The Wild Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.PUMPKIN);
    }

    @Override
    protected String entryId() {
        return "wild_trait";
    }
}
