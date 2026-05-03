package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.resources.ResourceLocation;

public class DevicesCategory extends CategoryProvider {

    public DevicesCategory(ModonomiconProviderBase parent) {
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

        // have mechanical automation in entries as needed.

        // add tnt
        // add barrel (same as homesteading)
        // add quern (same as homesteading)
        // add powder keg
        // add jukebox
        // add note block
        // add mechanical bits
        // add composter (same as homesteading)
        // add crankshaft
        // add bellows (same as metalworking)
        // add minecarts
        // add lamp guide
        // add steel pump (might be being removed in the pack, buckets can move sources)
        // add scribing table
        // add sewing table
        // add precision clock
        // add thermometer
        // add anemometer
        // add weather vane.
        // add stove (same as homesteading)
        // add beds. (thatch and normal)
        // add climate station
        // add compost tumbler
        // add mixing bowl (same as homesteading)
        // add stove
        // add wrought iron grill
        //
    }

    @Override
    protected String categoryName() {
        return "Devices";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCBlocks.QUERN);
    }

    @Override
    public String categoryId() {
        return "devices";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
