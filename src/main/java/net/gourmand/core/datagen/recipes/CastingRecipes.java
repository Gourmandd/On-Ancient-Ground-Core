package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.CastingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.stream.Stream;

public interface CastingRecipes extends Recipes {

    default void castingRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
            casting(metal.name() + "_ingot", TFCItems.MOLDS.get(Metal.ItemType.INGOT), metal, 0.1f);
            casting(metal.name() + "_fire_ingot", TFCItems.FIRE_INGOT_MOLD, metal, 0.01f);
        });
    }

    private void casting(String name, ItemLike item, CoreMetals.MetalType metal, float chance)
    {
        casting(name, item, metal, CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT).get(), units(Metal.ItemType.INGOT), chance);
    }

    private void casting(String name, ItemLike item, CoreMetals.MetalType metal, ItemLike result, int units, float chance)
    {
        add(name, new CastingRecipe(
                Ingredient.of(item),
                SizedFluidIngredient.of(fluidOf(metal), units),
                ItemStackProvider.of(result),
                chance
        ));
    }
}
