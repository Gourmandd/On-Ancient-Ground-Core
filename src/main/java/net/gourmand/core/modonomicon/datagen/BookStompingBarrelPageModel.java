package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookStompingBarrelPageModel extends BookRecipePageModel<BookStompingBarrelPageModel> {

    protected BookStompingBarrelPageModel() {
        super(ModonomiconIntegration.STOMPING_BARREL_PAGE);
    }

    public static BookStompingBarrelPageModel create() {
        return new BookStompingBarrelPageModel();
    }
}
