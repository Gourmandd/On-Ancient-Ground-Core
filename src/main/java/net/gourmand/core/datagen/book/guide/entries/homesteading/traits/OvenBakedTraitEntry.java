package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.blocks.oven.OvenType;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class OvenBakedTraitEntry extends EntryProvider {

    public OvenBakedTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain oven baked trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLBlocks.CURED_OVEN_TOP.get(OvenType.BRICK)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Oven Baked Trait** by default, modifies spoilage rate by **0.9**.
                \\
                \\
                It occurs when you cook food in a **Top Oven**.
               """);

        // page 2: clay top oven recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "knapping/clay_oven_top"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The uncured top oven recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Oven Baked";
    }

    @Override
    protected String entryDescription() {
        return "About The Oven Baked Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() { return BookIconModel.create(FLBlocks.CURED_OVEN_TOP.get(OvenType.BRICK)); }

    @Override
    protected String entryId() {
        return "oven_baked_trait";
    }
}
