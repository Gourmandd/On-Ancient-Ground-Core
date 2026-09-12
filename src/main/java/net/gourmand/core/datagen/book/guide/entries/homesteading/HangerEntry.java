package net.gourmand.core.datagen.book.guide.entries.homesteading;

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

public class HangerEntry extends EntryProvider {

    public HangerEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain hangar.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.HANGERS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Hangers** can be used to store meat and garlic, they are intended to be used inside of **Cellars**.
                \\
                \\
                Inside of **Cellars** the food contained in **Hangers** spoils slower.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/hanger/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The hanger recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Hangers";
    }

    @Override
    protected String entryDescription() {
        return "About Hangers.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.HANGERS.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "hanger";
    }
}
