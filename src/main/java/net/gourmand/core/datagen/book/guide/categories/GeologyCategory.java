package net.gourmand.core.datagen.book.guide.categories;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.datagen.book.guide.entries.RockEntry;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.resources.ResourceLocation;

public class GeologyCategory extends CategoryProvider {

    public GeologyCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return new String[]{
                "C_ac_efg__uw_E_ID",
                "i_bd_hlF__v__z_yJ",
                "j______________BK",
                "k_GH_mnopqsrt__Ax",
        };
    }

    @Override
    protected void generateEntries() {

        // add whether a rock is a fluxstone.

        // add entry for each rock category
        // add entry about the geology of the world

        //region Igneous Intrusive
        var graniteEntry = createRockEntry(
                Rock.GRANITE,
                "a",
                """
                **Granite** is a **Felsic** **Igneous Intrusive** rock. It can be found:
                - In **Uplift Regions**.
                - Deep in **Land Regions**.
                - Near the bottom of the world.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var dioriteEntry = createRockEntry(
                Rock.DIORITE,
                "b",
                """
                **Diorite** is a **Igneous Intrusive** rock.  It can be found:
                - In **Uplift Regions**.
                - Deep in **Land Regions**.
                - Near the bottom of the world.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var gabbroEntry = createRockEntry(
                Rock.GABBRO,
                "c",
                """
                **Gabbro** is a **Mafic** **Igneous Intrusive** rock. It can be found:
                - In **Uplift Regions**.
                - Deep in **Land Regions**.
                - Near the bottom of the world.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var peridotiteEntry = createRockEntry(
                CoreRocks.PERIDOTITE,
                "d",
                """
                **Peridotite** is a Mafic **Igneous Intrusive** rock. It can be found:
                - In **Land Regions**.
                - Near the bottom of the world.
                """
                //It can be used for **Rock Anvils**. // NYI
        );
        //endregion

        //region Igneous Extrusive
        var rhyoliteEntry = createRockEntry(
                Rock.RHYOLITE,
                "e",
                """
                **Rhyolite** is a **Igneous Extrusive** rock. It can be found:
                - In **Land Regions**.
                - In **Volcanic Regions**.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var basaltEntry = createRockEntry(
                Rock.BASALT,
                "f",
                """
                **Basalt** is a **Igneous Extrusive** rock. It can be found:
                - In **Land Regions**.
                - In **Volcanic Regions**.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var andesiteEntry = createRockEntry(
                Rock.ANDESITE,
                "g",
                """
                **Andesite** is a **Igneous Extrusive** rock. It can be found:
                - In **Land Regions**.
                - In **Volcanic Regions**.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var daciteEntry = createRockEntry(
                Rock.DACITE,
                "h",
                """
                **Dacite** is a **Igneous Extrusive** rock. It can be found:
                - In **Land Regions**.
                - In **Volcanic Regions**.
                \\
                \\
                It can be used for **Rock Anvils**.
                """
        );

        var nepheliniteEntry = createRockEntry(
                CoreRocks.NEPHELINITE,
                "i",
                """
                **Nephelinite** is a **Igneous Extrusive** rock. It can be found:
                - Deep, deep underground.
                """//It can be used for **Rock Anvils**. // NYI
        );

        var blackslagEntry = createRockEntry(
                CoreRocks.BLACKSLAG,
                "j",
                """
                **Blackslag** is a **Igneous Extrusive** rock. It can be found:
                - Near the bottom of the world.
                - Deep, deep underground.
                \\
                It's formation is a mystery as of now.
                """//It can be used for **Rock Anvils**. // NYI
        );

        var picriteBasaltEntry = createRockEntry(
                CoreRocks.PICRITE_BASALT,
                "k",
                """
                **Picrite Basalt** is a **Igneous Extrusive** rock. It can be found:
                - Deep, deep underground.
                """//It can be used for **Rock Anvils**. // NYI
        );
        var phonoliteEntry = createRockEntry(
                CoreRocks.PHONOLITE,
                "F",
                """
                **Phonolite** is a **Igneous Extrusive** rock. It can be found:
                - In **Land Regions**.
                """//It can be used for **Rock Anvils**. // NYI
        );

        var komatiiteEntry = createRockEntry(
                CoreRocks.KOMATIITE,
                "l",
                """
                **Komatiite** is a **Igneous Extrusive** rock. It can be found:
                - In **Volcanic Regions**.
                - In **Land Regions**.
                """//It can be used for **Rock Anvils**. // NYI
        );
        //endregion

        //region Metamorphic
        var quartziteEntry = createRockEntry(
                Rock.QUARTZITE,
                "m",
                """
                **Quartzite** is a **Metamorphic** rock. It can be found:
                - In **Uplift Regions**.
                - Under **Chert**, **Sandstone**, **Red Sandstone** or **Arkose**.
                """
        );

        var slateEntry = createRockEntry(
                Rock.SLATE,
                "n",
                """
                **Slate** is a **Metamorphic** rock. It can be found:
                - Under **Sedimentary** rocks like **Claystone**, **Conglomerate**, **Shale**, **Argillite**, **Breccia**, and **Travertine**.
                - In **Uplift Regions** under **Diorite**, **Granite**, **Gabbro**.
                - In **Land Regions** under **Komatiite** and **Phonolite**.
                - In **Uplift Regions**.
                """
        );

        var phylliteEntry = createRockEntry(
                Rock.PHYLLITE,
                "o",
                """
                **Phyllite** is a **Metamorphic** rock. It can be found:
                - Under **Sedimentary** rocks like **Claystone**, **Conglomerate**, **Shale**, **Argillite**, **Breccia**, and **Travertine**.
                - In **Uplift Regions** under **Diorite**, **Granite**, **Gabbro**.
                - In **Land Regions** under **Komatiite** and **Phonolite**.
                - In **Uplift Regions**.
                """
        );

        var schistEntry = createRockEntry(
                Rock.SCHIST,
                "p",
                """
                **Schist** is a **Metamorphic** rock. It can be found:
                - In **Land Regions** under **Peridotite**, **Serpentine**, and **Blueschist**.
                - Under **Phyllite** or **Slate**.
                - Near the bottom of the world.
                """
        );

        var gneissEntry = createRockEntry(
                Rock.GNEISS,
                "q",
                """
                **Gneiss** is a **Metamorphic** rock. It can be found:
                - In **Land Regions** under **Peridotite**, **Serpentine**, and **Blueschist**.
                - Under **Phyllite** or **Slate**.
                - Near the bottom of the world.
                """
        );

        var marbleEntry = createRockEntry(
                Rock.MARBLE,
                "r",
                """
                **Marble** is a **Metamorphic** rock. It can be found:
                - In **Uplift Regions**.
                - Under **Chalk**, **Dolomite** or **Limestone**.
                """
        );

        var blueschistEntry = createRockEntry(
                CoreRocks.BLUESCHIST,
                "s",
                """
                **Blueschist** is a **Metamorphic** rock. It can be found:
                - In **Land Regions**.
                - In **Volcanic Regions**.
                - Near the bottom of the world.
                """
        );

        var serpentineEntry = createRockEntry(
                CoreRocks.SERPENTINE,
                "t",
                """
                **Serpentine** is a **Metamorphic** rock. It can be found:
                - In **Land Regions**.
                - Near the bottom of the world.
                """
        );

        var soapstoneEntry = createRockEntry(
                CoreRocks.SOAPSTONE,
                "G",
                """
                **Soapstone** is a **Metamorphic** rock found as veins in other **Metamorphic** rocks. \\
                This can make it relatively rare.
                """
        );

        var sueviteEntry = createRockEntry(
                CoreRocks.SUEVITE,
                "H",
                """
                **Suevite** is a **Metamorphic** rock found as meteors found near the surface. \\
                This can make it relatively rare.
                This means it is found alongside **Meteoric Iron**
                """
        );
        //endregion

        //region Carbonic Sedimentary
        var limestoneEntry = createRockEntry(
                Rock.LIMESTONE,
                "u",
                """
                **Limestone** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var dolomiteEntry = createRockEntry(
                Rock.DOLOMITE,
                "v",
                """
                **Dolomite** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var chalkEntry = createRockEntry(
                Rock.CHALK,
                "w",
                """
                **Chalk** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );
        //endregion

        //region Sedimentary
        var shaleEntry = createRockEntry(
                Rock.SHALE,
                "x",
                """
                **Shale** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var claystoneEntry = createRockEntry(
                Rock.CLAYSTONE,
                "y",
                """
                **Claystone** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var conglomerateEntry = createRockEntry(
                Rock.CONGLOMERATE,
                "z",
                """
                **Conglomerate** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var chertEntry = createRockEntry(
                Rock.CHERT,
                "A",
                """
                **Chert** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var tuffEntry = createRockEntry(
                Rock.TUFF,
                "B",
                """
                **Tuff** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var argilliteEntry = createRockEntry(
                CoreRocks.ARGILLITE,
                "C",
                """
                **Argillite** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                - Deep, deep underground.
                """
        );

        var travertineEntry = createRockEntry(
                CoreRocks.TRAVERTINE,
                "D",
                """
                **Travertine** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                - Deep, deep underground.
                """
        );

        var brecciaEntry = createRockEntry(
                CoreRocks.BRECCIA,
                "E",
                """
                **Breccia** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var sandstoneEntry = createRockEntry(
                CoreRocks.SANDSTONE,
                "I",
                """
                **Sandstone** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var redSandstoneEntry = createRockEntry(
                CoreRocks.RED_SANDSTONE,
                "J",
                """
                **Red Sandstone** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );

        var arkoseEntry = createRockEntry(
                CoreRocks.ARKOSE,
                "K",
                """
                **Arkose** is a **Sedimentary** rock. It can be found:
                - In **Land Regions**.
                - In **Uplift Regions**.
                """
        );
        //endregion
    }

    public BookEntryModel createRockEntry( RegistryRock rock, String symbol, String text){
        return this.add(new RockEntry(
                        this,
                        rock,
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

    @Override
    protected BookCategoryModel additionalSetup(BookCategoryModel category) {
        category.withBackground(ResourceLocation.parse(SpectrumCommon.MOD_ID + ":textures/gui/modonomicon/category_background.png"));
        return super.additionalSetup(category);
    }
}
