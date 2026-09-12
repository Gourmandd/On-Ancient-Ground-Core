package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.metalworking.*;
import net.gourmand.core.datagen.book.guide.entries.pottery.CrucibleEntry;
import net.gourmand.core.datagen.book.guide.entries.pottery.MoldTableEntry;
import net.minecraft.resources.ResourceLocation;

public class MetalworkingCategory extends CategoryProvider {

    public MetalworkingCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "____uvAwz___________",
                "___________________",
                "____efghi__o_s_t____",
                "___________________",
                "p_l_c___d__n_j_k_x_y",
                "___________________",
                "__m_a___b__B_q_r____",
        };
    }

    @Override
    protected void generateEntries() {

        var rock_anvil = this.add(new RockAnvilEntry(this).generate("c"));

        var double_ingot = this.add(new DoubleIngotEntry(this).generate("g").withParent(rock_anvil));

        var anvil = this.add(new AnvilEntry(this).generate("d").withParent(double_ingot));

        var anvil_working = this.add(new AnvilWorkingEntry(this).generate("b").withParent(anvil));
        var welding = this.add(new WeldingEntry(this).generate("a").withParent(rock_anvil).withParent(anvil));

        var rod = this.add(new RodEntry(this).generate("e"));
        var ingot = this.add(new IngotEntry(this).generate("f"));
        var sheet = this.add(new SheetEntry(this).generate("h"));
        var double_sheet = this.add(new DoubleSheetEntry(this).generate("i"));

        var heating = this.add(new HeatingEntry(this).generate("p"));
        var charcoal_pit = this.add(new CharcoalPitEntry(this).generate("l").withParent(heating));
        var charcoal_forge = this.add(new CharcoalForgeEntry(this).generate("m").withParent(charcoal_pit));

        var alloying = this.add(new AlloyingEntry(this).generate("n"));


        var bronzeMaking = this.add(new BronzeMakingEntry(this).generate("B").withParent(alloying));
        var bloomery = this.add(new BloomeryEntry(this).generate("j"));
        var ironMaking = this.add(new IronMakingEntry(this).generate("q").withParent(bloomery));
        var blast_furnace = this.add(new BlastFurnaceEntry(this).generate("k"));
        var steelMaking = this.add(new SteelMakingEntry(this).generate("r").withParent(blast_furnace));

        var crucible = this.add(new CrucibleEntry(this).generate("s"));
        var channelCasting = this.add(new MoldTableEntry(this).generate("t").withParent(crucible));
        var bellows = this.add(new BellowsEntry(this).generate("o"));

        var bars = this.add(new BarsEntry(this).generate("u"));
        var grate = this.add(new GrateEntry(this).generate("v"));
        var blocks = this.add(new PlatedBlocksEntry(this).generate("w"));

        var blackSteelMaking = this.add(new BlackSteelMakingEntry(this).generate("x"));
        var colouredSteelMaking = this.add(new ColouredSteelMakingEntry(this).generate("y"));

        var chains = this.add(new ChainEntry(this).generate("z"));

        var armor_trim = this.add(new ArmorTrimsEntry(this).generate("A"));
    }

    @Override
    protected String categoryName() {
        return "Metalworking";
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
