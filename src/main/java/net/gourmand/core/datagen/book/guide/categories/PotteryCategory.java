package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.datagen.book.guide.entries.gettingStarted.*;
import net.gourmand.core.datagen.book.guide.entries.mining.CeramicPanEntry;
import net.gourmand.core.datagen.book.guide.entries.pottery.*;
import net.minecraft.resources.ResourceLocation;

public class PotteryCategory extends CategoryProvider {

    public static final String ID = "pottery";

    public PotteryCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "__g_h___c____",
                "__i_l_______d",
                "j_k_q__a_b__e",
                "__n_m_______f",
                "__o_p________"
        };
    }

    @Override
    protected void generateEntries() {

        // add pit kiln guide
        // add kiln (firebox) guide.

        var clay = this.add(new ClayEntry(this).generate("a"));

        var fire_clay = this.add(new FireClayEntry(this).generate("b"));

        // both
        var ingot_mold = this.add(new IngotMoldEntry(this).generate("c").withParent(fire_clay).withParent(clay));

        // fire clay
        var crucible = this.add(new CrucibleEntry(this).generate("d"));

        var fire_bricks = this.add(new FireBricksEntry(this).generate("e").withParent(fire_clay));

        var mold_table = this.add(new MoldTableEntry(this).generate("f"));

        // clay
        var blowpipe = this.add(new BlowpipeEntry(this).generate("g"));

        var bowl = this.add(new BowlEntry(this).generate("h"));

        var bricks = this.add(new BricksEntry(this).generate("i"));

        var cooking_pot = this.add(new CookingPotEntry(this).generate("j"));

        var flower_pot = this.add(new FlowerPotEntry(this).generate("k"));

        var jug = this.add(new JugEntry(this).generate("l"));

        var large_vessel = this.add(new LargeVesselEntry(this).generate("m"));

        var mold = this.add(new MoldEntry(this).generate("n"));

        var pan = this.add(new CeramicPanEntry(this).generate("o"));

        var spindle = this.add(new SpindleEntry(this).generate("p"));

        var vessel = this.add(new VesselEntry(this).generate("q").withParent(clay));
    }

    @Override
    protected String categoryName() {
        return "Pottery";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.VESSEL.asItem());
    }

    @Override
    public String categoryId() {
        return ID;
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
