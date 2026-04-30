package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.minecraft.resources.ResourceLocation;

public class MetalworkingCategory extends CategoryProvider {

    public MetalworkingCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            ""
        };
    }

    @Override
    protected void generateEntries() {
        // add rock anvil
        // add anvil
        // add forging
        // add welding
        // add info on metal shapes (ingot, d.ingot, sheet, rod etc)
        // add bloomery
        // add blast furnace
        // add charcoal pit
        // add charcoal forge
        // add fuel guide
        // add alloying guide.
        // add bars
        // add grates
        // add plated blocks
        // add steel making info (after blast furnace)
        // add iron making info (after bloomery)
        // add coloured steel making info
        // add chain
        // add metal buckets.
    }

    @Override
    protected String categoryName() {
        return "Masonry";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.BRONZE).get(Metal.BlockType.ANVIL));
    }

    @Override
    public String categoryId() {
        return "metalworking";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
