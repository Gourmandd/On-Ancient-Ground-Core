package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookHeatingPageModel extends BookRecipePageModel<BookHeatingPageModel> {

    protected BookHeatingPageModel() {
        super(ModonomiconIntegration.HEATING_PAGE);
    }

    public static BookHeatingPageModel create() {
        return new BookHeatingPageModel();
    }
}
