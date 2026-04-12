package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.book.guide.entries.ores.OreEntry;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class OresCategory extends CategoryProvider {

    public static final String ID = "ores";

    public OresCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "w__a__m__dei",
                "x__b__k__fgh",
                "y__c__l___j_",
                "____________",
                "___nopqrstuv",
                "z___________",
                "A__GCDEFBHIJ",
        };
    }

    @Override
    protected void generateEntries() {
        // copper
        var nativeCopperEntry = createOreEntry(
                "native_copper",
                TFCItems.GRADED_ORES.get(Ore.NATIVE_COPPER).get(Ore.Grade.RICH).get(),
                "a",
                """
                Native Copper is an ore of **Copper** metal. \s
                \\
                It can be found in **Igneous Extrusive** rocks, at elevations above y=40.\s
                \\
                It can also be found in deposits in **Rivers**, which can be **Panned**. \s
                """
        );

        var malachiteEntry = createOreEntry(
                "malachite",
                TFCItems.GRADED_ORES.get(Ore.MALACHITE).get(Ore.Grade.RICH).get(),
                "b",
                """
                Malachite is an ore of **Copper** metal.\s
                \\
                It can be found primarily in *Marble* or **Limestone**, **Chalk**, and **Dolomite**. \s
                It can be found at most elevations, however deeper veins are often larger and richer. \s
                """
        );

        var tetrahedriteEntry = createOreEntry(
                "tetrahedrite",
                TFCItems.GRADED_ORES.get(Ore.TETRAHEDRITE).get(Ore.Grade.RICH).get(),
                "c",
                """
                Tetrahedrite is an ore of **Copper** metal. \s
                \\
                It can be found at any elevation, but deeper veins are often richer. \s
                It can be found in **Metamorphic** rocks. \s
                """
        );

        // misc metals

        var nativeGoldEntry = createOreEntry(
                "native_gold",
                TFCItems.GRADED_ORES.get(Ore.NATIVE_GOLD).get(Ore.Grade.RICH).get(),
                "d",
                """
                Native Gold is an ore of **Gold** metal. \s
                \\
                It can be found at elevations below y=70, but deeper veins are larger and richer. \s
                It can be found in **Igneous Extrusive** and **Igneous Intrusive** rocks. \s
                \\
                It can also be found in deposits in **Rivers**, which can be **Panned**. \s
                """
        );

        var nativeSilverEntry = createOreEntry(
                "native_silver",
                TFCItems.GRADED_ORES.get(Ore.NATIVE_SILVER).get(Ore.Grade.RICH).get(),
                "e",
                """
                Native Silver is an ore of **Silver** metal.\s
                \\
                Small poor veins can be found in **Granite** or **Diorite** in uplift regions, above y=90. \s
                Larger and richer veins can be found in **Granite**, **Diorite**, **Schist**, **Peridotite** and **Gneiss** deep underground below y=20. \s
                \\
                It can also be found in deposits in **Rivers**, which can be **Panned**. \s
                """
        );

        var cassiteriteEntry = createOreEntry(
                "cassiterite", TFCItems.GRADED_ORES.get(Ore.CASSITERITE).get(Ore.Grade.RICH).get(),
                "f",
                """
                Cassiterite is an ore of **Tin** metal.\s
                \\
                It can be found in **Igneous Intrusive** rocks at high elevation, above y=80 in uplift regions or in dikes.
                \\
                It can also be found in deposits in **Rivers**, which can be **Panned**.
               """
        );

        var bismuthiniteEntry = createOreEntry(
                "bismuthinite",
                TFCItems.GRADED_ORES.get(Ore.BISMUTHINITE).get(Ore.Grade.RICH).get(),
                "g",
                """
                Bismuthinite is an ore of **Bismuth** metal. \s
                \\
                It can be found in **Sedimentary** rocks near the surface, or larger and richer veins in **Igneous Intrusive** rocks deep underground.
                """
        );

        var garnieriteEntry = createOreEntry(
                "garnierite",
                TFCItems.GRADED_ORES.get(Ore.GARNIERITE).get(Ore.Grade.RICH).get(),
                "h",
                """
                Garnierite is an ore of **Nickel** metal. \s
                \\
                It can be found at elevations below y=0.\s
                It can be found primarily in **Gabbro** deep underground.\s
                Smaller, rarer veins can also be found in any **Igneous Intrusive** rock.\s
                """
        );

        var galenaEntry = createOreEntry(
                "galena",
                CoreItems.GRADED_ORES.get(CoreOres.GALENA).get(CoreOres.Grade.RICH).get(),
                "i",
                """
                Galena is an ore of **Lead** metal. It can be found primarily in **Igneous Intrusive** rocks
                \\
                It can be found in smaller amounts in **Igneous Extrusive** rocks, and as well as in **Limestone**, **Dolomite** and **Blueschist**.
                """
        );

        var sphaleriteEntry = createOreEntry(
                "sphalerite",
                TFCItems.GRADED_ORES.get(Ore.SPHALERITE).get(Ore.Grade.RICH).get(),
                "j",
                """
                Sphalerite is an ore of **Zinc** metal. \s
                \\
                Small, poor veins can be found in **Igneous Extrusive** rocks near the surface, and large richer veins can be found in **Igneous Intrusive** rocks deep underground.
                """
        );

        // iron
        var magnetiteEntry = createOreEntry(
                "magnetite",
                TFCItems.GRADED_ORES.get(Ore.MAGNETITE).get(Ore.Grade.RICH).get(),
                "k",
                """
                Magnetite is an ore of **Iron** metal. \s
                \\
                It can be found in large veins in any **Sedimentary** rocks near the surface.
                """
        );

        var limoniteEntry = createOreEntry(
                "limonite",
                TFCItems.GRADED_ORES.get(Ore.LIMONITE).get(Ore.Grade.RICH).get(),
                "l",
                """
                Limonite is an ore of **Iron** metal. \s
                \\
                It can be found in large veins in any **Sedimentary** rocks near the surface.
                """
        );

        var hematiteEntry = createOreEntry(
                "hematite",
                TFCItems.GRADED_ORES.get(Ore.HEMATITE).get(Ore.Grade.RICH).get(),
                "m",
                """
                Hematite is an ore of **Iron** metal. \s
                \\
                It can be found in large veins in any **Igneous Extrusive** rocks near the surface.
                """
        );

        // gems
        var amethystEntry = createOreEntry(
                "amethyst",
                TFCItems.GEMS.get(Ore.AMETHYST).get(),
                "n",
                """
                Amethyst is a decorative **Mineral** which can be used to make *Glass*. \s
                \\
                It can be found in **Sedimentary** and **Metamorphic** rock beneath rivers above y=40.
                """
        );

        var diamondEntry = createOreEntry(
                "diamond",
                TFCItems.GEMS.get(Ore.DIAMOND).get(),
                "o",
                """
                Kimberlite is a decorative and priceless **Gemstone**. \s
                \\
                It appears in thin vertical ore formations called **Kimberlite Pipes** found in **Gabbro** which can be up to a hundred blocks tall.
                It can be rarely found in hot springs.
                """
        );

        var emeraldEntry = createOreEntry(
                "emerald",
                TFCItems.GEMS.get(Ore.EMERALD).get(),
                "p",
                """
                Emerald is a decorative **Gemstone**. \s
                \\
                It looks quite pretty, maybe if you could find someone else in this incredibly lonely world you could trade it with them...
                It appears in thin vertical ore formations which can be up to a hundred blocks tall. It can be found in **Igneous Intrusive** rocks.
                It can be rarely found in hot springs.
                """
        );

        var lapisLazuliEntry = createOreEntry(
                "lapis_lazuli",
                TFCItems.GEMS.get(Ore.LAPIS_LAZULI).get(),
                "q",
                """
                Lapis Lazuli is a decorative **Mineral** which can be used to make **Blue Dye**. \s
                \\
                It can be found in large, but sparse veins in **Limestone**, **Marble**, **Arkose**, **Sandstone** and **Red Sandstone** between y=-20 and y=80.
                """
        );

        var opalEntry = createOreEntry(
                "opal",
                TFCItems.GEMS.get(Ore.OPAL).get(),
                "r",
                """
                Opal is a decorative **Gemstone**. \s
                \\
                It can be found in **Sedimentary** and **Igneous Extrusive** rock beneath rivers above y=40.
                """
        );

        var pyriteEntry = createOreEntry(
                "pyrite",
                TFCItems.GEMS.get(Ore.PYRITE).get(),
                "s",
                """
                Topaz is a decorative **Mineral**. \s
                \\
                It can be found in **Igneous Extrusive** and **Igneous Intrusive** rocks.
                """
        );

        var rubyEntry = createOreEntry(
                "ruby",
                TFCItems.GEMS.get(Ore.RUBY).get(),
                "t",
                """
                Ruby is a decorative **Gemstone**. \s
                \\
                It can be found in **Schist** and **Gneiss** rock below y=-10.
                """
        );

        var sapphireEntry = createOreEntry(
                "sapphire",
                TFCItems.GEMS.get(Ore.SAPPHIRE).get(),
                "u",
                """
                Sapphire is a decorative **Gemstone**. \s
                \\
                It can be found in **Hot Springs** in **Metamorphic**, **Igneous Intrusive** and **Igneous Extrusive** Rocks.
                \s"""
        );

        var topazEntry = createOreEntry(
                "topaz",
                TFCItems.GEMS.get(Ore.TOPAZ).get(),
                "v",
                """
                Topaz is a decorative **Mineral**. \s
                \\
                It can be found in **Sedimentary** and **Igneous Extrusive** rock beneath rivers above y=40.
                """
        );

        // coal
        var ligniteEntry = createOreEntry(
                "lignite",
                TFCItems.ORES.get(Ore.LIGNITE).get(),
                "w",
                """
                "Lignite is a type of low-grade **Coal** ore. \s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks."
                """
        );

        var bituminousCoalEntry = createOreEntry(
                "bituminous",
                TFCItems.ORES.get(Ore.BITUMINOUS_COAL).get(),
                "x",
                """
                Bituminous Coal is a type of mid-grade **Coal** ore. \s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks.
                """
        );

        var anthraciteEntry = createOreEntry(
                "anthracite",
                SpectrumItems.PURE_COAL.get(),
                "y",
                """
                Anthracite is a type of high-grade **Coal** ore. \s
                \\
                It can be found in very large flat deposits anywhere in **Metamorphic** rocks.
                """
        );

        // other minerals

        var kaoliniteEntry = createOreEntry(
                "kaolinite",
                TFCItems.KAOLIN_CLAY.get(),
                "z",
                """
                Kaolinite is a soft **Mineral** which is used in the construction of **Fire Clay**. \s
                \\
                It can be found spawning at high altitudes in **Plateaus**, **Old Mountains**, **Rolling Hills**, and **Highlands**, at a **Temperature** of at least 18°C, with a **Rainfall** of at least 300mm.
                The **Blood Lily** flower grows on **Kaolin clay**.
                """
        );

        var bauxiteEntry = createOreEntry(
                "bauxite",
                CoreItems.ORES.get(CoreOres.BAUXITE).get(),
                "A",
                """
                Bauxite is a **Mineral** ore of kaolinite found as flat deposits near the surface, indicated by granite loose rocks.
                \\
                It can be found in **Limestone**, **Dolomite**, **Chalk**, **Gneiss**, **Blueschist**, **Serpentine**, **Granite**, **Rhyolite** and **Basalt**.
                """
        );

        var graphiteEntry = createOreEntry(
                "graphite",
                TFCItems.ORES.get(Ore.GRAPHITE).get(),
                "B",
                """
                Graphite is a **Mineral** which is used in the construction of **Fire Clay**. \s
                \\
                It can be found in **Gneiss**, **Marble**, **Quartzite**, and **Schist** rocks, in elevations below y=60.
                """
        );

        var cinnabarEntry = createOreEntry(
                "cinnabar",
                TFCItems.ORES.get(Ore.CINNABAR).get(),
                "C",
                """
                Cinnabar is a **Mineral** which can be ground in the **Quern** to obtain **Redstone Dust**. \s
                \\
                It can be found in veins deep underground, in **Quartzite**, **Granite**, **Phyllite**, **Schist**, **Blueschist** and **Serpentine**. \s
                """
        );

        var cryoliteEntry = createOreEntry(
                "cryolite",
                TFCItems.ORES.get(Ore.CRYOLITE).get(),
                "D",
                """
                Cryolite is a **Mineral** which can be ground in the **Quern** to obtain **Redstone Dust**. \s
                \\
                It can be found in veins deep underground, in **Granite**, **Diorite**, **Blueschist**, **Peridotite** and **Komatiite**.
                """
        );

        var saltpeterEntry = createOreEntry(
                "saltpeter",
                TFCItems.ORES.get(Ore.SALTPETER).get(),
                "E",
                """
                Saltpeter is a **Mineral** which can be ground in the **Quern**, and then used in the crafting of **Gunpowder**. \s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks.
                """
        );

        var sulfurEntry = createOreEntry(
                "sulfur",
                TFCItems.ORES.get(Ore.SULFUR).get(),
                "F",
                """
                Sulfur is a **Mineral** which can be ground in the **Quern**, and then used in the crafting of **Gunpowder**. \s
                \\
                It is found near lava level deep underground, in sparse but large and plentiful veins, in any **Metamorphic** or **Igneous Intrusive** rock.
                """
        );

        var sylviteEntry = createOreEntry(
                "sylvite",
                TFCItems.ORES.get(Ore.SYLVITE).get(),
                "G",
                """
                Sylvite is a **Mineral** which can be ground in the **Quern**, and then used as a **Fertilizer**. \s
                \\
                It can be found in very large flat deposits near the surface in **Shale**, **Claystone**, **Chert**, **Sandstone**, **Red Sandstone**, **Arkose**, **Travertine** and **Argillite**.
                """
        );

        var boraxEntry = createOreEntry(
                "borax",
                TFCItems.ORES.get(Ore.BORAX).get(),
                "H",
                """
                Borax is a **Mineral** which can be ground in the **Quern** to produce **Flux**. \s
                \\
                It can be found in very large flat deposits near the surface in **Claystone**, **Limestone**, **Shale**, **Sandstone**, **Red Sandstone**, **Arkose**, **Travertine** and **Argillite**.
                """
        );

        var gypsumEntry = createOreEntry(
                "gypsum",
                TFCItems.ORES.get(Ore.GYPSUM).get(),
                "I",
                """
                Gypsum is a decorative **Mineral** which can be used to make **Plaster**. \s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks.
                """
        );

        var haliteEntry = createOreEntry(
                "halite",
                TFCItems.ORES.get(Ore.HALITE).get(),
                "J",
                """
                Halite is a **Mineral** which can be ground in the **Quern** to make **Salt**, which is an important **Preservative**.\s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks.
               \s"""
        );
    }

    @Override
    protected String categoryName() {
        return "Ores";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCItems.GRADED_ORES.get(Ore.NATIVE_COPPER).get(Ore.Grade.RICH));
    }

    @Override
    public String categoryId() {
        return ID;
    }

    public BookEntryModel createOreEntry(String id, Item item, String symbol, String text){
        return this.add(new OreEntry(
                this,
                id,
                item,
                text,
                ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "ore_preview/" + id)
                ).generate(symbol)
        );
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
