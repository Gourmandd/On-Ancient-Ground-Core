package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class NoteBlockEntry extends EntryProvider {

    public NoteBlockEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain note block.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Blocks.NOTE_BLOCK))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Note blocks** are a device which can play the note is it set to. Their note can be changed using **Right-Click**.
                \\
                \\
                They can be played using **Left-Click** or a redstone signal.
               \s""");

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "note_block"))
        );

        this.pageTitle(entryName());
        this.pageText("Their notes range from 0-24 (F#1 -> F#3), and their timbre depends on the block under them.");

        // page 3: explain note block.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle("Note Block Timbre")
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                - Harp: Undefined blocks.
                - Bass: Most Wooden Blocks like planks, logs, etc.
                - Snare Drum: Sand, Gravel, Concrete Powder.
                - Bell (Glockenspiel): Block of Gold.
                - Flute: Clay Block, Honeycomb Block.
                - Chime: Packed Ice.
                - Guitar: Wool.
               \s""");

        // page 4: explain note block.
        this.page("page4", () -> BookTextPageModel.create()
                .withTitle("Note Block Timbre")
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                - Xylophone: Bone Block.
                - Iron Xylophone (Vibraphone): Block of Iron.
                - Cow Bell: Soul Sand.
                - Didgeridoo: Pumpkin.
                - Bit: Block of Emerald.
                - Banjo: Hay Bale.
                - Pling (Electric Piano): Glowstone Block.
                - Mob Heads play the mob's sound effect.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Note Block";
    }

    @Override
    protected String entryDescription() {
        return "About Note Blocks.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Blocks.NOTE_BLOCK);
    }

    @Override
    protected String entryId() {
        return "note_block";
    }
}
