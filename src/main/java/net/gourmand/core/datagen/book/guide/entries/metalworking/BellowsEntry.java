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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BellowsEntry extends EntryProvider {

    public BellowsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bloomeries.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.BELLOWS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bellows** are a device which can for a short time, increase the temperature that you can reach using **Charcoal Forges**, **Blast Furnaces** or **Fire Pits**.
                \\
                \\
                To do so, point them into the block you want to effect and **Right-Click** them, they can also be automated with **Crankshafts**.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/bellows"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The bellows require **Leather** to make.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bellows";
    }

    @Override
    protected String entryDescription() {
        return "About Bellows!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.BELLOWS);
    }

    @Override
    protected String entryId() {
        return "bellows";
    }
}
