package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookOvenPageModel extends BookRecipePageModel<BookOvenPageModel>
{

    protected BookOvenPageModel()
    {
        super(ModonomiconIntegration.OVEN_PAGE);
    }

    public static BookOvenPageModel create()
    {
        return new BookOvenPageModel();
    }
}
