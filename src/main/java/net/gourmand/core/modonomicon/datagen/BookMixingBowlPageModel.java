package net.gourmand.core.modonomicon.datagen;

import com.klikli_dev.modonomicon.api.datagen.book.page.BookRecipePageModel;
import net.gourmand.core.modonomicon.ModonomiconIntegration;

public class BookMixingBowlPageModel extends BookRecipePageModel<BookMixingBowlPageModel> {

    protected BookMixingBowlPageModel() {
        super(ModonomiconIntegration.MIXING_BOWL_PAGE);
    }

    public static BookMixingBowlPageModel create() {
        return new BookMixingBowlPageModel();
    }
}
