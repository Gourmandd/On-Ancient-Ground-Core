package net.gourmand.core.datagen.providers;


import net.dries007.tfc.common.TFCTags;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class BuiltinFluidTags extends TagsProvider<Fluid> implements Accessors
{
    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinFluidTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.FLUID, lookup, AncientGroundCore.MODID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
            this.tag(TFCTags.Fluids.MOLTEN_METALS).add(getKey(metal.getFluid()));
        });

        Stream.of(DyeColor.values()).forEach(color -> {
            this.tag(TFCTags.Fluids.MOLTEN_METALS).add(CoreFluids.COLORED_GLASS.get(color).source().getKey());
        });
        this.tag(TFCTags.Fluids.MOLTEN_METALS).add(CoreFluids.CLEAR_GLASS.source().getKey());
    }

    protected void add(Map<?, DeferredHolder<Fluid, Fluid>> map, List<TagKey<Fluid>> tags ){
        for (DeferredHolder<Fluid, Fluid> fluid : map.values()){
            for (TagKey<Fluid> tag : tags){
                this.tag(tag).add(fluid.getKey());
            };
        }
    };

    protected ResourceKey<Fluid> getKey(Fluid fluid ){
        return fluid.builtInRegistryHolder().getKey();
    }
}
