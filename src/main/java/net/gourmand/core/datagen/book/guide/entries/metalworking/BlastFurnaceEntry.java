package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.*;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BlastFurnaceEntry extends EntryProvider {

    public BlastFurnaceEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain blast furnace.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.BLAST_FURNACE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A Blast Furnace is a device used to create Steel, by mixing Iron containing items, Charcoal, and Flux in a controlled, hot environment
                \\
                \\
                You will need to use Bellows on the Blast Furnace to reach a temperature capable of melting iron.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/blast_furnace"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The blast furnace is made from 8 **Double Sheets** of **Wrought Iron**.
               \s""");

        // page 3: explain how to build it.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("How to build the Blast Furnace");
        this.pageText("""
                To construct the blast furnace, it needs a chimney.
                \\
                \\
                The chimney is built of **Reinforced Fire Bricks**, crafted from **Fire Brick** blocks, and either **Wrought Iron** or **Steel** sheets.
                \\
                \\
                The blast furnace's chimney can be up to five layers of eight **Reinforced Fire Bricks**.
                Each layer increases the capacity of the blast furnace by 4 **Iron Containing Items**, allowing more steel to be smelted at once.
               \s""");

        // page 4: multiblock
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "metalworking/blast_furnace"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A blast furnace with a minimum height chimney.
               \s""");

        // page 5: explain how to use it.
        this.page("page5", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                To use the blast furnace, throw items into the chimney, each **Iron Containing Item** also needs a **Flux**.
                To fuel the blast furnace throw in **Charcoal**.
                \\
                \\
                In the blast furnace's interface, there are meters for the ore and fuel contents of the blast furnace.
                The top right slot must have a Tuyere, to be able to reach the hottest temperatures to smelt steel.
               \s""");

        // page 6: building and using it
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/blast_furnace_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A blast furnace interface.
               \s""");

        // page 7: explain how to use it.
        this.page("page7", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                Finally, to get started, light the blast furnace with a **Fire Starter**.
                Make sure that the blast furnace continues to have fuel, and use the bellows to increase the blast furnace's temperature.
                After a while the items will melt and convert into Pig Iron.
                \\
                This Pig Iron will drip into a fluid container like a Crucible below.
               \s""");

        // page 8: tuyere recipes.
        this.page("page8", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/tuyere/bronze"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/tuyere/wrought_iron"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Tuyere's being forged out of **Bronze** and **Wrought Iron**.
               \s""");

        // page 9: explain pig iron & refining.
        this.page("page9", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.PIG_IRON).get(Metal.ItemType.INGOT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Pig Iron** can be cast into ingot molds from the output slot of the crucible and worked into Steel.
               \s""");

        // page 10: tuyere recipes.
        this.page("page10", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/high_carbon_steel"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/steel"))
        );

        this.pageText("");
    }

    @Override
    protected String entryName() {
        return "Blast Furnace";
    }

    @Override
    protected String entryDescription() {
        return "Building a Blast Furnace!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.BLAST_FURNACE);
    }

    @Override
    protected String entryId() {
        return "blast_furnace";
    }
}
