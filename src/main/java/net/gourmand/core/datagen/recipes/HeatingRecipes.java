package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;

public interface HeatingRecipes extends Recipes
{

    float GLASS_MELT_TEMPERATURE = 1070f;

    default void heatingRecipes()
    {

        for (CoreOres oreType : CoreOres.values())
        {
            if (oreType.isGraded()) addOres(oreType, oreType.metal());
        }

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {

            for (Metal.ItemType itemType : Metal.ItemType.values())
            {
                if (itemType.has(metalType.getLikeMetal()))
                {
                    add(nameOf(CoreItems.METAL_ITEMS.get(metalType).get(itemType).get()),
                            new HeatingRecipe(
                                    ingredientOf(metalType, itemType),
                                    ItemStackProvider.empty(),
                                    new FluidStack(metalType.getFluid(), units(itemType)),
                                    temperatureOf(metalType),
                                    new ItemStack(CoreItems.METAL_ITEMS.get(metalType).get(itemType)).isDamageableItem()
                            )
                    );
                }
            }

            for (Metal.BlockType blockType : Metal.BlockType.values())
            {
                if (blockType.has(metalType.getLikeMetal()))
                {
                    add(nameOf(CoreBlocks.METALS.get(metalType).get(blockType).get()),
                            new HeatingRecipe(ingredientOf(metalType, blockType),
                                    ItemStackProvider.empty(),
                                    new FluidStack(metalType.getFluid(), units(blockType)),
                                    temperatureOf(metalType),
                                    new ItemStack(CoreBlocks.METALS.get(metalType).get(blockType).get()).isDamageableItem()
                            )
                    );
                }
            }
        }

        /*
        Stream.of(CoreMetals.BlockType.values()).forEach(type -> {
            Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    addCustomMetalBlock(metal, type);
                }
            });

            Stream.of(Metal.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    addCustomMetalBlock(metal, type);
                }
            });
        });
         */

        for (CoreClay clayType : CoreClay.values())
        {

            final Map<CoreClay.ItemType, DeferredHolder<Item, Item>> MAP = CoreItems.CERAMICS.get(clayType);

            add(MAP.get(CoreClay.ItemType.UNFIRED_BLOWPIPE).get(), TFCItems.CERAMIC_BLOWPIPE.get(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_FLOWER_POT).get(), Items.FLOWER_POT, 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_BOWL).get(), TFCBlocks.CERAMIC_BOWL.asItem(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_PAN).get(), TFCItems.EMPTY_PAN.get(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_SPINDLE_HEAD).get(), TFCItems.SPINDLE_HEAD.asItem(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_POT).get(), TFCItems.POT.asItem(), 1399);

            add(MAP.get(CoreClay.ItemType.UNFIRED_BRICK).get(), MAP.get(CoreClay.ItemType.BRICK).get(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_VESSEL).get(), MAP.get(CoreClay.ItemType.VESSEL).get(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_JUG).get(), MAP.get(CoreClay.ItemType.JUG).get(), 1399);
            add(MAP.get(CoreClay.ItemType.UNFIRED_LARGE_VESSEL).get(), CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(CoreClay.BlockType.LARGE_VESSEL).get().asItem(), 1399);

            for (CoreClay.ItemType itemType : CoreClay.ItemType.values())
            {

                if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD && itemType.hasType(clayType))
                {

                    final DeferredHolder<Item, Item> MOLD = MAP.get(itemType);
                    add(MOLD.get(), CategoryUtil.Tools.UNFIRED_MOLD_TYPE_TO_FIRED.get(itemType), 1399);
                }
            }
        }


        // Colored glass
        for (DyeColor color : DyeColor.values())
        {
            Block framedGlassBlock = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS.get(color).value();
            Block framedGlassPane = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS_PANE.get(color).value();

            Block glassBlock = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS.get(color);
            Block glassPane = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(color);

            Block leadGlassBlock = CoreBlocks.COLOURED_LEAD_GLASS.get(color).get();
            Block leadGlassPane = CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get();

            add(Ingredient.of(framedGlassBlock), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 800), GLASS_MELT_TEMPERATURE);
            add(Ingredient.of(framedGlassPane), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 50), GLASS_MELT_TEMPERATURE);

            add(Ingredient.of(glassBlock), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 800), GLASS_MELT_TEMPERATURE);
            add(Ingredient.of(glassPane), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 50), GLASS_MELT_TEMPERATURE);

            add(Ingredient.of(leadGlassBlock), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 800), GLASS_MELT_TEMPERATURE);
            add(Ingredient.of(leadGlassPane), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 50), GLASS_MELT_TEMPERATURE);

            add(Ingredient.of(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get()), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 800), GLASS_MELT_TEMPERATURE);
            add(Ingredient.of(CoreItems.COLORED_LENS.get(color).get()), new FluidStack(CoreFluids.COLORED_GLASS.get(color).getSource(), 400), GLASS_MELT_TEMPERATURE);
        }

        add(Ingredient.of(CategoryUtil.Glass.QUARK_CLEAR_GLASS.value()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(CategoryUtil.Glass.QUARK_CLEAR_GLASS_PANE.value()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 50), GLASS_MELT_TEMPERATURE);

        add(Ingredient.of(Blocks.GLASS), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(Blocks.GLASS_PANE), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 50), GLASS_MELT_TEMPERATURE);

        add(Ingredient.of(CoreBlocks.CLEAR_LEAD_GLASS.get()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 50), GLASS_MELT_TEMPERATURE);

        add(Ingredient.of(CoreBlocks.CLEAR_MOLTEN_GLASS.get()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(TFCItems.LENS.get()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 400), GLASS_MELT_TEMPERATURE);

        add(Ingredient.of(TFCItems.SILICA_GLASS_BATCH.get()), new FluidStack(CoreFluids.CLEAR_GLASS.getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(TFCItems.VOLCANIC_GLASS_BATCH.get()), new FluidStack(CoreFluids.COLORED_GLASS.get(DyeColor.BLUE).getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(TFCItems.OLIVINE_GLASS_BATCH.get()), new FluidStack(CoreFluids.COLORED_GLASS.get(DyeColor.GREEN).getSource(), 800), GLASS_MELT_TEMPERATURE);
        add(Ingredient.of(TFCItems.HEMATITIC_GLASS_BATCH.get()), new FluidStack(CoreFluids.COLORED_GLASS.get(DyeColor.ORANGE).getSource(), 800), GLASS_MELT_TEMPERATURE);
    }

    /*
    private void addCustomMetalBlock(CoreMetals.MetalType metal, CoreMetals.BlockType type)
    {
        var item = CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(type).get().asItem();

        add(nameOf(item),
                new HeatingRecipe(
                        Ingredient.of(item),
                        ItemStackProvider.empty(),
                        new FluidStack(fluidFor(metal), units(type)),
                        temperatureOf(metal), new ItemStack(item).isDamageableItem()
                )
        );
    }

    private void addCustomMetalBlock(Metal metal, CoreMetals.BlockType type)
    {
        var item = CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(type).get().asItem();

        add(nameOf(item),
                new HeatingRecipe(
                        Ingredient.of(item),
                        ItemStackProvider.empty(),
                        new FluidStack(fluidFor(metal), units(type)),
                        temperatureOf(metal), new ItemStack(item).isDamageableItem()
                )
        );
    }
     */

    private void addOres(CoreOres ore, CoreMetals.MetalType metal)
    {
        final float temperature = temperatureOf(metal);

        add(Ingredient.of(CoreBlocks.SMALL_ORES.get(ore).get().asItem()), new FluidStack(fluidOf(metal), 10), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.POOR).get()), new FluidStack(fluidOf(metal), 15), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.NORMAL).get()), new FluidStack(fluidOf(metal), 25), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.RICH).get()), new FluidStack(fluidOf(metal), 35), temperature);
    }

    private void add(Item input, Item output, float temperature)
    {
        add(nameOf(input), new HeatingRecipe(Ingredient.of(input), ItemStackProvider.of(output), FluidStack.EMPTY, temperature, false));
    }

    private void add(Ingredient input, FluidStack output, float temperature)
    {
        add(nameOf(input), new HeatingRecipe(input, ItemStackProvider.empty(), output, temperature, false));
    }

    private void add(Ingredient input, ItemStackProvider output, float temperature)
    {
        add(new HeatingRecipe(input, output, FluidStack.EMPTY, temperature, false));
    }
}
