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
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class PreservedTraitEntry extends EntryProvider {

    public PreservedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain preserved trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.VESSELS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Preserved Trait** by default, modifies spoilage rate by **0.5**.
                \\
                \\
                Any food item can be placed in Vessels or (sealed) large vessels to be considered **Preserved** while inside.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_vessel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The unfired vessel recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Preserved";
    }

    @Override
    protected String entryDescription() {
        return "About The Preserved Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }
    
    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.VESSEL);
    }

    @Override
    protected String entryId() {
        return "preserved_trait";
    }
}
