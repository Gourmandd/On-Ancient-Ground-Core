package net.gourmand.core.registry;

import com.mojang.serialization.MapCodec;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.worldgen.OthersideBiomeSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.BiomeSource;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CoreWorldGen {

    public static final DeferredRegister<MapCodec<? extends BiomeSource>> BIOME_SOURCES = DeferredRegister.create(BuiltInRegistries.BIOME_SOURCE, AncientGroundCore.MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeSource>, MapCodec<OthersideBiomeSource>> OTHERSIDE_BIOME_SOURCE = BIOME_SOURCES.register( "otherside", () -> OthersideBiomeSource.CODEC);
}
