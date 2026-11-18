package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import de.dafuqs.spectrum.helpers.enchantments.FoundryHelper;
import net.dries007.tfc.common.recipes.HeatingRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeTypes;
import net.gourmand.core.registry.category.CoreDataMaps;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(FoundryHelper.class)
public class FoundryHelperMixin
{
    @WrapMethod(method = "getSmeltedItemStack")
    private static ItemStack modpack$changeFoundryCheck(ItemStack stack, Level world, Operation<ItemStack> original)
    {
        System.out.println(stack);
        CoreDataMaps.FoundryConversion data = stack.getItemHolder().getData(CoreDataMaps.FOUNDRY_CONVERSION);
        if (data != null)
        {
            System.out.println(data.stack());
            if (!data.stack().isEmpty())
            {
                return data.stack().copyWithCount(stack.getCount() * data.stack().getCount());
            }
        }
        else
        {
            List<RecipeHolder<HeatingRecipe>> recipeList = world.getRecipeManager().getAllRecipesFor(TFCRecipeTypes.HEATING.get());
            for (RecipeHolder<HeatingRecipe> holder : recipeList)
            {
                if (holder.value().getIngredient().equals(Ingredient.of(stack)))
                {
                    ItemStack itemstack = holder.value().getResultItem(world.registryAccess());
                    if (!itemstack.isEmpty()) {
                        return itemstack.copyWithCount(stack.getCount() * itemstack.getCount());
                    }
                }
            }
        }

        return stack;
    }
}
