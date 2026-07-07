package net.gourmand.core.datagen.providers;

import com.google.common.collect.ImmutableMap;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.crop.DeadCropBlock;
import net.dries007.tfc.common.blocks.crop.DeadDoubleCropBlock;
import net.dries007.tfc.common.blocks.crop.WildCropBlock;
import net.dries007.tfc.common.blocks.devices.SluiceBlock;
import net.dries007.tfc.common.blocks.plant.fruit.FruitTreeBranchBlock;
import net.dries007.tfc.common.blocks.plant.fruit.SpreadingBushBlock;
import net.dries007.tfc.common.blocks.rock.LooseRockBlock;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.loot.CropYieldProvider;
import net.dries007.tfc.util.loot.IsIsolatedCondition;
import net.dries007.tfc.util.loot.IsSluiceCondition;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.blocks.CoreDefaultCropBlock;
import net.gourmand.core.registry.blocks.CoreDoubleCropBlock;
import net.gourmand.core.registry.category.*;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Objects;

import static net.dries007.tfc.common.blocks.rock.Ore.*;
import static net.dries007.tfc.common.blocks.rock.Ore.AMETHYST;
import static net.dries007.tfc.common.blocks.rock.Ore.BISMUTHINITE;
import static net.dries007.tfc.common.blocks.rock.Ore.BORAX;
import static net.dries007.tfc.common.blocks.rock.Ore.DIAMOND;
import static net.dries007.tfc.common.blocks.rock.Ore.GYPSUM;
import static net.dries007.tfc.common.blocks.rock.Ore.HEMATITE;
import static net.dries007.tfc.common.blocks.rock.Ore.LAPIS_LAZULI;
import static net.dries007.tfc.common.blocks.rock.Ore.PYRITE;
import static net.dries007.tfc.common.blocks.rock.Ore.SPHALERITE;
import static net.dries007.tfc.common.blocks.rock.Ore.SULFUR;
import static net.dries007.tfc.common.blocks.rock.Ore.SYLVITE;
import static net.dries007.tfc.common.blocks.rock.Ore.TOPAZ;
import static net.gourmand.core.registry.category.CoreOres.BAUXITE;
import static net.gourmand.core.registry.category.CoreRocks.*;
import static net.gourmand.core.registry.category.CoreRocks.BRECCIA;

public class LootTableBuilders {

