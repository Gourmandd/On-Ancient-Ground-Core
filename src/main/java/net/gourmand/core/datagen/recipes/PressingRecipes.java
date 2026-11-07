package net.gourmand.core.datagen.recipes;

import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

public class PressingRecipes extends PressingRecipeGen
{

    public PressingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        pressingRecipes();
    }

    public void pressingRecipes()
    {
        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {

            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> SHEET = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET);

            create(DOUBLE_INGOT.getId().getPath(), b -> b.require(DOUBLE_INGOT.get()).output(SHEET.get(), 1));
        }

        for (Metal metalType : Metal.values())
        {
            if (metalType.defaultParts())
            {
                final TFCItems.ItemId DOUBLE_INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT);
                final TFCItems.ItemId SHEET = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET);

                create(DOUBLE_INGOT.getId().getPath(), b -> b.require(DOUBLE_INGOT.get()).output(SHEET.get(), 1));
            }
        }

        for (FLMetal metalType : FLMetal.values())
        {
            final TFCItems.ItemId DOUBLE_INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.DOUBLE_INGOT);
            final TFCItems.ItemId SHEET = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.SHEET);

            create(DOUBLE_INGOT.getId().getPath(), b -> b.require(DOUBLE_INGOT.get()).output(SHEET.get(), 1));
        }

        for (SoilBlockType.Variant soilType : SoilBlockType.Variant.values())
        {

            final TFCBlocks.Id<Block> DIRT = TFCBlocks.SOIL.get(SoilBlockType.DIRT).get(soilType);
            final TFCBlocks.Id<Block> GRASS = TFCBlocks.SOIL.get(SoilBlockType.GRASS).get(soilType);
            final TFCBlocks.Id<Block> GRASS_PATH = TFCBlocks.SOIL.get(SoilBlockType.GRASS_PATH).get(soilType);

            create(GRASS.getId().getPath(), b -> b.require(GRASS.asItem()).output(GRASS_PATH.get(), 1));
            create(DIRT.getId().getPath(), b -> b.require(DIRT.asItem()).output(GRASS_PATH.get(), 1));
        }
    }
}
