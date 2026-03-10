package net.gourmand.core.datagen.book.guide.entries.ores;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.util.TextUtil;
import net.minecraft.world.item.Item;

import java.util.Locale;

public class OreEntry extends EntryProvider {

    public final String ID;
    public final BookIconModel ICON;

    public OreEntry(CategoryProviderBase parent, String id, Item icon) {
        super(parent);
        this.ID = id;
        this.ICON = BookIconModel.create(icon);
    }

    @Override
    protected void generatePages() {
        this.page("page1", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());

        this.pageText(getOreText(ID));
    }

    @Override
    protected String entryName() {
        return TextUtil.getName(ID);
    }

    @Override
    protected String entryDescription() {
        return "How to find: " + TextUtil.getName(ID);
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return ICON;
    }

    @Override
    protected String entryId() {
        return ID;
    }

    private String getOreText(String ore){
        switch (ore){
            case "native_copper": {
                return "**Native Copper** is an ore of **Copper** metal. It can be found in **Igneous Extrusive** rocks, at elevations above y=40.It can also be found in deposits in rivers, which can be panned.";
            }
            case "native_gold": {
                return "**Native Gold** is an ore of **Gold** metal. It can be found at elevations below y=70, but deeper veins are larger and richer. It can be found in **Igneous Extrusive** and **Igneous Intrusive** rocks.It can also be found in deposits in rivers, which can be panned.";
            }
            default: {
                //throw new AssertionError("Ore: " + ore + " has no page text");
            }
        }
        return "";
    }

    private enum OreInfo {

        NATIVE_COPPER("above y=40", "in **Igneous Extrusive** rocks", "of **Copper** metal", true),
        NATIVE_GOLD("below y=70, but deeper vein are larger and richer", "in **Igneous Extrusive** and **Igneous Instrusive** rocks", "of **Gold** metal", true),
        NATIVE_SILVER("above y=90 are smaller and poorer veins found in **Granite** and **Diorite**", "in **Granite**, **Diorite** and **Schist** as richer veins deep underground", "of **Silver** metal", true),
        TETRAHEDRITE("at any elevation, with deeper veins being richer", "in **Metamorphic** rocks", "of **Copper** metal", false),
        MALACHITE("at any elevation, with deeper veins being richer", "primarily in **Marble**, **Limestone**, **Dolomite** and **Chalk**", "of **Copper** metal", false),
        CASSITERITE("above y=80, making them more likely to be found in uplift regions or dikes", "in **Igneous Intrusive**", "of **Tin** metal", true),
        BISMUTHINITE("at most elevations, with deeper veins being richer", "in **Sedimentary** rocks on the surface and **Igneous Intrusive** rocks deeper underground", "of **Bismuth** metal", false),
        GARNIERITE("below y=0", "primarily in **Gabbro** as richer veins deep underground and as poorer veins in any **Igneous Intrusive** rock", "of **Nickel** metal", false),
        ;


        final String id;
        final String depth;
        final String location;
        final String description;
        final boolean panned;

        OreInfo(String depth, String location, String desc, boolean panned){
            this.id = this.name().toLowerCase(Locale.ROOT);
            this.depth = depth;
            this.location = location;
            this.description = desc;
            this.panned = panned;
        }

        public String getPage(){
            return this.location;
        }

        // Storing as string for simplicity, as my ores implement RegistryOre, while TFC ores are plain enums.
        public String getOreText(String ore){
            
            return this.depth;
        }
    }
}
