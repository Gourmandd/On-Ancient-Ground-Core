package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.metalworking.*;
import net.minecraft.resources.ResourceLocation;

public class MetalworkingCategory extends CategoryProvider {

    public MetalworkingCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "efghi",
                "_____",
                "c___d",
                "_____",
                "a___b"
        };
    }

    @Override
    protected void generateEntries() {

        var rock_anvil = this.add(new RockAnvilEntry(this).generate("c"));

        var double_ingot = this.add(new DoubleIngotEntry(this).generate("g").withParent(rock_anvil));

        var anvil = this.add(new AnvilEntry(this).generate("d").withParent(double_ingot));

        var welding = this.add(new WeldingEntry(this).generate("a").withParent(rock_anvil).withParent(anvil));
        var anvil_working = this.add(new AnvilWorkingEntry(this).generate("b").withParent(anvil));

        var rod = this.add(new RodEntry(this).generate("e"));
        var ingot = this.add(new IngotEntry(this).generate("f"));
        var sheet = this.add(new SheetEntry(this).generate("h"));
        var double_sheet = this.add(new DoubleSheetEntry(this).generate("i"));

        // add bloomery
        // add blast furnace
        // add charcoal pit
        // add charcoal forge
        // add fuel guide
        // add alloying guide.
        // add bars
        // add grates
        // add plated blocks
        // add steel making info (after blast furnace)
        // add iron making info (after bloomery)
        // add coloured steel making info
        // add chain
        // add armour trims
        // add bellows
        // add channel casting (maybe the one in pottery category has enough info?)
        // add heating/temperature guide
    }

    @Override
    protected String categoryName() {
        return "Masonry";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.BRONZE).get(Metal.BlockType.ANVIL));
    }

    @Override
    public String categoryId() {
        return "metalworking";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
