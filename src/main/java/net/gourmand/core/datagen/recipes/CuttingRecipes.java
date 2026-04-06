package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.CuttingRecipeGen;
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

public class CuttingRecipes extends CuttingRecipeGen {

    public CuttingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        cuttingRecipes();
    }

    public void cuttingRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> DOUBLE_SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_SHEET);

            create(DOUBLE_SHEET::get, b -> b.duration(200)
                    .output(DOUBLE_INGOT.get(), 2)
            );

            create(DOUBLE_INGOT::get, b -> b.duration(100)
                    .output(INGOT.get(), 2)
            );
        });
    }
}
