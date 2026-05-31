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

public class PrecisionClockEntry extends EntryProvider {

    public PrecisionClockEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain precision clock.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.CALENDAR_CLOCK))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Precision Clock** is a device which emits redstone signals based on its mode. Which can be toggled using **Right-Click**.
                \\
                \\
                This signal is emitted to adjacent blocks (you don't need a comparator).
               """);

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/calendar_clock"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                It has two modes:  \s
                - Hour  \s
                - Month  \s
                """);

        // page 3: explain precision clock.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Hour Mode");
        this.pageText("""
                In **Hour Mode** it will emit a signal based on the hour on a 12-hour clock, with midnight and noon emitting 0, and 11:00/23:00 emitting a signal strength of 11.
                \\
                \\
                Keep in mind that this means that it **cannot** emit a signal strength of 12.
               """);

        // page 4: explain precision clock.
        this.page("page4", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Month Mode");
        this.pageText("""
                In **Month Mode** it will emit a signal based on the current month, with January emitting 0, and December emitting a signal strength of 11.
                \\
                \\
                Logic: Month order - 1.
                \\
                \\
                Keep in mind that this means that it **cannot** emit a signal strength of 12.
               """);
    }

    @Override
    protected String entryName() {
        return "Precision Clock";
    }

    @Override
    protected String entryDescription() {
        return "About Precision Clocks.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.CALENDAR_CLOCK);
    }

    @Override
    protected String entryId() {
        return "precision_clock";
    }
}
