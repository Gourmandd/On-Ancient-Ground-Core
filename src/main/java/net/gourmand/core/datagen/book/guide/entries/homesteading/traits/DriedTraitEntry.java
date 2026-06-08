package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class DriedTraitEntry extends EntryProvider {

    public DriedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain dried trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.FRUITS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Dried Trait** by default, modifies spoilage rate by **0.5**.
                \\
                \\
                It occurs when you dry food using a **drying Mat**.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/drying_mat"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The drying mat recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Dried";
    }

    @Override
    protected String entryDescription() {
        return "About The Dried Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.DRYING_MAT);
    }

    @Override
    protected String entryId() {
        return "dried_trait";
    }
}
