package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.loot.IsIsolatedCondition;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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

    private static LootTable.Builder createOreTable(Block oreBlock, Item oreItem){
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(oreBlock).when(() -> IsIsolatedCondition.INSTANCE)
                                .otherwise(LootItem.lootTableItem(oreItem))
                        )
                );
    }

    private void addOreTable(Block oreBlock, Item oreItem){
        this.add(oreBlock, createOreTable(oreBlock, oreItem));
    };

    @Override
    protected void generate() {

        generateOre();
        //generateCrop();
        //generateMetal();
        //generateRock();
        //generateWood();
    }

    private void generateOre(){
        CoreBlocks.BASIC_ORES.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.SMALL_ORES.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.ORES.values().forEach( map -> {
            Stream.of(CoreOres.values()).forEach(ore -> {
                if (!ore.isGraded() && ore.hasBlock() && !ore.hasPastelOreType()){
                    addOreTable(map.get(ore).get(), CoreItems.ORES.get(ore).get());
                }

                if (!ore.isGraded() && ore.hasBlock() && ore.hasPastelOreType()){
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
                if (!ore.isGraded() && ore.hasBlock() && !ore.hasPastelOreType()) {
                    addOreTable(map.get(ore).get(), CoreItems.ORES.get(ore).get());
                }

                if (!ore.isGraded() && ore.hasBlock() && ore.hasPastelOreType()) {
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
    }

    private void generateCrop(){

        Stream.of(CoreCrops.values()).forEach(crop -> {
            this.add(CoreBlocks.CROPS.get(crop).get(), this.createOreDrop(CoreBlocks.CROPS.get(crop).get(), CoreItems.CROP_SEEDS.get(crop).get()));
            this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), this.createOreDrop(CoreBlocks.DEAD_CROPS.get(crop).get(), CoreItems.CROP_SEEDS.get(crop).get()));
            this.add(CoreBlocks.WILD_CROPS.get(crop).get(), this.createOreDrop(CoreBlocks.WILD_CROPS.get(crop).get(), CoreItems.CROP_SEEDS.get(crop).get()));
        });

        Stream.of(CoreFruitTrees.values()).forEach(tree -> {
            this.dropPottedContents(CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.get(tree).get());
            this.dropSelf(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get());
            this.add(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), this.createOreDrop(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), Items.STICK.asItem()));
            this.add(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get(), this.createOreDrop(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get(), Items.STICK.asItem()));
            this.add(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(tree).get(), this.createOreDrop(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(tree).get(), Items.STICK.asItem()));
        });

        Stream.of(CoreStationaryBushes.values()).forEach(bush -> {
            this.add(CoreBlocks.STATIONARY_BUSHES.get(bush).get(), this.createOreDrop(CoreBlocks.STATIONARY_BUSHES.get(bush).get(), CoreBlocks.STATIONARY_BUSHES.get(bush).get().asItem()));
        });

        Stream.of(CoreSpreadingBushes.values()).forEach(bush -> {
            this.add(CoreBlocks.SPREADING_BUSHES.get(bush).get(), this.createOreDrop(CoreBlocks.SPREADING_BUSHES.get(bush).get(), CoreBlocks.SPREADING_CANES.get(bush).get().asItem()));
            this.add(CoreBlocks.SPREADING_CANES.get(bush).get(), this.createOreDrop(CoreBlocks.SPREADING_CANES.get(bush).get(), CoreBlocks.SPREADING_CANES.get(bush).get().asItem()));
        });
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
                    this.add(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), this.createOreDrop(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get().asItem()));
                }
                if ((type.hasVariants() || type == Rock.BlockType.MOSSY_COBBLE || type == Rock.BlockType.MOSSY_BRICKS) && rock.hasVariant(type)){
                    this.add(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab().get(), this.createOreDrop(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab().get(), CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab().get().asItem()));
                    this.add(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair().get(), this.createOreDrop(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair().get(), CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair().get().asItem()));
                    this.add(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall().get(), this.createOreDrop(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall().get(), CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall().get().asItem()));
                }
            });
        });
    }

    private void generateWood(){

        Stream.of(CorePastelWood.values()).forEach(wood -> {
            Stream.of(Wood.BlockType.values()).forEach( type -> {
                if (wood.hasBlockType(type)){
                    this.add(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get(), this.createOreDrop(CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get(), CoreBlocks.DEEPER_DOWN_WOODS.get(wood).get(type).get().asItem()));
                }
            });
        });

        CoreBlocks.TOOL_RACKS.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.LOOMS.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.SLUICES.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });

        CoreBlocks.SHELVES.values().forEach( holder -> {
            this.dropSelf(holder.get());
        });
    }
}
