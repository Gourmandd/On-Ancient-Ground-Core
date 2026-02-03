package net.gourmand.core.datagen.providers;


import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BuiltinBlockTags extends TagsProvider<Block> implements Accessors
{
    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinBlockTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.BLOCK, lookup, AncientGroundCore.MODID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        add(CoreBlocks.FRUIT_TREE_LEAVES, List.of(
            BlockTags.LEAVES,
            BlockTags.MINEABLE_WITH_HOE,
            TFCTags.Blocks.FRUIT_TREE_LEAVES)
        );

        add(CoreBlocks.FRUIT_TREE_SAPLINGS, List.of(
            BlockTags.MINEABLE_WITH_HOE,
            TFCTags.Blocks.MINEABLE_WITH_HOE,
            TFCTags.Blocks.MINEABLE_WITH_SCYTHE,
            TFCTags.Blocks.MINEABLE_WITH_KNIFE,
            TFCTags.Blocks.FRUIT_TREE_SAPLING)
        );

        add(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES, List.of(
                BlockTags.MINEABLE_WITH_AXE,
                TFCTags.Blocks.FRUIT_TREE_BRANCH)
        );

        for (CoreRocks rock : CoreRocks.values())
        {
            for (Ore ore : Ore.values())
            {
                if (ore.hasBlock())
                {
                    if (ore.isGraded())
                    {
                        addGradedOreTags(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES, ore, rock);
                    }
                    else
                    {
                        addOreTags(CoreBlocks.CUSTOM_ROCK_TFC_ORES, ore, rock);
                    }
                }
            }
        }

        for (CoreOres ore : CoreOres.values())
        {
            // if it's an ore such as bituminous coal
            if (!ore.hasBlock())
            {
                ResourceKey<Block> key = CoreBlocks.BASIC_ORES.get(ore).getKey();
                this.tag(Tags.Blocks.ORES).add(key);
                this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);
                this.tag(TFCTags.Blocks.PROSPECTABLE).add(key);
            }
            else
            {
                for (CoreRocks rock : CoreRocks.values())
                {
                    if (ore.hasBlock())
                    {
                        if (ore.isGraded())
                        {
                            addGradedOreTags(CoreBlocks.CUSTOM_ROCK_GRADED_ORES, ore, rock);
                        }
                        else
                        {
                            addOreTags(CoreBlocks.CUSTOM_ROCK_ORES, ore, rock);
                        }
                    }
                }
                for (Rock rock : Rock.values())
                {
                    if (ore.hasBlock())
                    {
                        if (ore.isGraded())
                        {
                            addGradedOreTags(CoreBlocks.GRADED_ORES, ore, rock);
                        }
                        else
                        {
                            addOreTags(CoreBlocks.ORES, ore, rock);
                        }
                    }
                }
            }
        }
    }

    protected void add(Map<?, DeferredHolder<Block, Block>> map, List<TagKey<Block>> tags ){
        for (DeferredHolder<Block, Block> block : map.values()){
            for (TagKey<Block> tag : tags){
                this.tag(tag).add(block.getKey());
            };
        }
    };

    private <T1 extends RegistryRock, T2> void addOreTags(Map<T1, Map<T2, DeferredHolder<Block, Block>>> map, T2 ore, T1 rock){
        ResourceKey<Block> key = map.get(rock).get(ore).getKey();
        this.tag(Tags.Blocks.ORES).add(key);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);
        this.tag(TFCTags.Blocks.PROSPECTABLE).add(key);
    };

    private <T1 extends RegistryRock, T2, T3 extends CoreOres.Grade> void addGradedOreTags(Map<T1, Map<T2, Map<T3, DeferredHolder<Block, Block>>>> map, T2 ore, T1 rock){

        for (CoreOres.Grade grade : CoreOres.Grade.values()){
            ResourceKey<Block> key = map.get(rock).get(ore).get(grade).getKey();
            this.tag(Tags.Blocks.ORES).add(key);
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);
            this.tag(TFCTags.Blocks.PROSPECTABLE).add(key);
        }
    };
}
