package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.resources.ResourceLocation;

public class GlassworkingCategory extends CategoryProvider {

    public GlassworkingCategory(ModonomiconProviderBase parent) {
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
        // add glassworking
        // add glass batches
        // add hand processes
        // add surface cast + panes
        // add basin pour + glass blocks
        // add lens
        // add jars
        // add spyglass
        // add leaded glass (once implemented)
        // add glass colouring guide
        // add tinted glass
        // add glass bottle
        // add reinforced glass
        // add wine bottle
        // add lanp glass
        // add wine glass
    }

    @Override
    protected String categoryName() {
        return "Glassworking";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.BLOWPIPE_WITH_GLASS);
    }

    @Override
    public String categoryId() {
        return "glassworking";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
