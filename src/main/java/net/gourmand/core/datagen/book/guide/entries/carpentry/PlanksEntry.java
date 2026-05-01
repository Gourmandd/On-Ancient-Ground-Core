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

public class PlanksEntry extends EntryProvider {

    public PlanksEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain planks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.PLANKS)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Planks** are a simple building material made out of 4 **lumber**. It also has stair and slab variants.
               \s""");

        // page 2: ash planks recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/planks/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash planks being made out Ash lumber.
               \s""");

        // page 3: birch plank slab recipe.
        this.page("page3", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/planks/birch_slab"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Birch plank slab being made out Birch lumber.
               \s""");

        // page 3: pine plank stairs recipe.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/planks/pine_stairs"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Pine plank stairs being made out Pine lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Planks";
    }

    @Override
    protected String entryDescription() {
        return "About Planks";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.PLANKS));
    }

    @Override
    protected String entryId() {
        return "planks";
    }
}
