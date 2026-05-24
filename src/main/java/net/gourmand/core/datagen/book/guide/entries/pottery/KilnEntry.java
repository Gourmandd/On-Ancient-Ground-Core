package net.gourmand.core.datagen.book.guide.entries.pottery;

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

public class KilnEntry extends EntryProvider {

    public KilnEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain kilns.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.FIREBOX))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Kiln is a structure used for smelting large amounts of items within a player-defined area.
                \\
                \\
                The Kiln is constructed from a Firebox, fireproof materials and optionally a **Grate**.
               \s""");

        // page 2: .
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/firebox"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/grate/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Kiln materials.
               \s""");

        // page 3: explain fire box functionality.
        this.page("page3", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Firebox interface has a temperature indicator and 16 slots, these slots store fuel and any fuel may be used.
                \\
                \\
                Below Bright Red, it heats 64 blocks, and above that it heats 128 blocks.
                \\
                \\
                The time it takes to heat the enclosed space is proportional to its volume and the temperature you seek to reach.
               \s""");

        // page 4: explain how they heat.
        this.page("page4", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Fireboxes' heated area originates on the block above them. The heat passes through non-occluding blocks, and **Grates**. It must be in an enclosed space to start heating.
                \\
                \\
                It is recommended to place fireboxes one block down with a grate on top of them, allowing easy access from below.
               \s""");

        // page 5: the space itself.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The structure of a Kiln is made of any combination of Clay Bricks, Fire Bricks, Tinted Glass, or Fireproof Doors.
                \\
                \\
                If the structure is broken during heating, the heat is lost and the heating must restart.
                \\
                \\
                The timer will also restart if the temperature increases or decreases from its initial setpoint.
               \s""");

        // page 6: the space itself.
        this.page("page6", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/fireproof_door"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Fireproof Door allows you to enter and exit without breaking blocks, while keeping a seal.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Kilns";
    }

    @Override
    protected String entryDescription() {
        return "About Kilns!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.FIREBOX);
    }

    @Override
    protected String entryId() {
        return "kilns";
    }
}
