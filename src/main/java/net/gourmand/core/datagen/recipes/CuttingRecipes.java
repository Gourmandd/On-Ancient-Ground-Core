package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.CuttingRecipeGen;
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
import java.util.stream.Stream;

public class CuttingRecipes extends CuttingRecipeGen
{

    public CuttingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        cuttingRecipes();
    }

    public void cuttingRecipes()
    {

        Stream.of(CoreMetals.MetalType.values()).forEach(metal ->
        {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> DOUBLE_SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_SHEET);

            create(DOUBLE_SHEET::get, b -> b.duration(200).output(DOUBLE_INGOT.get(), 2));

            create(DOUBLE_INGOT::get, b -> b.duration(100).output(INGOT.get(), 2));
        });


        Stream.of(SpectrumWood.values()).forEach(woodType ->
        {
            create(CoreItems.LUMBER.get(woodType).getId().getPath(), b -> b.duration(50).require(woodType.getPlanks()).output(CoreItems.LUMBER.get(woodType).get(), 4));
        });

        // Colored glass
        for (DyeColor color : DyeColor.values())
        {

            Block framedGlassBlock = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS.get(color).value();
            Block framedGlassPane = CategoryUtil.Glass.COLOR_TO_QUARK_GLASS_PANE.get(color).value();

            Block glassBlock = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS.get(color);
            Block glassPane = CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(color);

            Block leadGlassBlock = CoreBlocks.COLOURED_LEAD_GLASS.get(color).get();
            Block leadGlassPane = CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get();

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/framed_glass/" + color.getSerializedName()), b -> b.duration(50).require(framedGlassBlock).output(framedGlassPane, 16));

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/glass/" + color.getSerializedName()), b -> b.duration(50).require(glassBlock).output(glassPane, 16));

            create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/lead_glass/" + color.getSerializedName()), b -> b.duration(50).require(leadGlassBlock).output(leadGlassPane, 16));
        }
        // clear glass
        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/framed_glass/clear"), b -> b.duration(50).require(CategoryUtil.Glass.QUARK_CLEAR_GLASS.value()).output(CategoryUtil.Glass.QUARK_CLEAR_GLASS_PANE.value(), 16));

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/glass/clear"), b -> b.duration(50).require(Blocks.GLASS).output(Blocks.GLASS_PANE, 16));

        create(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "cutting/lead_glass/clear"), b -> b.duration(50).require(CoreBlocks.CLEAR_LEAD_GLASS.get()).output(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get(), 16));
    }
}
