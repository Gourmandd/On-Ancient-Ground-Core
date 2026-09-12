package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BrinedTraitEntry extends EntryProvider {

    public BrinedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain brined trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.FRUITS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Brined Trait** by default, modifies spoilage rate by **1.0**.
                \\
                \\
                It is an intermediate step for other preservation techniques. It is done by sealing any food item with 125mb of **Brine**.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/brined"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The brining recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Brined";
    }

    @Override
    protected String entryDescription() {
        return "About The Brined Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.WOODEN_BUCKET);
    }

    @Override
    protected String entryId() {
        return "brined_trait";
    }
}
