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
import org.apache.commons.lang3.stream.Streams;

import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CoreLanguageProvider extends AbstractModonomiconLanguageProvider {

    public CoreLanguageProvider(PackOutput packOutput, ModonomiconLanguageProvider cachedProvider) {
        super(packOutput, AncientGroundCore.MODID, "en_us", cachedProvider);
    }

    @Override
    protected void addTranslations() {

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

        addItem(CoreItems.GLASS_MOLD, "Glass Block Mold");
        addItem(CoreItems.GLASS_PANE_MOLD, "Glass Pane Mold");

        addItem(CoreItems.SNOW_SHOVEL, "Snow Shovel");
        addItem(CoreItems.SNOW_SHOVEL_HEAD, "Snow Shovel Head");

        addItem(CoreItems.WROUGHT_IRON_BUCKET, "Wrought Iron Bucket");
        add(CoreItems.WROUGHT_IRON_BUCKET.get().getDescriptionId() + ".filled", "%s Wrought Iron Bucket");

        // bulk lang
        Stream.of(CoreCrops.values()).forEach(crop -> {
            addItem(CoreItems.CROP_SEEDS.get(crop), getName(crop) + " Seeds");
            addBlock(CoreBlocks.WILD_CROPS.get(crop), "Wild " + getName(crop));
            addBlock(CoreBlocks.CROPS.get(crop), getName(crop));
            addBlock(CoreBlocks.DEAD_CROPS.get(crop), "Dead " + getName(crop));
        });

        Stream.of(CoreSpreadingBushes.values()).forEach(bush -> {
            addBlock(CoreBlocks.SPREADING_BUSHES.get(bush), getName(bush) + " Bush");
        });

        Stream.of(CoreStationaryBushes.values()).forEach(bush -> {
            addBlock(CoreBlocks.STATIONARY_BUSHES.get(bush), getName(bush) + " Bush");
        });

        Stream.of(CoreFruitTrees.values()).forEach(tree -> {
            addBlock(CoreBlocks.FRUIT_TREE_LEAVES.get(tree), getName(tree) + " Leaves");
            addBlock(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree), getName(tree) + " Branch");
            addBlock(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(tree), getName(tree) + " Growing Branch");
            addBlock(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree), getName(tree) + " Sapling");
            addBlock(CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.get(tree), getName(tree) + " Potted Sapling");
        });

        // custom clay types.
        Stream.of(CoreClay.values()).forEach(clay -> {
            Stream.of(CoreClay.ItemType.values()).forEach(type -> {
                if (type.hasType(clay)){
                    if (type.getType() == CoreClay.ItemPartType.UNFIRED_MOLD){
                        addItem(CoreItems.CERAMICS.get(clay).get(type),  "Unfired " + getName(clay) + " " + getName(type.name()) + " Mold");
                    } else {
                        addItem(CoreItems.CERAMICS.get(clay).get(type), getName(clay) + " " + getName(type.name()));
                    }
                }
            });
            add("item." + AncientGroundCore.MODID + ".ceramic." + clay.getSerializedName() + ".jug.filled", "%s " + getName(clay) + " Jug");
            add("emi." + TerraFirmaCraft.MOD_ID + ".ceramic." + clay.getSerializedName() + "_knapping", getName(clay) + " Knapping");
        });

        // custom rocks.
        Stream.of(CoreRocks.values()).forEach(rock -> {
            Stream.of(Rock.BlockType.values()).forEach(type -> {
                if (rock.hasVariant(type)){
                    if (type.hasVariants()){
                        if (isRockTypePrefixed(type)){
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair(), getName(type) + " " + getName(rock) + " Stairs");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab(), getName(type) + " " + getName(rock) + " Slab");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall(), getName(type)+ " " + getName(rock) + " Wall");
                        } else {
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair(), getName(rock) + " " + getName(type) + " Stairs");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab(), getName(rock) + " " + getName(type) + " Slab");
                            addBlock(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall(), getName(rock) + " " + getName(type) + " Wall");
                        }
                    }

                    if (isRockTypePrefixed(type)){
                        addBlock(CoreBlocks.ROCK_BLOCKS.get(rock).get(type),  getName(type) + " " + getName(rock));
                    } else {
                        addBlock(CoreBlocks.ROCK_BLOCKS.get(rock).get(type), getName(rock) + " " + getName(type));
                    }

                }
            });
            addItem(CoreItems.BRICKS.get(rock), getName(rock) + " Brick");
            addBlock(CoreBlocks.MORTARED_CUSTOM_COBBLE.get(rock), "Mortared " + getName(rock) + " Cobble");
        });

        // tfc rocks.
        Stream.of(Rock.values()).forEach(rock -> {
            addBlock(CoreBlocks.MORTARED_TFC_COBBLE.get(rock), "Mortared " + getName(rock) + " Cobble");
        });

        Stream.of(CoreDeeperDownWood.values()).forEach(wood -> {
            Stream.of(Wood.BlockType.values()).forEach(type -> {
                if (type.needsItem() && wood.hasBlockType(type)){
                    addBlock(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type), getName(wood) + " " + getName(type.name()));
                }
            });
            addItem(CoreItems.SUPPORTS.get(wood), getName(wood) + " Support");
            addItem(CoreItems.LUMBER.get(wood), getName(wood) + " Twig");
        });

        // custom metals.
        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
            Stream.of(Metal.ItemType.values()).forEach(itemType -> {
                if (itemType.has(metal.getLikeMetal())){
                    addItem(CoreItems.METAL_ITEMS.get(metal).get(itemType), getName(metal) + " " + getName(itemType.name()));
                }
            });

            if (!metal.hasOtherFluid()){
                add(CoreBlocks.METAL_FLUIDS.get(metal).get(), getName(metal));
                add(CoreItems.METAL_FLUID_BUCKETS.get(metal).get(), getName(metal) + " Bucket");
                add(CoreFluids.METALS.get(metal).type().get().getDescriptionId(), getName(metal));
            }

            Stream.of(Metal.BlockType.values()).forEach(blockType -> {
                if (blockType.has(metal.getLikeMetal())){
                    addBlock(CoreBlocks.METALS.get(metal).get(blockType), getName(metal) + " " + getName(blockType.name()));
                }
            });

            add("metal." + AncientGroundCore.MODID + "." + metal.getSerializedName(), getName(metal));
        });

        // TODO: find out if TFC still wants these as metal definitions don't exist anymore.
        Stream.of(DyeColor.values()).forEach(colour -> {
            add("metal." + AncientGroundCore.MODID + ".glass." + colour.getSerializedName(), getName(colour) + " Glass");
            add(CoreBlocks.COLORED_GLASS_FLUIDS.get(colour).get(), getName(colour) + " Glass");
            add(CoreItems.COLORED_GLASS_FLUID_BUCKETS.get(colour).get(), getName(colour) + " Glass Bucket");
            add(CoreFluids.COLORED_GLASS.get(colour).type().get().getDescriptionId(), getName(colour) + " Glass");
        });
        add("metal." + AncientGroundCore.MODID + ".glass.clear", "Clear Glass");
        add(CoreBlocks.CLEAR_GLASS_FLUID.get(), "Clear Glass");
        add(CoreItems.CLEAR_GLASS_FLUID_BUCKET.get(), "Clear Glass Bucket");
        add(CoreFluids.CLEAR_GLASS.type().get().getDescriptionId(), "Clear Glass");

        // custom ores.
        Stream.of(CoreOres.values()).forEach(ore -> {

            if (ore.hasBlock()){
                Stream.of(CoreRocks.values()).forEach(rock -> {
                    if (ore.isGraded()){
                        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
                            createOreKey(CoreBlocks.CUSTOM_ROCK_GRADED_ORES.get(rock).get(ore).get(grade), getName(grade.name()) + " " + getName(rock), getName(ore));
                        });
                    } else {
                        createOreKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rock).get(ore), getName(rock), getName(ore));
                    }
                });
                Stream.of(Rock.values()).forEach(rock -> {
                    if (ore.isGraded()){
                        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
                            createOreKey(CoreBlocks.GRADED_ORES.get(rock).get(ore).get(grade), getName(grade.name()) + " " + getName(rock), getName(ore));
                        });
                    } else {
                        createOreKey(CoreBlocks.ORES.get(rock).get(ore), getName(rock), getName(ore));
                    }
                });

                if (ore.isGraded()){
                    addBlock(CoreBlocks.SMALL_ORES.get(ore),"Small " + getName(ore));
                    Stream.of(CoreOres.Grade.values()).forEach(grade -> {
                        addItem(CoreItems.GRADED_ORES.get(ore).get(grade), getName(grade.name()) + " " + getName(ore));
                    });
                }
            } else {
                createOreKey(CoreBlocks.BASIC_ORES.get(ore), getName(ore));
                addItem(CoreItems.ORES.get(ore), getName(ore));
            }
        });

        // tfc ores.
        Stream.of(Ore.values()).forEach(ore -> {

            if (ore.hasBlock()){
                Stream.of(CoreRocks.values()).forEach(rock -> {
                    if (ore.isGraded()){
                        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
                            createOreKey(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(rock).get(ore).get(grade), getName(grade.name()) + " " + getName(rock), getName(ore.name()));
                        });
                    } else {
                        createOreKey(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(ore), getName(rock), getName(ore.name()));
                    }
                });
            }
        });

        // glass.
        Stream.of(DyeColor.values()).forEach(color -> {
            addItem(() -> CoreItems.COLORED_LENS.get(color).get(), getName(color) + " Lens");
            addBlock(() -> CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get(), getName(color) + " Molten Glass");
        });

        addBlock(CoreBlocks.CLEAR_MOLTEN_GLASS, "Clear Molten Glass");

        Stream.of(CoreClay.values()).forEach(clay -> {
            Stream.of(CoreClay.BlockType.values()).forEach(type -> {

                if (type.hasClayType(clay)){
                    addBlock(CoreBlocks.CERAMIC_BLOCKS.get(clay).get(type), getName(clay) + " Clay");
                }

                if (type.getType() == CoreClay.BlockPartType.BLOCK_SET){
                    String baseName = getName(clay) + " " + getName(type) + " ";
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).stair(), baseName + "Stairs");
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).slab(), baseName + "Slab");
                    addBlock(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).wall(), baseName + "Wall");
                }
            });
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            Streams.of(OreDeposit.values()).forEach(ore -> {
                addBlock(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore), getName(rock) + " " + getName(ore.name().toLowerCase(Locale.ROOT)) + " Deposit");
            });
        });

        addBlock(CoreBlocks.SLUDGE, "Sludge");
        addBlock(CoreBlocks.PRISMATIC_ICE, "Prismatic Ice");

        Stream.of(CoreGemstones.values()).forEach(gem -> {

            Stream.of(CoreGemstones.GemstoneBlocks.values()).forEach(blockType -> {
                if (blockType.isCluster() && blockType != CoreGemstones.GemstoneBlocks.CLUSTER){
                    final String prefix = blockType.getSerializedName().split("_")[0];
                    final String suffix = blockType.getSerializedName().split("_")[1];

                    addBlock(() -> CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(blockType).get(), getName(prefix) + " " + getName(gem) + " " + getName(suffix));
                } else {
                    addBlock(() -> CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(blockType).get(), getName(gem) + " " + getName(blockType));
                }
            });

            Stream.of(CoreGemstones.GemstoneItems.values()).forEach(itemType -> {
                addItem(() -> CoreItems.GEMSTONE_ITEMS.get(gem).get(itemType).get(), getName(gem) + " " + getName(itemType));
            });
        });


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

    private void createOreKey(Supplier<Block> block, String rock, String ore){
        addBlock(block, rock + " " + ore);

        if (ore.equals("Pyrite")){
            ore = "Native Gold?";
        }
        add(block.get().getDescriptionId() + ".prospected", ore);
    }

    private void createOreKey(Supplier<Block> block, String ore){
        addBlock(block, ore);

        add(block.get().getDescriptionId() + ".prospected", ore);
    }


    private boolean isRockTypePrefixed(Rock.BlockType type){
        return type == Rock.BlockType.LOOSE || type == Rock.BlockType.MOSSY_LOOSE || type == Rock.BlockType.CHISELED || type == Rock.BlockType.HARDENED || type == Rock.BlockType.SMOOTH || type == Rock.BlockType.RAW;
    }

    private String getName(String string){
        return TextUtil.getName(string);
    }

    private String getName(StringRepresentable entry){
        return TextUtil.getName(entry.getSerializedName());
    }
}
