package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookQuernPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class QuernEntry extends EntryProvider {

    public QuernEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain quern.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.QUERN))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Quern** is a device that can grind item. Creating powders, dyes and other items.
                \\
                \\
                It is assembled from a Base and Handstone.
                \\
                \\
                The Quern can also be connected to a Mechanical Power network.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/quern"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The quern and its handstone is made from raw and smooth rock.
               \s""");

        // page 3: put the handstone
        this.page("page3", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/homesteading/quern_1.png"))
        );

        this.pageText("""
               **Right-Click** with the handstone in hand to place it. (**Shift + Right-Click** to remove it)
               \s""");

        // page 4: put the ingredient
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/homesteading/quern_2.png"))
        );

        this.pageText("""
               **Right-Click** the top of the handstone to place your ingredients.
               \s""");

        // page 5: use the handstone
        this.page("page5", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/homesteading/quern_3.png"))
        );

        this.pageText("""
               **Right-Click** the handstone to start grinding.
               \s""");

        // page 6: your result
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/homesteading/quern_4.png"))
        );

        this.pageText("""
               **Right-Click** the quern to get your items.
               \s""");

        // page 7: quern recipes
        this.page("page7", () -> BookQuernPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/red_dye"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/powder/native_gold_poor"))
        );

        this.pageTitle(this.context.pageTitle());
        this.pageText("""
               Dyes and powders can be made with the quern.
               \s""");

        // page 8: quern recipes
        this.page("page8", () -> BookQuernPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/powder/flux"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/food/barley_flour"))
        );

        this.pageTitle(this.context.pageTitle());
        this.pageText("""
               Flour and flux can also be made.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Quern";
    }

    @Override
    protected String entryDescription() {
        return "About Querns.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.QUERN);
    }

    @Override
    protected String entryId() {
        return "quern";
    }
}
