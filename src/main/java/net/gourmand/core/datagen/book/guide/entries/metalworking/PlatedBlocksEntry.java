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
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class PlatedBlocksEntry extends EntryProvider {

    public PlatedBlocksEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain plated blocks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.BLOCK)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Plated Blockss** are a block worth 100 mb of metal, they have a cut variant and a decorational purpose.
                \\
                \\
                Brass plated blocks are used for the **Table Pour** glassworking operation.
                \\
                \\
                You can made them by crafting 4 sheets of most metals with planks.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/block/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "crafting/metal/cut_block/copper"))
        );

        this.pageTitle(entryName());
        this.pageText("Crafting plated blocks");
    }

    @Override
    protected String entryName() {
        return "Plated Blocks";
    }

    @Override
    protected String entryDescription() {
        return "About Plated Blocks";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.BLOCK));
    }

    @Override
    protected String entryId() {
        return "block";
    }
}
