package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ClayEntry extends EntryProvider {

    public ClayEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain how to get clay.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.CLAY_INDICATORS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Clay** is an useful material that can be used for **Pottery**, and **Daub**. \s
               \s""");

        // page 2: .
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "getting_started/clay_indicators"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                Clay indicators on clay.\s
               \s""");

        // page 3: explaining knapping.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CLAY_BALL))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Once you have clay, you can **Knap** the clay into unfired ceramics.\s
               \\
               \\
               To do so, you need to have five or more clay balls in your hand and **Right Click** in the air.\s
               \s""");

        // page 4: knapping GUI.
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/getting_started/clay_knapping.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Clay Knapping GUI.\s
                \\
                \\
                Tip: The "Clay Knapping" category in EMI shows everything you can make by knapping clay!
               \s""");

        // page 5: explaining knapping.
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.UNFIRED_VESSEL))
        );

        this.pageTitle(entryName());
        this.pageText("""
               To fire ceramics in the early game, you need to make a **Pit Kiln**.\s
               \\
               \\
               You can make various ceramics such as **Vessels**, **Jugs**, and **Molds**.\s
               \s""");

        // page 6: knapping vessel.
        this.page("page6", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_vessel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping a **Vessel**.\s
               \s""");

        // page 7: knapping large vessel.
        this.page("page7", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_large_vessel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping a **Large Vessel**.\s
               \s""");

        // page 8: knapping jug.
        this.page("page8", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_jug"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Knapping a **Jug**.\s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Clay";
    }

    @Override
    protected String entryDescription() {
        return "About Finding and Knapping Clay";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CLAY_BALL);
    }

    @Override
    protected String entryId() {
        return "clay";
    }
}
