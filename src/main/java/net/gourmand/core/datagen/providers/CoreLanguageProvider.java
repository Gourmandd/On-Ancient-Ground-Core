package net.gourmand.core.datagen.providers;

import com.klikli_dev.modonomicon.api.datagen.AbstractModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.*;
import net.gourmand.core.util.TextUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Locale;
import java.util.function.Supplier;

public class CoreLanguageProvider extends AbstractModonomiconLanguageProvider
{

    public CoreLanguageProvider(PackOutput packOutput, ModonomiconLanguageProvider cachedProvider)
    {
        super(packOutput, AncientGroundCore.MOD_ID, "en_us", cachedProvider);
    }

    @Override
    protected void addTranslations()
    {

        // misc lang
        add("item_group.metal.modpack", "Modpack Metal Items");
        add("item_group.nature.modpack", "Modpack Nature Items");
        add("item_group.ores.modpack", "Modpack Ores");
        add("item_group.rocks.modpack", "Modpack Rock Items");
        add("item_group.wood.modpack", "Modpack Wood");
        add("item_group.tools.modpack", "Modpack Tools");
        add("item_group.ceramics.modpack", "Modpack Ceramic Items");
        add("item_group.glass.modpack", "Modpack Glass");
        add("item_group.misc.modpack", "Modpack Misc Items");

        add("modpack.key.open_modpack_guide", "Open Modpack Guide");
        add("modpack.key.open_tfc_guide", "Open TFC Field Guide");

        // shutters doesn't do this.
        add("block.shutter.interaction_shutter_wood", "Shutter Interaction");
        add("block.shutter.interaction_shutter_iron", "Shutter Interaction");
        add("block.shutter.interaction_shutter_netherite", "Shutter Interaction");
        add("block.shutter.interaction_shutter_copper", "Shutter Interaction");
        add("block.shutter.interaction_shutter_glass", "Shutter Interaction");

        addItem(CoreItems.GLASS_MOLD, "Glass Block Mold");
        addItem(CoreItems.GLASS_PANE_MOLD, "Glass Pane Mold");

        addItem(CoreItems.SNOW_SHOVEL, "Snow Shovel");
        addItem(CoreItems.SNOW_SHOVEL_HEAD, "Snow Shovel Head");

        addItem(CoreItems.WROUGHT_IRON_BUCKET, "Wrought Iron Bucket");
        add(CoreItems.WROUGHT_IRON_BUCKET.get().getDescriptionId() + ".filled", "%s Wrought Iron Bucket");

        // bulk lang
        for (CoreCrops cropType : CoreCrops.values())
        {
            addItem(CoreItems.CROP_SEEDS.get(cropType), getName(cropType) + " Seeds");
            addBlock(CoreBlocks.WILD_CROPS.get(cropType), "Wild " + getName(cropType));
            addBlock(CoreBlocks.CROPS.get(cropType), getName(cropType));
            addBlock(CoreBlocks.DEAD_CROPS.get(cropType), "Dead " + getName(cropType));
        }

        for (CoreSpreadingBushes bushType : CoreSpreadingBushes.values())
        {
            addBlock(CoreBlocks.SPREADING_BUSHES.get(bushType), getName(bushType) + " Bush");
        }

        for (CoreStationaryBushes bushType : CoreStationaryBushes.values())
        {
            addBlock(CoreBlocks.STATIONARY_BUSHES.get(bushType), getName(bushType) + " Bush");
        }

        for (CoreFruitTrees treeType : CoreFruitTrees.values())
        {
            addBlock(CoreBlocks.FRUIT_TREE_LEAVES.get(treeType), getName(treeType) + " Leaves");
            addBlock(CoreBlocks.FRUIT_TREE_BRANCHES.get(treeType), getName(treeType) + " Branch");
            addBlock(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(treeType), getName(treeType) + " Growing Branch");
            addBlock(CoreBlocks.FRUIT_TREE_SAPLINGS.get(treeType), getName(treeType) + " Sapling");
            addBlock(CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.get(treeType), getName(treeType) + " Potted Sapling");
        }

        // custom clay types.
        for (CoreClay clayType : CoreClay.values())
        {
            for (CoreClay.ItemType itemType : CoreClay.ItemType.values())
            {
                if (itemType.hasType(clayType))
                {
                    if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD)
                    {
                        addItem(CoreItems.CERAMICS.get(clayType).get(itemType), "Unfired " + getName(clayType) + " " + getName(itemType.name()) + " Mold");
                    }
                    else
                    {
                        addItem(CoreItems.CERAMICS.get(clayType).get(itemType), getName(clayType) + " " + getName(itemType.name()));
                    }
                }
            }
            add("item." + AncientGroundCore.MOD_ID + ".ceramic." + clayType.getSerializedName() + ".jug.filled", "%s " + getName(clayType) + " Jug");
            add("emi." + TerraFirmaCraft.MOD_ID + ".ceramic." + clayType.getSerializedName() + "_knapping", getName(clayType) + " Knapping");
        }

