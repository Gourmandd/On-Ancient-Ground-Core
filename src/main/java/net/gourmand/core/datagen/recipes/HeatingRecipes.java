package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.stream.Stream;

public interface HeatingRecipes extends Recipes {

    default void heatingRecipes(){

        Stream.of(CoreOres.values()).forEach(ore -> {
            if (ore.isGraded())
                addOres(ore, ore.metal());
        });

        CoreItems.METAL_ITEMS.forEach((metal, items) -> items.forEach((type, item) -> add(nameOf(item.get()), new HeatingRecipe(
                ingredientOf(metal, type),
                ItemStackProvider.empty(),
                new FluidStack(metal.getFluid(), units(type)),
                temperatureOf(metal), new ItemStack(item).isDamageableItem()))));
        CoreBlocks.METALS.forEach((metal, items) -> items.forEach((type, item) -> add(nameOf(item.get()), new HeatingRecipe(
                ingredientOf(metal, type),
                ItemStackProvider.empty(),
                new FluidStack(metal.getFluid(), units(type)),
                temperatureOf(metal), new ItemStack(item.get()).isDamageableItem()))));
    }


    private void addOres(CoreOres ore, CoreMetals.MetalType metal)
    {
        final float temperature = temperatureOf(metal);

        add(Ingredient.of(CoreBlocks.SMALL_ORES.get(ore).get().asItem()), new FluidStack(fluidOf(metal), 10), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.POOR).get()), new FluidStack(fluidOf(metal), 15), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.NORMAL).get()), new FluidStack(fluidOf(metal), 25), temperature);
        add(Ingredient.of(CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.RICH).get()), new FluidStack(fluidOf(metal), 35), temperature);
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
