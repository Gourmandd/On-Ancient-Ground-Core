package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.dries007.tfc.common.items.Powder;
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
import java.util.stream.Stream;

public class CompactingRecipes extends CompactingRecipeGen {

    public CompactingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        compactingRecipes();
    }

    public void compactingRecipes(){


        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {

            final DeferredHolder<Item, Item> INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.INGOT);
            final DeferredHolder<Item, Item> DOUBLE_INGOT = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_INGOT);
            final DeferredHolder<Item, Item> SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.SHEET);
            final DeferredHolder<Item, Item> DOUBLE_SHEET = CoreItems.METAL_ITEMS.get(metal).get(Metal.ItemType.DOUBLE_SHEET);

            create(DOUBLE_INGOT.getId().getPath(), b -> b.require(INGOT.get())
                    .require(INGOT.get())
                    .require(TFCItems.POWDERS.get(Powder.FLUX))
                    .requiresHeat(HeatCondition.HEATED)
                    .output(DOUBLE_INGOT.get(), 1)
            );

            create(DOUBLE_SHEET.getId().getPath(), b -> b.require(SHEET.get())
                    .require(SHEET.get())
                    .require(TFCItems.POWDERS.get(Powder.FLUX))
                    .requiresHeat(HeatCondition.HEATED)
                    .output(DOUBLE_SHEET.get(), 1)
            );

            create(SHEET.getId().getPath(), b -> b.require(metal.getFlowingFluid(), 200)
                    .requiresHeat(HeatCondition.HEATED)
                    .output(SHEET.get(), 1)
            );
        });
    }
}
