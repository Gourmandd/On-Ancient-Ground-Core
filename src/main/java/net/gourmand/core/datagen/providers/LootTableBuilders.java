package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.crop.DeadCropBlock;
import net.dries007.tfc.common.blocks.crop.DeadDoubleCropBlock;
import net.dries007.tfc.common.blocks.crop.WildCropBlock;
import net.dries007.tfc.common.blocks.devices.SluiceBlock;
import net.dries007.tfc.common.blocks.plant.fruit.FruitTreeBranchBlock;
import net.dries007.tfc.common.blocks.plant.fruit.SpreadingBushBlock;
import net.dries007.tfc.common.blocks.rock.LooseRockBlock;
import net.dries007.tfc.util.loot.CropYieldProvider;
import net.dries007.tfc.util.loot.IsIsolatedCondition;
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
}
