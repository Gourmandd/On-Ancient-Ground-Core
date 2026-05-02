package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.book.guide.entries.equipment.*;
import net.gourmand.core.datagen.book.guide.entries.mining.CeramicPanEntry;
import net.gourmand.core.datagen.book.guide.entries.mining.ExcavatingToolsEntry;
import net.gourmand.core.datagen.book.guide.entries.mining.ProspectingEntry;
import net.minecraft.resources.ResourceLocation;

public class EquipmentCategory extends CategoryProvider {

    public EquipmentCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "_____v_w_____",
                "_____________",
                "ri__tbujl__do",
                "sn_________cp",
                "gh__ae_fm__kq"
        };
    }

    @Override
    protected void generateEntries() {

        // TODO: add buckets, shields, armour, sandpaper, horse armour, bone needle and spindle
        var axe = this.add(new AxeEntry(this).generate("a"));
        var chisel = this.add(new ChiselEntry(this).generate("b"));
        var firestarters = this.add(new FirestartersEntry(this).generate("c"));
        var glassblowing = this.add(new GlassblowingToolsEntry(this).generate("d"));
        var hammer = this.add(new HammerEntry(this).generate("e"));
        var hoe = this.add(new HoeEntry(this).generate("f"));
        var javelin = this.add(new JavelinEntry(this).generate("g"));
        var knife = this.add(new KnifeEntry(this).generate("h"));
        var mace = this.add(new MaceEntry(this).generate("i"));
        var pickaxe = this.add(new PickaxeEntry(this).generate("j"));
        var saddle = this.add(new SaddleEntry(this).generate("k"));
        var saw = this.add(new SawEntry(this).generate("l"));
        var shovel = this.add(new ShovelEntry(this).generate("m"));
        var sword = this.add(new SwordEntry(this).generate("n"));
        var scythe = this.add(new ScytheEntry(this).generate("t"));
        var shears = this.add(new ShearsEntry(this).generate("u"));

        var ceramic_pans = this.add(new CeramicPanEntry(this).generate("o"));
        var excavating_tools = this.add(new ExcavatingToolsEntry(this).generate("p"));
        var prospecting_tools = this.add(new ProspectingEntry(this).generate("q"));

        var bow = this.add(new BowEntry(this).generate("r"));
        var crossbow = this.add(new CrossbowEntry(this).generate("s"));

        var tool_tiers = this.add(new ToolTiersEntry(this).generate("v"));
        var damage_types = this.add(new DamageTypesEntry(this).generate("w"));
    }

    @Override
    protected String categoryName() {
        return "Equipment";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.SWORD));
    }

    @Override
    public String categoryId() {
        return "equipment";
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
