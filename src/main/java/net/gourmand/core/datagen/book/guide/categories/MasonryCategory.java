package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.masonry.*;
import net.minecraft.resources.ResourceLocation;

public class MasonryCategory extends CategoryProvider {

    public MasonryCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            "abcdefgj"
        };
    }

    @Override
    protected void generateEntries() {

        var bricks_and_mortar = this.add(new BricksAndMortarEntry(this).generate("a"));
        var stone_bricks = this.add(new StoneBricksEntry(this).generate("b"));
        var quarrying = this.add(new QuarryingEntry(this).generate("c"));
        var smooth = this.add(new SmoothStoneEntry(this).generate("d"));
        var chiseled_bricks = this.add(new ChiseledBricksEntry(this).generate("e"));
        var stone_pressure_plate = this.add(new StonePressurePlateEntry(this).generate("f"));
        var stone_button = this.add(new StoneButtonEntry(this).generate("g"));
        var cobblestone = this.add(new CobbleEntry(this).generate("h"));
        // add chiseling (mechanic)
        // add aqueducts
        // add mortared cobble
        // add collapse (same as mining category)
        // add plaster (see field guide "Advanced Materials")
    }

    @Override
    protected String categoryName() {
        return "Masonry";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.CHISEL));
    }

    @Override
    public String categoryId() {
        return "masonry";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
