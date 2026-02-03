package net.gourmand.core.datagen.providers;

import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BuiltinItemTags extends TagsProvider<Item> implements Accessors
{
    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinItemTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.ITEM, lookup, AncientGroundCore.MODID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        add(CoreBlocks.FRUIT_TREE_LEAVES, List.of(
                ItemTags.LEAVES)
        );

        add(CoreBlocks.FRUIT_TREE_SAPLINGS, List.of(
                ItemTags.SAPLINGS)
        );
    }

    protected void add(Map<?, DeferredHolder<Block, Block>> map, List<TagKey<Item>> tags ){
        for (DeferredHolder<Block, Block> block : map.values()){
            for (TagKey<Item> tag : tags){
                this.tag(tag).add(block.get().asItem().builtInRegistryHolder().key());
            };
        }
    };
}
