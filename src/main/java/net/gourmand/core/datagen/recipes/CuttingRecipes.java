package net.gourmand.core.datagen.recipes;

import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import com.simibubi.create.api.data.recipe.CuttingRecipeGen;
import com.therighthon.afc.common.blocks.AFCBlocks;
import com.therighthon.afc.common.blocks.AFCWood;
import com.therighthon.afc.common.items.AFCItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.SpectrumWood;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

public class CuttingRecipes extends CuttingRecipeGen
{

    public CuttingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        cuttingRecipes();
    }

    public void cuttingRecipes()
    {

        final int CUTTING_TIME = 100;
        final int LONG_CUTTING_TIME = 200;

        for (CoreMetals.MetalType metal : CoreMetals.MetalType.values())
        {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> DOUBLE_SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_SHEET);

            create(DOUBLE_SHEET::get, b -> b.duration(LONG_CUTTING_TIME).output(DOUBLE_INGOT.get(), 2));

            create(DOUBLE_INGOT::get, b -> b.duration(CUTTING_TIME).output(INGOT.get(), 2));
        }

        for (Metal metalType : Metal.values())
        {
            if (metalType.defaultParts())
            {
                final TFCItems.ItemId INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT);
                final TFCItems.ItemId DOUBLE_INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT);
                final TFCItems.ItemId DOUBLE_SHEET = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET);

                create(DOUBLE_SHEET::get, b -> b.duration(LONG_CUTTING_TIME).output(DOUBLE_INGOT.get(), 2));

                create(DOUBLE_INGOT::get, b -> b.duration(CUTTING_TIME).output(INGOT.get(), 2));
            }
        }

        for (FLMetal metalType : FLMetal.values())
        {
            final TFCItems.ItemId INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.INGOT);
            final TFCItems.ItemId DOUBLE_INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.DOUBLE_INGOT);
            final TFCItems.ItemId DOUBLE_SHEET = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.DOUBLE_SHEET);

            create(DOUBLE_SHEET::get, b -> b.duration(LONG_CUTTING_TIME).output(DOUBLE_INGOT.get(), 2));

            create(DOUBLE_INGOT::get, b -> b.duration(CUTTING_TIME).output(INGOT.get(), 2));
        }

        for (SpectrumWood woodType : SpectrumWood.values())
        {
            create(CoreItems.LUMBER.get(woodType).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(woodType.getPlanks())
                    .output(CoreItems.LUMBER.get(woodType).get(), 4)
            );
        }

        for (Wood woodType : Wood.values())
        {
            create(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.LOG).asItem())
                    .output(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).get(), 1)
            );

            create(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.WOOD).asItem())
                    .output(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).get(), 1)
            );

            create(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).getId().getPath() + "_from_log", b -> b
                    .duration(CUTTING_TIME)
                    .require(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).asItem())
                    .output(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).get(), 4)
            );

            create(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).getId().getPath() + "_from_wood", b -> b
                    .duration(CUTTING_TIME)
                    .require(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).asItem())
                    .output(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).get(), 4)
            );

            create(TFCItems.LUMBER.get(woodType).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(TFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).asItem())
                    .output(TFCItems.LUMBER.get(woodType).get(), 4)
            );
        }

        for (AFCWood woodType : AFCWood.values())
        {
            create(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.LOG).asItem())
                    .output(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).get(), 1)
            );

            create(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.WOOD).asItem())
                    .output(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).get(), 1)
            );

            create(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).getId().getPath() + "_from_log", b -> b
                    .duration(CUTTING_TIME)
                    .require(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_LOG).asItem())
                    .output(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).get(), 4)
            );

            create(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).getId().getPath() + "_from_wood", b -> b
                    .duration(CUTTING_TIME)
                    .require(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.STRIPPED_WOOD).asItem())
                    .output(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).get(), 4)
            );

            create(AFCItems.LUMBER.get(woodType).getId().getPath(), b -> b
                    .duration(CUTTING_TIME)
                    .require(AFCBlocks.WOODS.get(woodType).get(Wood.BlockType.PLANKS).asItem())
                    .output(AFCItems.LUMBER.get(woodType).get(), 4)
            );
        }

        // Colored glass
        for (DyeColor color : DyeColor.values())
        {

            Block framedGlassBlock = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS.get(color).value();
            Block framedGlassPane = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS_PANE.get(color).value();

            Block glassBlock = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS.get(color);
            Block glassPane = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(color);

            Block leadGlassBlock = CoreBlocks.COLOURED_LEAD_GLASS.get(color).get();
            Block leadGlassPane = CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get();

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/framed_glass/" + color.getSerializedName()), b -> b
                    .duration(CUTTING_TIME)
                    .require(framedGlassBlock)
                    .output(framedGlassPane, 16)
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/glass/" + color.getSerializedName()), b -> b
                    .duration(CUTTING_TIME)
                    .require(glassBlock)
                    .output(glassPane, 16)
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/lead_glass/" + color.getSerializedName()), b -> b
                    .duration(CUTTING_TIME)
                    .require(leadGlassBlock)
                    .output(leadGlassPane, 16)
            );
        }
        // clear glass
        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/framed_glass/clear"), b -> b
                .duration(CUTTING_TIME).
                        require(CategoryUtil.Glass.QUARK_CLEAR_GLASS.value())
                .output(CategoryUtil.Glass.QUARK_CLEAR_GLASS_PANE.value(), 16)
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/glass/clear"), b -> b
                .duration(CUTTING_TIME)
                .require(Blocks.GLASS)
                .output(Blocks.GLASS_PANE, 16)
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/lead_glass/clear"), b -> b
                .duration(CUTTING_TIME)
                .require(CoreBlocks.CLEAR_LEAD_GLASS.get())
                .output(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get(), 16)
        );
    }
}
