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
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.gourmand.core.registry.CoreItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BucketEntry extends EntryProvider {

    public BucketEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain buckets.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.WOODEN_BUCKET))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Buckets** are able to hold large amounts of fluid by interacting with containers or by collecting source blocks using **Right-Click**, only some types of buckets can place source blocks.
                 \\
                 \\
                 **Wooden Buckets** are the simplest to make, but cannot place source blocks. They cannot hold molten fluids such as lava.
               \s""");

        // page 2: wooden bucket recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wooden_bucket"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A wooden bucket being made out of lumber and glue.
               \s""");

        // page 3: explain iron buckets.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(CoreItems.WROUGHT_IRON_BUCKET.get()))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 **Wrought Iron Buckets** can place source blocks, but they cannot hold molten fluids such as lava.
               \s""");

        // page 4: iron bucket recipe.
        this.page("page4", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "anvil/bucket/wrought_iron"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A wrought iron bucket being forged out of a sheet of **Wrought Iron**.
               \s""");

        // page 5: explain rue steel buckets.
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.BLUE_STEEL_BUCKET))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 **Blue Steel Buckets** and **Red Steel Buckets** can place source blocks, and they can hold molten fluids such as lava.
               \s""");

        // page 6: blue steel bucket recipe.
        this.page("page6", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/bucket/blue_steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A blue steel bucket being forged out of a sheet of **Blue Steel**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Buckets";
    }

    @Override
    protected String entryDescription() {
        return "About Buckets";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.WOODEN_BUCKET);
    }

    @Override
    protected String entryId() {
        return "bucket";
    }
}
