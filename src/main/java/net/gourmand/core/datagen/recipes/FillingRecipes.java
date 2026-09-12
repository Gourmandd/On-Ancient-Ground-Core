package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;

import java.util.concurrent.CompletableFuture;

public class FillingRecipes extends FillingRecipeGen {

    public FillingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MOD_ID);
        fillingRecipes();
    }

    public void fillingRecipes(){

        for (DyeColor color : DyeColor.values()){
            create(AncientGroundCore.location("filling/molten_glass/" + color.getSerializedName()), b -> b
                    .require(CoreFluids.COLORED_GLASS.get(color).getSource(), 800)
                    .require(TFCItems.POWDERS.get(Powder.FLUX))
                    .output(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
            );
        }

        create(AncientGroundCore.location("filling/molten_glass/clear"), b -> b
                .require(CoreFluids.CLEAR_GLASS.getSource(), 800)
                .require(TFCItems.POWDERS.get(Powder.FLUX))
                .output(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
        );
    }
}
