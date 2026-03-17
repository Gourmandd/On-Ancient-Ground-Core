package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.datagen.book.guide.entries.ores.RockEntry;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.world.item.Item;

public class GeologyCategory extends CategoryProvider {

    public GeologyCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[0];
    }

    @Override
    protected void generateEntries() {

        // igneous intrusive
        var graniteEntry = createRockEntry(
                "granite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "a",
                """
                Granite is a Felsic Igneous Intrusive rock.
                You can find metals such as deep Native Gold, deep Bismuthinite, deep Sphalerite, Native Silver, Cassiterite and small Garnierite veins in Granite.
                You can find minerals such as Emerald, Cinnabar, Cryolite and deep Sulfur veins in Granite.
                It is able to be used for rock anvils.
                """
        );

        var dioriteEntry = createRockEntry(
                "diorite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.DIORITE).get(Rock.BlockType.LOOSE).asItem(),
                "b",
                """
                Diorite is a Igneous Intrusive rock.
                You can find minerals such as Emerald, Cryolite and deep Sulfur veins in Diorite.
                You can find metals such as deep Native Gold, deep Bismuthinite, deep Sphalerite, Native Silver, Cassiterite and small Garnierite veins in Diorite.
                It is able to be used for rock anvils.
                """
        );

        var gabbroEntry = createRockEntry(
                "gabbro",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GABBRO).get(Rock.BlockType.LOOSE).asItem(),
                "c",
                """
                Gabbro is a Mafic Igneous Intrusive rock.
                You can find minerals such as Emerald, and deep Sulfur veins in Gabbro.
                You can find metals such as deep Native Gold, deep Bismuthinite, deep Sphalerite, Cassiterite, and small Garnierite veins in Gabbro.
                It is able to be used for rock anvils.
                """
        );

        var peridotiteEntry = createRockEntry(
                "peridotite",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.PERIDOTITE).get(Rock.BlockType.LOOSE).get().asItem(),
                "d",
                """
                Peridotite is a Mafic Igneous Intrusive rock.
                In it you can find minerals such as Amethyst, Emerald, and deep Sulfur veins.
                In Peridotite you can find metals in deep Native Gold, deep Bismuthinite, deep Sphalerite, Native Silver, Cassiterite, and small Garnierite veins.
                """
                //It is able to be used for rock anvils. // NYI
        );

        // intrusive extrusive
        var rhyoliteEntry = createRockEntry(
                "rhyolite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.RHYOLITE).get(Rock.BlockType.LOOSE).asItem(),
                "e",
                """
                Rhyolite is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Rhyolite you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                It is able to be used for rock anvils.
                """
        );

        var basaltEntry = createRockEntry(
                "basalt",
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.LOOSE).asItem(),
                "f",
                """
                Basalt is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Basalt you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                It is able to be used for rock anvils.
                """
        );

        var andesiteEntry = createRockEntry(
                "andesite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.LOOSE).asItem(),
                "g",
                """
                Andesite is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Andesite you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                It is able to be used for rock anvils.
                """
        );

        var daciteEntry = createRockEntry(
                "granite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.DACITE).get(Rock.BlockType.LOOSE).asItem(),
                "h",
                """
                Dacite is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Dacite you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                It is able to be used for rock anvils.
                """
        );

        var nepheliniteEntry = createRockEntry(
                "nephelinite",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.NEPHELINITE).get(Rock.BlockType.LOOSE).get().asItem(),
                "i",
                """
                Nephelinite is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, Topaz veins.
                In Nephelinite you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                """//It is able to be used for rock anvils. // NYI
        );

        var blackslagEntry = createRockEntry(
                "blackslag",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.BLACKSLAG).get(Rock.BlockType.LOOSE).get().asItem(),
                "j",
                """
                Blackslag is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Blackslag you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                """//It is able to be used for rock anvils. // NYI
        );

        var picriteBasaltEntry = createRockEntry(
                "picrite_basalt",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.PICRITE_BASALT).get(Rock.BlockType.LOOSE).get().asItem(),
                "k",
                """
                Picrite Basalt is a Igneous Extrusive rock.
                In it you can find minerals such as in Opal, Sapphire, and Topaz veins.
                In Picrite Basalt you can find metals in deep Native Gold, Native Copper, Hematite, and poor Sphalerite veins.
                """//It is able to be used for rock anvils. // NYI
        );

        var komatiiteEntry = createRockEntry(
                "komatiite",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.KOMATIITE).get(Rock.BlockType.LOOSE).get().asItem(),
                "l",
                """
                Picrite Basalt is a Igneous Extrusive rock.
                In it you can find minerals such as in Amethyst, Opal, Sapphire, Sulfur, and Topaz veins.
                In Picrite Basalt you can find metals in deep Native Gold, Bismuthinite, Native Silver, Native Copper, Garnierite, Hematite, poor Cassiterite and poor Sphalerite veins.
                """//It is able to be used for rock anvils. // NYI
        );

        // metamorphic
        var quartziteEntry = createRockEntry(
                "quartzite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.QUARTZITE).get(Rock.BlockType.LOOSE).asItem(),
                "m",
                """
                Quartzite is a Metamorphic rock.
                In it you can find minerals such as Amethyst, Cinnabar, Graphite, and Sulfur.
                In Quartzite you can find metals in Tetrahedrite veins.
                """
        );

        var slateEntry = createRockEntry(
                "slate",
                TFCBlocks.ROCK_BLOCKS.get(Rock.SLATE).get(Rock.BlockType.LOOSE).asItem(),
                "n",
                """
                Slate is a Metamorphic rock.
                In it you can find minerals such as Amethyst, and Sulfur.
                In Slate you can find metals in Tetrahedrite veins.
                """
        );

        var phylliteEntry = createRockEntry(
                "phyllite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.PHYLLITE).get(Rock.BlockType.LOOSE).asItem(),
                "o",
                """
                Phyllite is a Metamorphic rock.
                In it you can find minerals such as Amethyst, and Sulfur.
                In Phyllite you can find metals in Tetrahedrite veins.
                """
        );

        var schistEntry = createRockEntry(
                "schist",
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.LOOSE).asItem(),
                "p",
                """
                Schist is a Metamorphic rock.
                In it you can find minerals such as Amethyst, Cinnabar, Ruby, Graphite and Sulfur.
                In Schist you can find metals in Tetrahedrite, and deep Native Silver veins.
                """
        );

        var gneissEntry = createRockEntry(
                "gneiss",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GNEISS).get(Rock.BlockType.LOOSE).asItem(),
                "q",
                """
                Slate is a Metamorphic rock.
                In it you can find minerals such as in Amethyst, and Sulfur veins.
                In Slate you can find metals in Tetrahedrite veins.
                """
        );

        var marbleEntry = createRockEntry(
                "marble",
                TFCBlocks.ROCK_BLOCKS.get(Rock.MARBLE).get(Rock.BlockType.LOOSE).asItem(),
                "r",
                """
                Slate is a Metamorphic rock.
                In it you can find minerals such as in Amethyst, and Sulfur veins.
                In Slate you can find metals in Tetrahedrite veins.
                """
        );

        var blueschistEntry = createRockEntry(
                "blueschist",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.BLUESCHIST).get(Rock.BlockType.LOOSE).get().asItem(),
                "s",
                """
                Slate is a Metamorphic rock.
                In it you can find minerals such as in Amethyst, and Sulfur veins.
                In Slate you can find metals in Tetrahedrite veins.
                """
        );

        var serpentineEntry = createRockEntry(
                "serpentine",
                CoreBlocks.ROCK_BLOCKS.get(CoreRocks.SERPENTINE).get(Rock.BlockType.LOOSE).get().asItem(),
                "t",
                """
                Quartzite is a Metamorphic rock.
                In it you can find minerals such as in veins.
                In Quartzite you can find metals in Tetrahedrite veins.
                """
        );

        //sedimentary carbonic
        var limestoneEntry = createRockEntry(
                "limestone",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "u",
                """
                """
        );

        var dolomiteEntry = createRockEntry(
                "dolomite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "v",
                """
                """
        );

        var chalkEntry = createRockEntry(
                "chalk",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "w",
                """
                """
        );

        //sedimentary other
        var shaleEntry = createRockEntry(
                "shale",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "x",
                """
                """
        );

        var claystoneEntry = createRockEntry(
                "claystone",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "y",
                """
                """
        );

        var conglomerateEntry = createRockEntry(
                "conglomerate",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "z",
                """
                """
        );

        var chertEntry = createRockEntry(
                "chert",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "A",
                """
                """
        );

        var tuffEntry = createRockEntry(
                "tuff",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "B",
                """
                """
        );

        var argilliteEntry = createRockEntry(
                "granite",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "C",
                """
                """
        );

        var travertineEntry = createRockEntry(
                "travertine",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "D",
                """
                """
        );

        var brecciaEntry = createRockEntry(
                "breccia",
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.LOOSE).asItem(),
                "E",
                """
                """
        );
    }

    public BookEntryModel createRockEntry(String id, Item item, String symbol, String text){
        return this.add(new RockEntry(
                        this,
                        id,
                        item,
                        text
                ).generate(symbol)
        );
    }

    @Override
    protected String categoryName() {
        return "Geology";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.LOOSE).asItem());
    }

    @Override
    public String categoryId() {
        return "geology";
    }
}
