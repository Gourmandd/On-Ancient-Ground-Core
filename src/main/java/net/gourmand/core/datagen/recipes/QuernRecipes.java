package net.gourmand.core.datagen.recipes;

import com.simibubi.create.AllItems;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.recipes.QuernRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.gourmand.core.registry.category.CategoryUtil;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public interface QuernRecipes extends Recipes
{

    default void quernRecipes()
    {
        for (DyeColor color : DyeColor.values())
        {
            add(CategoryUtil.Color.TO_CONCRETE.get(color).asItem(), CategoryUtil.Color.TO_CONCRETE_POWDER.get(color).asItem());
        }

        add("4", Blocks.OBSIDIAN.asItem(), AllItems.POWDERED_OBSIDIAN.asItem(), 4);
        add(TFCBlocks.GROUNDCOVER.get(GroundcoverBlockType.OBSIDIAN_SHARD).asItem(), AllItems.POWDERED_OBSIDIAN.asItem(), 1);
    }

    private void add(Item input, Item output)
    {
        add(input, output, 1);
    }

    private void add(Item input, Item output, int count)
    {
        add(Ingredient.of(input), ItemStackProvider.of(output, count));
    }

    private void add(String suffix, Item input, Item output, int count)
    {
        add(new QuernRecipe(Ingredient.of(input), ItemStackProvider.of(output, count)), suffix);
    }

    private void add(Ingredient input, ItemStackProvider output)
    {
        add(new QuernRecipe(input, output));
    }

    private void add(QuernRecipe recipe, String suffix)
    {
        add(nameOf(recipe) + "_" + suffix, recipe);
    }
}
