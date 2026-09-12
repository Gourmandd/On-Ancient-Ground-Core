package net.gourmand.core.datagen.book.guide.entries.masonry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class StonePressurePlateEntry extends EntryProvider {

    public StonePressurePlateEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain stone pressure plate.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.PRESSURE_PLATE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 **Stone Pressure Plates** need to be placed on a solid block.
                 \\
                 \\
                 When a player or mob is on top of them, they emit a strong **Redstone** signal on the block they are on and a weak one to the surrounding blocks.
               """);

        // page 2: smooth stone crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/pressure_plate/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               How to make a stone pressure plate.
               """);
    }

    @Override
    protected String entryName() {
        return "Stone Pressure Plate";
    }

    @Override
    protected String entryDescription() {
        return "About Stone Pressure Plate.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.PRESSURE_PLATE));
    }

    @Override
    protected String entryId() {
        return "stone_pressure_plate";
    }
}
