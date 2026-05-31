package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class JukeboxEntry extends EntryProvider {

    public JukeboxEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain jukebox.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Blocks.JUKEBOX))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Jukeboxes** are a device which can play music discs. They can be inserted and taken out by hand or automatically with devices like **Hoppers**.
                \\
                \\
                They emit a redstone signal while the music is playing. With differing signal strength per disc.
               \s""");

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "jukebox"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                use **R + Right-Click** and EMI to see recipes for music discs!
               \s""");
    }

    @Override
    protected String entryName() {
        return "Jukebox";
    }

    @Override
    protected String entryDescription() {
        return "About Jukeboxes.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Blocks.JUKEBOX);
    }

    @Override
    protected String entryId() {
        return "jukebox";
    }
}
