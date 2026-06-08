package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.Food;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class PickledTraitEntry extends EntryProvider {

    public PickledTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain pickled trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.VEGETABLES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Pickled Trait** by default, modifies spoilage rate by **0.5**.
                \\
                \\
                First, any food item needs to be **Brined** and then be sealed in a barrel with 125mb of vinegar.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/pickled"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The pickling recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Pickled";
    }

    @Override
    protected String entryDescription() {
        return "About The Pickled Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }
    
    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FOOD.get(Food.TOMATO));
    }

    @Override
    protected String entryId() {
        return "pickling_trait";
    }
}
