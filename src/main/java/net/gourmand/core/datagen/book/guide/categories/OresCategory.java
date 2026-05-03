package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.datagen.book.guide.entries.OreEntry;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class OresCategory extends CategoryProvider {

    public static final String ID = "ores";

    public OresCategory(SingleBookSubProvider parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "_w__a__m__dei_",
                "_x__b__k__fgh_",
                "_y__c__l___j__",
                "______________",
                "Kz__nopqrstuvL",
                "_A__GCDEFBHIJ_",
        };
    }

    @Override
    protected void generateEntries() {

        // add flux info

        // in the ore entries add their pickaxe tier required to mine.

        // copper
        var nativeCopperEntry = createOreEntry(
                "native_copper",
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.NATIVE_COPPER),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.MALACHITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.TETRAHEDRITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.NATIVE_GOLD),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.NATIVE_SILVER),
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
                "cassiterite",
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.CASSITERITE),
                TFCItems.GRADED_ORES.get(Ore.CASSITERITE).get(Ore.Grade.RICH).get(),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.BISMUTHINITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.GARNIERITE),
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
                CoreTags.Items.CORE_ORE_MULTIBLOCK.get(CoreOres.GALENA),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.SPHALERITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.MAGNETITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.LIMONITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.HEMATITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.AMETHYST),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.DIAMOND),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.EMERALD),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.LAPIS_LAZULI),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.OPAL),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.PYRITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.RUBY),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.SAPPHIRE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.TOPAZ),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.LIGNITE),
                TFCItems.ORES.get(Ore.LIGNITE).get(),
                "w",
                """
                "Lignite is a type of low-grade **Coal** ore. \s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks."
                """
        );

        var bituminousCoalEntry = createOreEntry(
                "bituminous_coal",
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.BITUMINOUS_COAL),
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
                CoreTags.Items.CORE_ORE_MULTIBLOCK.get(CoreOres.ANTHRACITE),
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
                CoreTags.Items.KAOLIN_CLAYS,
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
                CoreTags.Items.CORE_ORE_MULTIBLOCK.get(CoreOres.BAUXITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.GRAPHITE),
                TFCItems.ORES.get(Ore.GRAPHITE).get(),
                "B",
                """
                Graphite is a **Mineral** which is used in the construction of **Fire Clay**. \s
                \\
                It can be found in **Gneiss**, **Marble**, **Quartzite**, **Schist**, **Blueschist**, and **Serpentine** rocks, in elevations below y=60.
                """
        );

        var cinnabarEntry = createOreEntry(
                "cinnabar",
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.CINNABAR),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.CRYOLITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.SALTPETER),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.SULFUR),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.SYLVITE),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.BORAX),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.GYPSUM),
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
                CoreTags.Items.TFC_ORE_MULTIBLOCK.get(Ore.HALITE),
                TFCItems.ORES.get(Ore.HALITE).get(),
                "J",
                """
                Halite is a **Mineral** which can be ground in the **Quern** to make **Salt**, which is an important **Preservative**.\s
                \\
                It can be found in very large flat deposits near the surface in **Sedimentary** rocks.
               \s"""
        );

        var meteoricIronEntry = createOreEntry(
                "meteoric_iron",
                CoreTags.Items.CORE_ORE_MULTIBLOCK.get(CoreOres.METEORIC_IRON),
                CoreItems.ORES.get(CoreOres.METEORIC_IRON).get(),
                "K",
                """
                Meteoric Iron is a very rare ore of **Iron** found it meteorites alongside **Suevite** rock. It can be refined into **Wrought Iron** with a **Copper** anvil.\s
                \\
                Meteors can generate anywhere near the surface, except for oceans and high up on mountains, they can generate in any rock type,
               \s"""
        );

        var quartzEntry = createOreEntry(
                "quartz",
                CoreTags.Items.CORE_ORE_MULTIBLOCK.get(CoreOres.QUARTZ),
                Items.QUARTZ.asItem(),
                "L",
                """
                Quartz is a **Mineral** which can be found in pipe formations, it has various practical and aesthetic purposes.\s
                \\
                Poor quartz veins can generate in any **Sedimentary** rock, they are large but sparse.\s
                \\
                Richer veins can be found in **Granite**, **Rhyolite**, **Dacite**, **Quartzite** and **Schist**.
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

    public BookEntryModel createOreEntry(String id, TagKey<Item> tag, Item item, String symbol, String text){
        return this.add(new OreEntry(
                this,
                id,
                item,
                text,
                tag
                ).generate(symbol)
        );
    }

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
