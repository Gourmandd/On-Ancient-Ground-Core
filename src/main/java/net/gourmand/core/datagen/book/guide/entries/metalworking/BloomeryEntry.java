package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BloomeryEntry extends EntryProvider {

    public BloomeryEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bloomeries.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.BLOOMERY))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Bloomery is a device used to smelt Iron containing items into **Iron Blooms** which can be refined into **Wrought Iron**.
                \\
                \\
                An "Iron containing item" is any item that melts into **Cast Iron**, such are ores, ingots, and misc items.
                \\
                \\
                The bloomery block will open and close with Right Click
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/bloomery"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The bloomery is made from 8 **Double Sheets** of any bronze type.
               \s""");

        // page 3: explain bloomeries.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Building and Using the Bloomery");
        this.pageText("""
                The bloomery can contain 16 items per layer of the chimney. It has a maximum of 4 layers.
                The chimney is made of layers of stone blocks.
                \\
                \\
                 To add items to the bloomery throw items inside, blocks should form inside.
                \\
                \\
                To make a Bloom, throw in 2 Charcoal, and 100 mB of Cast Iron.
                 After filling the bloomery, light the bloomery block, and the Bloom to smelt will finish in 12 hours.
                 When the bloomery finishes, it will leave a Bloom block. Mine this repeatedly to get your Raw Iron Blooms!
               \s""");

        // page 4: building and using it
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "metalworking/bloomery"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A maximum size bloomery made of stone bricks.
               \s""");

        // page 5: refining blooms
        this.page("page5", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/refined_iron_bloom"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/wrought_iron"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                To make iron from a bloom, you need to refine it first!
               \s""");

        // page 6: notes.
        this.page("page6", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Note!");
        this.pageText("""
                - If the bloomery holds more items than it can handle based on its chimney size, it will spit them out its front.
                - To get your items back from an unlit bloomery, **break the bloomery block itself**.
                - Blooms must be worked first! they will only melt back into cast iron.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bloomery";
    }

    @Override
    protected String entryDescription() {
        return "Building a Bloomery!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.BLOOMERY);
    }

    @Override
    protected String entryId() {
        return "bloomery";
    }
}
