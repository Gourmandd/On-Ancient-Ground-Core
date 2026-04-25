package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;

public class PitKilnEntry extends EntryProvider {

    public PitKilnEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain how pit kilns work.
        this.page("page1", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Pit Kilns** create temperatures capable of firing pottery and melting metals.
                \\
                \\
                To create them, create a pit surrounded by blocks, use **V** to place items in the pit and **Right Click** 8 Straw and 8 Logs on the placed items.
               \s""");

        // page 2: multiblock.
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "getting_started/pit_kiln"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                Light the pit kiln to start it. (It Takes 8 minutes).\s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Pit Kiln";
    }

    @Override
    protected String entryDescription() {
        return "About Pit Kilns";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "textures/gui/book/icons/pit_kiln.png"));
    }

    @Override
    protected String entryId() {
        return "pit_kiln";
    }
}
