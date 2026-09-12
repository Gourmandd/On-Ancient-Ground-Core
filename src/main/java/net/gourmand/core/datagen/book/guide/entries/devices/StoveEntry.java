package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.resources.ResourceLocation;

public class StoveEntry extends EntryProvider {

    public StoveEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain stove.
        this.page("page1", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context.pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Stove** is a version of the **Fire Pit** which cannot spread fire to nearby blocks.
                \\
                \\
                Safe for use indoors.
               \s""");

        // page 2: interface.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/stove"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The stove crafting recipe.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Stoves";
    }

    @Override
    protected String entryDescription() {
        return "About Stoves.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.STOVE);
    }

    @Override
    protected String entryId() {
        return "stove";
    }
}
