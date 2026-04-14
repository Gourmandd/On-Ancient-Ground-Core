package net.gourmand.core.datagen.book;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.api.datagen.MultiblockProvider;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Locale;
import java.util.Map;

import static net.gourmand.core.registry.category.CoreOres.*;
import static net.dries007.tfc.common.blocks.rock.Ore.*;

public class CoreMultiblockProvider extends MultiblockProvider {

    public CoreMultiblockProvider(PackOutput packOutput) {
        super(packOutput, AncientGroundCore.MODID);
    }

    private static final Map<CoreOres, DeferredHolder<Block, Block>> CORE_ORES = ImmutableMap.<CoreOres, DeferredHolder<Block, Block>>builder()
            .put(GALENA, CoreBlocks.GRADED_ORES.get(Rock.LIMESTONE).get(GALENA).get(CoreOres.Grade.NORMAL))
            .put(METEORIC_IRON, CoreBlocks.BASIC_ORES.get(METEORIC_IRON))
            .put(ANTHRACITE, CoreBlocks.BASIC_ORES.get(ANTHRACITE))
            .put(BAUXITE, CoreBlocks.BASIC_ORES.get(BAUXITE))
            .build();

    private static final Map<Ore, DeferredHolder<Block, Block>> TFC_ORES = ImmutableMap.<Ore, DeferredHolder<Block, Block>>builder()
            .put(NATIVE_COPPER, TFCBlocks.GRADED_ORES.get(Rock.ANDESITE).get(NATIVE_COPPER).get(Ore.Grade.NORMAL).holder())
            .put(NATIVE_GOLD, TFCBlocks.GRADED_ORES.get(Rock.DIORITE).get(NATIVE_GOLD).get(Ore.Grade.NORMAL).holder())
            .put(HEMATITE, TFCBlocks.GRADED_ORES.get(Rock.RHYOLITE).get(HEMATITE).get(Ore.Grade.NORMAL).holder())
            .put(NATIVE_SILVER, TFCBlocks.GRADED_ORES.get(Rock.GRANITE).get(NATIVE_SILVER).get(Ore.Grade.NORMAL).holder())
            .put(CASSITERITE, TFCBlocks.GRADED_ORES.get(Rock.GRANITE).get(CASSITERITE).get(Ore.Grade.NORMAL).holder())
            .put(BISMUTHINITE, TFCBlocks.GRADED_ORES.get(Rock.DIORITE).get(BISMUTHINITE).get(Ore.Grade.NORMAL).holder())
            .put(GARNIERITE, TFCBlocks.GRADED_ORES.get(Rock.GABBRO).get(GARNIERITE).get(Ore.Grade.NORMAL).holder())
            .put(Ore.MALACHITE, TFCBlocks.GRADED_ORES.get(Rock.MARBLE).get(Ore.MALACHITE).get(Ore.Grade.NORMAL).holder())
            .put(MAGNETITE, TFCBlocks.GRADED_ORES.get(Rock.CLAYSTONE).get(MAGNETITE).get(Ore.Grade.NORMAL).holder())
            .put(LIMONITE, TFCBlocks.GRADED_ORES.get(Rock.CHERT).get(LIMONITE).get(Ore.Grade.NORMAL).holder())
            .put(SPHALERITE, TFCBlocks.GRADED_ORES.get(Rock.GRANITE).get(SPHALERITE).get(Ore.Grade.NORMAL).holder())
            .put(TETRAHEDRITE, TFCBlocks.GRADED_ORES.get(Rock.GNEISS).get(TETRAHEDRITE).get(Ore.Grade.NORMAL).holder())
            .put(GYPSUM, TFCBlocks.ORES.get(Rock.DOLOMITE).get(GYPSUM).holder())
            .put(CINNABAR, TFCBlocks.ORES.get(Rock.QUARTZITE).get(CINNABAR).holder())
            .put(CRYOLITE, TFCBlocks.ORES.get(Rock.DIORITE).get(CRYOLITE).holder())
            .put(BORAX, TFCBlocks.ORES.get(Rock.SHALE).get(BORAX).holder())
            .put(GRAPHITE, TFCBlocks.ORES.get(Rock.MARBLE).get(GRAPHITE).holder())
            .put(SALTPETER, TFCBlocks.ORES.get(Rock.DOLOMITE).get(SALTPETER).holder())
            .put(SULFUR, TFCBlocks.ORES.get(Rock.SCHIST).get(SULFUR).holder())
            .put(SYLVITE, CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(CoreRocks.SANDSTONE).get(SYLVITE))
            .put(AMETHYST, TFCBlocks.ORES.get(Rock.LIMESTONE).get(AMETHYST).holder())
            .put(DIAMOND, TFCBlocks.ORES.get(Rock.GABBRO).get(DIAMOND).holder())
            .put(EMERALD, TFCBlocks.ORES.get(Rock.DIORITE).get(EMERALD).holder())
            .put(LAPIS_LAZULI, TFCBlocks.ORES.get(Rock.LIMESTONE).get(LAPIS_LAZULI).holder())
            .put(OPAL, TFCBlocks.ORES.get(Rock.SHALE).get(OPAL).holder())
            .put(PYRITE, TFCBlocks.ORES.get(Rock.DACITE).get(PYRITE).holder())
            .put(RUBY, TFCBlocks.ORES.get(Rock.SCHIST).get(RUBY).holder())
            .put(SAPPHIRE, TFCBlocks.ORES.get(Rock.PHYLLITE).get(SAPPHIRE).holder())
            .put(TOPAZ, TFCBlocks.ORES.get(Rock.QUARTZITE).get(TOPAZ).holder())
            .put(BITUMINOUS_COAL, TFCBlocks.BITUMINOUS_COAL.holder())
            .put(LIGNITE, TFCBlocks.LIGNITE.holder())
            .put(HALITE, TFCBlocks.HALITE.holder())
            .build();

    @Override
    public void buildMultiblocks() {

        CORE_ORES.forEach((ore, block) -> {
            this.add(this.modLoc("ore_preview/" + ore.getSerializedName()), new DenseMultiblockBuilder()
                    .layer("BBB")
                    .layer("B0B")
                    .layer("BBB")
                    .block('B', () -> Blocks.AIR)
                    .block('0', block)
                    .build(false)
            );
        });

        TFC_ORES.forEach((ore, block) -> {
            this.add(this.modLoc("ore_preview/" + ore.name().toLowerCase(Locale.ROOT) ), new DenseMultiblockBuilder()
                    .layer("BBB")
                    .layer("B0B")
                    .layer("BBB")
                    .block('B', () -> Blocks.AIR)
                    .block('0', block)
                    .build(false)
            );
        });

        this.add(this.modLoc("ore_preview/kaolinite"), new DenseMultiblockBuilder()
                .layer("BBB")
                .layer("B0B")
                .layer("BBB")
                .block('B', () -> Blocks.AIR)
                .block('0', TFCBlocks.PINK_KAOLIN_CLAY)
                .build(false)
        );
    }
}
