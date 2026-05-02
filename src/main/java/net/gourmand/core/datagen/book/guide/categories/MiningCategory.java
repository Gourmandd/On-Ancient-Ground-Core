package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.mining.CeramicPanEntry;
import net.gourmand.core.datagen.book.guide.entries.mining.SluiceEntry;
import net.gourmand.core.datagen.book.guide.entries.mining.SupportEntry;
import net.minecraft.resources.ResourceLocation;

public class MiningCategory extends CategoryProvider {

    public MiningCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            "a_b_c"
        };
    }

    @Override
    protected void generateEntries() {

        var sluice = this.add(new SluiceEntry(this).generate("a"));
        var pan = this.add(new CeramicPanEntry(this).generate("b"));
        var support = this.add(new SupportEntry(this).generate("c"));
        // add prospecting guide
        // add surface ore guide
        // add river ore deposit guide
        // add collapse guide
        // add supports guide
        // add excavators
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
