package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class WeatherVaneEntry extends EntryProvider {

    public WeatherVaneEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain weather vanes.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.VANE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Weather Vanes** are a device which emit redstone signals based on wind orientation.
                \\
                \\
                It starts at 0 at North and increases as it goes clockwise, with South resulting in a signal strength of 8.
               """);

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/vane"))
        );

        this.pageTitle(entryName());
        this.pageText("This signal is emitted to adjacent blocks (you don't need a comparator).");
    }

    @Override
    protected String entryName() {
        return "Weather Vane";
    }

    @Override
    protected String entryDescription() {
        return "About Weather Vanes.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.VANE);
    }

    @Override
    protected String entryId() {
        return "weather_vane";
    }
}
