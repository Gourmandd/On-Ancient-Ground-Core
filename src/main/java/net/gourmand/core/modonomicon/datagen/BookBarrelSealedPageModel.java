package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookBarrelSealedPageModel extends BookRecipePageModel<BookBarrelSealedPageModel> {

    protected BookBarrelSealedPageModel() {
        super(ModonomiconIntegration.BARREL_SEALED_PAGE);
    }

    public static BookBarrelSealedPageModel create() {
        return new BookBarrelSealedPageModel();
    }
}
