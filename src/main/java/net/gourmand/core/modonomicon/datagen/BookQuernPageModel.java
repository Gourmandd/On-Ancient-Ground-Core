package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookQuernPageModel extends BookRecipePageModel<BookQuernPageModel> {

    protected BookQuernPageModel() {
        super(ModonomiconIntegration.QUERN_PAGE);
    }

    public static BookQuernPageModel create() {
        return new BookQuernPageModel();
    }
}
