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

public class BookshelfEntry extends EntryProvider {

    public BookshelfEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bookshelves. (they work like chiseled bookselves)
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.BARREL)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bookshelves** can hold 6 books, **Right-Click** them with a book in a empty slot to place it and with an empty hand to take a book.
               \s""");

        // page 2: ash door recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/bookshelf/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash bookshelf being made out Ash lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bookshelves";
    }

    @Override
    protected String entryDescription() {
        return "About Bookshelves";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.BOOKSHELF));
    }

    @Override
    protected String entryId() {
        return "bookshelf";
    }
}
