package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.FLTags;
import com.eerussianguy.firmalife.common.items.FLFood;
import com.eerussianguy.firmalife.common.items.FLItems;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FermentedTraitEntry extends EntryProvider {

    public FermentedTraitEntry(CategoryProviderBase parent) {
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
                The **Fermented Trait** by default, modifies spoilage rate by **0.25**.
                \\
                \\
                It occurs to smashed grapes when you seal them in a barrel with 100mb of water. Its required to make **Wine**.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "barrel_sealed/ferment_white_grapes"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "barrel_sealed/ferment_red_grapes"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The fermenting recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Fermented";
    }

    @Override
    protected String entryDescription() {
        return "About The Fermented Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLItems.FOODS.get(FLFood.SMASHED_WHITE_GRAPES));
    }

    @Override
    protected String entryId() {
        return "fermented_trait";
    }
}
