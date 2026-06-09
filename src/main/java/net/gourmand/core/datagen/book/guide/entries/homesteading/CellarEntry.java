package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class CellarEntry extends EntryProvider {

    public CellarEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain cellars.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLBlocks.SEALED_BRICKS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Cellar is a multiblock controlled by a Climate Station, consisting of an enclosed space of Sealed Bricks. Its purpose is to slow down the spoilage of items stored inside of it in **Hangers**, or **Food Shelves**.
                \\
                \\
                They preform better the lower the ambient temperature.
               """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/sealed_bricks"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/sealed_brick_door"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The relevant recipes.
               """);

        // page 3: explain barrels.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                To build it, create an enclosed space out of **Sealed Bricks**, with a **Sealed Brick Door**.
                \\
                \\
                Inside, place a **Climate Station** on the floor and against a wall, then right click it to check if the cellar is valid.
               """);

        // page 4: interface.
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withVisualizeButton(false)
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "homesteading/cellar"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An example cellar.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Cellars";
    }

    @Override
    protected String entryDescription() {
        return "About Cellars.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.SEALED_BRICKS);
    }

    @Override
    protected String entryId() {
        return "cellar";
    }
}
