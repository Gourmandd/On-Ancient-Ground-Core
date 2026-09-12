package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookCastingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BronzeMakingEntry extends EntryProvider {

    public BronzeMakingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bronze making.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(
                        TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.INGOT),
                        TFCItems.METAL_ITEMS.get(Metal.BISMUTH_BRONZE).get(Metal.ItemType.INGOT),
                        TFCItems.METAL_ITEMS.get(Metal.BLACK_BRONZE).get(Metal.ItemType.INGOT)
                ))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bronze** is a metal decently stronger than **Copper**. It has three different formulations, requiring different metals.
                \\
                \\
                Its used for tools, appliances, and importantly **Bloomeries**.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/bronze_ingot"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/black_bronze_ingot"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                It is made by casting the various alloys.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bronze";
    }

    @Override
    protected String entryDescription() {
        return "About Bronze!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.INGOT));
    }

    @Override
    protected String entryId() {
        return "bronze";
    }
}