        // custom rocks.
        for (CoreRocks rockType : CoreRocks.values())
        {
            for (Rock.BlockType blockType : Rock.BlockType.values())
            {
                if (rockType.hasVariant(blockType))
                {
                    if (blockType.hasVariants())
                    {
                        if (isRockTypePrefixed(blockType))
                        {
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).stair(), getName(blockType) + " " + getName(rockType) + " Stairs");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).slab(), getName(blockType) + " " + getName(rockType) + " Slab");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).wall(), getName(blockType) + " " + getName(rockType) + " Wall");
                        }
                        else
                        {
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).stair(), getName(rockType) + " " + getName(blockType) + " Stairs");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).slab(), getName(rockType) + " " + getName(blockType) + " Slab");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).wall(), getName(rockType) + " " + getName(blockType) + " Wall");
                        }
                    }

                    if (isRockTypePrefixed(blockType))
                    {
                        addBlock(CoreBlocks.ROCK_BLOCKS.get(rockType).get(blockType), getName(blockType) + " " + getName(rockType));
                    }
                    else
                    {
                        addBlock(CoreBlocks.ROCK_BLOCKS.get(rockType).get(blockType), getName(rockType) + " " + getName(blockType));
                    }

                }
            }
            addItem(CoreItems.BRICKS.get(rockType), getName(rockType) + " Brick");
        }

        for (SpectrumWood woodType : SpectrumWood.values())
        {
            for (Wood.BlockType blockType : Wood.BlockType.values())
            {
                if (blockType.needsItem() && woodType.hasBlockType(blockType) && blockType != Wood.BlockType.SAPLING)
                {
                    addBlock(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType), getName(woodType) + " " + getName(blockType.name()));
                }
            }
            if (woodType.getSpectrumWoodType() == SpectrumWood.SpectrumWoodType.WEEPING_GALA)
            {
                addBlock(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.SAPLING), getName(woodType) + " Sprig");
            }
            else
            {
                addBlock(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.SAPLING), getName(woodType) + " " + getName(Wood.BlockType.SAPLING.name()));
            }
            addItem(CoreItems.SUPPORTS.get(woodType), getName(woodType) + " Support");
            addItem(CoreItems.LUMBER.get(woodType), getName(woodType) + " Lumber");
        }

        // custom metals.
        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            add("metal." + AncientGroundCore.MOD_ID + "." + metalType.getSerializedName(), getName(metalType));
            for (Metal.ItemType itemType : Metal.ItemType.values())
            {
                if (itemType.has(metalType.getLikeMetal()))
                {
                    addItem(CoreItems.METAL_ITEMS.get(metalType).get(itemType), getName(metalType) + " " + getName(itemType.name()));
                }
            }
            if (!metalType.hasOtherFluid())
            {
                add(CoreBlocks.METAL_FLUIDS.get(metalType).get(), getName(metalType));
                add(CoreItems.METAL_FLUID_BUCKETS.get(metalType).get(), getName(metalType) + " Bucket");
                add(CoreFluids.METALS.get(metalType).type().get().getDescriptionId(), getName(metalType));
            }
            for (Metal.BlockType blockType : Metal.BlockType.values())
            {
                if (blockType.has(metalType.getLikeMetal()))
                {
                    addBlock(CoreBlocks.METALS.get(metalType).get(blockType), getName(metalType) + " " + getName(blockType.name()));
                }
            }
        }

        // TODO: find out if TFC still wants these as metal definitions don't exist anymore.
        for (DyeColor color : DyeColor.values())
        {
            add("metal." + AncientGroundCore.MOD_ID + ".glass." + color.getSerializedName(), getName(color) + " Glass");
            add(CoreBlocks.COLORED_GLASS_FLUIDS.get(color).get(), getName(color) + " Glass");
            add(CoreItems.COLORED_GLASS_FLUID_BUCKETS.get(color).get(), getName(color) + " Glass Bucket");
            add(CoreFluids.COLORED_GLASS.get(color).type().get().getDescriptionId(), getName(color) + " Glass");
        }
        add("metal." + AncientGroundCore.MOD_ID + ".glass.clear", "Clear Glass");
        add(CoreBlocks.CLEAR_GLASS_FLUID.get(), "Clear Glass");
        add(CoreItems.CLEAR_GLASS_FLUID_BUCKET.get(), "Clear Glass Bucket");
        add(CoreFluids.CLEAR_GLASS.type().get().getDescriptionId(), "Clear Glass");

        // custom ores.
        for (CoreOres oreType : CoreOres.values())
        {
            if (oreType.hasBlock())
            {
                for (CoreRocks rockType : CoreRocks.values())
                {
                    if (rockType.hasOres())
                    {
                        if (oreType.isGraded())
                        {
                            for (CoreOres.Grade grade : CoreOres.Grade.values())
                            {
                                createOreKey(CoreBlocks.CUSTOM_ROCK_GRADED_ORES.get(rockType).get(oreType).get(grade), getName(grade.name()) + " " + getName(rockType), getName(oreType));
                            }
                        }
                        else
                        {
                            createOreKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rockType).get(oreType), getName(rockType), getName(oreType));
                        }
                    }
                }
                for (Rock rockType : Rock.values())
                {
                    if (oreType.isGraded())
                    {
                        for (CoreOres.Grade grade : CoreOres.Grade.values())
                        {
                            createOreKey(CoreBlocks.GRADED_ORES.get(rockType).get(oreType).get(grade), getName(grade.name()) + " " + getName(rockType), getName(oreType));
                        }
                    }
                    else
                    {
                        createOreKey(CoreBlocks.ORES.get(rockType).get(oreType), getName(rockType), getName(oreType));
                    }
                }
                if (oreType.isGraded())
                {
                    addBlock(CoreBlocks.SMALL_ORES.get(oreType), "Small " + getName(oreType));
                    for (CoreOres.Grade grade : CoreOres.Grade.values())
                    {
                        addItem(CoreItems.GRADED_ORES.get(oreType).get(grade), getName(grade.name()) + " " + getName(oreType));
                    }
                }
            }
            else
            {
                createOreKey(CoreBlocks.BASIC_ORES.get(oreType), getName(oreType));
                addItem(CoreItems.ORES.get(oreType), getName(oreType));
            }
        }

        // tfc ores.
        for (Ore oreType : Ore.values())
        {
            if (oreType.hasBlock())
            {
                for (CoreRocks rockType : CoreRocks.values())
                {
                    if (rockType.hasOres())
                    {
                        if (oreType.isGraded())
                        {
                            for (CoreOres.Grade grade : CoreOres.Grade.values())
                            {
                                createOreKey(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(rockType).get(oreType).get(grade), getName(grade.name()) + " " + getName(rockType), getName(oreType.name()));
                            }
                        }
                        else
                        {
                            createOreKey(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rockType).get(oreType), getName(rockType), getName(oreType.name()));
                        }
                    }
                }
            }
        }

        // glass.
        for (DyeColor color : DyeColor.values())
        {
            addItem(() -> CoreItems.COLORED_LENS.get(color).get(), getName(color) + " Lens");
            addBlock(() -> CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get(), getName(color) + " Molten Glass");
            addBlock(() -> CoreBlocks.COLOURED_LEAD_GLASS.get(color).get(), getName(color) + " Lead Glass");
            addBlock(() -> CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get(), getName(color) + " Lead Glass Pane");
        }

        addBlock(CoreBlocks.CLEAR_MOLTEN_GLASS, "Clear Molten Glass");
        addBlock(CoreBlocks.CLEAR_LEAD_GLASS, "Clear Lead Glass");
        addBlock(CoreBlocks.CLEAR_LEAD_GLASS_PANE, "Clear Lead Glass Pane");

        for (CoreClay clayType : CoreClay.values())
        {
            for (CoreClay.BlockType blockType : CoreClay.BlockType.values())
            {
                if (blockType.hasClayType(clayType))
                {
                    addBlock(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType), getName(clayType) + " " + getName(blockType));
                }
                if (blockType.getType() == CoreClay.BlockPartType.BLOCK_SET)
                {
                    String baseName = getName(clayType) + " " + getName(blockType) + " ";
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).stair(), baseName + "Stairs");
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).slab(), baseName + "Slab");
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).wall(), baseName + "Wall");
                }
            }
        }

        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.hasOres())
            {
                for (OreDeposit deposit : OreDeposit.values())
                {
                    addBlock(CoreBlocks.ORE_DEPOSITS.get(rockType).get(deposit), getName(rockType) + " " + getName(deposit.name().toLowerCase(Locale.ROOT)) + " Deposit");
                }
            }
        }

        addBlock(CoreBlocks.PRISMATIC_ICE, "Prismatic Ice");
        addBlock(CoreBlocks.LEAD_BULB_BLOCK, "Lead Bulb");

        for (CoreGemstones gemType : CoreGemstones.values())
        {
            for (CoreGemstones.GemstoneBlocks blockType : CoreGemstones.GemstoneBlocks.values())
            {
                if (blockType.isCluster() && blockType != CoreGemstones.GemstoneBlocks.CLUSTER)
                {
                    final String prefix = blockType.getSerializedName().split("_")[0];
                    final String suffix = blockType.getSerializedName().split("_")[1];

                    addBlock(() -> CoreBlocks.GEMSTONE_BLOCKS.get(gemType).get(blockType).get(), getName(prefix) + " " + getName(gemType) + " " + getName(suffix));
                }
                else
                {
                    addBlock(() -> CoreBlocks.GEMSTONE_BLOCKS.get(gemType).get(blockType).get(), getName(gemType) + " " + getName(blockType));
                }
            }
            for (CoreGemstones.GemstoneItems itemType : CoreGemstones.GemstoneItems.values())
            {
                addItem(() -> CoreItems.GEMSTONE_ITEMS.get(gemType).get(itemType).get(), getName(gemType) + " " + getName(itemType));
            }
        }

        CoreBlocks.SPECTRUM_WOOD_BOARDS.forEach((wood, block) -> addBlock(block, getName(wood) + " Boards"));
        CoreBlocks.TFC_WOOD_BOARDS.forEach((wood, block) -> addBlock(block, getName(wood) + " Boards"));
        CoreBlocks.AFC_WOOD_BOARDS.forEach((wood, block) -> addBlock(block, getName(wood) + " Boards"));

        CoreBlocks.SPECTRUM_WOOD_SHUTTERS.forEach((wood, block) -> addBlock(block, getName(wood) + " Shutters"));
        CoreBlocks.TFC_WOOD_SHUTTERS.forEach((wood, block) -> addBlock(block, getName(wood) + " Shutters"));
        CoreBlocks.AFC_WOOD_SHUTTERS.forEach((wood, block) -> addBlock(block, getName(wood) + " Shutters"));

        /*
        Stream.of(CoreMetals.BlockType.values()).forEach(type -> {

            String prefix = getName(type.getSerializedName().split("_cut")[0]).concat(" ").replace("Cut Block ", "").replace("Slab ", "").replace("Stairs ", "");
            String baseName = getName(type.getSerializedName().replace("weathered_", "").replace("oxidized_", "").replace("exposed_", ""));

            Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    addBlock(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(type), prefix + getName(metal) + " " + baseName);
                }
            });

            Stream.of(Metal.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    addBlock(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(type), prefix + getName(metal) + " " + baseName);
                }
            });
        });
         */


        //        // Adds a translation with the given key and the given value.
        //        add("translation.key.1", "Translation 1");
        //
        //        // Helpers are available for various common object types. Every helper has two variants: an add() variant
        //        // for the object itself, and an addTypeHere() variant that accepts a supplier for the object.
        //        // The different names for the supplier variants are required due to generic type erasure.
        //        // All following examples assume the existence of the values as suppliers of the needed type.
        //
        //        // Adds a block translation.
        //        add(MyBlocks.EXAMPLE_BLOCK.get(), "Example Block");
        //        addBlock(MyBlocks.EXAMPLE_BLOCK, "Example Block");
        //        // Adds an item translation.
        //        add(MyItems.EXAMPLE_ITEM.get(), "Example Item");
        //        addItem(MyItems.EXAMPLE_ITEM, "Example Item");
        //        // Adds an item stack translation. This is mainly for items that have NBT-specific names.
        //        add(MyItems.EXAMPLE_ITEM_STACK.get(), "Example Item");
        //        addItemStack(MyItems.EXAMPLE_ITEM_STACK, "Example Item");
        //        // Adds an entity type translation.
        //        add(MyEntityTypes.EXAMPLE_ENTITY_TYPE.get(), "Example Entity");
        //        addEntityType(MyEntityTypes.EXAMPLE_ENTITY_TYPE, "Example Entity");
        //        // Adds an enchantment translation.
        //        add(MyEnchantments.EXAMPLE_ENCHANTMENT.get(), "Example Enchantment");
        //        addEnchantment(MyEnchantments.EXAMPLE_ENCHANTMENT, "Example Enchantment");
        //        // Adds a mob effect translation.
        //        add(MyMobEffects.EXAMPLE_MOB_EFFECT.get(), "Example Effect");
        //        addEffect(MyMobEffects.EXAMPLE_MOB_EFFECT, "Example Effect");
    }

    private void createOreKey(Supplier<Block> block, String rock, String ore)
    {
        addBlock(block, rock + " " + ore);

        if (ore.equals("Pyrite"))
        {
            ore = "Native Gold?";
        }
        add(block.get().getDescriptionId() + ".prospected", ore);
    }

    private void createOreKey(Supplier<Block> block, String ore)
    {
        addBlock(block, ore);

        add(block.get().getDescriptionId() + ".prospected", ore);
    }


    private boolean isRockTypePrefixed(Rock.BlockType type)
    {
        return type == Rock.BlockType.LOOSE || type == Rock.BlockType.MOSSY_LOOSE || type == Rock.BlockType.CHISELED || type == Rock.BlockType.HARDENED || type == Rock.BlockType.SMOOTH || type == Rock.BlockType.RAW;
    }

    private String getName(String string)
    {
        return TextUtil.getName(string);
    }

    private String getName(StringRepresentable entry)
    {
        return TextUtil.getName(entry.getSerializedName());
    }
}
