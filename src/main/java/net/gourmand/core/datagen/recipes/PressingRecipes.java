package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.PressingRecipeGen;
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

public class PressingRecipes extends PressingRecipeGen {

    public PressingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        rollingRecipes();
    }

    public void rollingRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {

            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.SHEET);

            create(DOUBLE_INGOT.getId().getPath(), b -> b.require(DOUBLE_INGOT.get())
                    .output(SHEET.get(), 1)
            );
        });
    }
}
