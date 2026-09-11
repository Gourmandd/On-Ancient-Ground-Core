package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import net.gourmand.core.common.emi.CookingPotEMIRecipeExtraData;
import net.gourmand.core.registry.recipe.GatedCookingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.integration.emi.EMIPlugin;

import java.util.Optional;

@Mixin(value = EMIPlugin.class, remap = false)
public class FDEMIPluginMixin
{
    @WrapOperation(method = "register", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/EmiRegistry;addRecipe(Ldev/emi/emi/api/recipe/EmiRecipe;)V"))
    private void modpack$addEMIRecipe(EmiRegistry instance, EmiRecipe emiRecipe, Operation<Void> original)
    {
        original.call(instance, emiRecipe);
        if (emiRecipe instanceof CookingPotEMIRecipeExtraData recipeExtraData)
        {
            Optional.ofNullable(emiRecipe.getId()).ifPresent(id ->
            {
                instance.getRecipeManager().byKey(id).ifPresent(recipe ->
                {
                    if (recipe.value() instanceof GatedCookingRecipe gatedRecipe)
                    {
                        if (gatedRecipe.getRequiredAdvancement().isPresent() && gatedRecipe.getRevealSecretAdvancement().isPresent())
                        {
                            recipeExtraData.modpack$setAdvancements(gatedRecipe.getRequiredAdvancement().get(), gatedRecipe.getRevealSecretAdvancement().get());
                        }
                        else
                            if (gatedRecipe.getRequiredAdvancement().isPresent())
                            {
                                recipeExtraData.modpack$setAdvancements(gatedRecipe.getRequiredAdvancement().get(), null);
                            }
                            else
                                if (gatedRecipe.getRevealSecretAdvancement().isPresent())
                                {
                                    recipeExtraData.modpack$setAdvancements(null, gatedRecipe.getRevealSecretAdvancement().get());
                                }
                    }
                });
            });

        }
    }

}