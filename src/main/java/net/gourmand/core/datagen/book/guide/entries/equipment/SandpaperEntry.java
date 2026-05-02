package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SandpaperEntry extends EntryProvider {

    public SandpaperEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain sandpaper.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(AllItems.SAND_PAPER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Sandpaper** can be used to polish items, to do so hold sandpaper in your offhand and the item you wish to polish in your main hand,
                 and hold **Right-Click** until the item is converted.
               \s""");

        // page 2: recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(Create.ID, "crafting/materials/sand_paper"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Sandpaper being crafted out of a paper, glue and sand. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Sandpaper";
    }

    @Override
    protected String entryDescription() {
        return "About Sandpaper";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(AllItems.SAND_PAPER);
    }

    @Override
    protected String entryId() {
        return "sandpaper";
    }
}
