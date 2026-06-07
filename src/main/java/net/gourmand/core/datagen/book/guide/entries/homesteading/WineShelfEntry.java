package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.FLTags;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class WineShelfEntry extends EntryProvider {

    public WineShelfEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain wine shelves.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.KEGS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Wine Shelf** allows you to store wine in style.
                \\
                \\
                **Right-Click** with a wine bottle in hand to place it, and with an empty hand to remove a bottle.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/wine_shelf/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The wine shelf is made from logs, and treated lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Wine Shelf";
    }

    @Override
    protected String entryDescription() {
        return "About Wine Shelves.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.WINE_SHELVES.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "wine_shelf";
    }
}
