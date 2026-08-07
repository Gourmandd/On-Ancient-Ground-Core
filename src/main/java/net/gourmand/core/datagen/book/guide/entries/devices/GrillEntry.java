package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookAnvilWorkingPageModel;
import net.minecraft.resources.ResourceLocation;

public class GrillEntry extends EntryProvider {

    public GrillEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain grill.
        this.page("page1", () -> BookAnvilWorkingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/wrought_iron_grill"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Grill** is a version of the **Fire Pit** created by **Right-Clicking** with a **Wrought Iron Grill**. It can cook up to 5 item, giving food items the **Wood Grilled** trait.
                \\
                \\
                You can interact with it in world using **Right-Click** and **Crouch + Right-Click** on the red bounding boxes on top.
               \s""");

        // page 2: interface.
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/book/devices/grill_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The grill interface. Open by **Right-Clicking** it.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Grills";
    }

    @Override
    protected String entryDescription() {
        return "About Grills.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.GRILL);
    }

    @Override
    protected String entryId() {
        return "grill";
    }
}
