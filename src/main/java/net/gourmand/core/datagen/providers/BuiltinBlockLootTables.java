package net.gourmand.core.datagen.providers;

import com.therighthon.afc.common.blocks.AFCWood;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.plant.fruit.Lifecycle;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockCategory;
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
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BuiltinBlockLootTables extends BlockLootSubProvider
{
    public BuiltinBlockLootTables(HolderLookup.Provider lookupProvider)
    {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        // The contents of our DeferredRegister.
        return CoreBlocks.BLOCKS.getEntries().stream()
                // Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
                .map(e -> (Block) e.value()).toList();
    }

    @Override
    protected void generate()
    {
        generateOre();
        generateCrop();
        generateMetal();
        generateRock();
        generateWood();
        generateGemstones();
        generateMisc();
    }

    private void generateOre()
    {
        for (CoreOres oreType : CoreOres.values())
        {
            if (!oreType.hasBlock())
            {
                switch (oreType)
                {
                    case ANTHRACITE -> addOreTable(CoreBlocks.BASIC_ORES.get(oreType).get(), SpectrumItems.PURE_COAL.get());
                    case QUARTZ -> addOreTable(CoreBlocks.BASIC_ORES.get(oreType).get(), Items.QUARTZ);
                    default -> addOreTable(CoreBlocks.BASIC_ORES.get(oreType).get(), CoreItems.ORES.get(oreType).get());
                }
            }

            if (oreType.isGraded())
            {
                this.dropSelf(CoreBlocks.SMALL_ORES.get(oreType).get());
            }
        }

        for (Rock rockType : Rock.values())
        {
            for (CoreOres oreType : CoreOres.values())
            {
                if (!oreType.isGraded() && oreType.hasBlock() && !oreType.hasSpectrumOreType())
                {
                    addOreTable(CoreBlocks.ORES.get(rockType).get(oreType).get(), CoreItems.ORES.get(oreType).get());
                }

                if (!oreType.isGraded() && oreType.hasBlock() && oreType.hasSpectrumOreType())
                {
                    addOreTable(CoreBlocks.ORES.get(rockType).get(oreType).get(), oreType.getPastelOre());
                }

                if (oreType.isGraded())
                {
                    for (CoreOres.Grade grade : CoreOres.Grade.values())
                    {
                        addOreTable(CoreBlocks.GRADED_ORES.get(rockType).get(oreType).get(grade).get(), CoreItems.GRADED_ORES.get(oreType).get(grade).get());
                    }
                }
            }
        }

        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.hasOres())
            {
                for (CoreOres oreType : CoreOres.values())
                {
                    if (!oreType.isGraded() && oreType.hasBlock() && !oreType.hasSpectrumOreType())
                    {
                        addOreTable(CoreBlocks.CUSTOM_ROCK_ORES.get(rockType).get(oreType).get(), CoreItems.ORES.get(oreType).get());
                    }

                    if (!oreType.isGraded() && oreType.hasBlock() && oreType.hasSpectrumOreType())
                    {
                        addOreTable(CoreBlocks.CUSTOM_ROCK_ORES.get(rockType).get(oreType).get(), oreType.getPastelOre());
                    }

                    if (oreType.isGraded())
                    {
                        for (CoreOres.Grade grade : CoreOres.Grade.values())
                        {
                            addOreTable(CoreBlocks.CUSTOM_ROCK_GRADED_ORES.get(rockType).get(oreType).get(grade).get(), CoreItems.GRADED_ORES.get(oreType).get(grade).get());
                        }
                    }
                }
            }
        }

        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.hasOres())
            {
                for (Ore oreType : Ore.values())
                {
                    if (!oreType.isGraded() && oreType.hasBlock())
                    {
                        addOreTable(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rockType).get(oreType).get(), TFCItems.ORES.get(oreType).get());
                    }

                    if (oreType.isGraded())
                    {
                        for (CoreOres.Grade grade : CoreOres.Grade.values())
                        {
                            addOreTable(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(rockType).get(oreType).get(grade).get(), TFCItems.GRADED_ORES.get(oreType).get(CoreOres.getTFCgrade(grade)).get());
                        }
                    }
                }
            }
        }

        for (CoreRocks rock : CoreRocks.values())
        {
            for (OreDeposit ore : OreDeposit.values())
            {
                if (rock.hasOres())
                {
                    this.dropSelf(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore).get());
                }
            }
        }
    }

    private void generateCrop()
    {

        for (CoreCrops cropType : CoreCrops.values())
        {
            addCropTable(cropType);
        }

        for (CoreFruitTrees treeType : CoreFruitTrees.values())
        {
            addFruitTreeTable(treeType);
        }

        for (CoreStationaryBushes bushType : CoreStationaryBushes.values())
        {
            addStationaryBushTable(bushType);
        }

        for (CoreSpreadingBushes bushType : CoreSpreadingBushes.values())
        {
            addSpreadingBushTable(bushType);
        }
    }

    private void generateMetal()
    {

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            for (Metal.BlockType blockType : Metal.BlockType.values())
            {
                if (blockType.has(metalType.getLikeMetal()))
                {
                    this.dropSelf(CoreBlocks.METALS.get(metalType).get(blockType).get());
                }
            }
        }

        /*
        Stream.of(CoreMetals.BlockType.values()).forEach(type -> {
            Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    this.dropSelf(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(type).get());
                }
            });

            Stream.of(Metal.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    this.dropSelf(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(type).get());
                }
            });
        });
         */

        this.dropSelf(CoreBlocks.LEAD_BULB_BLOCK.get());
    }

    private void generateRock()
    {

        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.category() == RockCategory.IGNEOUS_EXTRUSIVE || rockType.category() == RockCategory.IGNEOUS_INTRUSIVE)
            {
                this.dropSelf(CoreBlocks.MAGMA_BLOCKS.get(rockType).get());
            }

            for (Rock.BlockType blockType : Rock.BlockType.values())
            {
                if (rockType.hasVariant(blockType))
                {
                    addRockBlockTable(rockType, blockType);
                }
                if ((blockType.hasVariants() || blockType == Rock.BlockType.MOSSY_COBBLE || blockType == Rock.BlockType.MOSSY_BRICKS) && rockType.hasVariant(blockType))
                {
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).slab().get());
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).stair().get());
                    this.dropSelf(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(blockType).wall().get());
                }
            }

            this.dropSelf(CoreBlocks.MORTARED_CUSTOM_COBBLE.get(rockType).get());
        }

        for (Rock rockType : Rock.values())
        {
            this.dropSelf(CoreBlocks.MORTARED_TFC_COBBLE.get(rockType).get());
        }
    }

    private void generateWood()
    {

        for (SpectrumWood woodType : SpectrumWood.values())
        {
            for (Wood.BlockType blockType : Wood.BlockType.values())
            {
                if (woodType.hasBlockType(blockType))
                {
                    if (blockType == Wood.BlockType.BARREL)
                    {
                        this.add(
                                CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType).get(),
                                LootTableBuilders.createSealableBlockTable(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType).get())
                        );
                    }
                    else
                        if (blockType == Wood.BlockType.SLUICE)
                        {
                            this.add(
                                    CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType).get(),
                                    LootTableBuilders.createSluiceTable(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType).get())
                            );
                        }
                        else
                        {
                            this.dropSelf(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(blockType).get());
                        }
                }
            }

            dropSelf(CoreBlocks.SPECTRUM_WOOD_BOARDS.get(woodType).get());
            dropSelf(CoreBlocks.SPECTRUM_WOOD_SHUTTERS.get(woodType).get());
        }

        for (Wood woodType : Wood.values())
        {
            dropSelf(CoreBlocks.TFC_WOOD_BOARDS.get(woodType).get());
            dropSelf(CoreBlocks.TFC_WOOD_SHUTTERS.get(woodType).get());
        }

        for (AFCWood woodType : AFCWood.values())
        {
            dropSelf(CoreBlocks.AFC_WOOD_BOARDS.get(woodType).get());
            dropSelf(CoreBlocks.AFC_WOOD_SHUTTERS.get(woodType).get());
        }
    }

    private void generateGemstones()
    {

        for (CoreGemstones gemType : CoreGemstones.values())
        {
            for (CoreGemstones.GemstoneBlocks blockType : CoreGemstones.GemstoneBlocks.values())
            {

                DeferredHolder<Block, Block> block = CoreBlocks.GEMSTONE_BLOCKS.get(gemType).get(blockType);

                switch (blockType)
                {
                    case BLOCK, POWDER_BLOCK, PILLAR -> this.dropSelf(block.get());
                    case BUDDING_BLOCK -> dropAir(block.get());
                    case CLUSTER -> this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gemType).get(CoreGemstones.GemstoneItems.SHARD).get(), 4));
                    case LARGE_CLUSTER -> this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gemType).get(CoreGemstones.GemstoneItems.POWDER).get(), 4));
                    case MEDIUM_CLUSTER -> this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gemType).get(CoreGemstones.GemstoneItems.POWDER).get(), 2));
                    case SMALL_CLUSTER -> this.add(block.get(), createClusterTable(block.get(), CoreItems.GEMSTONE_ITEMS.get(gemType).get(CoreGemstones.GemstoneItems.POWDER).get(), 1));
                }
            }
        }
    }

    private void generateMisc()
    {
        for (DyeColor color : DyeColor.values())
        {
            this.dropSelf(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get());
            this.dropSelf(CoreBlocks.COLOURED_LEAD_GLASS.get(color).get());
            this.dropSelf(CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get());
        }

        this.dropSelf(CoreBlocks.CLEAR_MOLTEN_GLASS.get());
        this.dropSelf(CoreBlocks.CLEAR_LEAD_GLASS.get());
        this.dropSelf(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get());

        for (CoreClay clayType : CoreClay.values())
        {
            for (CoreClay.BlockType blockType : CoreClay.BlockType.values())
            {
                if (blockType.hasClayType(clayType))
                {
                    if (blockType == CoreClay.BlockType.CLAY_BLOCK)
                    {
                        this.add(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType).get(),
                                LootTableBuilders.createClayBlockTable(
                                        CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType).get(),
                                        CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.CLAY_BALL).get()
                                )
                        );
                    }
                    else
                        if (blockType == CoreClay.BlockType.LARGE_VESSEL)
                        {
                            this.add(
                                    CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType).get(),
                                    LootTableBuilders.createSealableBlockTable(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType).get())
                            );
                        }
                        else
                        {
                            this.dropSelf(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(blockType).get());
                        }

                }

                if (blockType.getType() == CoreClay.BlockPartType.BLOCK_SET)
                {
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).stair().get());
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).slab().get());
                    this.dropSelf(CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(blockType).wall().get());
                }
            }
        }

        this.dropOther(CoreBlocks.PRISMATIC_ICE.get(), Items.AIR);
    }

    //region methods to call from generate()
    private void addOreTable(Block oreBlock, Item oreItem)
    {
        this.add(oreBlock, LootTableBuilders.createOreTable(oreBlock, oreItem));
    }

    private void addCropTable(CoreCrops crop)
    {

        final var PRODUCT = CategoryUtil.CoreCrop.TO_CROP_PRODUCT.get(crop);

        switch (crop.getCropType())
        {
            case SINGLE ->
            {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildSingleCropTable(crop, PRODUCT));
            }
            case DOUBLE ->
            {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createDoubleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadDoubleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildDoubleCropTable(crop, PRODUCT));
            }
            case SPREADING ->
            {
                this.add(CoreBlocks.CROPS.get(crop).get(), LootTableBuilders.createSpreadingCropTable(crop));
                this.add(CoreBlocks.DEAD_CROPS.get(crop).get(), LootTableBuilders.createDeadSingleCropTable(crop, PRODUCT));
                this.add(CoreBlocks.WILD_CROPS.get(crop).get(), LootTableBuilders.createWildSpreadingCropTable(crop));
            }
        }
    }

    private void addFruitTreeTable(CoreFruitTrees tree)
    {

        this.dropPottedContents(CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.get(tree).get());
        this.add(CoreBlocks.FRUIT_TREE_BRANCHES.get(tree).get(), LootTableBuilders.createBranchTable(tree));
        this.add(CoreBlocks.FRUIT_TREE_GROWING_BRANCHES.get(tree).get(), LootTableBuilders.createGrowingBranchTable());
        this.add(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), createFruitTreeLeavesTable(tree));
        this.add(CoreBlocks.FRUIT_TREE_SAPLINGS.get(tree).get(), LootTableBuilders.createFruitTreeSaplingTable(tree));
    }

    private void addStationaryBushTable(CoreStationaryBushes bush)
    {
        this.add(CoreBlocks.STATIONARY_BUSHES.get(bush).get(), LootTableBuilders.createStationaryBushTable(bush));
    }

    private void addSpreadingBushTable(CoreSpreadingBushes bush)
    {
        this.add(CoreBlocks.SPREADING_BUSHES.get(bush).get(), LootTableBuilders.createSpreadingBushTable(bush));
        this.add(CoreBlocks.SPREADING_CANES.get(bush).get(), LootTableBuilders.createSpreadingBushCaneTable(bush));
    }

    private void addRockBlockTable(CoreRocks rock, Rock.BlockType type)
    {
        switch (type)
        {
            case LOOSE, MOSSY_LOOSE -> this.add(
                    CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createLooseRockDropTable(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get())
            );
            case SPIKE -> this.add(
                    CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createRockDropTable(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get(), 1, 2)
            );
            case ROPE_ANCHOR -> this.add(
                    CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), LootTableBuilders.createRockDropTable(CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get(), 1)
            );
            case RAW, HARDENED -> this.add(
                    CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(),
                    LootTableBuilders.createRawRockDropTable(CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rock).value(), CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get())
            );
            default -> this.dropSelf(
                    CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get()
            );
        }
    }

    private void dropAir(Block block)
    {
        this.add(block, LootTable.lootTable());
    }

    private LootTable.Builder createFruitTreeLeavesTable(CoreFruitTrees tree)
    {
        return this.createSilkTouchOrShearsDispatchTable(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(),
                this.applyExplosionCondition(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get(), LootItem.lootTableItem(Items.STICK)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CoreBlocks.FRUIT_TREE_LEAVES.get(tree).get())
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(TFCBlockStateProperties.LIFECYCLE, Lifecycle.FRUITING))
                        )
                )
        );
    }

    protected LootTable.Builder createClusterTable(Block block, Item item, float amount)
    {
        return this.createSilkTouchDispatchTable(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount)))
                        .apply(ApplyBonusCount.addOreBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                this.applyExplosionDecay(block,
                                        LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        );
    }
    //endregion
}
