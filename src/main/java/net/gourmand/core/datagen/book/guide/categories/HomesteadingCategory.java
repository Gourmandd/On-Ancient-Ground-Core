package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.resources.ResourceLocation;

public class HomesteadingCategory extends CategoryProvider {

    public HomesteadingCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            ""
        };
    }

    @Override
    protected void generateEntries() {
        // add barrel
        // add quern

        // add keg
        // add stomping barrel
        // add wine shelf
        // add winemaking
        // add barrel press

        // add composter

        // add food shelf
        // add hanger
        // add cellar guide

        // add cheesemaking
        // jarring guide
        // preservation methods list. Vessel, cooking, salting, pickled, brining, cellar + bad ones.
        // add drying guide.

        // fishing guide (here or in agriculture category?)
        // add olive oil making
        // leather making guide.
        // light sources guide
        // add papermaking
        // add scraping guide

        // add breadmaking (flatbread + bread)
        // add oven
        // add appliances for oven

        // sandwich making.
        // salad making
        // soup making
        // firmalife spices guide
        // add mixing bowl
        // chocolate making guide
        // pie making guide
        // pizza making guide

        // add pasta and burritos (I don't know, it's not a dynamic food, so a bit too specific)
    }

    @Override
    protected String categoryName() {
        return "Homesteading";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.JUTE_NET);
    }

    @Override
    public String categoryId() {
        return "homesteading";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
