package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookCastingPageModel extends BookRecipePageModel<BookCastingPageModel> {

    protected BookCastingPageModel() {
        super(ModonomiconIntegration.CASTING_PAGE);
    }

    public static BookCastingPageModel create() {
        return new BookCastingPageModel();
    }
}
