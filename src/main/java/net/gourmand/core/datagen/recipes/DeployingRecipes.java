package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class DeployingRecipes extends DeployingRecipeGen {

    public DeployingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        compactingRecipes();
    }

    public void compactingRecipes(){


        Stream.of(CoreClay.values()).forEach(clayType -> {

            final DeferredHolder<Item, Item> CLAY_BALL = clayType.getClayBallItem();
            final DeferredHolder<Block, Block> CLAY_BLOCK = clayType.getClayBlock();

            Stream.of(CoreClay.ItemType.values()).forEach(itemType -> {

                if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD && itemType.hasType(clayType)){

                    final DeferredHolder<Item, Item> MOLD = CoreItems.CERAMICS.get(clayType).get(itemType);

                    create(MOLD.getId().getPath(), b -> b.require(CLAY_BLOCK.get())
                            .toolNotConsumed()
                            .require(CategoryUtil.Tools.MOLD_TO_TOOL_BLADE_TAG.get(itemType))
                            .output(MOLD.get())
                    );
                }
            });
        });
    }
}
