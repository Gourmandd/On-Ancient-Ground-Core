package net.gourmand.core.datagen.book.guide.entries.devices;

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

public class AnemometerEntry extends EntryProvider {

    public AnemometerEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain anemometer.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ANEMOMETER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Anemometers** are a device which emit redstone signals in a range based on wind speed.
                \\
                \\
                This signal is emitted to adjacent blocks (you don't need a comparator).
               """);

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/anemometer"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A reading of 0km/h results in a signal strength of 0.
                \\
                \\
                A reading of 115km/h results in a signal strength of 15.
                """);
    }

    @Override
    protected String entryName() {
        return "Anemometer";
    }

    @Override
    protected String entryDescription() {
        return "About Anemometers.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ANEMOMETER);
    }

    @Override
    protected String entryId() {
        return "anemometer";
    }
}
