package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class BowEntry extends EntryProvider {

    public BowEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bows.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.BOW))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bows** are a ranged weapon that can fire arrows in a curved arc at opponents. They need to be drawn for a second or two to fire the arrow.
                \\
                \\
                They are decent at defending against predators from a distance. Their damage type is **Piercing**
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "crafting/bow"))
        );


        this.pageTitle(entryName());
        this.pageText("""
                The crafting recipe for the bow.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bows";
    }

    @Override
    protected String entryDescription() {
        return "About Bows.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.BOW);
    }

    @Override
    protected String entryId() {
        return "bow";
    }
}
