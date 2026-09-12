package net.gourmand.core.registry;

import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.recipe.GatedCookingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CoreRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, AncientGroundCore.MOD_ID);

    public static final RecipeSerializer<GatedCookingRecipe> GATED_COOKING = register("gated_cooking_pot", new GatedCookingRecipe.Serializer());

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        RECIPES.register(id, () -> serializer);
        return serializer;
    }
}
