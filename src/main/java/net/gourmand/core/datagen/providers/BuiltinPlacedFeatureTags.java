package net.gourmand.core.datagen.providers;

import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class BuiltinPlacedFeatureTags extends TagsProvider<PlacedFeature> {

    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinPlacedFeatureTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.PLACED_FEATURE, lookup, AncientGroundCore.MOD_ID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    private static final TagKey<PlacedFeature> TFC_ORE_VEINS = TagKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/veins")
    );

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // port the other tag entries to this
//        this.tag(TFC_ORE_VEINS)
//                .add(BuiltinPlacedFeatures.MONSTER_ROOM)
//                .replace(false);
    }
}
