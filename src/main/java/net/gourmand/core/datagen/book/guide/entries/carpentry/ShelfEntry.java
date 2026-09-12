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

public class ShelfEntry extends EntryProvider {

    public ShelfEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain shelves.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SHELF)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Shelves** can have items placed on them using **Right-Click** or by pressing **V** on or under them while holding an item.
                To remove an item, do the same but with a empty hand.

               \s""");

        // page 2: ash shelf recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/shelf/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash shelf being made out Ash lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Shelves";
    }

    @Override
    protected String entryDescription() {
        return "About Shelves";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SHELF));
    }

    @Override
    protected String entryId() {
        return "shelf";
    }
}
