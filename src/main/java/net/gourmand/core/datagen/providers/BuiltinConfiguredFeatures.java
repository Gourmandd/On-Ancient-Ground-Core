package net.gourmand.core.datagen.providers;

import com.mojang.datafixers.util.Pair;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.util.collections.IWeighted;
import net.dries007.tfc.util.registry.RegistryRock;
import net.dries007.tfc.world.feature.*;
import net.dries007.tfc.world.feature.cave.CaveVegetationConfig;
import net.dries007.tfc.world.feature.vein.*;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreWorldGen;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreRocks;
import net.gourmand.core.registry.category.TFCOres;
import net.gourmand.core.util.RegistryOre;
import net.gourmand.core.util.DataWeighted;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;

import java.util.*;
import java.util.stream.Stream;

public class BuiltinConfiguredFeatures  {
    // Some features are commented out, and their generated jsons are moved to the non-datagenned data directories.
    // Each time they generate, the order of the maps is different, this is so that git doesn't bother with them each time data gen is run.

    public static BootstrapContext<ConfiguredFeature<?, ?>> CTX;

    // TFC non-vein features, but rock related.

    //region Rock related feature ResourceKeys
    public static final ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_DEEP_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "cassiterite_deep_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "cassiterite_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CAVE_VEGETATION = createKey(TerraFirmaCraft.MOD_ID, "cave_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBBLE_BOULDER = createKey(TerraFirmaCraft.MOD_ID, "cobble_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBBLE_BOULDER_SMALL = createKey(TerraFirmaCraft.MOD_ID, "cobble_boulder_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_VOLCANO_FISSURE = createKey(TerraFirmaCraft.MOD_ID, "diamond_volcano_fissure");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_EMPTY_HOT_SPRING = createKey(TerraFirmaCraft.MOD_ID, "emerald_empty_hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_HOT_SPRING = createKey(TerraFirmaCraft.MOD_ID, "emerald_hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_VOLCANO_SPRING_WATER_FISSURE = createKey(TerraFirmaCraft.MOD_ID, "emerald_volcano_spring_water_fissure");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EXTRA_WATER_SURFACE_SPRING = createKey(TerraFirmaCraft.MOD_ID, "extra_water_surface_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_HOT_SPRING = createKey(TerraFirmaCraft.MOD_ID, "lava_hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_SPRING = createKey(TerraFirmaCraft.MOD_ID, "lava_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_SURFACE_SPRING = createKey(TerraFirmaCraft.MOD_ID, "lava_surface_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BOULDER = createKey(TerraFirmaCraft.MOD_ID, "mossy_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BOULDER_SMALL = createKey(TerraFirmaCraft.MOD_ID, "mossy_boulder_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_COPPER_DEEP_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_copper_deep_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_COPPER_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_copper_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_GOLD_DEEP_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_gold_deep_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_GOLD_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_gold_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_SILVER_DEEP_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_silver_deep_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_SILVER_DEPOSIT = createKey(TerraFirmaCraft.MOD_ID, "native_silver_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OCEAN_RIDGE_RIVULET = createKey(TerraFirmaCraft.MOD_ID, "ocean_ridge_rivulet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAW_BOULDER = createKey(TerraFirmaCraft.MOD_ID, "raw_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAW_BOULDER_SMALL = createKey(TerraFirmaCraft.MOD_ID, "raw_boulder_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_EMPTY_HOT_SPRING = createKey(TerraFirmaCraft.MOD_ID, "sapphire_empty_hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_HOT_SPRING = createKey(TerraFirmaCraft.MOD_ID, "sapphire_hot_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_VOLCANO_SPRING_WATER_FISSURE = createKey(TerraFirmaCraft.MOD_ID, "sapphire_volcano_spring_water_fissure");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRATOVOLCANO_RIVULET = createKey(TerraFirmaCraft.MOD_ID, "stratovolcano_rivulet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_RIVULET = createKey(TerraFirmaCraft.MOD_ID, "sulfur_rivulet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOPAZ_VOLCANO_FISSURE = createKey(TerraFirmaCraft.MOD_ID, "topaz_volcano_fissure");
    //endregion

    //region Ore Vein ResourceKeys
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST = createKey(TerraFirmaCraft.MOD_ID, "vein/amethyst");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BITUMINOUS_COAL = createKey(TerraFirmaCraft.MOD_ID, "vein/bituminous_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BORAX = createKey(TerraFirmaCraft.MOD_ID, "vein/borax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYOLITE = createKey(TerraFirmaCraft.MOD_ID, "vein/cryolite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_DIKE = createKey(TerraFirmaCraft.MOD_ID, "vein/diorite_dike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD = createKey(TerraFirmaCraft.MOD_ID, "vein/emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAKE_NATIVE_GOLD = createKey(TerraFirmaCraft.MOD_ID, "vein/fake_native_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GABBRO_DIKE = createKey(TerraFirmaCraft.MOD_ID, "vein/gabbro_dike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_DIKE = createKey(TerraFirmaCraft.MOD_ID, "vein/granite_dike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPHITE = createKey(TerraFirmaCraft.MOD_ID, "vein/graphite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVEL = createKey(TerraFirmaCraft.MOD_ID, "vein/gravel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GYPSUM = createKey(TerraFirmaCraft.MOD_ID, "vein/gypsum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HALITE = createKey(TerraFirmaCraft.MOD_ID, "vein/halite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAPIS_LAZULI = createKey(TerraFirmaCraft.MOD_ID, "vein/lapis_lazuli");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIGNITE = createKey(TerraFirmaCraft.MOD_ID, "vein/lignite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_BISMUTHINITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_bismuthinite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_CASSITERITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_cassiterite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_CINNABAR = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_cinnabar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_HEMATITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_hematite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_LIMONITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_limonite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_MAGNETITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_magnetite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_MALACHITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_NATIVE_COPPER = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_native_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_NATIVE_SILVER = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_native_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_SPHALERITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_sphalerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MONTANE_TETRAHEDRITE = createKey(TerraFirmaCraft.MOD_ID, "vein/montane_tetrahedrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_BISMUTHINITE = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_bismuthinite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_CINNABAR = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_cinnabar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_GARNIERITE = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_garnierite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_MALACHITE = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_NATIVE_GOLD = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_native_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_NATIVE_SILVER = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_native_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_SPHALERITE = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_sphalerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_TETRAHEDRITE = createKey(TerraFirmaCraft.MOD_ID, "vein/normal_tetrahedrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPAL = createKey(TerraFirmaCraft.MOD_ID, "vein/opal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RICH_NATIVE_GOLD = createKey(TerraFirmaCraft.MOD_ID, "vein/rich_native_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_MARBLE_BELT = createKey(TerraFirmaCraft.MOD_ID, "vein/ruby_marble_belt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SALTPETER = createKey(TerraFirmaCraft.MOD_ID, "vein/saltpeter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR = createKey(TerraFirmaCraft.MOD_ID, "vein/sulfur");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_BISMUTHINITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_bismuthinite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_HEMATITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_hematite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_LIMONITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_limonite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_MAGNETITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_magnetite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_MALACHITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_NATIVE_COPPER = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_native_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_SPHALERITE = createKey(TerraFirmaCraft.MOD_ID, "vein/surface_sphalerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SYLVITE = createKey(TerraFirmaCraft.MOD_ID, "vein/sylvite");
    //endregion

    // This Mod's features

    public static final ResourceKey<ConfiguredFeature<?, ?>> MONSTER_ROOM = createKey(AncientGroundCore.MODID, "monster_room");

    //region Geode ResourceKeys
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_GEODE = createKey(AncientGroundCore.MODID, "vein/geode/amethyst");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_GEODE = createKey(AncientGroundCore.MODID, "vein/geode/citrine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOPAZ_GEODE = createKey(AncientGroundCore.MODID, "vein/geode/topaz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ONYX_GEODE = createKey(AncientGroundCore.MODID, "vein/geode/onyx");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOONSTONE_GEODE = createKey(AncientGroundCore.MODID, "vein/geode/moonstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/bismuth");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BORAX_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/borax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CASSITERITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/cassiterite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CINNABAR_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/cinnabar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYOLITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/cryolite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GARNIERITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/garnierite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPHITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/graphite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GYPSUM_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/gypsum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HALITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/halite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HEMATITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/hematite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAOLINITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/kaolinite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAPIS_LAZULI_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/lapis_lazuli");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIGNITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/lignite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMONITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/limonite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALACHITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/malachite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_COPPER_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/native_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_GOLD_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/native_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATIVE_SILVER_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/native_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPAL_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/opal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALTAERIA_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/paltaeria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SALTPETER_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/saltpeter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/sapphite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHIMERSTONE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/shimmerstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPHALERITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/sphalerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRATINE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/stratine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/sulfur");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SYLVITE_DEEPER_DOWN = createKey(AncientGroundCore.MODID, "vein/deeper_down/sylvite");
    //endregion

    //region Ore Vein ResourceKeys
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANTHRACITE = createKey(AncientGroundCore.MODID, "vein/anthracite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AZURITE = createKey(AncientGroundCore.MODID, "vein/azurite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AZURITE_VOLCANIC = createKey(AncientGroundCore.MODID, "vein/azurite_volcanic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAUXITE = createKey(AncientGroundCore.MODID, "vein/bauxite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> METEORIC_IRON = createKey(AncientGroundCore.MODID, "vein/meteoric_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NORMAL_GALENA = createKey(AncientGroundCore.MODID, "vein/normal_galena");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALTAERIA = createKey(AncientGroundCore.MODID, "vein/paltaeria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUARTZ = createKey(AncientGroundCore.MODID, "vein/quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUARTZ_SEDIMENTARY = createKey(AncientGroundCore.MODID, "vein/quartz_sedimentary");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHIMMERSTONE = createKey(AncientGroundCore.MODID, "vein/shimmerstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHIMMERSTONE_VOLCANIC = createKey(AncientGroundCore.MODID, "vein/shimmerstone_volcanic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOAPSTONE = createKey(AncientGroundCore.MODID, "vein/soapstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRATINE = createKey(AncientGroundCore.MODID, "vein/stratine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SURFACE_GALENA = createKey(AncientGroundCore.MODID, "vein/surface_galena");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TERRACOTTA = createKey(AncientGroundCore.MODID, "vein/terracotta");
    //endregion

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx){

        CTX = ctx;

        bootstrapModpackFeatures();
        bootstrapTFCFeatures();
    }

    private static void bootstrapModpackFeatures(){
        CTX.register(
                MONSTER_ROOM,
                new ConfiguredFeature<>(CoreWorldGen.MONSTER_ROOM.get(),
                        new NoneFeatureConfiguration()
                )
        );

        //region Geode
        createGeode(AMETHYST_GEODE, Blocks.AMETHYST_BLOCK, Blocks.CALCITE, Blocks.SMOOTH_BASALT, Blocks.BUDDING_AMETHYST, 0.1);
        createGeode(CITRINE_GEODE, SpectrumBlocks.CITRINE_BLOCK.get(), Blocks.CALCITE, Blocks.SMOOTH_BASALT, SpectrumBlocks.BUDDING_CITRINE.get(), 0.1);
        createGeode(TOPAZ_GEODE, SpectrumBlocks.TOPAZ_BLOCK.get(), Blocks.CALCITE, Blocks.SMOOTH_BASALT, SpectrumBlocks.BUDDING_TOPAZ.get(), 0.1);
        createGeode(ONYX_GEODE, SpectrumBlocks.ONYX_BLOCK.get(), Blocks.CALCITE, Blocks.SMOOTH_BASALT, SpectrumBlocks.BUDDING_ONYX.get(), 0.1);
        createGeode(MOONSTONE_GEODE, SpectrumBlocks.MOONSTONE_BLOCK.get(), Blocks.CALCITE, Blocks.SMOOTH_BASALT, SpectrumBlocks.BUDDING_MOONSTONE.get(), 0.1);

        createOreGeode(BISMUTH_DEEPER_DOWN, CoreRocks.ARGILLITE, TFCOres.BISMUTHINITE,
                CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(CoreRocks.ARGILLITE).get(Ore.BISMUTHINITE).get(CoreOres.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(BORAX_DEEPER_DOWN, Rock.SHALE, TFCOres.BORAX,
                TFCBlocks.ROCK_BLOCKS.get(Rock.SHALE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(CASSITERITE_DEEPER_DOWN, Rock.DIORITE, TFCOres.CASSITERITE,
                TFCBlocks.GRADED_ORES.get(Rock.DIORITE).get(Ore.CASSITERITE).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(CINNABAR_DEEPER_DOWN, Rock.GNEISS, TFCOres.CINNABAR,
                TFCBlocks.ROCK_BLOCKS.get(Rock.GNEISS).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(CRYOLITE_DEEPER_DOWN, Rock.GRANITE, TFCOres.CRYOLITE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(DIAMOND_DEEPER_DOWN, Rock.GABBRO, TFCOres.DIAMOND,
                TFCBlocks.ROCK_BLOCKS.get(Rock.GABBRO).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(EMERALD_DEEPER_DOWN, Rock.DIORITE, TFCOres.EMERALD,
                TFCBlocks.ROCK_BLOCKS.get(Rock.DIORITE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(GARNIERITE_DEEPER_DOWN, Rock.GABBRO, TFCOres.GARNIERITE,
                TFCBlocks.GRADED_ORES.get(Rock.GABBRO).get(Ore.GARNIERITE).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(GRAPHITE_DEEPER_DOWN, Rock.MARBLE, TFCOres.GRAPHITE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.MARBLE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(GYPSUM_DEEPER_DOWN, Rock.CLAYSTONE, TFCOres.GYPSUM,
                TFCBlocks.ROCK_BLOCKS.get(Rock.CLAYSTONE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(HALITE_DEEPER_DOWN, Rock.CLAYSTONE, TFCOres.HALITE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.CLAYSTONE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(HEMATITE_DEEPER_DOWN, Rock.ANDESITE, TFCOres.HEMATITE,
                TFCBlocks.GRADED_ORES.get(Rock.ANDESITE).get(Ore.HEMATITE).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.GNEISS).get(Rock.BlockType.HARDENED).get()
        );

        createGeode(KAOLINITE_DEEPER_DOWN,
                TFCBlocks.ROCK_BLOCKS.get(Rock.CHERT).get(Rock.BlockType.HARDENED).get(),
                CoreBlocks.BASIC_ORES.get(CoreOres.BAUXITE).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get(),
                CoreBlocks.BASIC_ORES.get(CoreOres.BAUXITE).get(),
                0.25
        );

        createOreGeode(LAPIS_LAZULI_DEEPER_DOWN, Rock.LIMESTONE, TFCOres.LAPIS_LAZULI,
                TFCBlocks.ROCK_BLOCKS.get(Rock.LIMESTONE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.MARBLE).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(LIGNITE_DEEPER_DOWN, Rock.DOLOMITE, TFCOres.LIGNITE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.DOLOMITE).get(Rock.BlockType.HARDENED).get(),
                CoreBlocks.BASIC_ORES.get(CoreOres.ANTHRACITE).get()
        );

        createOreGeode(LIMONITE_DEEPER_DOWN, CoreRocks.ARKOSE, TFCOres.LIMONITE,
                CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(CoreRocks.ARKOSE).get(Ore.LIMONITE).get(CoreOres.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(MALACHITE_DEEPER_DOWN, CoreRocks.PICRITE_BASALT, CoreOres.MALACHITE,
                CoreBlocks.CUSTOM_ROCK_ORES.get(CoreRocks.PICRITE_BASALT).get(CoreOres.MALACHITE).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(NATIVE_COPPER_DEEPER_DOWN, Rock.ANDESITE, TFCOres.NATIVE_COPPER,
                TFCBlocks.GRADED_ORES.get(Rock.ANDESITE).get(Ore.NATIVE_COPPER).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(NATIVE_GOLD_DEEPER_DOWN, Rock.ANDESITE, TFCOres.NATIVE_GOLD,
                TFCBlocks.GRADED_ORES.get(Rock.ANDESITE).get(Ore.NATIVE_GOLD).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(NATIVE_SILVER_DEEPER_DOWN, Rock.GRANITE, TFCOres.NATIVE_SILVER,
                TFCBlocks.GRADED_ORES.get(Rock.GRANITE).get(Ore.NATIVE_SILVER).get(Ore.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(OPAL_DEEPER_DOWN, Rock.DOLOMITE, TFCOres.OPAL,
                TFCBlocks.ROCK_BLOCKS.get(Rock.DOLOMITE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(PALTAERIA_DEEPER_DOWN, CoreRocks.TRAVERTINE, CoreOres.PALTAERIA,
                CoreBlocks.CUSTOM_ROCK_ORES.get(CoreRocks.TRAVERTINE).get(CoreOres.PALTAERIA).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(RUBY_DEEPER_DOWN, Rock.CHERT, TFCOres.RUBY,
                TFCBlocks.ROCK_BLOCKS.get(Rock.CHERT).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.PHYLLITE).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SALTPETER_DEEPER_DOWN, Rock.SHALE, TFCOres.SALTPETER,
                TFCBlocks.ROCK_BLOCKS.get(Rock.SHALE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SLATE).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SAPPHIRE_DEEPER_DOWN, Rock.MARBLE, TFCOres.SAPPHIRE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.MARBLE).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SHIMERSTONE_DEEPER_DOWN, CoreRocks.KOMATIITE, CoreOres.SHIMMERSTONE,
                CoreBlocks.CUSTOM_ROCK_ORES.get(CoreRocks.KOMATIITE).get(CoreOres.SHIMMERSTONE).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SULFUR_DEEPER_DOWN, Rock.GABBRO, TFCOres.SULFUR,
                TFCBlocks.ROCK_BLOCKS.get(Rock.GABBRO).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SPHALERITE_DEEPER_DOWN, CoreRocks.BLACKSLAG, TFCOres.SPHALERITE,
                CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(CoreRocks.BLACKSLAG).get(Ore.SPHALERITE).get(CoreOres.Grade.POOR).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(STRATINE_DEEPER_DOWN, CoreRocks.ARGILLITE, CoreOres.STRATINE,
                CoreBlocks.CUSTOM_ROCK_ORES.get(CoreRocks.ARGILLITE).get(CoreOres.STRATINE).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.PHYLLITE).get(Rock.BlockType.HARDENED).get()
        );

        createOreGeode(SYLVITE_DEEPER_DOWN, Rock.CHERT, TFCOres.SYLVITE,
                TFCBlocks.ROCK_BLOCKS.get(Rock.CHERT).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ROCK_BLOCKS.get(Rock.PHYLLITE).get(Rock.BlockType.HARDENED).get()
        );
        //endregion

        // See notice at the top of the class.
        /*
        //region Ore Veins
        createDiscVein(ANTHRACITE, 30, 3, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.METAMORPHIC), List.of(), CoreOres.ANTHRACITE),
                Optional.empty(),
                80,
                0.9f,
                -64,
                0,
                false,
                false,
                hash("anthracite"),
                false
        ));

        createPipeVein(AZURITE, 10, 5, 2, 20, 2, 20, 1, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), CoreOres.AZURITE),
                Optional.empty(),
                50,
                0.1f,
                -64,
                70,
                false,
                false,
                hash("azurite_ore"),
                false
        ));

        createClusterVein(AZURITE_VOLCANIC, 10, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), CoreOres.AZURITE),
                Optional.empty(),
                50,
                0.1f,
                0,
                100,
                false,
                false,
                hash("azurite_ore_volcanic"),
                false
        ));

        createDiscVein(BAUXITE, 50, 1, new VeinConfig(
                createOreVeinMap(List.of(), List.of(
                        Rock.DOLOMITE, Rock.CHALK, Rock.LIMESTONE,
                        Rock.BASALT, Rock.RHYOLITE, Rock.GRANITE,
                        Rock.GNEISS, CoreRocks.SERPENTINE, CoreRocks.BLUESCHIST),
                        CoreOres.BAUXITE
                ),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(Rock.GRANITE.getBlock(Rock.BlockType.LOOSE).get().defaultBlockState(), 1d)))
                )),
                600,
                0.8f,
                0,
                120,
                false,
                false,
                hash("bauxite"),
                false
        ));

        createClusterVein(METEORIC_IRON, 14, new VeinConfig(
                createMeteorOreVeinMap(),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(CoreRocks.SUEVITE.getBlock(Rock.BlockType.LOOSE).get().defaultBlockState(), 1d)))
                )),
                800,
                1f,
                30,
                200,
                false,
                false,
                hash("meteoric_iron"),
                false
        ));

        createClusterVein(NORMAL_GALENA, 40, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), CoreOres.GALENA, 15, 25, 60),
                Optional.of(new Indicator(35, 12, 1, 5,
                        new DataWeighted<>(List.of(Pair.of(CoreBlocks.SMALL_ORES.get(CoreOres.GALENA).get().defaultBlockState(), 1d)))
                )),
                300,
                0.6f,
                -30,
                20,
                false,
                false,
                hash("normal_galena"),
                false
        ));

        createClusterVein(PALTAERIA, 20, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE, RockCategory.IGNEOUS_INTRUSIVE), List.of(), CoreOres.PALTAERIA),
                Optional.empty(),
                80,
                0.3f,
                60,
                200,
                false,
                false,
                hash("paltaeria_ore"),
                false
        ));

        createPipeVein(QUARTZ, 30, 2, 6, 16, 0, 2, 0, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.GRANITE, Rock.RHYOLITE, Rock.DACITE, Rock.SCHIST, Rock.QUARTZITE), CoreOres.QUARTZ),
                Optional.empty(),
                40,
                0.5f,
                -64,
                100,
                false,
                false,
                hash("quartz_ore"),
                false
        ));

        createPipeVein(QUARTZ_SEDIMENTARY, 15, 6, 2, 20, 0, 10, 0, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), CoreOres.QUARTZ),
                Optional.empty(),
                80,
                0.1f,
                0,
                100,
                false,
                false,
                hash("quartz_sedimentary"),
                false
        ));

        createDiscVein(SHIMMERSTONE, 50, 3, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), CoreOres.SHIMMERSTONE),
                Optional.empty(),
                180,
                0.1f,
                -35,
                -12,
                true,
                true,
                hash("shimmerstone_ore"),
                false
        ));

        createClusterVein(SHIMMERSTONE_VOLCANIC, 20, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), CoreOres.SHIMMERSTONE),
                Optional.empty(),
                50,
                0.3f,
                0,
                100,
                false,
                false,
                hash("shimmerstone_volcanic_ore"),
                false
        ));

        createDiscVein(SOAPSTONE, 30, 3, new VeinConfig(
                createSoapstoneOreVeinMap(),
                Optional.empty(),
                80,
                0.9f,
                -64,
                80,
                false,
                false,
                hash("soapstone_ore"),
                false
        ));

        createClusterVein(STRATINE, 27, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.METAMORPHIC), List.of(), CoreOres.STRATINE),
                Optional.empty(),
                80,
                0.1f,
                -60,
                30,
                false,
                false,
                hash("stratine_ore"),
                false
        ));

        createClusterVein(SURFACE_GALENA, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(Rock.LIMESTONE, Rock.DOLOMITE), CoreOres.GALENA, 70, 25, 5),
                Optional.empty(),
                200,
                0.3f,
                40,
                130,
                false,
                false,
                hash("surface_galena"),
                false
        ));

        createClusterVein(TERRACOTTA, 20, new VeinConfig(
                createTerracottaOreVeinMap(),
                Optional.empty(),
                80,
                0.9f,
                0,
                128,
                false,
                false,
                hash("terracotta_ore"),
                false
        ));
        //endregion
         */
    }

    private static void bootstrapTFCFeatures(){

        //region Non-Vein
        createSoilDisc(CASSITERITE_DEEP_DEPOSIT, createDepositMap(OreDeposit.CASSITERITE), 3, 10, 3, 0.9f);
        createSoilDisc(CASSITERITE_DEPOSIT, createDepositMap(OreDeposit.CASSITERITE), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_COPPER_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_COPPER), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_COPPER_DEPOSIT, createDepositMap(OreDeposit.NATIVE_COPPER), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_GOLD_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_GOLD), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_GOLD_DEPOSIT, createDepositMap(OreDeposit.NATIVE_GOLD), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_SILVER_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_SILVER), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_SILVER_DEPOSIT, createDepositMap(OreDeposit.NATIVE_SILVER), 1, 3, 2, 0.9f);

        //createCaveVegetation(); // See notice at the top of the class.

        createBoulder(COBBLE_BOULDER, List.of(Rock.BlockType.RAW, Rock.BlockType.COBBLE), false);
        createBoulder(COBBLE_BOULDER_SMALL, List.of(Rock.BlockType.RAW, Rock.BlockType.COBBLE), true);

        createBoulder(MOSSY_BOULDER, List.of(Rock.BlockType.MOSSY_COBBLE, Rock.BlockType.COBBLE), false);
        createBoulder(MOSSY_BOULDER_SMALL, List.of(Rock.BlockType.MOSSY_COBBLE, Rock.BlockType.COBBLE), true);

        createBoulder(RAW_BOULDER, List.of(Rock.BlockType.RAW), false);
        createBoulder(RAW_BOULDER_SMALL, List.of(Rock.BlockType.RAW), true);

        // See notice at the top of the class.
        //createFissure(DIAMOND_VOLCANO_FISSURE, Ore.DIAMOND, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), Blocks.LAVA.defaultBlockState(), 3, 6, 3, 6, 3);
        //createFissure(TOPAZ_VOLCANO_FISSURE, Ore.TOPAZ, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), Blocks.LAVA.defaultBlockState(), 3, 6, 3, 6, 3);
        //createFissure(EMERALD_VOLCANO_SPRING_WATER_FISSURE, Ore.EMERALD, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 3, 6, 3, 6, 3);
        //createFissure(SAPPHIRE_VOLCANO_SPRING_WATER_FISSURE, Ore.SAPPHIRE, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 3, 6, 3, 6, 3);

        //createHotSpring(EMERALD_EMPTY_HOT_SPRING, Ore.EMERALD, Optional.empty(), Blocks.AIR.defaultBlockState(), 14, 5, 2, 3);
        //createHotSpring(EMERALD_HOT_SPRING, Ore.EMERALD, Optional.empty(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 14, 5, 5, 3);
        //createHotSpring(LAVA_HOT_SPRING, null, Optional.empty(), Blocks.LAVA.defaultBlockState(), 10, 0, 0, 0, true, createLavaHotSpringMap());
        //createHotSpring(SAPPHIRE_EMPTY_HOT_SPRING, Ore.SAPPHIRE, Optional.empty(), Blocks.AIR.defaultBlockState(), 14, 5, 5, 3);
        //createHotSpring(SAPPHIRE_HOT_SPRING, Ore.SAPPHIRE, Optional.empty(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 14, 5, 2, 3);

        createSpring(EXTRA_WATER_SURFACE_SPRING, Fluids.WATER.defaultFluidState());
        createSpring(LAVA_SPRING, Fluids.LAVA.defaultFluidState());
        createSpring(LAVA_SURFACE_SPRING, Fluids.LAVA.defaultFluidState());

        createRivulet(OCEAN_RIDGE_RIVULET);
        createRivulet(STRATOVOLCANO_RIVULET);
        createSulfurRivulet(SULFUR_RIVULET);
        //endregion Features

        List<RegistryRock> malachiteFoundIn = List.of(Rock.MARBLE, Rock.LIMESTONE, Rock.DOLOMITE, Rock.CHALK, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE, CoreRocks.ARKOSE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE);

        // See notice at the top of the class.
        /*
        //region Ore Vein Features
        createDiscVein(AMETHYST, 8, 4, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY, RockCategory.METAMORPHIC), List.of(), TFCOres.AMETHYST),
                Optional.empty(),
                25,
                0.2f,
                40,
                60,
                false,
                false,
                hash("amethyst"),
                false
        ));

        createDiscVein(BITUMINOUS_COAL, 50, 3, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.BITUMINOUS_COAL),
                Optional.empty(),
                210,
                0.9f,
                -35,
                -12,
                true,
                true,
                hash("bituminous_coal"),
                false
        ));

        createDiscVein(BORAX, 23, 3, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.CHERT, Rock.CLAYSTONE, Rock.SHALE, CoreRocks.ARKOSE, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE), TFCOres.BORAX),
                Optional.empty(),
                40,
                0.2f,
                40,
                100,
                false,
                false,
                hash("borax"),
                false
        ));

        createClusterVein(CRYOLITE, 18, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.GRANITE, Rock.DIORITE, CoreRocks.PERIDOTITE), TFCOres.CRYOLITE),
                Optional.empty(),
                16,
                0.7f,
                -70,
                -10,
                false,
                false,
                hash("cryolite"),
                false
        ));

        createPipeVein(DIORITE_DIKE, 150, 18, 7, 20, 2, 5, 0, new VeinConfig(
                createDikeVeinMap(Rock.DIORITE),
                Optional.empty(),
                300,
                0.98f,
                -64,
                180,
                false,
                false,
                hash("diorite"),
                false
        ));

        createPipeVein(EMERALD, 60, 5, 5, 13, 0, 2, 0, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.EMERALD),
                Optional.empty(),
                80,
                0.15f,
                -64,
                100,
                false,
                false,
                hash("emerald"),
                false
        ));

        createClusterVein(FAKE_NATIVE_GOLD, 15, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE, RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.PYRITE),
                Optional.empty(),
                16,
                0.35f,
                -50,
                70,
                false,
                false,
                hash("fake_native_gold"),
                false
        ));

        createPipeVein(GABBRO_DIKE, 150, 18, 7, 20, 2, 5, 0, new VeinConfig(
                createDikeVeinMap(Rock.GABBRO),
                Optional.empty(),
                300,
                0.98f,
                -64,
                180,
                false,
                false,
                hash("gabbro"),
                false
        ));

        createPipeVein(GRANITE_DIKE, 150, 18, 7, 20, 2, 5, 0, new VeinConfig(
                createDikeVeinMap(Rock.GRANITE),
                Optional.empty(),
                300,
                0.98f,
                -64,
                180,
                false,
                false,
                hash("granite"),
                false
        ));

        createClusterVein(GRAPHITE, 20, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.GNEISS, Rock.MARBLE, Rock.QUARTZITE, Rock.SCHIST, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE), TFCOres.GRAPHITE),
                Optional.empty(),
                20,
                0.4f,
                -30,
                60,
                false,
                false,
                hash("graphite"),
                false
        ));

        createDiscVein(GRAVEL, 44, 2, new VeinConfig(
                createGravelVeinMap(),
                Optional.empty(),
                30,
                0.98f,
                -64,
                100,
                false,
                false,
                hash("gravel"),
                false
        ));

        createDiscVein(GYPSUM, 25, 5, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.GYPSUM),
                Optional.empty(),
                70,
                0.3f,
                40,
                100,
                false,
                false,
                hash("gypsum"),
                false
        ));

        createDiscVein(HALITE, 35, 4, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.HALITE),
                Optional.empty(),
                110,
                0.85f,
                -45,
                -12,
                true,
                true,
                hash("halite"),
                false
        ));

        createClusterVein(LAPIS_LAZULI, 30, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.LIMESTONE, Rock.MARBLE, CoreRocks.SANDSTONE, CoreRocks.ARKOSE, CoreRocks.RED_SANDSTONE), TFCOres.LAPIS_LAZULI),
                Optional.empty(),
                30,
                0.12f,
                -20,
                80,
                false,
                false,
                hash("lapis_lazuli"),
                false
        ));

        createDiscVein(LIGNITE, 40, 2, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.LIGNITE),
                Optional.empty(),
                160,
                0.85f,
                -20,
                -8,
                true,
                true,
                hash("lignite"),
                false
        ));

        createClusterVein(MONTANE_BISMUTHINITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.BISMUTHINITE, 70, 25, 5),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.BISMUTHINITE).get().defaultBlockState(), 1d)))
                )),
                24,
                0.3f,
                100,
                220,
                false,
                false,
                hash("montane_bismuthinite"),
                false
        ));

        createClusterVein(MONTANE_CASSITERITE, 15, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.CASSITERITE, OreDeposit.CASSITERITE, 35, 40, 25, 10),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.CASSITERITE).get().defaultBlockState(), 1d)))
                )),
                2,
                0.4f,
                80,
                300,
                false,
                false,
                hash("montane_cassiterite"),
                false
        ));

        createClusterVein(MONTANE_CINNABAR, 14, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.GNEISS, Rock.SCHIST, Rock.PHYLLITE, Rock.QUARTZITE, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE), TFCOres.CINNABAR),
                Optional.empty(),
                14,
                0.6f,
                120,
                280,
                false,
                false,
                hash("montane_cinnabar"),
                false
        ));

        createClusterVein(MONTANE_HEMATITE, 25, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.HEMATITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.HEMATITE).get().defaultBlockState(), 1d)))
                )),
                25,
                0.4f,
                90,
                250,
                false,
                false,
                hash("montane_hematite"),
                false
        ));

        createClusterVein(MONTANE_LIMONITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.LIMONITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.LIMONITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.4f,
                90,
                250,
                false,
                false,
                hash("montane_limonite"),
                false
        ));

        createClusterVein(MONTANE_MAGNETITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.MAGNETITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.MAGNETITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.4f,
                90,
                250,
                false,
                false,
                hash("montane_magnetite"),
                false
        ));

        createClusterVein(MONTANE_MALACHITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(), malachiteFoundIn, TFCOres.MALACHITE, 70, 25, 5),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.MALACHITE).get().defaultBlockState(), 1d)))
                )),
                11,
                0.25f,
                40,
                300,
                false,
                false,
                hash("montane_malachite"),
                false
        ));

        createClusterVein(MONTANE_NATIVE_COPPER, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.NATIVE_COPPER, OreDeposit.NATIVE_COPPER, 70, 25, 5, 10),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_COPPER).get().defaultBlockState(), 1d)))
                )),
                16,
                0.25f,
                100,
                300,
                false,
                false,
                hash("montane_native_copper"),
                false
        ));

        createClusterVein(MONTANE_NATIVE_SILVER, 10, new VeinConfig(
                createGradedOreVeinMap(List.of(), List.of(Rock.GRANITE, Rock.DIORITE, CoreRocks.PERIDOTITE), TFCOres.NATIVE_SILVER, 70, 25, 5),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_SILVER).get().defaultBlockState(), 1d)))
                )),
                7,
                0.2f,
                90,
                280,
                false,
                false,
                hash("montane_native_silver"),
                false
        ));

        createClusterVein(MONTANE_SPHALERITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.SPHALERITE, 70, 25, 5),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.SPHALERITE).get().defaultBlockState(), 1d)))
                )),
                20,
                0.3f,
                100,
                220,
                false,
                false,
                hash("montane_sphalerite"),
                false
        ));

        createClusterVein(MONTANE_TETRAHEDRITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.METAMORPHIC), List.of(), TFCOres.TETRAHEDRITE, 70, 25, 5),
                Optional.of(new Indicator(35, 8, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.TETRAHEDRITE).get().defaultBlockState(), 1d)))
                )),
                3,
                0.25f,
                90,
                270,
                false,
                false,
                hash("montane_tetrahedrite"),
                false
        ));

        createClusterVein(NORMAL_BISMUTHINITE, 40, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.BISMUTHINITE, 15, 25, 60),
                Optional.of(new Indicator(35, 0, 1, 4,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.BISMUTHINITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.6f,
                -80,
                20,
                false,
                false,
                hash("normal_bismuthinite"),
                false
        ));

        createClusterVein(NORMAL_CINNABAR, 18, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.GNEISS, Rock.SCHIST, Rock.PHYLLITE, Rock.QUARTZITE, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE), TFCOres.CINNABAR),
                Optional.empty(),
                14,
                0.6f,
                -70,
                10,
                false,
                false,
                hash("normal_cinnabar"),
                false
        ));

        createClusterVein(NORMAL_GARNIERITE, 18, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.GARNIERITE, 35, 40, 25),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.GARNIERITE).get().defaultBlockState(), 1d)))
                )),
                25,
                0.3f,
                -80,
                0,
                false,
                false,
                hash("normal_garnierite"),
                false
        ));

        createClusterVein(NORMAL_MALACHITE, 30, new VeinConfig(
                createGradedOreVeinMap(List.of(), malachiteFoundIn, TFCOres.MALACHITE, 35, 40, 25),
                Optional.of(new Indicator(35, 25, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.MALACHITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.5f,
                -30,
                70,
                false,
                false,
                hash("normal_malachite"),
                false
        ));

        createClusterVein(NORMAL_NATIVE_GOLD, 15, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE, RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.NATIVE_GOLD, 35, 40, 25),
                Optional.of(new Indicator(35, 40, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_GOLD).get().defaultBlockState(), 1d)))
                )),
                90,
                0.25f,
                0,
                70,
                false,
                false,
                hash("normal_native_gold"),
                false
        ));

        createClusterVein(NORMAL_NATIVE_SILVER, 25, new VeinConfig(
                createGradedOreVeinMap(List.of(), List.of(Rock.GRANITE, Rock.DIORITE, Rock.GNEISS, Rock.SCHIST, CoreRocks.PERIDOTITE), TFCOres.NATIVE_SILVER, 15, 25, 60),
                Optional.of(new Indicator(35, 0, 1, 9,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_SILVER).get().defaultBlockState(), 1d)))
                )),
                25,
                0.6f,
                -80,
                20,
                false,
                false,
                hash("normal_native_silver"),
                false
        ));

        createClusterVein(NORMAL_SPHALERITE, 40, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.SPHALERITE, 15, 25, 60),
                Optional.of(new Indicator(35, 0, 1, 5,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.SPHALERITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.6f,
                -80,
                20,
                false,
                false,
                hash("normal_sphalerite"),
                false
        ));

        createClusterVein(NORMAL_TETRAHEDRITE, 40, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.METAMORPHIC), List.of(), TFCOres.TETRAHEDRITE, 35, 40, 25),
                Optional.of(new Indicator(35, 25, 1, 5,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.TETRAHEDRITE).get().defaultBlockState(), 1d)))
                )),
                40,
                0.5f,
                -30,
                30,
                false,
                false,
                hash("normal_tetrahedrite"),
                false
        ));

        createDiscVein(OPAL, 8, 4, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY, RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.OPAL),
                Optional.empty(),
                25,
                0.2f,
                40,
                60,
                false,
                false,
                hash("opal"),
                false
        ));

        createClusterVein(RICH_NATIVE_GOLD, 40, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE), List.of(), TFCOres.NATIVE_GOLD, 15, 35, 60),
                Optional.of(new Indicator(35, 0, 1, 4,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_GOLD).get().defaultBlockState(), 1d)))
                )),
                50,
                0.5f,
                -80,
                20,
                false,
                false,
                hash("rich_native_gold"),
                false
        ));

        createDiscVein(RUBY_MARBLE_BELT, 50, 5, new VeinConfig(
                createRubyMarbleBeltVeinMapChance(),
                Optional.empty(),
                16,
                1f,
                -40,
                -4,
                true,
                true,
                hash("ruby_marble_belt"),
                false
        ));

        createDiscVein(SALTPETER, 35, 5, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.SALTPETER),
                Optional.empty(),
                110,
                0.4f,
                40,
                100,
                false,
                false,
                hash("saltpeter"),
                false
        ));

        createDiscVein(SULFUR, 18, 5, new VeinConfig(
                createOreVeinMap(List.of(RockCategory.IGNEOUS_INTRUSIVE, RockCategory.METAMORPHIC), List.of(), TFCOres.SULFUR),
                Optional.empty(),
                4,
                0.25f,
                -64,
                -45,
                false,
                false,
                hash("sulfur"),
                true
        ));

        createClusterVein(SURFACE_BISMUTHINITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.BISMUTHINITE, 70d, 25d, 5d),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.BISMUTHINITE).get().defaultBlockState(), 1d)))
                )),
                48,
                0.3f,
                40,
                100,
                false,
                false,
                hash("surface_bismuthinite"),
                false
        ));

        createClusterVein(SURFACE_HEMATITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.HEMATITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.HEMATITE).get().defaultBlockState(), 1d)))
                )),
                45,
                0.4f,
                10,
                90,
                false,
                false,
                hash("surface_hematite"),
                false
        ));

        createClusterVein(SURFACE_LIMONITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.LIMONITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.LIMONITE).get().defaultBlockState(), 1d)))
                )),
                90,
                0.4f,
                10,
                90,
                false,
                false,
                hash("surface_limonite"),
                false
        ));

        createClusterVein(SURFACE_MAGNETITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.SEDIMENTARY), List.of(), TFCOres.MAGNETITE, 35, 40, 25),
                Optional.of(new Indicator(35, 24, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.MAGNETITE).get().defaultBlockState(), 1d)))
                )),
                90,
                0.4f,
                10,
                90,
                false,
                false,
                hash("surface_magnetite"),
                false
        ));

        createClusterVein(SURFACE_MALACHITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(), malachiteFoundIn, TFCOres.MALACHITE, 70, 25, 5),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.MALACHITE).get().defaultBlockState(), 1d)))
                )),
                48,
                0.25f,
                40,
                100,
                false,
                false,
                hash("surface_malachite"),
                false
        ));

        createClusterVein(SURFACE_NATIVE_COPPER, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.NATIVE_COPPER, OreDeposit.NATIVE_COPPER, 70, 25, 5, 10),
                Optional.of(new Indicator(35, 14, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.NATIVE_COPPER).get().defaultBlockState(), 1d)))
                )),
                36,
                0.25f,
                40,
                100,
                false,
                false,
                hash("surface_native_copper"),
                false
        ));

        createClusterVein(SURFACE_SPHALERITE, 20, new VeinConfig(
                createGradedOreVeinMap(List.of(RockCategory.IGNEOUS_EXTRUSIVE), List.of(), TFCOres.SPHALERITE, 70, 25, 5),
                Optional.of(new Indicator(35, 12, 1, 0,
                        new DataWeighted<>(List.of(Pair.of(TFCBlocks.SMALL_ORES.get(Ore.SPHALERITE).get().defaultBlockState(), 1d)))
                )),
                40,
                0.3f,
                40,
                100,
                false,
                false,
                hash("surface_sphalerite"),
                false
        ));

        createDiscVein(SYLVITE, 35, 5, new VeinConfig(
                createOreVeinMap(List.of(), List.of(Rock.SHALE, Rock.CLAYSTONE, Rock.CHERT, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE, CoreRocks.ARGILLITE, CoreRocks.ARKOSE, CoreRocks.TRAVERTINE), TFCOres.SYLVITE),
                Optional.empty(),
                60,
                0.35f,
                40,
                100,
                false,
                false,
                hash("sylvite"),
                false
        ));
        //endregion
         */
    }

    //region Functions to create features
    private static void createSoilDisc(ResourceKey<ConfiguredFeature<?, ?>> key, Map<Block, BlockState> map, int minRadius, int maxRadius, int height, float integrity){
        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.SOIL_DISC.get(),
                        new SoilDiscConfig(map, minRadius, maxRadius, height, integrity)
                )
        );
    }

    private static void createCaveVegetation(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            map.putLast(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(
                            Pair.of(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.COBBLE).get().defaultBlockState(), 2d),
                            Pair.of(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.MOSSY_COBBLE).get().defaultBlockState(), 8d)
                    )
            ));
        }

        for (CoreRocks rock : CoreRocks.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(
                            Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.COBBLE).get().defaultBlockState(), 2d),
                            Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.MOSSY_COBBLE).get().defaultBlockState(), 8d)
                    )
            ));
        }

        CTX.register(
                CAVE_VEGETATION,
                new ConfiguredFeature<>(TFCFeatures.CAVE_VEGETATION.get(),
                        new CaveVegetationConfig(map)
                )
        );
    }

    private static void createBoulder(ResourceKey<ConfiguredFeature<?, ?>> key, List<Rock.BlockType> list, boolean isBaby){

        Map<Block, List<BlockState>> map = new LinkedHashMap<>();

        list.forEach(blockType -> {

            Stream.of(Rock.values()).forEach(rock -> {
                map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(),
                        List.of(
                                TFCBlocks.ROCK_BLOCKS.get(rock).get(blockType).get().defaultBlockState()
                        )
                );
            });

            Stream.of(CoreRocks.values()).forEach(rock -> {
                map.put(rock.getBlock(Rock.BlockType.RAW).get(),
                        List.of(
                                rock.getBlock(blockType).get().defaultBlockState()
                        )
                );
            });
        });

        if (isBaby) {
            CTX.register(
                    key,
                    new ConfiguredFeature<>(TFCFeatures.BABY_BOULDER.get(),
                            new BoulderConfig(map)
                    )
            );
        } else {
            CTX.register(
                    key,
                    new ConfiguredFeature<>(TFCFeatures.BOULDER.get(),
                            new BoulderConfig(map)
                    )
            );
        }
    }

    private static void createFissure(ResourceKey<ConfiguredFeature<?, ?>> key, Ore ore, BlockState wallState, BlockState fluidState, int count, int radius, int radiusDeco, int countDeco, int rarityDeco){

        Map<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            RockCategory category = rock.displayCategory().category();
            if (category != RockCategory.SEDIMENTARY){
                map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                        List.of(
                                Pair.of(TFCBlocks.ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                        )
                ));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            RockCategory category = rock.displayCategory().category();
            if (category != RockCategory.SEDIMENTARY){
                map.put(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                        List.of(
                                Pair.of(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                        )
                ));
            }
        }

        FissureConfig.Decoration deco = new FissureConfig.Decoration(map, rarityDeco, radiusDeco, countDeco);

        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.FISSURE.get(),
                        new FissureConfig(Optional.of(wallState), fluidState, count, radius, VerticalAnchor.BOTTOM, 10, 24, 6, Optional.of(deco))
                )
        );
    }

    private static void createHotSpring(ResourceKey<ConfiguredFeature<?, ?>> key, Ore ore, Optional<BlockState> wallState, BlockState fluidState, int radius, int radiusDeco, int countDeco, int rarityDeco){
        createHotSpring(key, ore, wallState, fluidState, radius, radiusDeco, countDeco, rarityDeco, false, Optional.empty());
    }

    private static void createHotSpring(ResourceKey<ConfiguredFeature<?, ?>> key, Ore ore, Optional<BlockState> wallState, BlockState fluidState, int radius, int radiusDeco, int countDeco, int rarityDeco, boolean allowUnderwater, Optional<Map<Block, IWeighted<BlockState>>> replacement){

        Map<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        if (ore != null){
            Stream.of(Rock.values()).forEach(rock -> {

                RockCategory category = rock.displayCategory().category();
                if (category != RockCategory.SEDIMENTARY){
                    map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                            List.of(
                                    Pair.of(TFCBlocks.ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                            )
                    ));
                }
            });

            Stream.of(CoreRocks.values()).forEach(rock -> {
                RockCategory category = rock.displayCategory().category();
                if (category != RockCategory.SEDIMENTARY){
                    map.put(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                            List.of(
                                    Pair.of(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                            )
                    ));
                }
            });
        }

        if (ore != null){
            FissureConfig.Decoration deco = new FissureConfig.Decoration(map, rarityDeco, radiusDeco, countDeco);
            CTX.register(
                    key,
                    new ConfiguredFeature<>(TFCFeatures.HOT_SPRING.get(),
                            new HotSpringConfig(wallState, fluidState, radius, Optional.of(deco), allowUnderwater, replacement)
                    )
            );
        } else {
            CTX.register(
                    key,
                    new ConfiguredFeature<>(TFCFeatures.HOT_SPRING.get(),
                            new HotSpringConfig(wallState, fluidState, radius, Optional.empty(), allowUnderwater, replacement)
                    )
            );
        }
    }

    private static void createSpring(ResourceKey<ConfiguredFeature<?, ?>> key, FluidState fluidState){

        List<Block> list = new ArrayList<Block>();

        Stream.of(Rock.values()).forEach(rock -> {
            list.add(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get());
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            list.add(rock.getBlock(Rock.BlockType.RAW).get());
        });

        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.SPRING.get(),
                        new SpringConfiguration(fluidState, true, 4, 1, CTX.lookup(Registries.BLOCK).getOrThrow(Tags.Blocks.STONES))
                )
        );
    }

    private static void createRivulet(ResourceKey<ConfiguredFeature<?, ?>> key){

        Map<Block, BlockState> map = new LinkedHashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {

            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(
                        TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(),
                        TFCBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState()
                );

                map.put(
                        TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get(),
                        TFCBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState()
                );
            }
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {

            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(
                        rock.getBlock(Rock.BlockType.RAW).get(),
                        CoreBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState()
                );

                map.put(
                        CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get(),
                        CoreBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState()
                );
            }
        });

        map.put(
                TFCBlocks.ROCK_BLOCKS.get(Rock.TUFF).get(Rock.BlockType.RAW).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.ANDESITE).get().defaultBlockState()
        );

        map.put(
                TFCBlocks.ROCK_BLOCKS.get(Rock.TUFF).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.MAGMA_BLOCKS.get(Rock.ANDESITE).get().defaultBlockState()
        );

        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.RIVULET.get(),
                        new BlockStateMapConfig(map)
                )
        );
    }

    private static void createSulfurRivulet(ResourceKey<ConfiguredFeature<?, ?>> key){

        Map<Block, BlockState> map = new LinkedHashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {

            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(
                        TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(),
                        TFCBlocks.ORES.get(rock).get(Ore.SULFUR).get().defaultBlockState()
                );

                map.put(
                        TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get(),
                        TFCBlocks.ORES.get(rock).get(Ore.SULFUR).get().defaultBlockState()
                );
            }
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {

            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(
                        rock.getBlock(Rock.BlockType.RAW).get(),
                        CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(Ore.SULFUR).get().defaultBlockState()
                );

                map.put(
                        CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get(),
                        CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(Ore.SULFUR).get().defaultBlockState()
                );
            }
        });

        map.put(
                TFCBlocks.ROCK_BLOCKS.get(Rock.TUFF).get(Rock.BlockType.RAW).get(),
                TFCBlocks.ORES.get(Rock.TUFF).get(Ore.SULFUR).get().defaultBlockState()
        );

        map.put(
                TFCBlocks.ROCK_BLOCKS.get(Rock.TUFF).get(Rock.BlockType.HARDENED).get(),
                TFCBlocks.ORES.get(Rock.TUFF).get(Ore.SULFUR).get().defaultBlockState()
        );

        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.RIVULET.get(),
                        new BlockStateMapConfig(map)
                )
        );
    }

    private static void createDiscVein(ResourceKey<ConfiguredFeature<?, ?>> key, int size, int height, VeinConfig config){
        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.DISC_VEIN.get(),
                        new DiscVeinConfig(config, size, height)
                )
        );
    }

    private static void createPipeVein(ResourceKey<ConfiguredFeature<?, ?>> key, int height, int radius, int minSkew, int maxSkew, int minSlant, int maxSlant, float sign, VeinConfig config){
        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.PIPE_VEIN.get(),
                        new PipeVeinConfig(config, height, radius, minSkew, maxSkew, minSlant, maxSlant, sign)
                )
        );
    }

    private static void createClusterVein(ResourceKey<ConfiguredFeature<?, ?>> key, int size, VeinConfig config){
        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.CLUSTER_VEIN.get(),
                        new ClusterVeinConfig(config, size)
                )
        );
    }

    private static void createGeode(ResourceKey<ConfiguredFeature<?, ?>> key, Block inner, Block middle, Block outer, Block alt, double altChance){

        new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(alt.defaultBlockState(), 1)
                        .add(alt.defaultBlockState(), 2)
                        .add(alt.defaultBlockState(), 1)
                        .add(inner.defaultBlockState(), 2)
                        .build()
        );

        CTX.register(
                key,
                new ConfiguredFeature<>(Feature.GEODE,
                        new GeodeConfiguration(
                                new GeodeBlockSettings(
                                        SimpleStateProvider.simple(Blocks.AIR.defaultBlockState()), //not default, but is common //filling
                                        SimpleStateProvider.simple(inner), //inner
                                        SimpleStateProvider.simple(alt), //alt inner
                                        SimpleStateProvider.simple(middle), //middle
                                        SimpleStateProvider.simple(outer), //outer
                                        List.of(Blocks.AIR.defaultBlockState()), //not default, but is common
                                        BlockTags.FEATURES_CANNOT_REPLACE, //not default, but is common
                                        BlockTags.GEODE_INVALID_BLOCKS //not default, but is common
                                ),
                                new GeodeLayerSettings(
                                        1.7, //default
                                        2.2, //default
                                        3.2, //default
                                        4.2 //default
                                ),
                                new GeodeCrackSettings(
                                        1, //default
                                        2, //default
                                        2 //default
                                ),
                                0.35, //default
                                altChance, // arg
                                true, //default
                                UniformInt.of(4, 5), //default
                                UniformInt.of(3, 4), //default
                                UniformInt.of(1, 2), //default
                                -16,  //default
                                16, //default
                                0.05,  //default
                                1  //default
                        )
                )
        );
    }

    private static void createOreGeode(ResourceKey<ConfiguredFeature<?, ?>> key, RegistryRock rock, RegistryOre ore, Block middle, Block outer){

        WeightedStateProvider altInner;

        if (ore.isGraded()){
            altInner = new WeightedStateProvider(
                    SimpleWeightedRandomList.<BlockState>builder()
                            .add(ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState(), 1)
                            .add(ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState(), 2)
                            .add(ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState(), 1)
                            .add(rock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 2)
                            .build()
            );
        } else {
            altInner = new WeightedStateProvider(
                    SimpleWeightedRandomList.<BlockState>builder()
                            .add(ore.getOreBlock(rock, null).defaultBlockState(), 2)
                            .add(rock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 2)
                            .build()
            );
        }

        CTX.register(
                key,
                new ConfiguredFeature<>(Feature.GEODE,
                        new GeodeConfiguration(
                                new GeodeBlockSettings(
                                        SimpleStateProvider.simple(Blocks.AIR.defaultBlockState()), //not default, but is common //filling
                                        SimpleStateProvider.simple(rock.getBlock(Rock.BlockType.RAW).get().defaultBlockState()), //inner
                                        altInner, //alt inner
                                        SimpleStateProvider.simple(middle), //middle
                                        SimpleStateProvider.simple(outer), //outer
                                        List.of(Blocks.AIR.defaultBlockState()), //not default, but is common
                                        BlockTags.FEATURES_CANNOT_REPLACE, //not default, but is common
                                        BlockTags.GEODE_INVALID_BLOCKS //not default, but is common
                                ),
                                new GeodeLayerSettings(
                                        1.7, //default
                                        2.2, //default
                                        3.2, //default
                                        4.2 //default
                                ),
                                new GeodeCrackSettings(
                                        1, //default
                                        2, //default
                                        2 //default
                                ),
                                0.35, //default
                                1,
                                true, //default
                                UniformInt.of(4, 5), //default
                                UniformInt.of(3, 4), //default
                                UniformInt.of(1, 2), //default
                                -16,  //default
                                16, //default
                                0.05,  //default
                                1  //default
                        )
                )
        );
    }
    //endregion

    //region Functions to create block maps for features.
    private static Map<Block, BlockState> createDepositMap(OreDeposit deposit){
        Map<Block, BlockState> map = new LinkedHashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {
            map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), TFCBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState());
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            map.put(rock.getBlock(Rock.BlockType.RAW).get(), CoreBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState());
        });

        return map;
    }

    private static Optional<Map<Block, IWeighted<BlockState>>> createLavaHotSpringMap(){
        Map<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {
            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                        List.of(
                                Pair.of(TFCBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState(), 1d),
                                Pair.of(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get().defaultBlockState(), 2d)
                        )
                ));
            }
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<BlockState>(
                        List.of(
                                Pair.of(CoreBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState(), 1d),
                                Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get().defaultBlockState(), 2d)
                        )
                ));
            }
        });

