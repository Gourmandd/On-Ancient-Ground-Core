package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.datagen.book.guide.entries.devices.*;
import net.gourmand.core.datagen.book.guide.entries.metalworking.BellowsEntry;
import net.gourmand.core.datagen.book.guide.entries.metalworking.BlastFurnaceEntry;
import net.gourmand.core.datagen.book.guide.entries.metalworking.BloomeryEntry;
import net.gourmand.core.datagen.book.guide.entries.pottery.CrucibleEntry;
import net.gourmand.core.datagen.book.guide.entries.pottery.KilnEntry;
import net.minecraft.resources.ResourceLocation;

public class DevicesCategory extends CategoryProvider {

    public DevicesCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            "a_c_e_k_gh",
            "b_d_f_l_ij",
            "__________",
            "mp_n_o_v_s",
            "_________t",
            "qr_______u"
        };
    }

    @Override
    protected void generateEntries() {

        // have mechanical automation in entries as needed.

        var bloomery = this.add(new BloomeryEntry(this).generate("a"));
        var blast_furnace = this.add(new BlastFurnaceEntry(this).generate("b"));

        var tnt = this.add(new TntEntry(this).generate("c"));
        var powder_keg = this.add(new PowderKegEntry(this).generate("d"));
        var jukebox = this.add(new JukeboxEntry(this).generate("e"));
        var note_block = this.add(new NoteBlockEntry(this).generate("f"));

        var clock = this.add(new PrecisionClockEntry(this).generate("g"));
        var thermometer = this.add(new ThermometerEntry(this).generate("h"));
        var anemometer = this.add(new AnemometerEntry(this).generate("i"));
        var weather_vane = this.add(new WeatherVaneEntry(this).generate("j"));

        var thatch_bed = this.add(new ThatchBedEntry(this).generate("k"));
        var wool_bed = this.add(new BedEntry(this).generate("l"));

        var barrel = this.add(new BarrelEntry(this).generate("m"));
        var quern = this.add(new QuernEntry(this).generate("n"));
        var composter = this.add(new ComposterEntry(this).generate("o"));

        var keg = this.add(new KegEntry(this).generate("p").withParent(barrel));
        var stomping_barrel = this.add(new StompingBarrelEntry(this).generate("q"));
        var wine_shelf = this.add(new WineShelfEntry(this).generate("r"));

        var bellow = this.add(new BellowsEntry(this).generate("s"));
        var crucible = this.add(new CrucibleEntry(this).generate("t"));
        var firebox = this.add(new KilnEntry(this).generate("u"));

        var mixing_bowl = this.add(new MixingBowlEntry(this).generate("v"));
        // add mechanical bits
        // add crankshaft
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
