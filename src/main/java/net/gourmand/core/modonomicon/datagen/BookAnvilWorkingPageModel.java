package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookAnvilWorkingPageModel extends BookRecipePageModel<BookAnvilWorkingPageModel> {

    protected BookAnvilWorkingPageModel() {
        super(ModonomiconIntegration.ANVIL_WORKING_PAGE);
    }

    public static BookAnvilWorkingPageModel create() {
        return new BookAnvilWorkingPageModel();
    }
}
