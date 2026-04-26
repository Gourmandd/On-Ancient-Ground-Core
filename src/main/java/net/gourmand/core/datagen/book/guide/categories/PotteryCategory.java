package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.datagen.book.guide.entries.gettingStarted.*;
import net.gourmand.core.datagen.book.guide.entries.pottery.CrucibleEntry;
import net.gourmand.core.datagen.book.guide.entries.pottery.FireClayEntry;
import net.minecraft.resources.ResourceLocation;

public class PotteryCategory extends CategoryProvider {

    public static final String ID = "pottery";

    public PotteryCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "_____",
                "_____",
                "a_b_c",
                "_____",
                "_____"
        };
    }

    @Override
    protected void generateEntries() {
        var clay = this.add(new ClayEntry(this).generate("a"));

        var fire_clay = this.add(new FireClayEntry(this).generate("b"));

        var crucible = this.add(new CrucibleEntry(this).generate("c").withParent(fire_clay));
    }

    @Override
    protected String categoryName() {
        return "Pottery";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.VESSEL.asItem());
    }

    @Override
    public String categoryId() {
        return ID;
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
