package net.gourmand.core.registry;

import com.mojang.serialization.MapCodec;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.worldgen.ModpackMonsterRoom;
import net.gourmand.core.registry.worldgen.OthersideBiomeSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CoreWorldGen {

    public static final DeferredRegister<MapCodec<? extends BiomeSource>> BIOME_SOURCES = DeferredRegister.create(BuiltInRegistries.BIOME_SOURCE, AncientGroundCore.MOD_ID);
    public static final DeferredRegister<Feature<?>> CONFIGURED_FEATURE_TYPE = DeferredRegister.create(BuiltInRegistries.FEATURE, AncientGroundCore.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends BiomeSource>, MapCodec<OthersideBiomeSource>> OTHERSIDE_BIOME_SOURCE = BIOME_SOURCES.register( "otherside", () -> OthersideBiomeSource.CODEC);

    public static final DeferredHolder<Feature<?>, ModpackMonsterRoom> MONSTER_ROOM = CONFIGURED_FEATURE_TYPE.register( "monster_room", () -> new ModpackMonsterRoom(NoneFeatureConfiguration.CODEC));
}
