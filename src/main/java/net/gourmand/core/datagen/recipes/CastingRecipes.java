package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.CastingRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.stream.Stream;

public interface CastingRecipes extends Recipes
{

    default void castingRecipes()
    {

        Stream.of(CoreMetals.MetalType.values()).forEach(metal ->
        {
            casting(metal.name() + "_ingot", TFCItems.MOLDS.get(Metal.ItemType.INGOT), metal, 0.1f);
            casting(metal.name() + "_fire_ingot", TFCItems.FIRE_INGOT_MOLD, metal, 0.01f);
        });

        for (DyeColor color : DyeColor.values())
        {
            casting("molten_glass/" + color.getSerializedName(), CoreItems.GLASS_MOLD.get().asItem(), CoreFluids.COLORED_GLASS.get(color).getSource(), CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get().asItem(), 800, 0.0f);

            casting("glass_pane/" + color.getSerializedName(), CoreItems.GLASS_MOLD.get().asItem(), CoreFluids.COLORED_GLASS.get(color).getSource(), CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(color), 50, 0.0f);
        }

        casting("molten_glass/clear", CoreItems.GLASS_MOLD.get().asItem(), CoreFluids.CLEAR_GLASS.getSource(), CoreBlocks.CLEAR_MOLTEN_GLASS.get().asItem(), 800, 0.0f);

        casting("glass_pane/clear", CoreItems.GLASS_MOLD.get().asItem(), CoreFluids.CLEAR_GLASS.getSource(), Blocks.GLASS_PANE, 50, 0.0f);
    }

    private void casting(String name, ItemLike item, CoreMetals.MetalType metal, float chance)
    {
        casting(name, item, metal, CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT).get(), units(Metal.ItemType.INGOT), chance);
    }

    private void casting(String name, ItemLike item, CoreMetals.MetalType metal, ItemLike result, int units, float chance)
    {
        casting(name, item, fluidOf(metal), result, units, chance);
    }

    private void casting(String name, ItemLike item, Fluid fluid, ItemLike result, int units, float chance)
    {
        add(name, new CastingRecipe(Ingredient.of(item), SizedFluidIngredient.of(fluid, units), ItemStackProvider.of(result), chance));
    }
}
