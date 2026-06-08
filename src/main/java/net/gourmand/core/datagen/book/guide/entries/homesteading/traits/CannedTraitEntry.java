package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.Food;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.world.item.crafting.Ingredient;

public class CannedTraitEntry extends EntryProvider {

    public CannedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain canned trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.SEALED_PRESERVES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Canned Trait** by default, modifies spoilage rate by **0.0**.
                \\
                \\
                It occurs when you seal and boil jam jars.
               """);

        // TODO: Add a recipe once pot recipe have a page type.
    }

    @Override
    protected String entryName() {
        return "Canned";
    }

    @Override
    protected String entryDescription() {
        return "About The Canned Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FRUIT_PRESERVES.get(Food.RED_APPLE));
    }

    @Override
    protected String entryId() {
        return "canned_trait";
    }
}
