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

public class MoldTableEntry extends EntryProvider {

    public MoldTableEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain mold tables.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.MOLD_TABLE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Mold Tables** can have molds placed into them, they can be connected to **Crucibles** with **Casting Channels** to cast metal from inside when right clicked.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_mold_table"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A Mold table being knapped out of **Fire Clay**.
               \s""");

        // page 3: explain mold tables.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.MOLD_TABLE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bellows** can be used to cool down molds in **Mold Tables** faster.
                \\
                \\
                Redstone can be used to trigger a pour by triggering a channel.
                \\
                \\
                Comparators can read a signal from **Mold Tables**.
               \s""");

        // page 4: channel knapping recipe.
        this.page("page4", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_channel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Casting channels being knapped out of **Fire Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Mold Tables and Casting Channels";
    }

    @Override
    protected String entryDescription() {
        return "About Mold Tables and Casting Channels";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.MOLD_TABLE);
    }

    @Override
    protected String entryId() {
        return "mold_tables";
    }
}
