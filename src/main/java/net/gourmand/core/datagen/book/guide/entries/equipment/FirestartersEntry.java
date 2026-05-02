package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class FirestartersEntry extends EntryProvider {

    public FirestartersEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain firestarters.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.FIRESTARTER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                There are various ways to conveniently start fires.
                \\
                \\
                **Firestarters** are the cheapest way to make fire, but you need to hold **Right-Click** pointing on a block for 6 seconds to do so.
               \s""");

        // page 2: firestarter recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/firestarters"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Firestarters** just require two sticks!
               \s""");

        // page 3: explain flint and pyrite.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.FLINT_AND_PYRITE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Flint and Pyrite** is a early game way to start fires, they have a chance to create fire when used.
               \s""");

        // page 4: flint and pyrite recipe.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/flint_and_pyrite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Flint and Pyrite's** most expensive component is **Pyrite**.
               \s""");

        // page 5: explain flint and steel.
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.FLINT_AND_STEEL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Flint and Steel** is a mid game way to start fires, they have a 100% chance to create fire when used.
               \s""");

        // page 6: flint and steel recipe.
        this.page("page6", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "crafting/flint_and_steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Flint and Steel** most expensive component is **Steel**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Starting Fires";
    }

    @Override
    protected String entryDescription() {
        return "About Fire Starting.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FIRESTARTER);
    }

    @Override
    protected String entryId() {
        return "starting_fires";
    }
}
