package net.gourmand.core.registry.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.*;

import java.util.stream.Stream;

public class OthersideBiomeSource extends BiomeSource {

    public static final MapCodec<OthersideBiomeSource> CODEC = RecordCodecBuilder.mapCodec((source) -> source.group(RegistryOps.retrieveElement(Biomes.THE_END), RegistryOps.retrieveElement(Biomes.FLOWER_FOREST), RegistryOps.retrieveElement(Biomes.MUSHROOM_FIELDS), RegistryOps.retrieveElement(Biomes.SMALL_END_ISLANDS), RegistryOps.retrieveElement(Biomes.END_BARRENS)).apply(source,source.stable(OthersideBiomeSource::new)));
    private final Holder<Biome> end;
    private final Holder<Biome> highlands;
    private final Holder<Biome> midlands;
    private final Holder<Biome> islands;
    private final Holder<Biome> barrens;

    public static OthersideBiomeSource create(HolderGetter<Biome> biomeGetter) {
        return new OthersideBiomeSource(biomeGetter.getOrThrow(Biomes.THE_END), biomeGetter.getOrThrow(Biomes.FLOWER_FOREST), biomeGetter.getOrThrow(Biomes.MUSHROOM_FIELDS), biomeGetter.getOrThrow(Biomes.SMALL_END_ISLANDS), biomeGetter.getOrThrow(Biomes.END_BARRENS));
    }

    private OthersideBiomeSource(Holder<Biome> end, Holder<Biome> highlands, Holder<Biome> midlands, Holder<Biome> islands, Holder<Biome> barrens) {
        this.end = end;
        this.highlands = highlands;
        this.midlands = midlands;
        this.islands = islands;
        this.barrens = barrens;
    }

    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(this.end, this.highlands, this.midlands, this.islands, this.barrens);
    }

    protected MapCodec<? extends BiomeSource> codec() {
        return CODEC;
    }

    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        int i = QuartPos.toBlock(x);
        int j = QuartPos.toBlock(y);
        int k = QuartPos.toBlock(z);
        int l = SectionPos.blockToSectionCoord(i);
        int i1 = SectionPos.blockToSectionCoord(k);

        long d = (long)l * l + (long) i1 * i1;
        long d1 = 4096L;

        if (d <= d1) return this.end;
        if (d <= d1 * 2) return this.highlands;
        if (d <= d1 * 3) return this.midlands;
        return this.barrens;
    }
}
