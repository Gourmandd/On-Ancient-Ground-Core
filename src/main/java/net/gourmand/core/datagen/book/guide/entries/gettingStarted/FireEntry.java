package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.*;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FireEntry extends EntryProvider {

    public FireEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain fire and uses.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.FIRESTARTER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 You will need to create fire to ignite **Fire Pits**, **Pit Kilns**, and **Charcoal Forges**.
                 \\
                 \\
                 The earliest way to makes fires are the **Firestarters**, hold **Right Click** for 5 seconds while holding them and pointing at a block.
                \s""");

        // page 2: how to craft firestarters.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1("tfc:crafting/firestarter")
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Firestarters** are a very cheap way to create fire.\s
               \s""");

        // page 3: explain fire pits work.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.FIREPIT.asItem()))
        );

        this.pageTitle(entryName());
        this.pageText("""
               To make a **Fire Pit**: drop 1 log, 3 sticks and 0-5 kindling items (like straw) on the ground. Then set fire to it with something like the firestarters.
               \s""");

        // page 4: multiblock.
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "getting_started/fire_pit"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                Fire Pit on the ground.\s
               \s""");

        // page 5: explain fire pits work.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
               Fire Pits can cook items, useful for cooking raw meat and vegetables. It can be reignited with a firestarter.
               \\
               \\
               You can insert logs as fuel in the GUI and by **Right Clicking** the **Fire Pit** with the log in your hand.
               \s""");

        // page 6: multiblock.
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/getting_started/fire_pit_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Logs, raw and cooked items go in the slots as shown.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Fire Mechanics";
    }

    @Override
    protected String entryDescription() {
        return "About Fire";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FIRESTARTER.asItem());
    }

    @Override
    protected String entryId() {
        return "fire";
    }
}
