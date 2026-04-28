package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookForgingPageModel extends BookRecipePageModel<BookForgingPageModel> {

    protected BookForgingPageModel() {
        super(ModonomiconIntegration.FORGING_PAGE);
    }

    public static BookForgingPageModel create() {
        return new BookForgingPageModel();
    }
}
