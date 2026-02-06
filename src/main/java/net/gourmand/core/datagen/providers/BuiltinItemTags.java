package net.gourmand.core.datagen.providers;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BuiltinItemTags extends TagsProvider<Item> implements Accessors
{
    private final ExistingFileHelper.IResourceType resourceType;

    final TagKey<Item> SHIMMERSTONE_ORES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("pastel", "shimmerstone_ores"));
    final TagKey<Item> AZURITE_ORES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("pastel", "azurite_ores"));

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
                this.tag(Tags.Items.ORES).add(getKey(CoreBlocks.BASIC_ORES.get(ore)));
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

        for (Rock rock : Rock.values())
        {
            this.tag(AZURITE_ORES).add(getKey(CoreBlocks.ORES.get(rock).get(CoreOres.AZURITE)));
            this.tag(SHIMMERSTONE_ORES).add(getKey(CoreBlocks.ORES.get(rock).get(CoreOres.SHIMMERSTONE)));
        }

        for (CoreRocks rock : CoreRocks.values())
        {
            this.tag(AZURITE_ORES).add(getKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rock).get(CoreOres.AZURITE)));
            this.tag(SHIMMERSTONE_ORES).add(getKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rock).get(CoreOres.SHIMMERSTONE)));
        }
    }

    protected void add(Map<?, DeferredHolder<Block, Block>> map, List<TagKey<Item>> tags ){
        for (DeferredHolder<Block, Block> block : map.values()){
            for (TagKey<Item> tag : tags){
                this.tag(tag).add(getKey(block));
            };
        }
    };

    protected ResourceKey<Item> getKey(DeferredHolder<Block, Block> block ){
        return block.get().asItem().builtInRegistryHolder().key();
    };

    private <T1 extends RegistryRock, T2> void addOreTags(Map<T1, Map<T2, DeferredHolder<Block, Block>>> map, T2 ore, T1 rock){
        DeferredHolder<Block, Block> block = map.get(rock).get(ore);
        this.tag(Tags.Items.ORES).add(getKey(block));
    };

    private <T1 extends RegistryRock, T2, T3 extends CoreOres.Grade> void addGradedOreTags(Map<T1, Map<T2, Map<T3, DeferredHolder<Block, Block>>>> map, T2 ore, T1 rock){

        for (CoreOres.Grade grade : CoreOres.Grade.values()){
            DeferredHolder<Block, Block> block = map.get(rock).get(ore).get(grade);
            this.tag(Tags.Items.ORES).add(getKey(block));
        }
    };
}
