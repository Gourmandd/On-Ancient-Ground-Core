package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CharcoalPitEntry extends EntryProvider {

    public CharcoalPitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain charcoal.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CHARCOAL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Charcoal** is a simple, and location independent way to fuel **Charcoal Forges**, and is required to use the **Bloomery** and **Blast Furnace**.
                \\
                \\
                First you need to bury **Log Piles** with unflammable blocks, such as dirt.
                \\
                \\
                To place a log pile **Shift + Right-Click** the ground with a log in hand, you can add more logs with **Right-Click** and remove them with **Right-Click** and an empty hand.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "metalworking/charcoal_pit"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Caution: Log pipes are highly flammable!
               \s""");

        // page 3: explain building it.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CHARCOAL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Putting more logs in the log pile results in more charcoal.
                \\
                \\
                Make sure that your log piles (in any shape) are covered fully with blocks, otherwise they will burn.
                \\
                \\
                Then set fire to a log pile and cover it right up, you can hear the fire spread and see smoke leaving the mound as its working.
               \s""");

        // page 4: making it
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "metalworking/charcoal_pit_finished"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               It will be finished when it stops emitting smoke (in 15 minutes). Mine the charcoal piles with a shovel to get the charcoal.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Charcoal Pits";
    }

    @Override
    protected String entryDescription() {
        return "How to make Charcoal!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CHARCOAL);
    }

    @Override
    protected String entryId() {
        return "charcoal_pit";
    }
}
