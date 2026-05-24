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
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SteelMakingEntry extends EntryProvider {

    public SteelMakingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain steel making.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.STEEL).get(Metal.ItemType.INGOT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Steel** is a durable metal, used for machinery, and it more durable than **Wrought Iron**.
                \\
                \\
                It is made using a **Blast Furnace** which in itself requires **Kaolin** and **Graphite**.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/high_carbon_steel"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                It is made by refining weak steel ingots made using a **Blast Furnace**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Steel";
    }

    @Override
    protected String entryDescription() {
        return "About Steel!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.STEEL).get(Metal.ItemType.INGOT));
    }

    @Override
    protected String entryId() {
        return "steel";
    }
}
