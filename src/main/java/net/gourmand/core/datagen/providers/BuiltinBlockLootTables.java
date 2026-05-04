package net.gourmand.core.datagen.providers;

import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.plant.fruit.Lifecycle;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.*;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Stream;

public class BuiltinBlockLootTables extends BlockLootSubProvider {

    public BuiltinBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // The contents of our DeferredRegister.
        return CoreBlocks.BLOCKS.getEntries()
                .stream()
                // Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
                .map(e -> (Block) e.value())
                .toList();
    }

    // methods to call from generate()
    private void addOreTable(Block oreBlock, Item oreItem){
        this.add(oreBlock, LootTableBuilders.createOreTable(oreBlock, oreItem));
    }

    private void addCropTable(CoreCrops crop){

        final var PRODUCT = CategoryUtil.CoreCrop.TO_CROP_PRODUCT.get(crop);

        switch (crop.getCropType()){
            case SINGLE -> {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildSingleCropTable(crop, PRODUCT));
            }
            case DOUBLE -> {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createDoubleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadDoubleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildDoubleCropTable(crop, PRODUCT));
            }
            case SPREADING -> {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createSpreadingCropTable(crop));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildSpreadingCropTable(crop));
            }
        }
    }

    private void addFruitTreeTable(CoreFruitTrees tree){

        this.dropPottedContents(CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.get(tree).get());
        this.add(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get(), LootTableBuilders.createBranchTable(tree));
        this.add(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(tree).get(), LootTableBuilders.createGrowingBranchTable());
        this.add(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), createFruitTreeLeavesTable(tree));
        this.add(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get(), LootTableBuilders.createFruitTreeSaplingTable(tree));
    }

    private void addStationaryBushTable(CoreStationaryBushes bush){
        this.add(CoreBlocks.STATIONARY_BUSHES.get(bush).get(), LootTableBuilders.createStationaryBushTable(bush));
    }

    private void addSpreadingBushTable(CoreSpreadingBushes bush){
        this.add(CoreBlocks.SPREADING_BUSHES.get(bush).get(), LootTableBuilders.createSpreadingBushTable(bush));
        this.add(CoreBlocks.SPREADING_CANES.get(bush).get(), LootTableBuilders.createSpreadingBushCaneTable(bush));
    }

    private void addRockBlockTable(CoreRocks rock, Rock.BlockType type){
      switch (type){
          case LOOSE, MOSSY_LOOSE -> {
              this.add(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createLooseRockDropTable(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get()));
          }
          case SPIKE -> {
              this.add(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createRockDropTable(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get(), 1, 2));
          }
          case RAW, HARDENED -> {
              this.add(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createRawRockDropTable(
                      CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rock).value(),
                      CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get()
              ));
          }
          default -> {
              this.dropSelf(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get());
          }
      }
    }

    private void dropAir(Block block){
        this.add(block, LootTable.lootTable());
    }

    @Override
    protected void generate() {

        generateOre();
        generateCrop();
        generateMetal();
        generateRock();
        generateWood();
        generateMisc();
        generateGemstones();

        Stream.of(DyeColor.values()).forEach(color -> {
            this.dropSelf(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get());
        });

        this.dropSelf(CoreBlocks.CLEAR_MOLTEN_GLASS.get());
    }

    private void generateOre(){


        Stream.of(CoreOres.values()).forEach(ore -> {
            if (!ore.hasBlock()){
                switch (ore){
                    case ANTHRACITE -> {
                        addOreTable(CoreBlocks.BASIC_ORES.get(ore).get(), SpectrumItems.PURE_COAL.get());
                    }
                    case QUARTZ -> {
                        addOreTable(CoreBlocks.BASIC_ORES.get(ore).get(), Items.QUARTZ);
                    }
                    default -> {
                        addOreTable(CoreBlocks.BASIC_ORES.get(ore).get(), CoreItems.ORES.get(ore).get());
                    }
                }
            }
        });

        CoreBlocks.SMALL_ORES.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.ORES.values().forEach( map -> {
            Stream.of(CoreOres.values()).forEach(ore -> {
                if (!ore.isGraded() && ore.hasBlock() && !ore.hasSpectrumOreType()){
                    addOreTable(map.get(ore).get(), CoreItems.ORES.get(ore).get());
                }

                if (!ore.isGraded() && ore.hasBlock() && ore.hasSpectrumOreType()){
                    addOreTable(map.get(ore).get(),  ore.getPastelOre());
                }
            });
        });

        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
            CoreBlocks.GRADED_ORES.values().forEach( map -> {
                Stream.of(CoreOres.values()).forEach(ore -> {
                    if (ore.isGraded()){
                        addOreTable(map.get(ore).get(grade).get(), CoreItems.GRADED_ORES.get(ore).get(grade).get());
                    }
                });
            });
        });

        CoreBlocks.CUSTOM_ROCK_ORES.values().forEach( map -> {
            Stream.of(CoreOres.values()).forEach(ore -> {
                if (!ore.isGraded() && ore.hasBlock() && !ore.hasSpectrumOreType()) {
                    addOreTable(map.get(ore).get(), CoreItems.ORES.get(ore).get());
                }

                if (!ore.isGraded() && ore.hasBlock() && ore.hasSpectrumOreType()) {
                    addOreTable(map.get(ore).get(), ore.getPastelOre());
                }
            });
        });

        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
            CoreBlocks.CUSTOM_ROCK_GRADED_ORES.values().forEach( map -> {
                Stream.of(CoreOres.values()).forEach(ore -> {
                    if (ore.isGraded()) {
                        addOreTable(map.get(ore).get(grade).get(), CoreItems.GRADED_ORES.get(ore).get(grade).get());
                    }
                });
            });
        });

        CoreBlocks.CUSTOM_ROCK_TFC_ORES.values().forEach( map -> {
            Stream.of(Ore.values()).forEach(ore -> {
                if (!ore.isGraded() && ore.hasBlock()) {
                    addOreTable(map.get(ore).get(), TFCItems.ORES.get(ore).get());
                }
            });
        });

        Stream.of(CoreOres.Grade.values()).forEach(grade -> {
            CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.values().forEach( map -> {
                Stream.of(Ore.values()).forEach(ore -> {
                    if (ore.isGraded()) {
                        addOreTable(map.get(ore).get(grade).get(), TFCItems.GRADED_ORES.get(ore).get(CoreOres.getTFCgrade(grade)).get());
                    }
                });
            });
        });

        for (CoreRocks rock : CoreRocks.values()){
            for (OreDeposit ore : OreDeposit.values()){
                this.dropSelf(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore).get());
            }
        }
    }

    private void generateCrop(){

        Stream.of(CoreCrops.values()).forEach(this::addCropTable);

        Stream.of(CoreFruitTrees.values()).forEach(this::addFruitTreeTable);

        Stream.of(CoreStationaryBushes.values()).forEach(this::addStationaryBushTable);

        Stream.of(CoreSpreadingBushes.values()).forEach(this::addSpreadingBushTable);
    }

    private void generateMetal(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
            Stream.of(Metal.BlockType.values()).forEach(type -> {
                if (type.has(metal.getLikeMetal())){
                    this.add(CoreBlocks.METALS.get(metal).get(type).get(), this.createOreDrop(CoreBlocks.METALS.get(metal).get(type).get(), CoreBlocks.METALS.get(metal).get(type).get().asItem()));
                }
            });
        });
    }

    private void generateRock(){

        CoreBlocks.MAGMA_BLOCKS.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        Stream.of(CoreRocks.values()).forEach(rock -> {
            Stream.of(Rock.BlockType.values()).forEach(type -> {
                if (rock.hasVariant(type)){
                    addRockBlockTable(rock, type);
                }
                if ((type.hasVariants() || type == Rock.BlockType.MOSSY_COBBLE || type == Rock.BlockType.MOSSY_BRICKS) && rock.hasVariant(type)){
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab().get());
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair().get());
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall().get());
                }
            });
        });

        CoreBlocks.MORTARED_TFC_COBBLE.values().forEach(holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.MORTARED_CUSTOM_COBBLE.values().forEach(holder -> {
            this.dropSelf(holder.get());
        });
    }

    private void generateWood(){

        Stream.of(CoreDeeperDownWood.values()).forEach(wood -> {
            Stream.of(Wood.BlockType.values()).forEach( type -> {
                if (wood.hasBlockType(type)){
                    if (type == Wood.BlockType.SLUICE){
                        this.add(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get(), LootTableBuilders.createSluiceTable(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get()));
                    } else {
                        this.dropSelf(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get());
                    }
                }
            });
        });
    }

    private void generateGemstones(){

        Stream.of(CoreGemstones.values()).forEach(gem -> {
            Stream.of(CoreGemstones.GemstoneBlocks.values()).forEach(blockType -> {

                DeferredHolder<Block, Block> block = CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(blockType);

                switch (blockType){
                    case BLOCK, POWDER_BLOCK, PILLAR -> {
                        this.dropSelf(block.get());
                    }
                    case BUDDING_BLOCK -> {
                        dropAir(block.get());
                    }
                    case CLUSTER -> {
                        this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gem).get(CoreGemstones.GemstoneItems.SHARD).get(), 4));
                    }
                    case LARGE_CLUSTER -> {
                        this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gem).get(CoreGemstones.GemstoneItems.POWDER).get(), 4));
                    }
                    case MEDIUM_CLUSTER -> {
                        this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gem).get(CoreGemstones.GemstoneItems.POWDER).get(), 2));
                    }
                    case SMALL_CLUSTER -> {
                        this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gem).get(CoreGemstones.GemstoneItems.POWDER).get(), 1));
                    }
                }
            });
        });
    }

    private void generateMisc(){

        this.dropSelf(CoreBlocks.CLEAR_MOLTEN_GLASS.get());
        Stream.of(DyeColor.values()).forEach(color -> {
            this.dropSelf(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get());
        });


        this.dropSelf(CoreBlocks.CLEAR_LEAD_GLASS.get());
        Stream.of(DyeColor.values()).forEach(color -> {
            this.dropSelf(CoreBlocks.COLOURED_LEAD_GLASS.get(color).get());
        });

        this.dropSelf(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get());
        Stream.of(DyeColor.values()).forEach(color -> {
            this.dropSelf(CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get());
        });


        Stream.of(CoreClay.values()).forEach(clay -> {
            Stream.of(CoreClay.BlockType.values()).forEach(type -> {

                if (type.hasClayType(clay)){
                    if (type == CoreClay.BlockType.CLAY_BLOCK){
                        this.add(CoreBlocks.CERAMIC_BLOCKS.get(clay).get(type).get(), LootTableBuilders.createClayBlockTable(CoreBlocks.CERAMIC_BLOCKS.get(clay).get(type).get(), CoreItems.CERAMICS.get(clay).get(CoreClay.ItemType.CLAY_BALL).get()));
                    } else {
                        this.dropSelf(CoreBlocks.CERAMIC_BLOCKS.get(clay).get(type).get());
                    }
                }

                if (type.getType() == CoreClay.BlockPartType.BLOCK_SET){
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).stair().get());
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).slab().get());
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clay).get(type).wall().get());
                }
            });
        });

        this.dropOther(CoreBlocks.SLUDGE.get(), Items.AIR);
        this.dropOther(CoreBlocks.PRISMATIC_ICE.get(), Items.AIR);
    }

    private LootTable.Builder createFruitTreeLeavesTable(CoreFruitTrees tree){
        return this.createSilkTouchOrShearsDispatchTable(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(),
                this.applyExplosionCondition(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), LootItem.lootTableItem(Items.STICK)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get())
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(TFCBlockStateProperties.LIFECYCLE, Lifecycle.FRUITING))
                        )
                )
        );
    }

    //gemstones
    protected LootTable.Builder createClusterTable(Block block, Item item, float amount){
        return this.createSilkTouchDispatchTable(
                block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount)))
                        .apply(ApplyBonusCount.addOreBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                this.applyExplosionDecay(
                                        block, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        );
    }
}