    // ore
    protected static LootTable.Builder createOreTable(Block oreBlock, Item oreItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(oreBlock).when(() -> IsIsolatedCondition.INSTANCE)
                                .otherwise(LootItem.lootTableItem(oreItem))
                        )
                );
    }

    //crops
    protected static LootTable.Builder createSingleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(productItem)
                                .apply(
                                        SetItemCountFunction.setCount(new CropYieldProvider(ConstantValue.exactly(0), new UniformGenerator(ConstantValue.exactly(6), ConstantValue.exactly(10))))
                                )
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.CROPS.get(crop).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoreDefaultCropBlock.AGE, crop.getRipeStage()))
                                )
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get()))
                );
    }

    protected static LootTable.Builder createDoubleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(productItem)
                                .apply(
                                        SetItemCountFunction.setCount(new CropYieldProvider(ConstantValue.exactly(0), new UniformGenerator(ConstantValue.exactly(6), ConstantValue.exactly(10))))
                                )
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.CROPS.get(crop).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(CoreDoubleCropBlock.AGE, 7)
                                                        .hasProperty(CoreDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM)
                                                )
                                )
                        )
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get())
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.CROPS.get(crop).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoreDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM))
                                )
                        )
                );
    }

    protected static LootTable.Builder createSpreadingCropTable(CoreCrops crop){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get()))
                );
    }

    protected static LootTable.Builder createDeadSingleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(
                                AlternativesEntry.alternatives(
                                        LootItem.lootTableItem(productItem).when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.DEAD_CROPS.get(crop).get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DeadCropBlock.MATURE, false))
                                        ),
                                        LootItem.lootTableItem(productItem).when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.DEAD_CROPS.get(crop).get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DeadCropBlock.MATURE, true))
                                        ).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(3))))
                                )
                        )
                );
    }

    protected static LootTable.Builder createDeadDoubleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion()).add(
                                AlternativesEntry.alternatives(
                                        LootItem.lootTableItem(productItem).when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.DEAD_CROPS.get(crop).get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(DeadDoubleCropBlock.MATURE, false)
                                                                .hasProperty(DeadDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM)
                                                        )
                                        ),
                                        LootItem.lootTableItem(productItem).when(
                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.DEAD_CROPS.get(crop).get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(DeadCropBlock.MATURE, true)
                                                                .hasProperty(DeadDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM)
                                                        )
                                        ).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(3))))
                                )
                        )
                );
    }

    protected static LootTable.Builder createWildSingleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get()))
                ).withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                    .add(LootItem.lootTableItem(productItem).when(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.WILD_CROPS.get(crop).get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WildCropBlock.MATURE, true))
                    ).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(3)))))
                );
    }

    protected static LootTable.Builder createWildSpreadingCropTable(CoreCrops crop){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get()))
                );
    }

    protected static LootTable.Builder createWildDoubleCropTable(CoreCrops crop, Item productItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreItems.CROP_SEEDS.get(crop).get())).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.WILD_CROPS.get(crop).get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(DeadDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM)
                                        )
                        )
                ).withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(productItem).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.WILD_CROPS.get(crop).get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(WildCropBlock.MATURE, true)
                                                .hasProperty(DeadDoubleCropBlock.PART, CoreDoubleCropBlock.Part.BOTTOM)
                                        )
                        ).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(3)))))
                );
    }

    // fruit trees
    protected static LootTable.Builder createGrowingBranchTable(){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(Items.STICK))
                        .apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(4))))
                );
    }

    protected static LootTable.Builder createBranchTable(CoreFruitTrees tree){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get()))
                        .when(AllOfCondition.allOf(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES)),
                                AnyOfCondition.anyOf(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(FruitTreeBranchBlock.UP, true)
                                                        .hasProperty(FruitTreeBranchBlock.WEST, true)
                                                ),
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(FruitTreeBranchBlock.UP, true)
                                                        .hasProperty(FruitTreeBranchBlock.NORTH, true)
                                                ),
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(FruitTreeBranchBlock.UP, true)
                                                        .hasProperty(FruitTreeBranchBlock.EAST, true)
                                                ),
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(FruitTreeBranchBlock.UP, true)
                                                        .hasProperty(FruitTreeBranchBlock.SOUTH, true)
                                                )
                                )))
                ).withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(Items.STICK))
                        .apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(4))))
                );
    }

    protected static LootTable.Builder createFruitTreeSaplingTable(CoreFruitTrees tree){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(TFCBlockStateProperties.SAPLINGS, 1)
                                )
                        )
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(TFCBlockStateProperties.SAPLINGS, 2)
                                        )
                        )
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(TFCBlockStateProperties.SAPLINGS, 3)
                                        )
                        )
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))).when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(TFCBlockStateProperties.SAPLINGS, 4)
                                        )
                        )
                ).withPool(LootPool.lootPool().when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(Items.STICK))
                        .apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(4))))
                );
    }

    //bushes
    protected static LootTable.Builder createStationaryBushTable(CoreStationaryBushes bush){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(CoreBlocks.STATIONARY_BUSHES.get(bush).get())
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(TFCTags.Items.TOOLS_SHARP)))
                        )
                );
    }

    protected static LootTable.Builder createSpreadingBushCaneTable(CoreSpreadingBushes bush){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(Items.STICK))
                );
    }

    protected static LootTable.Builder createSpreadingBushTable(CoreSpreadingBushes bush){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(Items.STICK))
                ).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(CoreBlocks.SPREADING_BUSHES.get(bush).get())
                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(TFCTags.Items.TOOLS_SHARP))
                                            .and(LootItemRandomChanceCondition.randomChance(ConstantValue.exactly(0.5F)))
                                    ),
                                LootItem.lootTableItem(CoreBlocks.SPREADING_BUSHES.get(bush).get())
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(TFCTags.Items.TOOLS_SHARP))
                                                .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.SPREADING_BUSHES.get(bush).get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SpreadingBushBlock.STAGE, 2))
                                                )
                                        )
                        ))
                );
    }

    protected static LootTable.Builder createRockDropTable(Block block, int min, int max){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(block))
                        .apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(min), ConstantValue.exactly(max))))
                );
    }

    protected static LootTable.Builder createRockDropTable(Block block, int number){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(block))
                        .apply(SetItemCountFunction.setCount(new ConstantValue(number)))
                );
    }

    protected static LootTable.Builder createRawRockDropTable(Block block, Block item){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(block).when(() -> IsIsolatedCondition.INSTANCE),
                                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(1), ConstantValue.exactly(4))))
                        ))
                );
    }

    protected static LootTable.Builder createLooseRockDropTable(Block block){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(block)).apply(
                                SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LooseRockBlock.COUNT, 2))
                                        )
                        ).apply(
                                SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LooseRockBlock.COUNT, 3))
                                        )
                        )
                );
    }

    protected static LootTable.Builder createSluiceTable(Block block){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(block))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SluiceBlock.UPPER, true))
                        )
                );
    }

    protected static LootTable.Builder createClayBlockTable(Block block, Item item){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(item)).apply(SetItemCountFunction.setCount(new UniformGenerator(ConstantValue.exactly(2), ConstantValue.exactly(4))))
                );
    }

    protected static LootTable.Builder createDepositPanningTable(OreDeposit ore, CoreRocks rock){

        Map<CoreRocks, Item> PANNING_ALT = ImmutableMap.<CoreRocks, Item>builder()
                .put(SERPENTINE, TFCItems.ORES.get(GRAPHITE).asItem())
                .put(PERIDOTITE, TFCItems.ORES.get(CRYOLITE).asItem())
                .put(BLUESCHIST, CoreItems.ORES.get(BAUXITE).get())
                .put(SOAPSTONE, TFCItems.GEMS.get(SAPPHIRE).asItem())
                .put(SANDSTONE, TFCItems.GEMS.get(LAPIS_LAZULI).asItem())
                .put(SUEVITE, TFCItems.GEMS.get(DIAMOND).asItem())
                .put(KOMATIITE, TFCItems.ORES.get(SULFUR).asItem())
                .put(RED_SANDSTONE, TFCItems.GEMS.get(AMETHYST).asItem())
                .put(PHONOLITE, TFCItems.GEMS.get(PYRITE).asItem())
                .put(ARKOSE, TFCItems.ORES.get(GYPSUM).asItem())
                .put(BLACKSLAG, TFCItems.GRADED_ORES.get(HEMATITE).get(Ore.Grade.RICH).asItem())
                .put(PICRITE_BASALT, TFCItems.GRADED_ORES.get(SPHALERITE).get(Ore.Grade.RICH).asItem())
                .put(TRAVERTINE, TFCItems.ORES.get(BORAX).asItem())
                .put(ARGILLITE, TFCItems.ORES.get(SYLVITE).asItem())
                .put(NEPHELINITE, TFCItems.GEMS.get(TOPAZ).asItem())
                .put(BRECCIA, TFCItems.GRADED_ORES.get(BISMUTHINITE).get(Ore.Grade.RICH).asItem())
                .build();

        Item mainResult = null;

        switch (ore){
            case CASSITERITE -> mainResult = TFCBlocks.SMALL_ORES.get(Ore.CASSITERITE).asItem();
            case NATIVE_COPPER -> mainResult = TFCBlocks.SMALL_ORES.get(Ore.NATIVE_COPPER).asItem();
            case NATIVE_GOLD -> mainResult = TFCBlocks.SMALL_ORES.get(Ore.NATIVE_GOLD).asItem();
            case NATIVE_SILVER -> mainResult = TFCBlocks.SMALL_ORES.get(Ore.NATIVE_SILVER).asItem();
        }

        Item altItem = CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get().asItem();

        if (rock.displayCategory().category() == RockCategory.IGNEOUS_EXTRUSIVE){
            altItem = TFCBlocks.GROUNDCOVER.get(GroundcoverBlockType.PUMICE).asItem();
        }

        return LootTable.lootTable()
                .withPool(LootPool.lootPool().add(
                                AlternativesEntry.alternatives(
                                        LootItem.lootTableItem(mainResult).when(
                                                LootItemRandomChanceCondition.randomChance(0.5f)
                                        ),
                                        LootItem.lootTableItem(mainResult).when(
                                                LootItemRandomChanceCondition.randomChance(0.1f).and(
                                                        IsSluiceCondition.isSluice()
                                                )
                                        ),
                                        LootItem.lootTableItem(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get().asItem()).when(
                                                LootItemRandomChanceCondition.randomChance(0.5f)
                                        ),
                                        LootItem.lootTableItem(altItem).when(
                                                LootItemRandomChanceCondition.randomChance(0.25f)
                                        ),
                                        LootItem.lootTableItem(Objects.requireNonNull(PANNING_ALT.get(rock))).when(
                                                LootItemRandomChanceCondition.randomChance(0.0533f)
                                        )
                                )
                        )
                );
    }
}
