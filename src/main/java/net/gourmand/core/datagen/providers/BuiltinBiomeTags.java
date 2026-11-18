package net.gourmand.core.datagen.providers;

import de.dafuqs.spectrum.registries.SpectrumBiomeKeys;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class BuiltinBiomeTags extends TagsProvider<Biome>
{
    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinBiomeTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.BIOME, lookup, AncientGroundCore.MOD_ID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(CoreTags.Biomes.IN_DEEPER_DOWN)
                .add(SpectrumBiomeKeys.BLACK_LANGAST)
                .add(SpectrumBiomeKeys.CRYSTAL_GARDENS)
                .add(SpectrumBiomeKeys.DEEP_BARRENS)
                .add(SpectrumBiomeKeys.DRAGONROT_SWAMP)
                .add(SpectrumBiomeKeys.DEEP_DRIPSTONE_CAVES)
                .add(SpectrumBiomeKeys.HOWLING_SPIRES)
                .add(SpectrumBiomeKeys.NOXSHROOM_FOREST)
                .add(SpectrumBiomeKeys.RAZOR_EDGE);
    }
}
