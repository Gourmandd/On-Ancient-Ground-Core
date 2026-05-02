package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CrossbowEntry extends EntryProvider {

    public CrossbowEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain crossbows.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CROSSBOW))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Crossbows** are a ranged weapon that can fire arrows in a straight arc at opponents. They need to be drawn for a second or two to load an arrow.
                \\
                \\
                They are decent at defending against predators from a distance. Their damage type is **Piercing**
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "crossbow"))
        );


        this.pageTitle(entryName());
        this.pageText("""
                The crafting recipe for the crossbow.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Crossbows";
    }

    @Override
    protected String entryDescription() {
        return "About Crossbows.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CROSSBOW);
    }

    @Override
    protected String entryId() {
        return "crossbow";
    }
}
