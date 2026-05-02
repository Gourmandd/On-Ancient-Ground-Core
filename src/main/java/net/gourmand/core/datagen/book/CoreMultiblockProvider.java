package net.gourmand.core.datagen.book;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.api.datagen.MultiblockProvider;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.plant.Plant;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

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
            .put(QUARTZ, CoreBlocks.BASIC_ORES.get(QUARTZ))
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

        Stream.of(CoreRocks.values()).forEach(rock -> {
            this.add(this.modLoc("rock_preview/" + rock.getSerializedName()), new DenseMultiblockBuilder()
                    .layer("BBB")
                    .layer("B0B")
                    .layer("BBB")
                    .block('B', () -> Blocks.AIR)
                    .block('0', () -> BuiltInRegistries.BLOCK.get(CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rock).key()))
                    .build(false)
            );
        });

        Stream.of(Rock.values()).forEach(rock -> {
            this.add(this.modLoc("rock_preview/" + rock.getSerializedName()), new DenseMultiblockBuilder()
                    .layer("BBB")
                    .layer("B0B")
                    .layer("BBB")
                    .block('B', () -> Blocks.AIR)
                    .block('0', () -> TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get())
                    .build(false)
            );
        });

        this.add(this.modLoc("getting_started/stick_and_stones" ), new DenseMultiblockBuilder()
                .layer("CARAC", "ACRAA", "RA0AR", "AACRA", "RAACA")
                .layer("GGGGG", "GGGGG", "GGGGG", "GGGGG", "GGGGG")
                .block('A', () -> Blocks.AIR)
                .block('G', () -> TFCBlocks.SOIL.get(SoilBlockType.GRASS).get(SoilBlockType.Variant.ENTISOL).get())
                .block('0', () -> TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.TWIG).get())
                .block('C', () -> TFCBlocks.WOODS.get(Wood.CHESTNUT).get(Wood.BlockType.TWIG).get())
                .block('R', () -> TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.LOOSE).get())
                .build(false)
        );

        this.add(this.modLoc("getting_started/clay_indicators" ), new DenseMultiblockBuilder()
                .layer("AA1AA", "AA2AA", "AA0AA", "AA4AA", "AA5AA")
                .layer("GGGGG", "GGGGG", "GGGGG", "GGGGG", "GGGGG")
                .block('A', () -> Blocks.AIR)
                .block('G', () -> TFCBlocks.SOIL.get(SoilBlockType.CLAY_GRASS).get(SoilBlockType.Variant.ENTISOL).get())
                .block('1', () -> TFCBlocks.PLANTS.get(Plant.ATHYRIUM_FERN).get())
                .block('2', () -> TFCBlocks.PLANTS.get(Plant.CANNA).get())
                .block('0', () -> TFCBlocks.PLANTS.get(Plant.GOLDENROD).get())
                .block('4', () -> TFCBlocks.PLANTS.get(Plant.PAMPAS_GRASS).get())
                .block('5', () -> TFCBlocks.PLANTS.get(Plant.PEROVSKIA).get())
                .build(false)
        );

        this.add(this.modLoc("getting_started/fire_pit" ), new DenseMultiblockBuilder()
                .layer("AAA", "A0A", "AAA")
                .layer("GGG", "GGG", "GGG")
                .block('A', () -> Blocks.AIR)
                .block('G', () -> TFCBlocks.SOIL.get(SoilBlockType.GRASS).get(SoilBlockType.Variant.ENTISOL).get())
                .blockstate('0', TFCBlocks.FIREPIT, "[lit=true]")
                .build(false)
        );

        this.add(this.modLoc("getting_started/pit_kiln" ), new DenseMultiblockBuilder()
                .layer("GGG", "G0G", "GGG")
                .layer("DDD", "DDD", "DDD")
                .block('A', () -> Blocks.AIR)
                .block('D', () -> TFCBlocks.SOIL.get(SoilBlockType.DIRT).get(SoilBlockType.Variant.ENTISOL).get())
                .block('G', () -> TFCBlocks.SOIL.get(SoilBlockType.GRASS).get(SoilBlockType.Variant.ENTISOL).get())
                .blockstate('0', TFCBlocks.PIT_KILN, "[stage=15]")
                .build(false)
        );

        this.add(this.modLoc("pottery/kaolin_clay_indicators" ), new DenseMultiblockBuilder()
                .layer("ABA", "AAA", "A0A", "AAA", "ABA")
                .layer("GGG", "GGG", "GGG", "GGG", "GGG")
                .block('A', () -> Blocks.AIR)
                .block('G', TFCBlocks.KAOLIN_CLAY_GRASS)
                .block('B', () -> TFCBlocks.PLANTS.get(Plant.BLOOD_LILY).get())
                .block('0', () -> TFCBlocks.PLANTS.get(Plant.BLOOD_LILY).get())
                .build(false)
        );

        this.add(this.modLoc("pottery/crucible" ), new DenseMultiblockBuilder()
                .layer("AAA", "ACB", "AAA")
                .layer("GGG", "G0G", "GGG")
                .block('A', () -> Blocks.AIR)
                .block('G', () -> TFCBlocks.ROCK_BLOCKS.get(Rock.DACITE).get(Rock.BlockType.BRICKS).get())
                .block('C', TFCBlocks.CRUCIBLE)
                .block('B', TFCBlocks.BELLOWS)
                .blockstate('0', TFCBlocks.CHARCOAL_FORGE, "[heat_level=7]")
                .build(false)
        );

        this.add(this.modLoc("mining/support_range" ), new DenseMultiblockBuilder()
                .layer("SAAAAAAAS", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "SAAAAAAAS")
                .layer("LAAAAAAAL", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "LAAAAAAAL")
                .layer("SPBPBPBPS", "PAAAPAAAP", "BAAABAAAB", "PAAAPAAAP", "BAA123AAB", "PAAAPAAAP", "BAAABAAAB", "PAAAPAAAP", "SPBPBPBPS")
                .layer("LAAAAAAAL", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAVAVAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "LAAAAAAAL")
                .layer("SAAAAAAAS", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAVAVAAA", "AAAAAAAAA", "AAAAAAAAA", "AAAAAAAAA", "SAAAAAAAS")
                .layer("GGGGGGGGG", "GGGGGGGGG", "GGGGGGGGG", "GGGGGGGGG", "GGGG0GGGG", "GGGGGGGGG", "GGGGGGGGG", "GGGGGGGGG", "GGGGGGGGG" )
                .block('A', () -> Blocks.AIR)
                .blockstate('1', TFCBlocks.WOODS.get(Wood.PINE).get(Wood.BlockType.VERTICAL_SUPPORT), "[south=true]")
                .blockstate('2', TFCBlocks.WOODS.get(Wood.PINE).get(Wood.BlockType.HORIZONTAL_SUPPORT), "[south=true,north=true]")
                .blockstate('3', TFCBlocks.WOODS.get(Wood.PINE).get(Wood.BlockType.VERTICAL_SUPPORT), "[north=true]")
                .block('B', () -> Blocks.RED_STAINED_GLASS)
                .block('P', () -> Blocks.MAGENTA_STAINED_GLASS)
                .block('S', () -> Blocks.BLUE_STAINED_GLASS)
                .block('L', () -> Blocks.LIGHT_BLUE_STAINED_GLASS)
                .block('G', TFCBlocks.ROCK_BLOCKS.get(Rock.SHALE).get(Rock.BlockType.RAW))
                .block('V', TFCBlocks.WOODS.get(Wood.PINE).get(Wood.BlockType.VERTICAL_SUPPORT))
                .block('0', TFCBlocks.ROCK_BLOCKS.get(Rock.SHALE).get(Rock.BlockType.RAW))
                .build(false)
        );
    }
}
