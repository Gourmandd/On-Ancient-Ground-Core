package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class IngotMoldEntry extends EntryProvider {

    public IngotMoldEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain ingot molds.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.MOLDS.get(Metal.ItemType.INGOT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ingot Molds** can be used to **Cast** ingots. (See **Casting** for more info)
                \\
                \\
                Regular **Ingot Molds** have a 1/10 chance to break, while **Fire Ingot Molds** have a 1/100 chance to break.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_ingot_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An Ingot Mold being knapped out of **Clay**.
               \s""");

        // page 3: fire knapping recipe.
        this.page("page3", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_fire_ingot_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An Ingot Mold being knapped out of **Fire Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Ingot Molds";
    }

    @Override
    protected String entryDescription() {
        return "About Ingot Molds";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.MOLDS.get(Metal.ItemType.INGOT));
    }

    @Override
    protected String entryId() {
        return "ingot_mold";
    }
}
