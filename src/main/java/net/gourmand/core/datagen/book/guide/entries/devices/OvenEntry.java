package net.gourmand.core.datagen.book.guide.entries.devices;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.blocks.oven.OvenType;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;

public class OvenEntry extends EntryProvider {

    public OvenEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain oven.
        this.page("page1", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Oven** is a multiblock device which lets you heat and cook items in bulk.
                \\
                \\
                It consists of **Bottom Ovens**, **Top Ovens** and, optionally **Chimneys**, all knapped from clay and fired in-world (as opposed to in a **Kiln**)
                \\
                \\
                **Appliances** can add to its functionality.
               \s""");

        // page 2: multiblock.
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "devices/oven"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                A small oven insulated with bricks.
               \s""");

        // page 3: explain bottom oven.
        this.page("page3", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Bottom Oven");
        this.pageText("""
                The bottom oven hold logs as fuel, **Right-Click** with a log in hand to add it, and **Crouch + Right-Click** to remove a log.
                \\
                \\
                To light it, use any kind of **Firestarter** with **Right-Click**.
                \\
                \\
                Its heat is transferred to the rest of the oven, diminishing the further it travels.
               \s""");

        // page 4: knapping recipe.
        this.page("page4", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "knapping/clay_oven_bottom"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping recipe for a clay oven bottom.
               \s""");

        // page 5: explain clay oven top.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Top Oven");
        this.pageText("""
                The top oven holds items which get heated to its temperature.
                \\
                \\
                Use **Right-Click** to add items, and **Crouch + Right-Click** to remove them (with a damage penalty). To safely remove items, **Right-Click** with a **Peel**.
                \\
                \\
                It can draw heat from a bottom oven under it, or top ovens to its sides.
               \s""");

        // page 6: knapping recipe.
        this.page("page6", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "knapping/clay_oven_top"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping recipe for a clay oven top.
               \s""");

        // page 7: explain chimney.
        this.page("page7", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Chimney");
        this.pageText("""
                The chimney is used to route smoke up and out, instead of covering the area while the oven is in use.
                \\
                \\
                To do this, place a chimney stack behind a bottom oven, it will also cover adjacent bottom oven(s).
               \s""");

        // page 8: knapping recipe.
        this.page("page8", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "knapping/clay_oven_chimney"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping recipe for a clay oven chimney.
               \s""");

        // page 9: building the oven.
        this.page("page9", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Building the oven");
        this.pageText("""
                The oven consists of a bottom oven with top ovens on top (or to the side of this first top oven, with reduced heat flow), with an optional chimney covered previously.
                \\
                \\
                Top and bottom oven blocks need to be insulated on all sides but the front, else they lose half their heat. You can insulate using blocks such as bricks, cobble, and chimneys (at the back).
               \s""");

        // page 10: multiblock.
        this.page("page10", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "devices/oven_showcase"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                An unfired larger oven insulated with various blocks.
               \s""");

        // page 11: curing the oven.
        this.page("page11", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Curing the oven");
        this.pageText("""
                To cure the oven blocks, add logs into the unfired bottom oven, and light them.
                \\
                \\
                Once the internal temperature reaches around [#](a51d2d)Dark Red:[#]() (600C) it will start a 80 second curing timer.
                \\
                \\
                When it sustains [#](a51d2d)Dark Red:[#]() for 80 seconds, your oven blocks will be cured.
               \s""");

        // page 12: multiblock.
        this.page("page12", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "devices/oven_showcase_fired"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                The same larger oven, now fired.
               \s""");

        // page 13: oven insulation.
        this.page("page13", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/oven_insulation"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Once fired, this **Oven Insulation** item can be used to replace insulating blocks.
               \s""");

        // page 14: multiblock.
        this.page("page14", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "devices/oven_insulated"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                An oven insulated with **Oven Insulation** items.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Ovens";
    }

    @Override
    protected String entryDescription() {
        return "About Ovens.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.CURED_OVEN_TOP.get(OvenType.BRICK).asItem());
    }

    @Override
    protected String entryId() {
        return "oven";
    }
}
