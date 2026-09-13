package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.CrushingRecipeGen;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

public class CrushingRecipes extends CrushingRecipeGen
{

    public CrushingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        compactingRecipes();
    }

    public void compactingRecipes()
    {

        for (CoreRocks rockType : CoreRocks.values())
        {

            final DeferredHolder<Block, Block> COBBLE = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.COBBLE);
            final DeferredHolder<Block, Block> MOSSY_COBBLE = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE);
            final DeferredHolder<Block, Block> GRAVEL = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.GRAVEL);
            final DeferredHolder<Block, Block> SAND = TFCBlocks.SAND.get(CategoryUtil.CoreRock.TO_SAND_COLOR.get(rockType)).holder();

            create(COBBLE.getId().getPath(), b -> b.require(COBBLE.get()).output(GRAVEL.get()));

            create(MOSSY_COBBLE.getId().getPath(), b -> b.require(MOSSY_COBBLE.get()).output(GRAVEL.get()));

            create(GRAVEL.getId().getPath(), b -> b.require(GRAVEL.get()).output(SAND.get()));
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

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/framed_glass/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(framedGlassBlock)
                    .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/framed_glass_pane/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(framedGlassPane)
                    .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/glass/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(glassBlock)
                    .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/glass_pane/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(glassPane)
                    .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/lead_glass/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(leadGlassBlock)
                    .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/lead_glass_pane/" + color.getSerializedName()), b -> b
                    .duration(100)
                    .require(leadGlassPane)
                    .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(color))
            );
        }
        // clear glass
        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/framed_glass/clear"), b -> b
                .duration(100)
                .require(CategoryUtil.Glass.QUARK_CLEAR_GLASS.value())
                .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/framed_glass_pane/clear"), b -> b
                .duration(100)
                .require(CategoryUtil.Glass.QUARK_CLEAR_GLASS_PANE.value())
                .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/glass/clear"), b -> b
                .duration(100)
                .require(Blocks.GLASS)
                .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/glass_pane/clear"), b -> b
                .duration(100)
                .require(Blocks.GLASS_PANE)
                .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/lead_glass/clear"), b -> b
                .duration(100)
                .require(CoreBlocks.CLEAR_LEAD_GLASS.get())
                .output(CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "crushing/lead_glass_pane/clear"), b -> b
                .duration(100)
                .require(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get())
                .output(0.06f, CategoryUtil.Glass.COLOR_TO_GLASS_BATCH.get(DyeColor.WHITE))
        );
    }
}
