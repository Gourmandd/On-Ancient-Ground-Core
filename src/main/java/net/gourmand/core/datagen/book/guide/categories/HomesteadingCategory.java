package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.datagen.book.guide.entries.homesteading.*;
import net.gourmand.core.datagen.book.guide.entries.homesteading.traits.*;
import net.minecraft.resources.ResourceLocation;

public class HomesteadingCategory extends CategoryProvider {

    public HomesteadingCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
            "_abc_g",
            "_def_h",
            "_____i",
            "______",
            "______",
            "______",
            "12345_",
            "67890_",
            "______",
            "!£$%^_",
            "_&*(__"
        };
    }

    @Override
    protected void generateEntries() {

        var barrel = this.add(new BarrelEntry(this).generate("a"));
        var quern = this.add(new QuernEntry(this).generate("b"));
        var composter = this.add(new ComposterEntry(this).generate("c"));

        var keg = this.add(new KegEntry(this).generate("d").withParent(barrel));
        var stomping_barrel = this.add(new StompingBarrelEntry(this).generate("e"));
        var wine_shelf = this.add(new WineShelfEntry(this).generate("f"));

        var cellar = this.add(new CellarEntry(this).generate("g"));
        var food_shelf = this.add(new FoodShelfEntry(this).generate("h"));
        var hanger = this.add(new HangerEntry(this).generate("i"));

        // add winemaking
        // add barrel press

        var salted_trait = this.add(new SaltedTraitEntry(this).generate("1"));
        var brined_trait = this.add(new BrinedTraitEntry(this).generate("2"));
        var pickled_trait = this.add(new PickledTraitEntry(this).generate("3"));
        var preserved_trait = this.add(new PreservedTraitEntry(this).generate("4"));
        var preserved_in_vinegar_trait = this.add(new PreservedInVinegarTraitEntry(this).generate("5"));
        var charcoal_grilled_trait = this.add(new CharcoalGrilledTraitEntry(this).generate("6"));
        var wood_grilled_trait = this.add(new WoodGrilledTraitEntry(this).generate("7"));
        var burnt_to_a_crisp_trait = this.add(new BurntToACrispTraitEntry(this).generate("8"));
        var wild_trait = this.add(new WildTraitEntry(this).generate("9"));
        var canned_trait = this.add(new CannedTraitEntry(this).generate("0"));
        var aging_traits = this.add(new AgingTraitsEntry(this).generate("!"));
        var dried_trait = this.add(new DriedTraitEntry(this).generate("£"));
        var fermented_trait = this.add(new FermentedTraitEntry(this).generate("$"));
        var grape_traits = this.add(new GrapeTraitEntry(this).generate("%"));
        var hung_trait = this.add(new HungTraitEntry(this).generate("^"));
        var oven_baked_trait = this.add(new OvenBakedTraitEntry(this).generate("&"));
        var shelved_traits = this.add(new ShelvedTraitEntry(this).generate("*"));
        var smoked_trait = this.add(new SmokedTraitEntry(this).generate("("));

        // add cheesemaking
        // jarring guide
        // add drying guide.
        // add smoking guide.

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
