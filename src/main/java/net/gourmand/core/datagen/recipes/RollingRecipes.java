package net.gourmand.core.datagen.recipes;

import com.mrh0.createaddition.datagen.RecipeGen.RollingRecipeGen;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class RollingRecipes extends RollingRecipeGen {

    public RollingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        rollingRecipes();
    }

    public void rollingRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> ROD = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.ROD);

            create(ROD.getId().getPath(), b -> b.require(INGOT.get())
                    .output(ROD.get(), 2)
            );
        });
    }
}
