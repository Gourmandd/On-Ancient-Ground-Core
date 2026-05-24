package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BarsEntry extends EntryProvider {

    public BarsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bars.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.BARS)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bars** are a block worth 25 mb of metal, they can be placed like glass panes.
                \\
                \\
                You can get them from forging **Sheets** or **Double Sheets** of many metals.
               \s""");

        // page 2: forging recipe.
        this.page("page2", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/bars/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/bars/copper_double"))
        );

        this.pageTitle(entryName());
        this.pageText("Forging copper bars.");
    }

    @Override
    protected String entryName() {
        return "Bars";
    }

    @Override
    protected String entryDescription() {
        return "About Bars";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.BARS));
    }

    @Override
    protected String entryId() {
        return "bars";
    }
}
