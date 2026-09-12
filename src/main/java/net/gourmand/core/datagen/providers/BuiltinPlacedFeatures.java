package net.gourmand.core.datagen.providers;

import net.athebyne.exclusions_lib.predicates.OverlapsStructureBlockPredicate;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BuiltinPlacedFeatures {

    public static BootstrapContext<PlacedFeature> CTX;
    public static HolderGetter<ConfiguredFeature<?, ?>> REGISTRY;

    public static final ResourceKey<PlacedFeature> MONSTER_ROOM = createKey(AncientGroundCore.MOD_ID, "monster_room");

    public static final ResourceKey<PlacedFeature> AMETHYST_GEODE = createKey(AncientGroundCore.MOD_ID, "vein/geode/amethyst");
    public static final ResourceKey<PlacedFeature> CITRINE_GEODE = createKey(AncientGroundCore.MOD_ID, "vein/geode/citrine");
    public static final ResourceKey<PlacedFeature> TOPAZ_GEODE = createKey(AncientGroundCore.MOD_ID, "vein/geode/topaz");
    public static final ResourceKey<PlacedFeature> ONYX_GEODE = createKey(AncientGroundCore.MOD_ID, "vein/geode/onyx");
    public static final ResourceKey<PlacedFeature> MOONSTONE_GEODE = createKey(AncientGroundCore.MOD_ID, "vein/geode/moonstone");

    public static final ResourceKey<PlacedFeature> BISMUTH_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/bismuth");
    public static final ResourceKey<PlacedFeature> BORAX_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/borax");
    public static final ResourceKey<PlacedFeature> CASSITERITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/cassiterite");
    public static final ResourceKey<PlacedFeature> CINNABAR_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/cinnabar");
    public static final ResourceKey<PlacedFeature> CRYOLITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/cryolite");
    public static final ResourceKey<PlacedFeature> DIAMOND_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/diamond");
    public static final ResourceKey<PlacedFeature> EMERALD_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/emerald");
    public static final ResourceKey<PlacedFeature> GARNIERITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/garnierite");
    public static final ResourceKey<PlacedFeature> GRAPHITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/graphite");
    public static final ResourceKey<PlacedFeature> GYPSUM_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/gypsum");
    public static final ResourceKey<PlacedFeature> HALITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/halite");
    public static final ResourceKey<PlacedFeature> HEMATITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/hematite");
    public static final ResourceKey<PlacedFeature> KAOLINITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/kaolinite");
    public static final ResourceKey<PlacedFeature> LAPIS_LAZULI_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/lapis_lazuli");
    public static final ResourceKey<PlacedFeature> LIGNITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/lignite");
    public static final ResourceKey<PlacedFeature> LIMONITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/limonite");
    public static final ResourceKey<PlacedFeature> MALACHITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/malachite");
    public static final ResourceKey<PlacedFeature> NATIVE_COPPER_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/native_copper");
    public static final ResourceKey<PlacedFeature> NATIVE_GOLD_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/native_gold");
    public static final ResourceKey<PlacedFeature> NATIVE_SILVER_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/native_silver");
    public static final ResourceKey<PlacedFeature> OPAL_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/opal");
    public static final ResourceKey<PlacedFeature> PALTAERIA_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/paltaeria");
    public static final ResourceKey<PlacedFeature> RUBY_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/ruby");
    public static final ResourceKey<PlacedFeature> SALTPETER_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/saltpeter");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/sapphite");
    public static final ResourceKey<PlacedFeature> SHIMERSTONE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/shimmerstone");
    public static final ResourceKey<PlacedFeature> SPHALERITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/sphalerite");
    public static final ResourceKey<PlacedFeature> STRATINE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/stratine");
    public static final ResourceKey<PlacedFeature> SULFUR_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/sulfur");
    public static final ResourceKey<PlacedFeature> SYLVITE_DEEPER_DOWN = createKey(AncientGroundCore.MOD_ID, "vein/deeper_down/sylvite");

    public static final ResourceKey<PlacedFeature> ANTHRACITE = createKey(AncientGroundCore.MOD_ID, "vein/anthracite");
    public static final ResourceKey<PlacedFeature> AZURITE = createKey(AncientGroundCore.MOD_ID, "vein/azurite");
    public static final ResourceKey<PlacedFeature> AZURITE_VOLCANIC = createKey(AncientGroundCore.MOD_ID, "vein/azurite_volcanic");
    public static final ResourceKey<PlacedFeature> BAUXITE = createKey(AncientGroundCore.MOD_ID, "vein/bauxite");
    public static final ResourceKey<PlacedFeature> METEORIC_IRON = createKey(AncientGroundCore.MOD_ID, "vein/meteoric_iron");
    public static final ResourceKey<PlacedFeature> NORMAL_GALENA = createKey(AncientGroundCore.MOD_ID, "vein/normal_galena");
    public static final ResourceKey<PlacedFeature> PALTAERIA = createKey(AncientGroundCore.MOD_ID, "vein/paltaeria");
    public static final ResourceKey<PlacedFeature> QUARTZ = createKey(AncientGroundCore.MOD_ID, "vein/quartz");
    public static final ResourceKey<PlacedFeature> QUARTZ_SEDIMENTARY = createKey(AncientGroundCore.MOD_ID, "vein/quartz_sedimentary");
    public static final ResourceKey<PlacedFeature> SHIMMERSTONE = createKey(AncientGroundCore.MOD_ID, "vein/shimmerstone");
    public static final ResourceKey<PlacedFeature> SHIMMERSTONE_VOLCANIC = createKey(AncientGroundCore.MOD_ID, "vein/shimmerstone_volcanic");
    public static final ResourceKey<PlacedFeature> MARLSTONE = createKey(AncientGroundCore.MOD_ID, "vein/marlstone");
    public static final ResourceKey<PlacedFeature> STRATINE = createKey(AncientGroundCore.MOD_ID, "vein/stratine");
    public static final ResourceKey<PlacedFeature> SURFACE_GALENA = createKey(AncientGroundCore.MOD_ID, "vein/surface_galena");
    public static final ResourceKey<PlacedFeature> TERRACOTTA = createKey(AncientGroundCore.MOD_ID, "vein/terracotta");

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx){

        CTX = ctx;
        REGISTRY = ctx.lookup(Registries.CONFIGURED_FEATURE);

        create(MONSTER_ROOM, BuiltinConfiguredFeatures.MONSTER_ROOM,
                List.of(
                        CountPlacement.of(16),
                        InSquarePlacement.spread(),
                        createHeight(-48, 64)
                )
        );

        createDeeperDownGeode(BISMUTH_DEEPER_DOWN, BuiltinConfiguredFeatures.BISMUTH_DEEPER_DOWN);
        createDeeperDownGeode(BORAX_DEEPER_DOWN, BuiltinConfiguredFeatures.BORAX_DEEPER_DOWN);
        createDeeperDownGeode(CASSITERITE_DEEPER_DOWN, BuiltinConfiguredFeatures.CASSITERITE_DEEPER_DOWN);
        createDeeperDownGeode(CINNABAR_DEEPER_DOWN, BuiltinConfiguredFeatures.CINNABAR_DEEPER_DOWN);
        createDeeperDownGeode(CRYOLITE_DEEPER_DOWN, BuiltinConfiguredFeatures.CRYOLITE_DEEPER_DOWN);
        createDeeperDownGeode(DIAMOND_DEEPER_DOWN, BuiltinConfiguredFeatures.DIAMOND_DEEPER_DOWN);
        createDeeperDownGeode(EMERALD_DEEPER_DOWN, BuiltinConfiguredFeatures.EMERALD_DEEPER_DOWN);
        createDeeperDownGeode(GARNIERITE_DEEPER_DOWN, BuiltinConfiguredFeatures.GARNIERITE_DEEPER_DOWN);
        createDeeperDownGeode(GRAPHITE_DEEPER_DOWN, BuiltinConfiguredFeatures.GRAPHITE_DEEPER_DOWN);
        createDeeperDownGeode(GYPSUM_DEEPER_DOWN, BuiltinConfiguredFeatures.GYPSUM_DEEPER_DOWN);
        createDeeperDownGeode(HALITE_DEEPER_DOWN, BuiltinConfiguredFeatures.HALITE_DEEPER_DOWN);
        createDeeperDownGeode(HEMATITE_DEEPER_DOWN, BuiltinConfiguredFeatures.HEMATITE_DEEPER_DOWN);
        createDeeperDownGeode(KAOLINITE_DEEPER_DOWN, BuiltinConfiguredFeatures.KAOLINITE_DEEPER_DOWN);
        createDeeperDownGeode(LAPIS_LAZULI_DEEPER_DOWN, BuiltinConfiguredFeatures.LAPIS_LAZULI_DEEPER_DOWN);
        createDeeperDownGeode(LIGNITE_DEEPER_DOWN, BuiltinConfiguredFeatures.LIGNITE_DEEPER_DOWN);
        createDeeperDownGeode(LIMONITE_DEEPER_DOWN, BuiltinConfiguredFeatures.LIMONITE_DEEPER_DOWN);
        createDeeperDownGeode(MALACHITE_DEEPER_DOWN, BuiltinConfiguredFeatures.KAOLINITE_DEEPER_DOWN);
        createDeeperDownGeode(NATIVE_COPPER_DEEPER_DOWN, BuiltinConfiguredFeatures.NATIVE_COPPER_DEEPER_DOWN);
        createDeeperDownGeode(NATIVE_GOLD_DEEPER_DOWN, BuiltinConfiguredFeatures.NATIVE_GOLD_DEEPER_DOWN);
        createDeeperDownGeode(NATIVE_SILVER_DEEPER_DOWN, BuiltinConfiguredFeatures.NATIVE_SILVER_DEEPER_DOWN);
        createDeeperDownGeode(OPAL_DEEPER_DOWN, BuiltinConfiguredFeatures.OPAL_DEEPER_DOWN);
        createDeeperDownGeode(PALTAERIA_DEEPER_DOWN, BuiltinConfiguredFeatures.PALTAERIA_DEEPER_DOWN);
        createDeeperDownGeode(RUBY_DEEPER_DOWN, BuiltinConfiguredFeatures.RUBY_DEEPER_DOWN);
        createDeeperDownGeode(SALTPETER_DEEPER_DOWN, BuiltinConfiguredFeatures.SALTPETER_DEEPER_DOWN);
        createDeeperDownGeode(SAPPHIRE_DEEPER_DOWN, BuiltinConfiguredFeatures.SAPPHIRE_DEEPER_DOWN);
        createDeeperDownGeode(SHIMERSTONE_DEEPER_DOWN, BuiltinConfiguredFeatures.SHIMERSTONE_DEEPER_DOWN);
        createDeeperDownGeode(SPHALERITE_DEEPER_DOWN, BuiltinConfiguredFeatures.SPHALERITE_DEEPER_DOWN);
        createDeeperDownGeode(STRATINE_DEEPER_DOWN, BuiltinConfiguredFeatures.STRATINE_DEEPER_DOWN);
        createDeeperDownGeode(SULFUR_DEEPER_DOWN, BuiltinConfiguredFeatures.SULFUR_DEEPER_DOWN);
        createDeeperDownGeode(SYLVITE_DEEPER_DOWN, BuiltinConfiguredFeatures.SYLVITE_DEEPER_DOWN);

        createGeode(AMETHYST_GEODE, BuiltinConfiguredFeatures.AMETHYST_GEODE,
                UniformHeight.of(
                        VerticalAnchor.aboveBottom(16),
                        VerticalAnchor.absolute(32)
                ),
                24
        );

        createGeode(CITRINE_GEODE, BuiltinConfiguredFeatures.CITRINE_GEODE,
                UniformHeight.of(
                        VerticalAnchor.aboveBottom(40),
                        VerticalAnchor.absolute(40)
                ),
                40
        );

        createGeode(TOPAZ_GEODE, BuiltinConfiguredFeatures.TOPAZ_GEODE,
                TrapezoidHeight.of(
                        VerticalAnchor.absolute(120),
                        VerticalAnchor.absolute(384)
                ),
                10
        );

        createGeode(MOONSTONE_GEODE, BuiltinConfiguredFeatures.MOONSTONE_GEODE,
                UniformHeight.of(
                        VerticalAnchor.aboveBottom(12),
                        VerticalAnchor.aboveBottom(30)
                ),
                24
        );

        createGeode(ONYX_GEODE, BuiltinConfiguredFeatures.ONYX_GEODE,
                UniformHeight.of(
                        VerticalAnchor.aboveBottom(12),
                        VerticalAnchor.aboveBottom(30)
                ),
                24
        );

        createBlank(ANTHRACITE, BuiltinConfiguredFeatures.ANTHRACITE);
        createBlank(AZURITE, BuiltinConfiguredFeatures.AZURITE);
        createBlank(AZURITE_VOLCANIC, BuiltinConfiguredFeatures.AZURITE_VOLCANIC);
        createBlank(BAUXITE, BuiltinConfiguredFeatures.BAUXITE);
        createBlank(METEORIC_IRON, BuiltinConfiguredFeatures.METEORIC_IRON);
        createBlank(NORMAL_GALENA, BuiltinConfiguredFeatures.NORMAL_GALENA);
        createBlank(PALTAERIA, BuiltinConfiguredFeatures.PALTAERIA);
        createBlank(QUARTZ, BuiltinConfiguredFeatures.QUARTZ);
        createBlank(QUARTZ_SEDIMENTARY, BuiltinConfiguredFeatures.QUARTZ_SEDIMENTARY);
        createBlank(SHIMMERSTONE, BuiltinConfiguredFeatures.SHIMMERSTONE);
        createBlank(SHIMMERSTONE_VOLCANIC, BuiltinConfiguredFeatures.SHIMMERSTONE_VOLCANIC);
        createBlank(MARLSTONE, BuiltinConfiguredFeatures.MARLSTONE);
        createBlank(STRATINE, BuiltinConfiguredFeatures.STRATINE);
        createBlank(SURFACE_GALENA, BuiltinConfiguredFeatures.SURFACE_GALENA);
        createBlank(TERRACOTTA, BuiltinConfiguredFeatures.TERRACOTTA);
    }

    private static void createDeeperDownGeode(ResourceKey<PlacedFeature> keyPlaced, ResourceKey<ConfiguredFeature<?, ?>> keyConfigured){
        createGeode(keyPlaced, keyConfigured,
                    UniformHeight.of(
                            VerticalAnchor.aboveBottom(12),
                            VerticalAnchor.aboveBottom(300)
                    ),
                40
        );
    }

    private static void createGeode(ResourceKey<PlacedFeature> keyPlaced, ResourceKey<ConfiguredFeature<?, ?>> keyConfigured, HeightProvider height, int rarity){
        create(keyPlaced, keyConfigured,
                List.of(
                        RarityFilter.onAverageOnceEvery(rarity),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(height),
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(BlockPredicate.not(new OverlapsStructureBlockPredicate(Vec3i.ZERO, HolderSet.empty(), 7)))
                )
        );
    }

    private static void createBlank(ResourceKey<PlacedFeature> keyPlaced, ResourceKey<ConfiguredFeature<?, ?>> keyConfigured){
        create(keyPlaced, keyConfigured, List.of());
    }

    private static ResourceKey<PlacedFeature> createKey(String name, String path){
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(name, path)
        );
    }

    private static void create(ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> keyFeature, List<PlacementModifier> list){
        CTX.register(key, new PlacedFeature(REGISTRY.getOrThrow(keyFeature), list));
    }

    private static HeightRangePlacement createHeight(int min, int max){
        return HeightRangePlacement.of(
                UniformHeight.of(
                        VerticalAnchor.absolute(min),
                        VerticalAnchor.absolute(max)
                )
        );
    }
}
