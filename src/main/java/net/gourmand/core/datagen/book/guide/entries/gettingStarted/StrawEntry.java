package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.world.item.crafting.Ingredient;

public class StrawEntry extends EntryProvider {

    public StrawEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain how to get straw.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.STRAW))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Straw** is an item acquired by breaking grasses with a **Knife**.
                \\
                \\
                It can be used for **Pit Kilns** and **Thatch**\s
               \s""");

        // page 2: thatch recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1("tfc:crafting/thatch")
        );

        this.pageTitle(entryName());
        this.pageText("""
                Crafting recipe to make thatch blocks.\s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Straw";
    }

    @Override
    protected String entryDescription() {
        return "About Straw";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.STRAW);
    }

    @Override
    protected String entryId() {
        return "straw";
    }
}
