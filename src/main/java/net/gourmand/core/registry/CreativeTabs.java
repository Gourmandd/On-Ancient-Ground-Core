package net.gourmand.core.registry;

import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.blocks.CoreDecorationBlockHolder;
import net.gourmand.core.registry.category.*;
import net.gourmand.core.registry.category.CoreDeeperDownWood;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AncientGroundCore.MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> METAL = register("metal", () -> new ItemStack(CoreItems.METAL_ITEMS.get(CoreMetals.MetalType.ALUMINIUM).get(Metal.ItemType.INGOT)), CreativeTabs::fillMetal);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NATURE = register("nature", () -> new ItemStack(CoreItems.CROP_SEEDS.get(CoreCrops.AMARANTH)), CreativeTabs::fillNature);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES = register("ores", () -> new ItemStack(CoreBlocks.ORES.get(Rock.ANDESITE).get(CoreOres.AZURITE).get()), CreativeTabs::fillOres);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ROCKS = register("rocks", () -> new ItemStack(CoreBlocks.ROCK_BLOCKS.get(CoreRocks.NEPHELINITE).get(Rock.BlockType.HARDENED).get()), CreativeTabs::fillRocks);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WOOD = register("wood", () -> new ItemStack(CoreBlocks.DEEPER_DOWN_WOODS.get(CoreDeeperDownWood.GREEN).get(Wood.BlockType.LOOM).get()), CreativeTabs::fillWood);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = register("tools", () -> new ItemStack(CoreItems.SNOW_SHOVEL), CreativeTabs::fillTools);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CERAMICS = register("ceramics", () -> new ItemStack(CoreItems.CERAMICS.get(CoreClay.YIXING).get(CoreClay.ItemType.VESSEL)), CreativeTabs::fillCeramics);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GLASS = register("glass", () -> new ItemStack(CoreBlocks.CLEAR_MOLTEN_GLASS.get()), CreativeTabs::fillGlass);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MISC = register("misc", () -> new ItemStack(CoreBlocks.PRISMATIC_ICE.get()), CreativeTabs::fillMisc);


    private static void fillNature(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for (CoreCrops crop : CoreCrops.values())
        {
            accept(out, CoreBlocks.WILD_CROPS, crop);
            accept(out, CoreItems.CROP_SEEDS, crop);
        }

        for (CoreSpreadingBushes bush : CoreSpreadingBushes.values())
        {
            accept(out, CoreBlocks.SPREADING_BUSHES, bush);
        }

        for (CoreStationaryBushes bush : CoreStationaryBushes.values())
        {
            accept(out, CoreBlocks.STATIONARY_BUSHES, bush);
        }

        for (CoreFruitTrees tree : CoreFruitTrees.values())
        {
            accept(out, CoreBlocks.FRUIT_TREE_SAPLINGS, tree);
            accept(out, CoreBlocks.FRUIT_TREE_LEAVES, tree);
        }
    }

    private static void fillMetal(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for(CoreMetals.MetalType metal : CoreMetals.MetalType.values())
        {
            for (Metal.ItemType type : Metal.ItemType.values())
            {
                accept(out, CoreItems.METAL_ITEMS, metal, type);
            }

            for (Metal.BlockType type : Metal.BlockType.values())
            {
                accept(out, CoreBlocks.METALS, metal, type);
            }
        }
    }

    private static void fillOres(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for (CoreOres ore : CoreOres.values())
        {
            if (ore.isGraded())
            {
                accept(out, CoreBlocks.SMALL_ORES, ore);
                accept(out, CoreItems.GRADED_ORES, ore, CoreOres.Grade.POOR);
                accept(out, CoreItems.GRADED_ORES, ore, CoreOres.Grade.NORMAL);
                accept(out, CoreItems.GRADED_ORES, ore, CoreOres.Grade.RICH);
            }
        }

        CoreBlocks.BASIC_ORES.values().forEach( basicOre ->
                out.accept(basicOre.get())
        );

        for (CoreOres ore : CoreOres.values())
        {
            if (!(ore.isGraded() || ore.hasSpectrumOreType())) accept(out, CoreItems.ORES, ore);
        }

        for (CoreOres ore : CoreOres.values())
        {
            if (ore.isGraded())
            {
                CoreBlocks.GRADED_ORES.values().forEach(map -> {
                        accept(out, map, ore, CoreOres.Grade.POOR);
                        accept(out, map, ore, CoreOres.Grade.NORMAL);
                        accept(out, map, ore, CoreOres.Grade.RICH);
                    }
                );

                CoreBlocks.CUSTOM_ROCK_GRADED_ORES.values().forEach(map -> {
                            accept(out, map, ore, CoreOres.Grade.POOR);
                            accept(out, map, ore, CoreOres.Grade.NORMAL);
                            accept(out, map, ore, CoreOres.Grade.RICH);
                        }
                );
            }
            else
            {
                if (ore.hasBlock())
                {
                    CoreBlocks.ORES.values().forEach(map -> accept(out, map, ore));
                    CoreBlocks.CUSTOM_ROCK_ORES.values().forEach(map -> accept(out, map, ore));
                }
            }
        }

        for (Ore ore : Ore.values())
        {
            if (ore.isGraded())
            {
                CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.values().forEach(map -> {
                            accept(out, map, ore, CoreOres.Grade.POOR);
                            accept(out, map, ore, CoreOres.Grade.NORMAL);
                            accept(out, map, ore, CoreOres.Grade.RICH);
                        }
                );
            }
            else
            {
                CoreBlocks.CUSTOM_ROCK_TFC_ORES.values().forEach(map -> accept(out, map, ore));
            }
        }

        for (CoreRocks rock : CoreRocks.values()){
            for (OreDeposit ore : OreDeposit.values()){
                out.accept(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore).get());
            }
        }
    }

    private static void fillRocks(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for (CoreRocks rock : CoreRocks.VALUES)
        {
            for (Rock.BlockType type : new Rock.BlockType[] {
                    Rock.BlockType.HARDENED,
                    Rock.BlockType.RAW,
                    Rock.BlockType.PRESSURE_PLATE,
                    Rock.BlockType.BUTTON,
                    Rock.BlockType.SPIKE,
                    Rock.BlockType.COBBLE,
                    Rock.BlockType.MOSSY_COBBLE,
                    Rock.BlockType.BRICKS,
                    Rock.BlockType.CRACKED_BRICKS,
                    Rock.BlockType.MOSSY_BRICKS,
                    Rock.BlockType.SMOOTH,
                    Rock.BlockType.CHISELED,
                    Rock.BlockType.AQUEDUCT,
                    Rock.BlockType.GRAVEL,
                    Rock.BlockType.LOOSE,
                    Rock.BlockType.MOSSY_LOOSE,
            })
            {
                if (rock.hasVariant(type))
                {
                    accept(out, CoreBlocks.ROCK_BLOCKS, rock, type);
                }

                if (type.hasVariants() && rock.hasVariant(type))
                {
                    accept(out, CoreBlocks.ROCK_DECORATIONS.get(rock).get(type));
                }
            }

            accept(out, CoreItems.BRICKS, rock);
            accept(out, CoreBlocks.MORTARED_CUSTOM_COBBLE, rock);
        }

        for (Rock rock : Rock.values()){
            accept(out, CoreBlocks.MORTARED_TFC_COBBLE, rock);
        }
    }

    private static void fillTools(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        out.accept(CoreItems.SNOW_SHOVEL.get());
        out.accept(CoreItems.SNOW_SHOVEL_HEAD.get());

        out.accept(CoreItems.GLASS_MOLD.get());
        out.accept(CoreItems.GLASS_PANE_MOLD.get());

        out.accept(CoreItems.WROUGHT_IRON_BUCKET.get());
    }

    private static void fillCeramics(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for (CoreClay clay : CoreClay.values())
        {
            for (CoreClay.ItemType type : CoreClay.ItemType.values())
            {
                if (type.hasType(clay)){
                    out.accept(CoreItems.CERAMICS.get(clay).get(type).get());
                }
            }

            Stream.of(CoreClay.BlockType.values()).forEach(type -> {

                if (type.hasClayType(clay)){
                    out.accept(CoreBlocks.CERAMIC_BLOCKS.get(clay).get(type).get());
                }

                if (type.getType() == CoreClay.BlockPartType.BLOCK_SET){
                    out.accept(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).stair().get());
                    out.accept(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).slab().get());
                    out.accept(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).wall().get());
                }
            });
        }
    }

    private static void fillWood(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        for (CoreDeeperDownWood wood : CoreDeeperDownWood.values())
        {
            CoreBlocks.DEEPER_DOWN_WOODS.get(wood).forEach((type,reg) ->
            {
                if (type.needsItem() && wood.hasBlockType(type))
                {
                    out.accept(reg.get());
                }
            });

            out.accept(CoreItems.SUPPORTS.get(wood).get());
            out.accept(CoreItems.LUMBER.get(wood).get());
        }
    }

    private static void fillGlass(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {
        Stream.of(DyeColor.values()).forEach(color -> {
            out.accept(CoreItems.COLORED_LENS.get(color).get());
            out.accept(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get());
            out.accept(CoreBlocks.COLOURED_LEAD_GLASS.get(color).get());
            out.accept(CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get());
        });

        out.accept(CoreBlocks.CLEAR_MOLTEN_GLASS.get());
        out.accept(CoreBlocks.CLEAR_LEAD_GLASS.get());
        out.accept(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get());
    }

    private static void fillMisc(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out)
    {

        out.accept(CoreBlocks.SLUDGE.get());
        out.accept(CoreBlocks.PRISMATIC_ICE.get());
    }

    private static <R extends DeferredHolder<?, ?>, K1, K2> void accept(CreativeModeTab.Output out, Map<K1, Map<K2, R>> map, K1 key1, K2 key2)
    {
        if (map.containsKey(key1))
        {
            accept(out, map.get(key1), key2);
        }
    }

    private static void accept(CreativeModeTab.Output out, CoreDecorationBlockHolder decoration)
    {
        out.accept(decoration.stair().get());
        out.accept(decoration.slab().get());
        out.accept(decoration.wall().get());
    }

    private static <R extends DeferredHolder<?, ?>, K> void accept(CreativeModeTab.Output out, Map<K, R> map, K key)
    {
        if (map.containsKey(key)) {
            out.accept((ItemLike) map.get(key));
        }
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String id, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator gen)
    {
        return CREATIVE_TABS.register(id, () -> CreativeModeTab.builder()
                .icon(icon)
                .title(Component.translatable("item_group." + id + "." + AncientGroundCore.MODID))
                .displayItems(gen)
                .build()
        );
    }
}
