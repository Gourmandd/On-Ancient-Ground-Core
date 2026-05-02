package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.mining.*;
import net.minecraft.resources.ResourceLocation;

public class MiningCategory extends CategoryProvider {

    public MiningCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "b_c_g_",
                "______",
                "_a__d_",
                "______",
                "_e__f_"
        };
    }

    @Override
    protected void generateEntries() {

        // maybe could add powder keg entry here as well, to explain blast mining.

        var ore_deposit = this.add(new OreDepositEntry(this).generate("a"));
        var sluice = this.add(new SluiceEntry(this).generate("b").withParent(ore_deposit));
        var pan = this.add(new CeramicPanEntry(this).generate("c").withParent(ore_deposit));

        var support = this.add(new SupportEntry(this).generate("d"));

        var ore_indicators = this.add(new SurfaceOreEntry(this).generate("e"));
        var prospecting = this.add(new ProspectingEntry(this).generate("f").withParent(ore_indicators));
        var excavating_tools = this.add(new ExcavatingToolsEntry(this).generate("g").withParent(support));
        //
    }

    @Override
    protected String categoryName() {
        return "Mining";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.PICKAXE));
    }

    @Override
    public String categoryId() {
        return "mining";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
