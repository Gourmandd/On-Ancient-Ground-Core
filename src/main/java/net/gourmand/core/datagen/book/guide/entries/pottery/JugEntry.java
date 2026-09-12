package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class JugEntry extends EntryProvider {

    public JugEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain jugs.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.JUG))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Jugs** can hold 100mb of non-hazardous fluids and be drank from. Some fluids can give **Nutrition** or status effects.
                \\
                \\
                Shift + right-clicking on any block empties (and deletes) the contained fluid. Right-clicking in the air with an empty **Jug** creates a sound!
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_jug"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A jug being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Jug";
    }

    @Override
    protected String entryDescription() {
        return "About Jugs";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.JUG);
    }

    @Override
    protected String entryId() {
        return "jug";
    }
}
