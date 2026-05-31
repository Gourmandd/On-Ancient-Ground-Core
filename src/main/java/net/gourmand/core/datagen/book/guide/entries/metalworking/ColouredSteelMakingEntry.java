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
import net.gourmand.core.modonomicon.datagen.BookAnvilWorkingPageModel;
import net.gourmand.core.modonomicon.datagen.BookWeldingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ColouredSteelMakingEntry extends EntryProvider {

    public ColouredSteelMakingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain coloured steel making.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT), TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.INGOT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Coloured Steels** are a very strong metal, used largely for tools.
                \\
                \\
                Lamps and bucked made out of them can hold lava and other dangerous fluids.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/high_carbon_red_steel_ingot"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/high_carbon_blue_steel_ingot"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                First, make and cast weak coloured steel ingots.
               \s""");

        // page 3: making it
        this.page("page3", () -> BookWeldingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/ingot/high_carbon_red_steel"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/ingot/high_carbon_blue_steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Secondly, weld weak coloured steel ingots with **Black Steel** into **High Carbon Coloured Steel**.
               \s""");

        // page 4: making it
        this.page("page4", () -> BookAnvilWorkingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/red_steel"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/ingot/blue_steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Then refine high carbon coloured steel ingots into **Coloured Steel**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Coloured Steel";
    }

    @Override
    protected String entryDescription() {
        return "About Coloured Steel!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT));
    }

    @Override
    protected String entryId() {
        return "coloured_steel";
    }
}
