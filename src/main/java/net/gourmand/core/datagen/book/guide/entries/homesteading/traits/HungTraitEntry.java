package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

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

public class HungTraitEntry extends EntryProvider {

    public HungTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain hung trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.CAN_BE_HUNG))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 The **Hung Traits** by default, modifies spoilage rate by **0.4**, **0.35**, and **0.25** depending on tier.
                 \\
                 \\
                 It occurs when you place meat or garlic in **Hangers** in a valid **Cellar**, the colder the ambient temperature, the higher tier of the trait it gets.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/hanger/oak"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The oak hanger recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Hung";
    }

    @Override
    protected String entryDescription() {
        return "About The Hung Traits.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.HANGERS.get(Wood.OAK));
    }

    @Override
    protected String entryId() {
        return "hung_trait";
    }
}
