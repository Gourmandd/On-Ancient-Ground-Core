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
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CrushingRecipes extends CrushingRecipeGen {

    public CrushingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        compactingRecipes();
    }

    public void compactingRecipes(){

        Stream.of(CoreRocks.values()).forEach(rockType -> {

            final DeferredHolder<Block, Block> COBBLE = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.COBBLE);
            final DeferredHolder<Block, Block> MOSSY_COBBLE = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE);
            final DeferredHolder<Block, Block> GRAVEL = CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.GRAVEL);
            final DeferredHolder<Block, Block> SAND = TFCBlocks.SAND.get(CategoryUtil.CoreRock.TO_SAND_COLOR.get(rockType)).holder();

            create(COBBLE.getId().getPath(), b -> b.require(COBBLE.get())
                    .output(GRAVEL.get(), 1)
            );

            create(MOSSY_COBBLE.getId().getPath(), b -> b.require(MOSSY_COBBLE.get())
                    .output(GRAVEL.get(), 1)
            );

            create(GRAVEL.getId().getPath(), b -> b.require(GRAVEL.get())
                    .output(SAND.get(), 1)
            );
        });
    }
}
