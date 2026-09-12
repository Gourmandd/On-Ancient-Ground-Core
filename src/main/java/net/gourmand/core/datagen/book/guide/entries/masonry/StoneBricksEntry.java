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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;

public class StoneBricksEntry extends EntryProvider {

    public StoneBricksEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain stone bricks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(ItemTags.STONE_BRICKS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Stone Bricks** are made of **Stone Bricks and Mortar**, making them a cheap and versatile building block.
               """);

        // page 2: bricks blocks crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/bricks/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               How to make stone bricks.
               """);

        // page 3: cracked brick recipe.
        this.page("page3", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/cracked_bricks/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Cracked bricks are an extra variant for creative decoration.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Stone Bricks";
    }

    @Override
    protected String entryDescription() {
        return "About Stone Bricks.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.BRICKS));
    }

    @Override
    protected String entryId() {
        return "stone_bricks";
    }
}
