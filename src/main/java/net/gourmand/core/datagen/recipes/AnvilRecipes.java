package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.component.forge.ForgeRule;
import net.dries007.tfc.common.recipes.AnvilRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.stream.Stream;

public interface AnvilRecipes extends Recipes{

    default void anvilRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
            anvil(
                    CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT).get(),
                    metal.tier(),
                    List.of(
                            ForgeRule.HIT_LAST,
                            ForgeRule.HIT_SECOND_LAST,
                            ForgeRule.HIT_THIRD_LAST
                    ),
                    CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.SHEET).get()
            );
            anvil(
                    CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT).get(),
                    metal.tier(),
                    List.of(
                            ForgeRule.BEND_LAST,
                            ForgeRule.DRAW_SECOND_LAST,
                            ForgeRule.DRAW_THIRD_LAST
                    ),
                    ItemStackProvider.of(CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.ROD).get(), 2)
            );
        });
    }

    private void anvil(ItemLike input, int tier, List<ForgeRule> rules, ItemLike output)
    {
        anvil(Ingredient.of(input), tier, rules, ItemStackProvider.of(output));
    }

    private void anvil(ItemLike input, int tier, List<ForgeRule> rules, ItemStackProvider output)
    {
        anvil(Ingredient.of(input), tier, rules, output);
    }

    private void anvil(Ingredient input, int tier, List<ForgeRule> rules, ItemStackProvider output)
    {
        add(new AnvilRecipe(input, tier, rules, false, output));
    }
}
