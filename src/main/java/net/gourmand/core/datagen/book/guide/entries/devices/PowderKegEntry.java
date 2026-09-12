package net.gourmand.core.datagen.book.guide.entries.devices;

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

public class PowderKegEntry extends EntryProvider {

    public PowderKegEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain powderkegs.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.POWDERKEG))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Powderkegs** are a device which can create a controllable sized explosion. It has 12 slots in which only gunpowder can be held. The explosion is proportional to the amount of gunpowder stored.
               \s""");

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/powderkeg"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                It can be sealed like a **Barrel**.
               \s""");

        // page 3: explain powderkegs.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Powderkegs** can be ignited using any type of firestarters, and by adjacent fire. They can ignite nearby powderkegs upon exploding.
                \\
                \\
                All broken blocks are dropped. Making to possible to blast mine.
               \s""");

        // page 4: crafting.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/gunpowder"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/gunpowder_with_graphite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                There are two formulations for gunpowder.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Powderkeg";
    }

    @Override
    protected String entryDescription() {
        return "About Powderkegs.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.POWDERKEG);
    }

    @Override
    protected String entryId() {
        return "powderkeg";
    }
}
