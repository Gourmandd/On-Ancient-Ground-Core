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

public class PanEntry extends EntryProvider {

    public PanEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain pans.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.EMPTY_PAN))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ceramic Pans** can be used to pan gravel ore deposits found in rivers or lakes.
                \\
                \\
                **Sluices** can semi-automate their functionality after unlocking saws.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_pan"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A ceramic pan being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Ceramic Pans";
    }

    @Override
    protected String entryDescription() {
        return "About Ceramic Pans";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.EMPTY_PAN);
    }

    @Override
    protected String entryId() {
        return "ceramic_pan";
    }
}
