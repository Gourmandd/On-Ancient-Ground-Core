package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.world.item.crafting.Ingredient;

public class RockAnvilEntry extends EntryProvider {

    public RockAnvilEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain rock anvils.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ROCK_ANVILS.get(Rock.BASALT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Rock Anvils** are a primitive type of anvil, it can also weld and anvil-work. You can use **Shift + Right-Click** to place or remove items on the anvil.
                \\
                \\
                It is required to make the double ingots to make a copper anvil to continue progression.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                To make a rock anvil, **Right-Click** the top face of a **Igneous Extrusive** or **Igneous Intrusive** raw rock block with a **Hammer**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Rock Anvils";
    }

    @Override
    protected String entryDescription() {
        return "About Rock Anvils";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_ANVILS.get(Rock.BASALT));
    }

    @Override
    protected String entryId() {
        return "rock_anvil";
    }
}
