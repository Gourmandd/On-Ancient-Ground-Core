package net.gourmand.core.datagen.book.guide.entries.carpentry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class WorkbenchEntry extends EntryProvider {

    public WorkbenchEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain workbenches.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.WORKBENCH)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Workbenches** are used to craft items requiring a 3x3 grid.
               \s""");

        // page 2: ash workbench recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/workbench/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash workbench being made out Ash planks.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Workbenches";
    }

    @Override
    protected String entryDescription() {
        return "About Workbenches";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.WORKBENCH));
    }

    @Override
    protected String entryId() {
        return "workbench";
    }
}
