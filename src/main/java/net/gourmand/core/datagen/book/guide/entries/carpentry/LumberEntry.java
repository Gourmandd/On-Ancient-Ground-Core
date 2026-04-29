package net.gourmand.core.datagen.book.guide.entries.carpentry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class LumberEntry extends EntryProvider {

    public LumberEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain lumber.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.LUMBER))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Lumber** is a material needed for most complex wooden blocks and items. You can make it using a saw and logs.
                \\
                \\
                Planks, slabs, and stairs can be also recycled into lumber.
               \s""");

        // page 2: ash lumber recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/lumber/ash_from_logs"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                8 Ash lumber being made out Ash logs.
               \s""");

        // page 3: ash lumber recipe.
        this.page("page3", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/lumber/birch_from_slabs"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                2 Birch lumber being made out Birch plank slabs.
               \s""");

        // page 3: ash lumber recipe.
        this.page("page4", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/lumber/pine_from_planks"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                4 Pine lumber being made out Pine planks.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Lumber";
    }

    @Override
    protected String entryDescription() {
        return "About Lumber";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.LUMBER.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "lumber";
    }
}
