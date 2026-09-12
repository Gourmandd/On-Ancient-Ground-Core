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

public class ScribingTableEntry extends EntryProvider {

    public ScribingTableEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain scribing table.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SCRIBING_TABLE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Scribing Tables** are a device that can be used to make design related items, such as banner patterns and armour trims. (See entry in Homesteading category for more info)
               \s""");

        // page 2: ash scribing table recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/scribing_table/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash scribing table being made out Ash lumber and planks.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Scribing Tables";
    }

    @Override
    protected String entryDescription() {
        return "About Scribing Tables";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SCRIBING_TABLE));
    }

    @Override
    protected String entryId() {
        return "scribing_table";
    }
}
