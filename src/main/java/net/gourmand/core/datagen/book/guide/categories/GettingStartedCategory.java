package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.datagen.book.guide.entries.gettingStarted.*;
import net.minecraft.resources.ResourceLocation;

public class GettingStartedCategory extends CategoryProvider {

    public static final String ID = "getting_started";

    public GettingStartedCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "k___h_j",
                "_______",
                "__c____",
                "___b___",
                "a___d_g",
                "___e___",
                "__f____"
        };
    }

    @Override
    protected void generateEntries() {
        // about modpack

        // getting started
        var introduction = this.add(new IntroductionEntry(this).generate("a"));

        // tools
        var axe = this.add(new AxeEntry(this).generate("b").withParent(introduction));

        var shovel = this.add(new ShovelEntry(this).generate("c").withParent(introduction));

        var knife = this.add(new KnifeEntry(this).generate("d").withParent(introduction));

        var javelin = this.add(new JavelinEntry(this).generate("e").withParent(introduction));

        var hoe = this.add(new HoeEntry(this).generate("f").withParent(introduction));

        var straw = this.add(new StrawEntry(this).generate("g").withParent(knife));

        var clay = this.add(new ClayEntry(this).generate("h").withParent(shovel));

        var pitkiln = this.add(new PitKilnEntry(this).generate("j")
                .withParent(straw)
                .withParent(clay)
        );

        var fire = this.add(new FireEntry(this).generate("k").withParent(shovel));
    }

    @Override
    protected String categoryName() {
        return "Getting Started";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.ROCK_TOOLS.get(RockCategory.METAMORPHIC).get(RockCategory.ItemType.AXE));
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
