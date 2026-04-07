package net.gourmand.core.registry.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.gourmand.core.AncientGroundCore;
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
        int i = QuartPos.toBlock(x); // Multiplies x by 4
        int j = QuartPos.toBlock(y); // Multiplies x by 4
        int k = QuartPos.toBlock(z); // Multiplies x by 4
        int l = SectionPos.blockToSectionCoord(i);  // (x * 4)/16
        int i1 = SectionPos.blockToSectionCoord(k);  // (z * 4)/16

        long d = ((long)l * l + (long) i1 * i1); // ((x * 4)/16)^2 + ((z * 4)/16)^2. creates a circular shape.
        long d1 = d >> 4;

        long THRESHOLD = 4096L;

        // keeping debug in, but set false in commited versions.
        if (y == 50 && false){
            AncientGroundCore.LOGGER.info("{} {} {} {} C: {} {} {}", d, l * l, i1 * i1, l, x, y, z);
        }

        if (d <= THRESHOLD) return this.end;
        if (d1 >> 1 <= THRESHOLD) return this.islands;
        // from here on each zone is twice the size of the previous.
        if (d1 >> 2 <= THRESHOLD) return this.highlands;
        if (d1 >> 3 <= THRESHOLD) return this.midlands;
        if (d1 >> 4 <= THRESHOLD) return this.highlands;
        if (d1 >> 5 <= THRESHOLD) return this.islands;
        return this.barrens;
    }
}
