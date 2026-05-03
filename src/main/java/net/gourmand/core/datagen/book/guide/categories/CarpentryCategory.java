package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.carpentry.*;
import net.gourmand.core.datagen.book.guide.entries.equipment.*;
import net.gourmand.core.datagen.book.guide.entries.mining.SluiceEntry;
import net.gourmand.core.datagen.book.guide.entries.mining.SupportEntry;
import net.minecraft.resources.ResourceLocation;

public class CarpentryCategory extends CategoryProvider {

    public CarpentryCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "_________",
                "__ngo_ksv",
                "__mfh_jru",
                "d_lep_iqt",
                "_________",
                "a__b___c_",
                "_________",
                "______xw_"
        };
    }

    @Override
    protected void generateEntries() {
        // add misc
        // vanilla barrel
        // powder keg (same entry as devices category)
        // barrel rack
        // bed
        // ladder
        // composter (same entry as devices & homesteading category)
        // item frame (same entry as devices category)
        // cartography table (same entry as devices category)
        // banner loom (same entry as devices category)
        // note block (same entry as devices category)
        // jukebox (same entry as devices category)

        var saw = this.add(new SawEntry(this).generate("a"));

        var lumber = this.add(new LumberEntry(this).generate("b").withParent(saw));

        var planks = this.add(new PlanksEntry(this).generate("c").withParent(lumber));

        // logs
        var support = this.add(new SupportEntry(this).generate("d").withParent(saw));

        // lumber
        var barrel = this.add(new BarrelEntry(this).generate("e").withParent(lumber));
        var chest = this.add(new ChestEntry(this).generate("f"));
        var trapped_chest = this.add(new TrappedChestEntry(this).generate("g"));
        var tool_rack = this.add(new ToolRackEntry(this).generate("h"));
        var bookshelf = this.add(new BookshelfEntry(this).generate("l"));
        var door = this.add(new DoorEntry(this).generate("m"));
        var trapdoor = this.add(new TrapdoorEntry(this).generate("n"));
        var pressure_plate = this.add(new PressurePlateEntry(this).generate("o"));
        var log_fence = this.add(new LogFenceEntry(this).generate("p"));

        // planks
        // add mechanical bits (same entry as devices category)
        // add food shelf (same as in homesteading category)
        // add hanger (same as in homesteading category)
        // add jarbnet
        // add keg (also mention it can do barrel recipes, I think it might be able, not mentioned in firmalife guide?)
        // add stomping barrel (same as in homesteading category)
        // add wine shelf (same as in homesteading category)
        // add barrel press (same as in homesteading category)

        var workbench = this.add(new WorkbenchEntry(this).generate("q").withParent(planks));
        var lectern = this.add(new LecternEntry(this).generate("i"));
        var sewing_table = this.add(new SewingTableEntry(this).generate("j"));
        var scribing_table = this.add(new ScribingTableEntry(this).generate("k"));
        var sign = this.add(new SignEntry(this).generate("r"));
        var hanging_sign = this.add(new HangingSignEntry(this).generate("s"));
        var fence = this.add(new FenceEntry(this).generate("t"));
        var fence_gate = this.add(new FenceGateEntry(this).generate("u"));
        var button = this.add(new ButtonEntry(this).generate("v"));

        var shelf = this.add(new ShelfEntry(this).generate("w").withParent(planks));
        var sluice = this.add(new SluiceEntry(this).generate("x"));
    }

    @Override
    protected String categoryName() {
        return "Carpentry";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.SAW));
    }

    @Override
    public String categoryId() {
        return "carpentry";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
