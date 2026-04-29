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
import net.minecraft.resources.ResourceLocation;

public class CarpentryCategory extends CategoryProvider {

    public CarpentryCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "___h___",
                "___g__k",
                "___f__j",
                "d__e__i",
                "_______",
                "a__b__c"
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
        // add bookshelf
        // add door
        // add trapdoor
        // add pressure plate
        var barrel = this.add(new BarrelEntry(this).generate("e").withParent(lumber));
        var chest = this.add(new ChestEntry(this).generate("f"));
        var trapped_chest = this.add(new TrappedChestEntry(this).generate("g"));
        var tool_rack = this.add(new ToolRackEntry(this).generate("h"));

        // planks
        // add shelf
        // add workbench
        // add button
        // add mechanical bits (same entry as devices category)
        // add signs
        // add hanging signs
        // add sluice
        // add fence
        // add fence_gate
        var lectern = this.add(new LecternEntry(this).generate("i").withParent(planks));
        var sewing_table = this.add(new SewingTableEntry(this).generate("j"));
        var scribing_table = this.add(new ScribingTableEntry(this).generate("k"));
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
