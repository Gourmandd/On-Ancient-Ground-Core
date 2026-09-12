package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookKnappingPageModel extends BookRecipePageModel<BookKnappingPageModel> {

    protected BookKnappingPageModel() {
        super(ModonomiconIntegration.KNAPPING_PAGE);
    }

    public static BookKnappingPageModel create() {
        return new BookKnappingPageModel();
    }
}
