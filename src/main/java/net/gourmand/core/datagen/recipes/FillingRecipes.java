package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.common.component.fluid.FluidComponent;
import net.dries007.tfc.common.component.heat.HeatComponent;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class FillingRecipes extends FillingRecipeGen implements Accessors
{

    public FillingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        fillingRecipes();
    }

    public void fillingRecipes()
    {

        for (DyeColor color : DyeColor.values())
        {
            create(AncientGroundCore.location("molten_glass/" + color.getSerializedName()), b -> b
                    .require(CoreFluids.COLORED_GLASS.get(color).getSource(), 800)
                    .require(TFCItems.POWDERS.get(Powder.FLUX))
                    .output(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
            );
        }

        create(AncientGroundCore.location("molten_glass/clear"), b -> b
                .require(CoreFluids.CLEAR_GLASS.getSource(), 800)
                .require(TFCItems.POWDERS.get(Powder.FLUX))
                .output(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
        );

        for (SoilBlockType.Variant soilType : SoilBlockType.Variant.values())
        {
            create(AncientGroundCore.location("grass/" + soilType.name().toLowerCase(Locale.ROOT)), b -> b
                    .require(Fluids.WATER, 500)
                    .require(TFCBlocks.SOIL.get(SoilBlockType.DIRT).get(soilType))
                    .output(TFCBlocks.SOIL.get(SoilBlockType.GRASS).get(soilType))
            );
        }

        for (Metal metalType : Metal.values())
        {
            if (metalType != Metal.WROUGHT_IRON)
            {
                create(AncientGroundCore.location("casting/ingot/fire_" + metalType.getSerializedName()), b -> b
                        .require(TFCFluids.METALS.get(metalType).getSource(), 100)
                        .require(Ingredient.of(TFCItems.FIRE_INGOT_MOLD))
                        .output(setMold(TFCItems.FIRE_INGOT_MOLD.get(), metalType, 100))
                );

                create(AncientGroundCore.location("casting/ingot/" + metalType.getSerializedName()), b -> b
                        .require(TFCFluids.METALS.get(metalType).getSource(), 100)
                        .require(Ingredient.of(TFCItems.MOLDS.get(Metal.ItemType.INGOT)))
                        .output(setMold(TFCItems.MOLDS.get(Metal.ItemType.INGOT).asItem(), metalType, 100))
                );

                if (metalType.allParts())
                {
                    for (Metal.ItemType itemType : Metal.ItemType.values())
                    {
                        if (itemType.hasMold() && itemType != Metal.ItemType.INGOT)
                        {
                            create(AncientGroundCore.location("casting/" + itemType.name().toLowerCase(Locale.ROOT) + "/fire_" + metalType.getSerializedName()), b -> b
                                    .require(TFCFluids.METALS.get(metalType).getSource(), units(itemType))
                                    .require(Ingredient.of(CoreItems.FIRE_MOLDS.get(itemType).get()))
                                    .output(setMold(CoreItems.FIRE_MOLDS.get(itemType).get().asItem(), metalType, units(itemType)))
                            );

                            if (metalType.tier() <= 2)
                            {
                                create(AncientGroundCore.location("casting/" + itemType.name().toLowerCase(Locale.ROOT) + "/" + metalType.getSerializedName()), b -> b
                                        .require(TFCFluids.METALS.get(metalType).getSource(), units(itemType))
                                        .require(Ingredient.of(TFCItems.MOLDS.get(itemType)))
                                        .output(setMold(TFCItems.MOLDS.get(itemType).asItem(), metalType, units(itemType)))
                                );
                            }
                        }
                    }
                }
            }
        }

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            create(AncientGroundCore.location("casting/ingot/fire_" + metalType.getSerializedName()), b -> b
                    .require(metalType.getFlowingFluid(), 100)
                    .require(Ingredient.of(TFCItems.FIRE_INGOT_MOLD))
                    .output(setMold(TFCItems.FIRE_INGOT_MOLD.get(), metalType, 100))
            );

            create(AncientGroundCore.location("casting/ingot/" + metalType.getSerializedName()), b -> b
                    .require(metalType.getFlowingFluid(), 100)
                    .require(Ingredient.of(TFCItems.MOLDS.get(Metal.ItemType.INGOT)))
                    .output(setMold(TFCItems.MOLDS.get(Metal.ItemType.INGOT).asItem(), metalType, 100))
            );
        }
    }

    private ItemStack setMold(Item moldItem, Metal metalType, int amount)
    {
        ItemStack output = moldItem.getDefaultInstance();
        output.set(TFCComponents.FLUID, FluidComponent.of(TFCFluids.METALS.get(metalType).getSource(), amount));
        output.set(TFCComponents.HEAT, HeatComponent.of(1.2f, temperatureOf(metalType)));

        return output;
    }

    private ItemStack setMold(Item moldItem, CoreMetals.MetalType metalType, int amount)
    {
        ItemStack output = moldItem.getDefaultInstance();
        output.set(TFCComponents.FLUID, FluidComponent.of(metalType.getFlowingFluid(), amount));
        output.set(TFCComponents.HEAT, HeatComponent.of(1.2f, temperatureOf(metalType)));

        return output;
    }
}
