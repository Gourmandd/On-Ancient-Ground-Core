package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.resources.ResourceLocation;

public class AgricultureCategory extends CategoryProvider {

    public AgricultureCategory(ModonomiconProviderBase parent) {
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
        // add composter (same as homesteading)
        // crop list
        // fruit tree list
        // bushes list
        // add animal husbandry
        // soil type list
        // fertiliser list
        // add hydration guide.
        // pet guide.
        // greenhouse guide.
        // greenhouse irrigation
        // greenhouse planters
        // greenhouse automation
    }

    @Override
    protected String categoryName() {
        return "Homesteading";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.JUTE_NET);
    }

    @Override
    public String categoryId() {
        return "homesteading";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
