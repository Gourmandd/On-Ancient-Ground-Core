package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.component.glass.GlassOperation;
import net.dries007.tfc.common.recipes.GlassworkingRecipe;
import net.gourmand.core.registry.CoreGlassworkingOperations;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public interface GlassworkingRecipes extends Recipes
{
    default void glassworkingRecipes()
    {
        add(Ingredient.of(TFCTags.Items.GLASS_BATCHES_NOT_T1), List.of(CoreGlassworkingOperations.OBSIDIAN.value(), GlassOperation.BASIN_POUR.value()), Items.TINTED_GLASS);

        // remove the old tinted glass recipe
        remove("tfc:glassworking/tinted_glass");
    }

    private void add(Ingredient input, List<GlassOperation> steps, ItemLike output)
    {
        add(nameOf(output), new GlassworkingRecipe(steps, input, new ItemStack(output)));
    }
}
