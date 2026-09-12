package net.gourmand.core.datagen.book.guide.entries.masonry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SmoothStoneEntry extends EntryProvider {

    public SmoothStoneEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain smooth stone.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.STONES_SMOOTH))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Smooth Stone** is made with a chisel and raw stone obtained through **Quarrying**. It requires no mortar.
               """);

        // page 2: smooth stone crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/smooth/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               How to make smooth stone.
               """);
    }

    @Override
    protected String entryName() {
        return "Smooth Stone";
    }

    @Override
    protected String entryDescription() {
        return "About Smooth Stone.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.SMOOTH));
    }

    @Override
    protected String entryId() {
        return "smooth_stone";
    }
}
