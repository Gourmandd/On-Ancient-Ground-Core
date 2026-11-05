package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.recipes.QuernRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.gourmand.core.registry.category.CategoryUtil;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public interface QuernRecipes extends Recipes
{

    default void quernRecipes()
    {
        for (DyeColor color : DyeColor.values())
        {
            add(CategoryUtil.Color.TO_CONCRETE.get(color).asItem(), CategoryUtil.Color.TO_CONCRETE_POWDER.get(color).asItem());
        }
    }

    private void add(Item input, Item output)
    {
        add(Ingredient.of(input), ItemStackProvider.of(output));
    }

    private void add(Ingredient input, ItemStackProvider output)
    {
        add(new QuernRecipe(input, output));
    }
}
