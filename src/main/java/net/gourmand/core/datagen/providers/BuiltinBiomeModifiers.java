package net.gourmand.core.datagen.providers;

import de.dafuqs.spectrum.registries.SpectrumBiomeKeys;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class BuiltinBiomeModifiers
{

    public static final ResourceKey<BiomeModifier> ADD_DEEPER_DOWN_ORES = createKey(AncientGroundCore.MOD_ID, "add_deeper_down_ores");
    public static final ResourceKey<BiomeModifier> ADD_WILD_AMARANTH = createKey(AncientGroundCore.MOD_ID, "add_wild_amaranth");
    public static final ResourceKey<BiomeModifier> ADD_DEEPER_DOWN_HARDENING = createKey(AncientGroundCore.MOD_ID, "add_deeper_down_hardening");

    public static void bootstrap(BootstrapContext<BiomeModifier> ctx)
    {
        HolderGetter<PlacedFeature> placedFeatures = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = ctx.lookup(Registries.BIOME);

        ctx.register(ADD_DEEPER_DOWN_ORES, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(CoreTags.Biomes.IN_DEEPER_DOWN),
                placedFeatures.getOrThrow(BuiltinPlacedFeatureTags.DD_ORE_VEINS),
                GenerationStep.Decoration.UNDERGROUND_DECORATION)
        );

        ctx.register(ADD_WILD_AMARANTH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(SpectrumBiomeKeys.CRYSTAL_GARDENS)),
                HolderSet.direct(placedFeatures.getOrThrow(BuiltinPlacedFeatures.AMARANTH_PATCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION)
        );

        ctx.register(ADD_DEEPER_DOWN_HARDENING, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(CoreTags.Biomes.IN_DEEPER_DOWN),
                HolderSet.direct(placedFeatures.getOrThrow(BuiltinPlacedFeatures.HARDENING)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION)
        );
    }

    private static ResourceKey<BiomeModifier> createKey(String name, String path){
        return ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(name, path)
        );
    }
}
