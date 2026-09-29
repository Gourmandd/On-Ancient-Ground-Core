package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookCookingPageModel extends BookRecipePageModel<BookCookingPageModel>
{

    protected BookCookingPageModel()
    {
        super(ModonomiconIntegration.COOKING_POT_PAGE);
    }

    public static BookCookingPageModel create()
    {
        return new BookCookingPageModel();
    }
}
