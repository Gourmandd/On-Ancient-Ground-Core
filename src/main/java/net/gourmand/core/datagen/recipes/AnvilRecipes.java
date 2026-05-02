package net.gourmand.core.datagen.recipes;

import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import net.dries007.tfc.common.component.forge.ForgeRule;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.AnvilRecipe;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.stream.Stream;

public interface AnvilRecipes extends Recipes {

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

        anvil(
                CoreItems.ORES.get(CoreOres.METEORIC_IRON).get(),
                Metal.COPPER.tier(),
                List.of(
                        ForgeRule.HIT_LAST,
                        ForgeRule.HIT_SECOND_LAST,
                        ForgeRule.HIT_THIRD_LAST
                ),
                TFCItems.METAL_ITEMS.get(Metal.WROUGHT_IRON).get(Metal.ItemType.INGOT).get()
        );

        anvil(
                TFCItems.METAL_ITEMS.get(Metal.WROUGHT_IRON).get(Metal.ItemType.SHEET).get(),
                Metal.WROUGHT_IRON.tier(),
                List.of(
                        ForgeRule.BEND_THIRD_LAST,
                        ForgeRule.BEND_SECOND_LAST,
                        ForgeRule.HIT_LAST
                ),
                ItemStackProvider.of(CoreItems.WROUGHT_IRON_BUCKET.get())
        );

        anvil(
                TFCItems.METAL_ITEMS.get(Metal.BRASS).get(Metal.ItemType.DOUBLE_SHEET).get(),
                Metal.BRONZE.tier(),
                List.of(
                        ForgeRule.BEND_LAST,
                        ForgeRule.BEND_SECOND_LAST,
                        ForgeRule.HIT_THIRD_LAST
                ),
                CoreItems.GLASS_MOLD.get()
        );

        anvil(
                TFCItems.METAL_ITEMS.get(Metal.BRASS).get(Metal.ItemType.DOUBLE_SHEET).get(),
                Metal.BRONZE.tier(),
                List.of(
                        ForgeRule.BEND_LAST,
                        ForgeRule.PUNCH_SECOND_LAST,
                        ForgeRule.HIT_THIRD_LAST
                ),
                CoreItems.GLASS_PANE_MOLD.get()
        );

        anvil(
                FLItems.METAL_ITEMS.get(FLMetal.STAINLESS_STEEL).get(FLMetal.ItemType.DOUBLE_SHEET).get(),
                Metal.STEEL.tier(),
                List.of(
                        ForgeRule.BEND_LAST,
                        ForgeRule.PUNCH_SECOND_LAST,
                        ForgeRule.UPSET_THIRD_LAST
                ),
                CoreItems.SNOW_SHOVEL_HEAD.get()
        );
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
