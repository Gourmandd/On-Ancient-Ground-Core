package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CategoryUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;

import java.util.concurrent.CompletableFuture;

public class MillingRecipes extends MillingRecipeGen
{
    public MillingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        millingRecipes();
    }

    public void millingRecipes()
    {
        for (DyeColor color : DyeColor.values())
        {
            create(AncientGroundCore.MOD_ID + "milling/concrete/" + color.getSerializedName(), b ->
                    b.require(CategoryUtil.Color.TO_CONCRETE.get(color).asItem())
                    .output(CategoryUtil.Color.TO_CONCRETE_POWDER.get(color).asItem())
            );
        }
    }
}
