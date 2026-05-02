package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SurfaceOreEntry extends EntryProvider {

    public SurfaceOreEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain ore indicators.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.SMALL_ORE_PIECES))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ore Indicators** are a patch of ore bits which can be found above ore veins.
                \\
                \\
                The small ores can be melted into 10 mb of metal, which is the main method of getting metal before getting a **Pickaxe**
               \s""");

        // page 2: image of an ore deposit.
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/ore_indicator.png"))
        );

        this.pageTitle("Ore Indicator");
        this.pageText("""
                A **Hematite** ore indicator in an **Andesite** region.
               \s""");

    }

    @Override
    protected String entryName() {
        return "Ore Indicators";
    }

    @Override
    protected String entryDescription() {
        return "About Ore Indicators";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_GOLD));
    }

    @Override
    protected String entryId() {
        return "ore_indicators";
    }
}
