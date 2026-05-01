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

public class ToolRackEntry extends EntryProvider {

    public ToolRackEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain tool racks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.TOOL_RACK)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Tool Racks** are a block that can store up to 4 tools.
                \\
                \\
                To use it, **Right-Click** onto it with a tool in hand, do so again with an empty hand while pointing at a racked tool to get it back.
               \s""");

        // page 2: ash tool rack recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/tool_rack/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash tool rack being made out Ash lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Tool Racks";
    }

    @Override
    protected String entryDescription() {
        return "About Tool Racks";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.TOOL_RACK));
    }

    @Override
    protected String entryId() {
        return "tool_rack";
    }
}
