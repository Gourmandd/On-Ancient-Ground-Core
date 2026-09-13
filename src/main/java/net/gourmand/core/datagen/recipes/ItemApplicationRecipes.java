package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.ItemApplicationRecipeGen;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ItemApplicationRecipes extends ItemApplicationRecipeGen
{

    public ItemApplicationRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        itemApplicationRecipes();
    }

    public void itemApplicationRecipes()
    {
        // Colored glass
        for (DyeColor color : DyeColor.values())
        {
            Block framedGlassBlock = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS.get(color).value();

            Block glassBlock = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS.get(color);

            Block leadGlassBlock = CoreBlocks.COLOURED_LEAD_GLASS.get(color).get();

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/framed_glass/" + color.getSerializedName()), b -> b
                    .require(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
                    .require(TFCItems.METAL_ITEMS.get(Metal.STEEL).get(Metal.ItemType.ROD))
                    .output(framedGlassBlock)
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/glass/" + color.getSerializedName()), b -> b
                    .require(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
                    .require(TFCItems.POWDERS.get(Powder.FLUX))
                    .output(glassBlock)
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/lead_glass/" + color.getSerializedName()), b -> b
                    .require(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
                    .require(CoreItems.METAL_ITEMS.get(CoreMetals.MetalType.LEAD).get(Metal.ItemType.ROD).get())
                    .output(leadGlassBlock)
            );
        }
        // clear glass
        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/framed_glass/clear"), b -> b
                .require(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
                .require(TFCItems.METAL_ITEMS.get(Metal.STEEL).get(Metal.ItemType.ROD))
                .output(CategoryUtil.Glass.QUARK_CLEAR_GLASS.value())
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/glass/clear"), b -> b
                .require(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
                .require(TFCItems.POWDERS.get(Powder.FLUX))
                .output(Blocks.GLASS)
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "item_application/lead_glass/clear"), b -> b
                .require(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
                .require(CoreItems.METAL_ITEMS.get(CoreMetals.MetalType.LEAD).get(Metal.ItemType.ROD).get())
                .output(CoreBlocks.CLEAR_LEAD_GLASS.get())
        );
    }
}
