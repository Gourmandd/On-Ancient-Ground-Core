package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.FLTags;
import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLFruit;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class GrapeTraitEntry extends EntryProvider {

    public GrapeTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain fermented trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.SMASHED_GRAPES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Bee Pollinated**, **Dirt Grown**, **Gravel Grown**, and **Slope Grown** traits by default, modifies spoilage rate by **0.8**, **0.9**, **0.8**, **0.8** respectfully.
                \\
                \\
                Grapes acquire these based on the surroundings they grow in. See **Here** for more.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/grape_trellis_post"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The grape trellis post recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Grape Traits";
    }

    @Override
    protected String entryDescription() {
        return "About The Grape Traits.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLItems.FRUITS.get(FLFruit.RED_GRAPES));
    }

    @Override
    protected String entryId() {
        return "grape_traits";
    }
}
