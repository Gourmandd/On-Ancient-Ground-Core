package net.gourmand.core.datagen.book.guide.entries.devices;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;

public class OvenAppliancesEntry extends EntryProvider {

    public OvenAppliancesEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain oven appliances.
        this.page("page1", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ovens are modular in nature, and there are several appliances which can be heated using **Bottom Ovens**.
                \\
                \\
                Most are placed on a bottom oven using **Right-Click**, or by placing the appliance block on top or under the bottom oven.
               \s""");

        // page 2: explain oven appliances.
        this.page("page2", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
               List of appliances covered.   \s
                 - Oven Hopper.
                 - Ashtray.
                 - Vat.
                 - Jarring Station.
                 - Pots and Grills.
               \s""");

        // page 3: explain oven hopper.
        this.page("page3", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/clay_oven_hopper"))
        );

        this.pageTitle("Oven Hopper");
        this.pageText("""
                The **Oven Hopper** collects dropped logs into its inventory (4 stacks of 4 logs), and inserts them into the bottom oven it is facing.
                \\
                \\
                It needs to be cured before use. It can take input from the top.
               \s""");

        // page 4: explain ashtray.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/ashtray"))
        );

        this.pageTitle("Ashtray");
        this.pageText("""
                The **Ashtray** is placed under a bottom oven, it has a 50%% chance to collect wood ash when a log is used as fuel.
                \\
                \\
                **Right-Click** it to extract ash, and **Left-Click** with ash in hand to insert.
               \s""");

        // page 5: explain vat.
        this.page("page5", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/vat"))
        );

        this.pageTitle("Vat");
        this.pageText("""
                The **Vat** takes heat from a bottom oven and can do boiling recipes in bulk, only using one type of item.
                \\
                \\
                It holds 10,000mb of fluid.
               \s""");

        // page 6: explain vat.
        this.page("page6", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Vat");
        this.pageText("""
                To boil, the vat needs to be sealed using **Crouch + Right-Click** or redstone.
                \\
                \\
                If a recipe produces more fluid than the vat can hold, it will not boil.
               \s""");

        // page 7: explain jarring station.
        this.page("page7", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/jarring_station"))
        );

        this.pageTitle("Jarring Station");
        this.pageText("""
                The **Jarring Station** can hold up to 9 jars with lids, use **Right-Click** to add jars, and with an empty had to remove them.
                \\
                \\
                If it's spout is facing a vat, it will automatically fill and seal the jars with the provided fluid.
               \s""");

        // page 8: explain jarring station.
        this.page("page8", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Jarring Station");
        this.pageText("""
                To create jam in a vat, boil water with sweeteners to create sugar water.
                \\
                \\
                Boil it once more with a ratio of 500mb of sugar water : 1 fruit.
                \\
                \\
                Unseal the vat and the jarring station will begin working.
               \s""");

        // page 9: pots and grills.
        this.page("page9", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Pots and Grills");
        this.pageText("""
                **Cooking Pots** and **Wrought Iron Grills** can be placed on bottom ovens, acting as their heat source.
                \\
                \\
                They work exactly the same as pot and grills on **Firepits**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Oven Appliances";
    }

    @Override
    protected String entryDescription() {
        return "About Oven Appliances.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.JARRING_STATION);
    }

    @Override
    protected String entryId() {
        return "oven_appliances";
    }
}
