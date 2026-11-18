package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import net.dries007.tfc.common.blocks.SandstoneBlockType;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CategoryUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;

import java.util.Locale;
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

        final int NORMAL_TIME = 200;
        final int LONG_TIME = 400;

        for (DyeColor color : DyeColor.values())
        {
            create(AncientGroundCore.MOD_ID + "milling/concrete/" + color.getSerializedName(), b -> b
                    .require(CategoryUtil.Color.TO_CONCRETE.get(color).asItem())
                    .duration(NORMAL_TIME)
                    .output(CategoryUtil.Color.TO_CONCRETE_POWDER.get(color).asItem())
            );
        }

        for (Ore oreType : Ore.values())
        {
            if (oreType.isGem())
            {
                create(AncientGroundCore.MOD_ID + "milling/gem/" + oreType.name().toLowerCase(Locale.ROOT), b -> b
                        .require(TFCItems.GEMS.get(oreType))
                        .duration(LONG_TIME)
                        .output(TFCItems.ORE_POWDERS.get(oreType))
                );

                create(AncientGroundCore.MOD_ID + "milling/ore/" + oreType.name().toLowerCase(Locale.ROOT), b -> b
                        .require(TFCItems.ORES.get(oreType))
                        .duration(LONG_TIME)
                        .output(TFCItems.ORE_POWDERS.get(oreType))
                );
            }
        }

        for (SandBlockType color : SandBlockType.values())
        {
            create(AncientGroundCore.MOD_ID + "milling/raw_sandstone/" + color.name().toLowerCase(Locale.ROOT), b -> b
                    .require(TFCBlocks.SANDSTONE.get(color).get(SandstoneBlockType.RAW))
                    .duration(NORMAL_TIME)
                    .output(TFCBlocks.SAND.get(color))
            );
        }
    }
}
