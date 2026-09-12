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

public class FenceGateEntry extends EntryProvider {

    public FenceGateEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain fence gates.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.FENCE_GATE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Fence Gates** can be used as a cheap barrier. They are 1.5 blocks tall preventing most creatures from jumping over.
                \\
                \\
                They can be opened and closed using **Right-Click**.
               \s""");

        // page 2: ash fence gate recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/fence_gate/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash fence gate being made out Ash planks and lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Fence Gates";
    }

    @Override
    protected String entryDescription() {
        return "About Fence Gates";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.FENCE_GATE));
    }

    @Override
    protected String entryId() {
        return "fence_gate";
    }
}
