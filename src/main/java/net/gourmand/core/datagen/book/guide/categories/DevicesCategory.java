package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.datagen.book.guide.entries.devices.*;
import net.gourmand.core.datagen.book.guide.entries.metalworking.BlastFurnaceEntry;
import net.gourmand.core.datagen.book.guide.entries.metalworking.BloomeryEntry;
import net.minecraft.resources.ResourceLocation;

public class DevicesCategory extends CategoryProvider {

    public DevicesCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            "a_c_e_k_gh",
            "b_d_f_l_ij"
        };
    }

    @Override
    protected void generateEntries() {

        // have mechanical automation in entries as needed.

        var bloomery = this.add(new BloomeryEntry(this).generate("a"));
        var blast_furnace = this.add(new BlastFurnaceEntry(this).generate("b"));

        var tnt = this.add(new TntEntry(this).generate("c"));
        var powderkeg = this.add(new PowderKegEntry(this).generate("d"));
        var jukebox = this.add(new JukeboxEntry(this).generate("e"));
        var note_block = this.add(new NoteBlockEntry(this).generate("f"));

        var clock = this.add(new PrecisionClockEntry(this).generate("g"));
        var thermometer = this.add(new ThermometerEntry(this).generate("h"));
        var anemometer = this.add(new AnemometerEntry(this).generate("i"));
        var weather_vane = this.add(new WeatherVaneEntry(this).generate("j"));

        var thatch_bed = this.add(new ThatchBedEntry(this).generate("k"));
        var wool_bed = this.add(new BedEntry(this).generate("l"));

        // add barrel (same as homesteading)
        // add quern (same as homesteading)
        // add mechanical bits
        // add composter (same as homesteading)
        // add crankshaft
        // add bellows (same as metalworking)
        // add minecarts
        // add lamp guide
        // add steel pump (might be being removed in the pack, buckets can move sources)
        // add scribing table
        // add sewing table
        // add stove (same as homesteading)
        // add climate station (maybe move this to greenhouse guide)
        // add compost tumbler
        // add mixing bowl (same as homesteading)
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
