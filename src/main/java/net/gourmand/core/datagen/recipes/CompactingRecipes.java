package net.gourmand.core.datagen.recipes;

import com.eerussianguy.firmalife.common.blocks.FLFluids;
import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.dries007.tfc.common.blocks.SandstoneBlockType;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class CompactingRecipes extends CompactingRecipeGen
{

    public CompactingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        compactingRecipes();
    }

    public void compactingRecipes()
    {


        for(CoreMetals.MetalType metalType: CoreMetals.MetalType.values())
        {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> SHEET = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET);
            final DeferredHolder<Item, Item> DOUBLE_SHEET = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET);

            create(
                    DOUBLE_INGOT.getId().getPath(), b -> b
                            .require(INGOT.get())
                            .require(INGOT.get())
                            .require(TFCItems.POWDERS.get(Powder.FLUX))
                            .requiresHeat(HeatCondition.HEATED)
                            .output(DOUBLE_INGOT.get())
            );

            create(
                    DOUBLE_SHEET.getId().getPath(), b -> b
                            .require(SHEET.get())
                            .require(SHEET.get())
                            .require(TFCItems.POWDERS.get(Powder.FLUX))
                            .requiresHeat(HeatCondition.HEATED)
                            .output(DOUBLE_SHEET.get())
            );

            create(
                    SHEET.getId().getPath(), b -> b
                            .require(metalType.getFlowingFluid(), 200)
                            .requiresHeat(HeatCondition.HEATED)
                            .output(SHEET.get())
            );
        }

        for(Metal metalType: Metal.values())
        {
            if (metalType.defaultParts())
            {
                final TFCItems.ItemId INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT);
                final TFCItems.ItemId DOUBLE_INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT);
                final TFCItems.ItemId SHEET = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET);
                final TFCItems.ItemId DOUBLE_SHEET = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET);
                final FlowingFluid FLUID = TFCFluids.METALS.get(metalType).getSource();

                create(
                        DOUBLE_INGOT.getId().getPath(), b -> b
                                .require(INGOT.get())
                                .require(INGOT.get())
                                .require(TFCItems.POWDERS.get(Powder.FLUX))
                                .requiresHeat(HeatCondition.HEATED)
                                .output(DOUBLE_INGOT.get())
                );

                create(
                        DOUBLE_SHEET.getId().getPath(), b -> b
                                .require(SHEET.get())
                                .require(SHEET.get())
                                .require(TFCItems.POWDERS.get(Powder.FLUX))
                                .requiresHeat(HeatCondition.HEATED)
                                .output(DOUBLE_SHEET.get())
                );

                if (metalType != Metal.WROUGHT_IRON)
                {
                    create(
                            SHEET.getId().getPath(), b -> b
                                    .require(FLUID, 200)
                                    .requiresHeat(HeatCondition.HEATED)
                                    .output(SHEET.get())
                    );
                }
            }
        }

        for(FLMetal metalType: FLMetal.values())
        {
            final TFCItems.ItemId INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.INGOT);
            final TFCItems.ItemId DOUBLE_INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.DOUBLE_INGOT);
            final TFCItems.ItemId SHEET = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.SHEET);
            final TFCItems.ItemId DOUBLE_SHEET = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.DOUBLE_SHEET);
            final FlowingFluid FLUID = FLFluids.METALS.get(metalType).getSource();

            create(
                    DOUBLE_INGOT.getId().getPath(), b -> b
                            .require(INGOT.get())
                            .require(INGOT.get())
                            .require(TFCItems.POWDERS.get(Powder.FLUX))
                            .requiresHeat(HeatCondition.HEATED)
                            .output(DOUBLE_INGOT.get())
            );

            create(
                    DOUBLE_SHEET.getId().getPath(), b -> b
                            .require(SHEET.get())
                            .require(SHEET.get())
                            .require(TFCItems.POWDERS.get(Powder.FLUX))
                            .requiresHeat(HeatCondition.HEATED)
                            .output(DOUBLE_SHEET.get())
            );

            create(
                    SHEET.getId().getPath(), b -> b
                            .require(FLUID, 200)
                            .requiresHeat(HeatCondition.HEATED)
                            .output(SHEET.get())
            );
        }

        // colored glass
        for (DyeColor color : DyeColor.values())
        {
            create(
                    AncientGroundCore.location("compacting/molten_glass/" + color.getSerializedName()), b -> b
                            .require(CoreFluids.COLORED_GLASS.get(color).getSource(), 800)
                            .output(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
            );
        }
        // clear glass
        create(
                AncientGroundCore.location("compacting/molten_glass/clear"), b -> b
                        .require(CoreFluids.CLEAR_GLASS.getSource(), 800)
                        .output(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
        );

        // dye
        for (DyeColor color : DyeColor.values())
        {
            create(
                    AncientGroundCore.location("compacting/dye/" + color.getSerializedName()), b -> b
                            .require(TFCFluids.COLORED_FLUIDS.get(color).getSource(), 1000)
                            .output(CategoryUtil.Color.TO_DYE.get(color))
                            .requiresHeat(HeatCondition.HEATED)
            );
        }

        for (SandBlockType color : SandBlockType.values())
        {
            create(
                    AncientGroundCore.location("compacting/sand/" + color.name().toLowerCase(Locale.ROOT)), b -> b
                            .require(TFCBlocks.SAND.get(color).get())
                            .require(TFCBlocks.SAND.get(color).get())
                            .output(TFCBlocks.SANDSTONE.get(color).get(SandstoneBlockType.RAW))
            );
        }
    }
}
