package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.recipes.WeldingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;

import static net.dries007.tfc.common.recipes.WeldingRecipe.Behavior.*;
import static net.dries007.tfc.util.Metal.ItemType;

public interface WeldingRecipes extends Recipes
{

    default void weldingRecipes()
    {

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            if (metalType.getLikeMetal().defaultParts())
            {
                weld(metalType, ItemType.INGOT, ItemType.INGOT, ItemType.DOUBLE_INGOT, IGNORE);
                weld(metalType, ItemType.SHEET, ItemType.SHEET, ItemType.DOUBLE_SHEET, IGNORE);
            }
            if (metalType.getLikeMetal().allParts())
            {
                weld(metalType, ItemType.UNFINISHED_HELMET, ItemType.SHEET, ItemType.HELMET, COPY_BEST);
                weld(metalType, ItemType.UNFINISHED_CHESTPLATE, ItemType.DOUBLE_SHEET, ItemType.CHESTPLATE, COPY_BEST);
                weld(metalType, ItemType.UNFINISHED_GREAVES, ItemType.SHEET, ItemType.GREAVES, COPY_BEST);
                weld(metalType, ItemType.UNFINISHED_BOOTS, ItemType.SHEET, ItemType.BOOTS, COPY_BEST);
                weld(metalType, ItemType.KNIFE_BLADE, ItemType.KNIFE_BLADE, ItemType.SHEARS, COPY_WORST);
            }
        }
    }

    private void weld(CoreMetals.MetalType ingot1, CoreMetals.MetalType ingot2, CoreMetals.MetalType ingotOut)
    {
        add(
                new WeldingRecipe(
                        ingredientOf(ingot1, ItemType.INGOT),
                        ingredientOf(ingot2, ItemType.INGOT),
                        ingotOut.tier() - 1,
                        ItemStackProvider.of(CoreItems.METAL_ITEMS.get(ingotOut).get(ItemType.INGOT).get()),
                        IGNORE
                )
        );
    }

    private void weld(CoreMetals.MetalType metal, ItemType input1, ItemType input2, ItemType output, WeldingRecipe.Behavior behavior)
    {
        add(
                new WeldingRecipe(
                        ingredientOf(metal, input1),
                        ingredientOf(metal, input2),
                        metal.tier() - 1,
                        ItemStackProvider.of(CoreItems.METAL_ITEMS.get(metal).get(output).get()),
                        behavior
                )
        );
    }
}
