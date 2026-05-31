package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ThermometerEntry extends EntryProvider {

    public ThermometerEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain thermometer.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.THERMOMETER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Thermometers** are a device which emit redstone signals based on its placement.
                \\
                \\
                This signal is emitted to adjacent blocks (you don't need a comparator).
               """);

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/thermometer"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                It has two modes:  \s
                - Ambient (placed anywhere)  \s
                - Device (when placed on a device with temperature like **Crucible**  \s
                """);

        // page 3: explain thermometer.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Ambient Mode");
        this.pageText("""
                In **Ambient Mode** it will emit a signal based on the ambient temperature's place on the -40C to +40C scale.
                \\
                \\
                -40C results in a signal strength of 0.
                \\
                \\
                +40C results in a signal strength of 15.
               """);

        // page 4: explain thermometer.
        this.page("page4", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Device Mode");
        this.pageText("""
                In **Device Mode** it will emit a signal based on the device's temperature place on the **No Heat** to **Brilliant White** scale.
                \\
                \\
                **No Heat** results in a signal strength of 0.
                \\
                \\
                **Brilliant White** results in a signal strength of 15.
               """);
    }

    @Override
    protected String entryName() {
        return "Thermometer";
    }

    @Override
    protected String entryDescription() {
        return "About Thermometers.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.THERMOMETER);
    }

    @Override
    protected String entryId() {
        return "thermometer";
    }
}
