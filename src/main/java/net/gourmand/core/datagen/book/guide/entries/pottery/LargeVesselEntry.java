package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class LargeVesselEntry extends EntryProvider {

    public LargeVesselEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain large vessels.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.LARGE_VESSEL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Large Vessels** can be used as early game storage and can be sealed.
               \\
               \\
               Sealed large vessels preserve food items and prevents pests from taking them.
               \\
               \\
               They can also be carried on your back.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_large_vessel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A large vessel being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Large Vessel";
    }

    @Override
    protected String entryDescription() {
        return "About Large Vessel";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.LARGE_VESSEL);
    }

    @Override
    protected String entryId() {
        return "large_vessel";
    }
}
