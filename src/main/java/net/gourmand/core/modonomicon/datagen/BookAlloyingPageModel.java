package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookAlloyingPageModel extends BookRecipePageModel<BookAlloyingPageModel> {

    protected BookAlloyingPageModel() {
        super(ModonomiconIntegration.ALLOYING_PAGE);
    }

    public static BookAlloyingPageModel create() {
        return new BookAlloyingPageModel();
    }
}
