package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class GrateEntry extends EntryProvider {

    public GrateEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain grates.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.GRATE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Grates** are a block worth 100 mb of metal, they are a transparent block. They are used in building **Kilns**.
                \\
                \\
                You can get them from crafting 4 bars of many metals.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/grate/copper"))
        );

        this.pageTitle(entryName());
        this.pageText("Crafting copper grates");
    }

    @Override
    protected String entryName() {
        return "Grates";
    }

    @Override
    protected String entryDescription() {
        return "About Grates";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.GRATE));
    }

    @Override
    protected String entryId() {
        return "grates";
    }
}
