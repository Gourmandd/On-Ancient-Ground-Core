package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class HeatingEntry extends EntryProvider {

    public HeatingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain item heating.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.FIREPIT))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Certain items can be heated to either melt them, or make a new item.
                \\
                \\
                Heating can be done with devices like:
                - **Firepits** (including grills)
                - **Vessels** contained in **Pit Kilns**
                - **Kilns**
                - **Charcoal Forges**
                - and **Crucibles**
               \s""");

        // page 2: crucible interface
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/charcoal_forge_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The 9 slots on the left of the **Crucibles's** interface, add heat to the items within.
               \s""");

        // page 3: explain heat and ranges.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Heat ranges");
        this.pageText("""
                An item temperature is shown on its tooltip.
                \\
                \\
                The temperature of an item is represented using colors:
                 - Warming: 1-80°C
                 - Hot: 80-210°C
                 - Very Hot: 210-480°C
                 - [#](a51d2d)Faint Red:[#]() 480-580°C
                 - [#](a51d2d)Dark Red:[#]() 580-730°C
                 - [#](f66151)Bright Red:[#]() 730-930°C
                 - [#](ff7800)Orange:[#]() 930-1100°C
                 - [#](FFDD00)Yellow:[#]() 1100-1300°C
                 - [#](f5c211)Yellow White:[#]() 1300-1400°C
                 - [#](dc8add)White:[#]() 1400-1500°C
                 - [#](dc8add)Brilliant White:[#]() >1500°C
               \s""");
    }

    @Override
    protected String entryName() {
        return "Heating";
    }

    @Override
    protected String entryDescription() {
        return "About Heating!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.FIREPIT);
    }

    @Override
    protected String entryId() {
        return "heating";
    }
}
