package net.gourmand.core.datagen.recipes;

import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import com.mrh0.createaddition.datagen.RecipeGen.RollingRecipeGen;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

public class RollingRecipes extends RollingRecipeGen
{

    public RollingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        rollingRecipes();
    }

    public void rollingRecipes()
    {

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> ROD = CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.ROD);

            create(ROD.getId().getPath(), b -> b.require(INGOT.get()).output(ROD.get(), 2));
        }

        for (Metal metalType : Metal.values())
        {
            if (metalType.defaultParts())
            {
                final TFCItems.ItemId INGOT = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT);
                final TFCItems.ItemId ROD = TFCItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.ROD);

                create(ROD.getId().getPath(), b -> b.require(INGOT.get()).output(ROD.get(), 2));
            }
        }

        for (FLMetal metalType : FLMetal.values())
        {
            final TFCItems.ItemId INGOT = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.INGOT);
            final TFCItems.ItemId ROD = FLItems.METAL_ITEMS.get(metalType).get(FLMetal.ItemType.ROD);

            create(ROD.getId().getPath(), b -> b.require(INGOT.get()).output(ROD.get(), 2));
        }
    }
}
