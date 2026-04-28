package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookWeldingPageModel extends BookRecipePageModel<BookWeldingPageModel> {

    protected BookWeldingPageModel() {
        super(ModonomiconIntegration.WELDING_PAGE);
    }

    public static BookWeldingPageModel create() {
        return new BookWeldingPageModel();
    }
}
