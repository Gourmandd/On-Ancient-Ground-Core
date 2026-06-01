package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookBarrelInstantPageModel extends BookRecipePageModel<BookBarrelInstantPageModel> {

    protected BookBarrelInstantPageModel() {
        super(ModonomiconIntegration.BARREL_INSTANT_PAGE);
    }

    public static BookBarrelInstantPageModel create() {
        return new BookBarrelInstantPageModel();
    }
}
