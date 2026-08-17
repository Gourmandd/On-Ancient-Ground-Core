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

public class CobbleEntry extends EntryProvider {

    public CobbleEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain cobble.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.COBBLE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Cobble** is a cheap building block. It landslides, meaning it will fall to a side if not supported by 2+ adjacent blocks.
                \\
                \\
                There is a mossy variant.
                \\
                \\
                Loose rock can be found on the ground or by mining raw rock.
               """);

        // page 2: cobble crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/cobble/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               How to make cobble.
               """);

        // page 3: mossy cobble crafting recipe.
        this.page("page3", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/mossy_cobble/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               How to make mossy cobble.
               """);
    }

    @Override
    protected String entryName() {
        return "Cobble";
    }

    @Override
    protected String entryDescription() {
        return "About Cobble.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.COBBLE));
    }

    @Override
    protected String entryId() {
        return "cobble";
    }
}