        return Optional.of(map);
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createOreVeinMap(List<RockCategory> categoryList, List<RegistryRock> rockList, RegistryOre ore){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, null).defaultBlockState(), 1d)
                        )
                ));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, null).defaultBlockState(), 1d)
                        )
                ));
            }
        }

        for (RegistryRock rock : rockList){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(
                            Pair.of(ore.getOreBlock(rock, null).defaultBlockState(), 1d)
                    )
            ));
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createGradedOreVeinMap(List<RockCategory> categoryList, List<RegistryRock> rockList, RegistryOre ore, double poorChance, double normalChance, double richChance){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), simpleGradedWeighted(rock, ore, poorChance, normalChance, richChance));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), simpleGradedWeighted(rock, ore, poorChance, normalChance, richChance));
            }
        }

        for (RegistryRock rock : rockList){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), simpleGradedWeighted(rock, ore, poorChance, normalChance, richChance));
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createGradedOreVeinMap(List<RockCategory> categoryList, List<RegistryRock> rockList, RegistryOre ore, OreDeposit deposit, double poorChance, double normalChance, double richChance, double despositChance){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState(), poorChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState(), normalChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState(), richChance),
                                Pair.of(TFCBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState(), despositChance)
                        )
                ));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            if (categoryList.contains(rock.displayCategory().category())){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState(), poorChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState(), normalChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState(), richChance),
                                Pair.of(CoreBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState(), despositChance)
                        )
                ));
            }
        }

        for (RegistryRock rock : rockList){
            if (rock instanceof Rock){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState(), poorChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState(), normalChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState(), richChance),
                                Pair.of(TFCBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState(), despositChance)
                        )
                ));
            }
            if (rock instanceof CoreRocks){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState(), poorChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState(), normalChance),
                                Pair.of(ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState(), richChance),
                                Pair.of(CoreBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState(), despositChance)
                        )
                ));
            }
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createMeteorOreVeinMap(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(
                            Pair.of(CoreRocks.SUEVITE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 65d),
                            Pair.of(CoreRocks.SUEVITE.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 5d),
                            Pair.of(CoreBlocks.BASIC_ORES.get(CoreOres.METEORIC_IRON).get().defaultBlockState(), 5d),
                            Pair.of(rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 25d)
                    )
            ));
        }

        for (CoreRocks rock : CoreRocks.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(
                            Pair.of(CoreRocks.SUEVITE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 65d),
                            Pair.of(CoreRocks.SUEVITE.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 5d),
                            Pair.of(CoreBlocks.BASIC_ORES.get(CoreOres.METEORIC_IRON).get().defaultBlockState(), 5d),
                            Pair.of(rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 25d)
                    )
            ));
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createGravelVeinMap(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(Pair.of(rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 1d))
            ));
        }

        for (CoreRocks rock : CoreRocks.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(Pair.of(rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState(), 1d))
            ));
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createSoapstoneOreVeinMap(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            if (rock.displayCategory().category() == RockCategory.METAMORPHIC){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(CoreRocks.SOAPSTONE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d)
                        )
                ));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            if (rock.displayCategory().category() == RockCategory.METAMORPHIC){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(CoreRocks.SOAPSTONE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d)
                        )
                ));
            }
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createTerracottaOreVeinMap(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        map.put(Rock.GRANITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.PINK_TERRACOTTA));
        map.put(Rock.DIORITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.WHITE_TERRACOTTA));
        map.put(Rock.GABBRO.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.GRAY_TERRACOTTA));
        map.put(Rock.SHALE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIGHT_GRAY_TERRACOTTA));
        map.put(Rock.CLAYSTONE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.TERRACOTTA));
        map.put(Rock.LIMESTONE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.YELLOW_TERRACOTTA));
        map.put(Rock.CONGLOMERATE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BROWN_TERRACOTTA));
        map.put(Rock.DOLOMITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.MAGENTA_TERRACOTTA));
        map.put(Rock.CHERT.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.ORANGE_TERRACOTTA));
        map.put(Rock.CHALK.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.WHITE_TERRACOTTA));
        map.put(Rock.RHYOLITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.GRAY_TERRACOTTA));
        map.put(Rock.BASALT.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BLACK_TERRACOTTA));
        map.put(Rock.ANDESITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIGHT_GRAY_TERRACOTTA));
        map.put(Rock.DACITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIGHT_GRAY_TERRACOTTA));
        map.put(Rock.QUARTZITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.PINK_TERRACOTTA));
        map.put(Rock.SLATE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIGHT_GRAY_TERRACOTTA));
        map.put(Rock.PHYLLITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.GREEN_TERRACOTTA));
        map.put(Rock.SCHIST.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.GREEN_TERRACOTTA));
        map.put(Rock.GNEISS.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BROWN_TERRACOTTA));
        map.put(Rock.MARBLE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.WHITE_TERRACOTTA));
        map.put(Rock.TUFF.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.YELLOW_TERRACOTTA));
        map.put(CoreRocks.ARGILLITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.TERRACOTTA));
        map.put(CoreRocks.NEPHELINITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BLACK_TERRACOTTA));
        map.put(CoreRocks.PICRITE_BASALT.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.PURPLE_TERRACOTTA));
        map.put(CoreRocks.BLACKSLAG.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BLACK_TERRACOTTA));
        map.put(CoreRocks.TRAVERTINE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BROWN_TERRACOTTA));
        map.put(CoreRocks.BRECCIA.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.WHITE_TERRACOTTA));
        map.put(CoreRocks.SUEVITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BLACK_TERRACOTTA));
        map.put(CoreRocks.PERIDOTITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIME_TERRACOTTA));
        map.put(CoreRocks.SERPENTINE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.GREEN_TERRACOTTA));
        map.put(CoreRocks.BLUESCHIST.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BLUE_TERRACOTTA));
        map.put(CoreRocks.ARKOSE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.BROWN_TERRACOTTA));
        map.put(CoreRocks.KOMATIITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.ORANGE_TERRACOTTA));
        map.put(CoreRocks.SOAPSTONE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.WHITE_TERRACOTTA));
        map.put(CoreRocks.SANDSTONE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.YELLOW_TERRACOTTA));
        map.put(CoreRocks.RED_SANDSTONE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.RED_TERRACOTTA));
        map.put(CoreRocks.PHONOLITE.getBlock(Rock.BlockType.RAW).get(), singleWeighted(Blocks.LIGHT_BLUE_TERRACOTTA));

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createDikeVeinMap(RegistryRock baseRock){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d))
            ));
            map.putLast(rock.getBlock(Rock.BlockType.GRAVEL).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d))
            ));
            map.putLast(rock.getBlock(Rock.BlockType.HARDENED).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.HARDENED).get().defaultBlockState(), 1d))
            ));
        }

        for (CoreRocks rock : CoreRocks.values()){
            map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d))
            ));
            map.putLast(rock.getBlock(Rock.BlockType.GRAVEL).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 1d))
            ));
            map.putLast(rock.getBlock(Rock.BlockType.HARDENED).get(), new DataWeighted<>(
                    List.of(Pair.of(baseRock.getBlock(Rock.BlockType.HARDENED).get().defaultBlockState(), 1d))
            ));
        }

        return map;
    }

    private static LinkedHashMap<Block, IWeighted<BlockState>> createRubyMarbleBeltVeinMapChance(){

        LinkedHashMap<Block, IWeighted<BlockState>> map = new LinkedHashMap<>();

        for (Rock rock : Rock.values()){
            if (rock.displayCategory().category() != RockCategory.SEDIMENTARY){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(Rock.MARBLE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 94d),
                                Pair.of(TFCOres.RUBY.getOreBlock(rock, null).defaultBlockState(), 6d)
                        )
                ));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            if (rock.displayCategory().category() != RockCategory.SEDIMENTARY){
                map.putLast(rock.getBlock(Rock.BlockType.RAW).get(), new DataWeighted<>(
                        List.of(
                                Pair.of(Rock.MARBLE.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), 94d),
                                Pair.of(TFCOres.RUBY.getOreBlock(rock, null).defaultBlockState(), 6d)
                        )
                ));
            }
        }

        return map;
    }
    //endregion

    //region Util
    private static DataWeighted<BlockState> singleWeighted(Block block){
        return new DataWeighted<>(List.of(Pair.of(block.defaultBlockState(), 1d)));
    }

    private static DataWeighted<BlockState> simpleGradedWeighted(RegistryRock rock, RegistryOre ore, double poorChance, double normalChance, double richChance){
        DataWeighted<BlockState> weighted = new DataWeighted<>(List.of());

        weighted.add(poorChance, ore.getOreBlock(rock, CoreOres.Grade.POOR).defaultBlockState());
        weighted.add(normalChance, ore.getOreBlock(rock, CoreOres.Grade.NORMAL).defaultBlockState());
        weighted.add(richChance, ore.getOreBlock(rock, CoreOres.Grade.RICH).defaultBlockState());

        return weighted;
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name, String path){
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(name, path)
        );
    }

    private static long hash(String name)
    {
        final RandomSupport.Seed128bit seed128 = RandomSupport.seedFromHashOf(name);
        return seed128.seedLo() ^ seed128.seedHi();
    }
    //endregion
}
