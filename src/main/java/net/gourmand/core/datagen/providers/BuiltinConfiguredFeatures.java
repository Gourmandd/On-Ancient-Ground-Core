package net.gourmand.core.datagen.providers;

import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreWorldGen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BuiltinConfiguredFeatures  {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MONSTER_ROOM = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "monster_room")
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx){

        ctx.register(
            MONSTER_ROOM,
            new ConfiguredFeature<>(CoreWorldGen.MONSTER_ROOM.get(),
                new NoneFeatureConfiguration()
            )
        );
    }
}
