package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.recipes.AlloyRecipe;
import net.dries007.tfc.util.AlloyRange;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.category.CoreMetals;

import java.util.List;

public interface AlloyRecipes extends Recipes {

    default void alloyingRecipes(){

        alloy(CoreMetals.MetalType.ALUMINIUM_BRONZE,
                rangeOf(Metal.TIN, 0.1d, 0.2d),
                rangeOf(Metal.COPPER, 0.4d, 0.6d),
                rangeOf(CoreMetals.MetalType.ALUMINIUM, 0.2d, 0.4d)
        );

        alloy(CoreMetals.MetalType.CAST_IRON_ALLOY,
                rangeOf(Metal.NICKEL, 0.1d, 0.3d),
                rangeOf(Metal.CAST_IRON, 0.7d, 0.9d)
        );

        alloy(CoreMetals.MetalType.ELECTRUM,
                rangeOf(Metal.SILVER, 0.3d, 0.7d),
                rangeOf(Metal.COPPER, 0.4d, 0.7d)
        );

        alloy(CoreMetals.MetalType.HARDENED_STEEL,
                rangeOf(Metal.NICKEL, 0.1d, 0.2d),
                rangeOf(Metal.STEEL, 0.8d, 0.9d)
        );

        alloy(CoreMetals.MetalType.NETHERSTEEL,
                rangeOf(Metal.RED_STEEL, 0.1d, 0.3d),
                rangeOf(Metal.STEEL, 0.2d, 0.6d),
                rangeOf(Metal.BRONZE, 0.1d, 0.2d)
        );
    }

    private AlloyRange rangeOf(Metal metal, double min, double max)
    {
        return new AlloyRange(fluidOf(metal), min, max);
    }

    private AlloyRange rangeOf(CoreMetals.MetalType metal, double min, double max)
    {
        return new AlloyRange(metal.getFluid(), min, max);
    }

    private void alloy(CoreMetals.MetalType metal, AlloyRange... ranges)
    {
        final AlloyRecipe recipe = new AlloyRecipe(List.of(ranges), metal.getFluid());
        add(metal.getSerializedName(), recipe);
    }
}
