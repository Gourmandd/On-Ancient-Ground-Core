package net.gourmand.core.datagen.book.guide;

import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.book.guide.categories.*;

import java.util.function.BiConsumer;

public class GuideBook extends SingleBookSubProvider {

    public static final String ID = "guide";

    public GuideBook(BiConsumer<String, String> defaultLang) {
        super(ID, AncientGroundCore.MODID, defaultLang);
    }

    @Override
    protected void registerDefaultMacros() {

    }

    @Override
    protected void generateCategories() {
        var gettingStartedCategory = this.add(new GettingStartedCategory(this).generate());
        var equipmentCategory = this.add(new EquipmentCategory(this).generate());
        var potteryCategory = this.add(new PotteryCategory(this).generate());
        var oresCategory = this.add(new OresCategory(this).generate());
        var geologyCategory = this.add(new GeologyCategory(this).generate());
    }

    @Override
    protected String bookName() {
        return "Guide To This World";
    }

    @Override
    protected String bookTooltip() {
        return "Helping you, one page at a time!";
    }

    @Override
    public String bookId() {
        return super.bookId();
    }
}
