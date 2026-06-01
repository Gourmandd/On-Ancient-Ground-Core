package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookBarrelFluidInstantPageModel extends BookRecipePageModel<BookBarrelFluidInstantPageModel> {

    protected BookBarrelFluidInstantPageModel() {
        super(ModonomiconIntegration.BARREL_FLUID_INSTANT_PAGE);
    }

    public static BookBarrelFluidInstantPageModel create() {
        return new BookBarrelFluidInstantPageModel();
    }
}
