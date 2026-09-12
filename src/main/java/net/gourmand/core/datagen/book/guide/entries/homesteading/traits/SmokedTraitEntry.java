package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.world.item.crafting.Ingredient;

public class SmokedTraitEntry extends EntryProvider {

    public SmokedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain smoked trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.RAW_MEATS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Smoked Trait** by default, modifies spoilage rate by **0.7**.
                \\
                \\
                It occurs when you **Smoke** meats of cheese.
                \\
                \\
                The **Rancid Smoked Trait** by default, modifies spoilage rate by **2.0**.
                \\
                \\
                It occurs when you smoke items with a low quality fuel like peat.
               """);

        // TODO: It would be a bit of a waste, here and a smoking page, but a page type for smoking recipes would elevate this.
    }

    @Override
    protected String entryName() {
        return "Smoked";
    }

    @Override
    protected String entryDescription() {
        return "About The Smoked Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.WOOL_YARN);
    }

    @Override
    protected String entryId() {
        return "smoked_trait";
    }
}
