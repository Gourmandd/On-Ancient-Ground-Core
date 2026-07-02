package net.gourmand.core.datagen.providers;

import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BuiltinPlacedFeatures {

    public static BootstrapContext<PlacedFeature> CTX;
    public static HolderGetter<ConfiguredFeature<?, ?>> REGISTRY;

    public static final ResourceKey<PlacedFeature> MONSTER_ROOM = createKey(AncientGroundCore.MODID, "monster_room");

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
