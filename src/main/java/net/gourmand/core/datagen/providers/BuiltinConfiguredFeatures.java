package net.gourmand.core.datagen.providers;

import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.util.collections.IWeighted;
import net.dries007.tfc.util.collections.Weighted;
import net.dries007.tfc.world.feature.*;
import net.dries007.tfc.world.feature.cave.CaveVegetationConfig;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreWorldGen;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;

import java.util.*;
import java.util.stream.Stream;

public class BuiltinConfiguredFeatures  {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MONSTER_ROOM = createKey(AncientGroundCore.MODID, "monster_room");
    public static BootstrapContext<ConfiguredFeature<?, ?>> CTX;

    // TFC non-vein features, but rock related.

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

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx){

        CTX = ctx;

        CTX.register(
            MONSTER_ROOM,
            new ConfiguredFeature<>(CoreWorldGen.MONSTER_ROOM.get(),
                new NoneFeatureConfiguration()
            )
        );

        createSoilDisc(CASSITERITE_DEEP_DEPOSIT, createDepositMap(OreDeposit.CASSITERITE), 3, 10, 3, 0.9f);
        createSoilDisc(CASSITERITE_DEPOSIT, createDepositMap(OreDeposit.CASSITERITE), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_COPPER_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_COPPER), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_COPPER_DEPOSIT, createDepositMap(OreDeposit.NATIVE_COPPER), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_GOLD_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_GOLD), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_GOLD_DEPOSIT, createDepositMap(OreDeposit.NATIVE_GOLD), 1, 3, 2, 0.9f);

        createSoilDisc(NATIVE_SILVER_DEEP_DEPOSIT, createDepositMap(OreDeposit.NATIVE_SILVER), 3, 10, 3, 0.9f);
        createSoilDisc(NATIVE_SILVER_DEPOSIT, createDepositMap(OreDeposit.NATIVE_SILVER), 1, 3, 2, 0.9f);

        createCaveVegetation();

        createBoulder(COBBLE_BOULDER, List.of(Rock.BlockType.RAW, Rock.BlockType.COBBLE), false);
        createBoulder(COBBLE_BOULDER_SMALL, List.of(Rock.BlockType.RAW, Rock.BlockType.COBBLE), true);

        createBoulder(MOSSY_BOULDER, List.of(Rock.BlockType.MOSSY_COBBLE, Rock.BlockType.COBBLE), false);
        createBoulder(MOSSY_BOULDER_SMALL, List.of(Rock.BlockType.MOSSY_COBBLE, Rock.BlockType.COBBLE), true);

        createBoulder(RAW_BOULDER, List.of(Rock.BlockType.RAW), false);
        createBoulder(RAW_BOULDER_SMALL, List.of(Rock.BlockType.RAW), true);

        createFissure(DIAMOND_VOLCANO_FISSURE, Ore.DIAMOND, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), Blocks.LAVA.defaultBlockState(), 3, 6, 3, 6, 3);
        createFissure(TOPAZ_VOLCANO_FISSURE, Ore.TOPAZ, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), Blocks.LAVA.defaultBlockState(), 3, 6, 3, 6, 3);
        createFissure(EMERALD_VOLCANO_SPRING_WATER_FISSURE, Ore.EMERALD, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 3, 6, 3, 6, 3);
        createFissure(SAPPHIRE_VOLCANO_SPRING_WATER_FISSURE, Ore.SAPPHIRE, Rock.BASALT.getBlock(Rock.BlockType.RAW).get().defaultBlockState(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 3, 6, 3, 6, 3);

        createHotSpring(EMERALD_EMPTY_HOT_SPRING, Ore.EMERALD, Optional.empty(), Blocks.AIR.defaultBlockState(), 14, 5, 2, 3);
        createHotSpring(EMERALD_HOT_SPRING, Ore.EMERALD, Optional.empty(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 14, 5, 5, 3);
        createHotSpring(LAVA_HOT_SPRING, null, Optional.empty(), Blocks.LAVA.defaultBlockState(), 10, 0, 0, 0, true, createLavaHotSpringMap());
        createHotSpring(SAPPHIRE_EMPTY_HOT_SPRING, Ore.SAPPHIRE, Optional.empty(), Blocks.AIR.defaultBlockState(), 14, 5, 5, 3);
        createHotSpring(SAPPHIRE_HOT_SPRING, Ore.SAPPHIRE, Optional.empty(), TFCBlocks.SPRING_WATER.get().defaultBlockState(), 14, 5, 2, 3);

        createSpring(EXTRA_WATER_SURFACE_SPRING, Fluids.WATER.defaultFluidState());
        createSpring(LAVA_SPRING, Fluids.LAVA.defaultFluidState());
        createSpring(LAVA_SURFACE_SPRING, Fluids.LAVA.defaultFluidState());

        createRivulet(OCEAN_RIDGE_RIVULET);
        createRivulet(STRATOVOLCANO_RIVULET);
        createSulfurRivulet(SULFUR_RIVULET);
    }

    private static void createSoilDisc(ResourceKey<ConfiguredFeature<?, ?>> key, Map<Block, BlockState> map, int minRadius, int maxRadius, int height, float integrity){
        CTX.register(
                key,
                new ConfiguredFeature<>(TFCFeatures.SOIL_DISC.get(),
                        new SoilDiscConfig(map, minRadius, maxRadius, height, integrity)
                )
        );
    }

    private static void createCaveVegetation(){

        Map<Block, IWeighted<BlockState>> map = new HashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {
            map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                    List.of(
                            Pair.of(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.COBBLE).get().defaultBlockState(), 2d),
                            Pair.of(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.MOSSY_COBBLE).get().defaultBlockState(), 8d)
                    )
            ));
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            map.put(rock.getBlock(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                    List.of(
                            Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.COBBLE).get().defaultBlockState(), 2d),
                            Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.MOSSY_COBBLE).get().defaultBlockState(), 8d)
                    )
            ));
        });

        CTX.register(
                CAVE_VEGETATION,
                new ConfiguredFeature<>(TFCFeatures.CAVE_VEGETATION.get(),
                        new CaveVegetationConfig(map)
                )
        );
    }

    private static void createBoulder(ResourceKey<ConfiguredFeature<?, ?>> key, List<Rock.BlockType> list, boolean isBaby){

        Map<Block, List<BlockState>> map = new HashMap<>();

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

        Map<Block, IWeighted<BlockState>> map = new HashMap<>();

        Stream.of(Rock.values()).forEach(rock -> {

            RockCategory category = rock.displayCategory().category();
            if (category != RockCategory.SEDIMENTARY){
                map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                        List.of(
                                Pair.of(TFCBlocks.ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                        )
                ));
            }
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            RockCategory category = rock.displayCategory().category();
            if (category != RockCategory.SEDIMENTARY){
                map.put(rock.getBlock(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                        List.of(
                                Pair.of(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                        )
                ));
            }
        });

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

        Map<Block, IWeighted<BlockState>> map = new HashMap<>();

        if (ore != null){
            Stream.of(Rock.values()).forEach(rock -> {

                RockCategory category = rock.displayCategory().category();
                if (category != RockCategory.SEDIMENTARY){
                    map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                            List.of(
                                    Pair.of(TFCBlocks.ORES.get(rock).get(ore).get().defaultBlockState(), 1d)
                            )
                    ));
                }
            });

            Stream.of(CoreRocks.values()).forEach(rock -> {
                RockCategory category = rock.displayCategory().category();
                if (category != RockCategory.SEDIMENTARY){
                    map.put(rock.getBlock(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
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

        Map<Block, BlockState> map = new HashMap<>();

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

        Map<Block, BlockState> map = new HashMap<>();

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

    private static Map<Block, BlockState> createDepositMap(OreDeposit deposit){
        Map<Block, BlockState> map = new HashMap<Block, BlockState>();

        Stream.of(Rock.values()).forEach(rock -> {
            map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), TFCBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState());
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            map.put(rock.getBlock(Rock.BlockType.RAW).get(), CoreBlocks.ORE_DEPOSITS.get(rock).get(deposit).get().defaultBlockState());
        });

        return map;
    }

    private static Optional<Map<Block, IWeighted<BlockState>>> createLavaHotSpringMap(){
        Map<Block, IWeighted<BlockState>> map = new HashMap<Block, IWeighted<BlockState>>();

        Stream.of(Rock.values()).forEach(rock -> {
            RockCategory category = rock.displayCategory().category();
            if (category == RockCategory.IGNEOUS_INTRUSIVE || category == RockCategory.IGNEOUS_EXTRUSIVE){
                map.put(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
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
                map.put(rock.getBlock(Rock.BlockType.RAW).get(), new Weighted<BlockState>(
                        List.of(
                                Pair.of(CoreBlocks.MAGMA_BLOCKS.get(rock).get().defaultBlockState(), 1d),
                                Pair.of(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.HARDENED).get().defaultBlockState(), 2d)
                        )
                ));
            }
        });

        return Optional.of(map);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name, String path){
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(name, path)
        );
    }
}
