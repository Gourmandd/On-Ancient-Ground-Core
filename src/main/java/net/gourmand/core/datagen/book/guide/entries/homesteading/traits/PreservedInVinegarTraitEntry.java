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

public class PreservedInVinegarTraitEntry extends EntryProvider {

    public PreservedInVinegarTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain pickled in vinegar trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.VEGETABLES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Preserved In Vinegar Trait** by default, modifies spoilage rate by **0.5**.
                \\
                \\
                Firstly, any food item needs to be **Pickled**, and then be sealed (and kept sealed) in a barrel with 125mb of vinegar per item.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/preserved_in_vinegar"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The preserving in vinegar recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Preserved In Vinegar";
    }

    @Override
    protected String entryDescription() {
        return "About The Preserved In Vinegar Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }
    
    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FOOD.get(Food.BEET));
    }

    @Override
    protected String entryId() {
        return "preserved_in_vinegar_trait";
    }
}
