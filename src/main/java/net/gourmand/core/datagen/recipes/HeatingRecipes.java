package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.stream.Stream;

public interface HeatingRecipes extends Recipes {

    default void heatingRecipes(){

        Stream.of(CoreOres.values()).forEach(ore -> {
            if (ore.isGraded())
                addOres(ore, ore.metal());
        });

        CoreItems.METAL_ITEMS.forEach((metal, items) -> items.forEach((type, item) ->
                add(nameOf(item.get()),
                        new HeatingRecipe(
                            ingredientOf(metal, type),
                            ItemStackProvider.empty(),
                            new FluidStack(metal.getFluid(), units(type)),
                            temperatureOf(metal), new ItemStack(item).isDamageableItem()
                        )
                )
        ));

        CoreBlocks.METALS.forEach((metal, items) -> items.forEach((type, item) ->
                add(nameOf(item.get()),
                        new HeatingRecipe(
                            ingredientOf(metal, type),
                            ItemStackProvider.empty(),
                            new FluidStack(metal.getFluid(), units(type)),
                            temperatureOf(metal), new ItemStack(item.get()).isDamageableItem()
                        )
                )
        ));

        Stream.of(CoreClay.values()).forEach(clayType -> {

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

            Stream.of(CoreClay.ItemType.values()).forEach(itemType -> {

                if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD && itemType.hasType(clayType)){

                    final DeferredHolder<Item, Item> MOLD = MAP.get(itemType);
                    add(MOLD.get(), getMoldOutputItem(itemType), 1399);
                }
            });
        });
    }


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

    private Item getMoldOutputItem(CoreClay.ItemType itemType){

        if (itemType.getType() != CoreClay.ItemPartType.UNFIRED_MOLD){
            return null;
        }

        switch (itemType){
            case PICKAXE_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.PICKAXE_HEAD).asItem();
            }
            case PROPICK_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.PROPICK_HEAD).asItem();
            }
            case AXE_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.AXE_HEAD).asItem();
            }
            case SHOVEL_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.SHOVEL_HEAD).asItem();
            }
            case HOE_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.HOE_HEAD).asItem();
            }
            case CHISEL_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.CHISEL_HEAD).asItem();
            }
            case HAMMER_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.HAMMER_HEAD).asItem();
            }
            case SAW_BLADE -> {
                return TFCItems.MOLDS.get(Metal.ItemType.SAW_BLADE).asItem();
            }
            case JAVELIN_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.JAVELIN_HEAD).asItem();
            }
            case SWORD_BLADE -> {
                return TFCItems.MOLDS.get(Metal.ItemType.SWORD_BLADE).asItem();
            }
            case MACE_HEAD -> {
                return TFCItems.MOLDS.get(Metal.ItemType.MACE_HEAD).asItem();
            }
            case KNIFE_BLADE -> {
                return TFCItems.MOLDS.get(Metal.ItemType.KNIFE_BLADE).asItem();
            }
            case SCYTHE_BLADE -> {
                return TFCItems.MOLDS.get(Metal.ItemType.SCYTHE_BLADE).asItem();
            }
            case BELL -> {
                return TFCItems.BELL_MOLD.asItem();
            }
            case INGOT -> {
                return TFCItems.MOLDS.get(Metal.ItemType.INGOT).asItem();
            }
            case null, default -> {
                throw new AssertionError("CoreClay.ItemType has no corresponding item set.");
            }
        }
    }
}
