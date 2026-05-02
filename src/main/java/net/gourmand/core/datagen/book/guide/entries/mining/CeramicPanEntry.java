package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class CeramicPanEntry extends EntryProvider {

    public CeramicPanEntry(CategoryProviderBase parent) {
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
                **Ceramic Pans** can be used to pan the gravel ore deposits found in rivers or lakes.
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

        // page 3: explain how to pan.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.EMPTY_PAN))
        );

        this.pageTitle(entryName());
        this.pageText("""
                To start panning:   \s
                 1. While holding the pan, use it on the ore deposit block.
                 2. While standing in water hold **Right-Click** while holding the pan and you will start panning.
                 3. After a few seconds it will finish panning and you will get a reward for your patience!
               \s""");

        // page 4: image of that.
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/panning.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A ceramic pan in use.
               \s""");

        // page 5: products.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Products of Panning");
        this.pageText("""
                The gem that can drop depends on rock type of the ore deposit.
                \\
                \\
                Sluicing can give three products:   \s
                - Ore: 50%%
                - Loose Rock: 25%%
                - Gem: 1%%
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
