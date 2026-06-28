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
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class BuiltinPlacedFeatures {

    public static final ResourceKey<PlacedFeature> MONSTER_ROOM = ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "monster_room")
    );

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx){

        HolderGetter<ConfiguredFeature<?, ?>> registry = ctx.lookup(Registries.CONFIGURED_FEATURE);

        ctx.register(
                MONSTER_ROOM,
                new PlacedFeature(
                        registry.getOrThrow(BuiltinConfiguredFeatures.MONSTER_ROOM),
                        List.of(
                                CountPlacement.of(16),
                                InSquarePlacement.spread(),
                                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(64)))
                        )
                )
        );
    }
}
