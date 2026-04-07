package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.recipes.CollapseRecipe;
import net.dries007.tfc.common.recipes.ingredients.BlockIngredient;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.stream.Stream;

public interface CollapseRecipes extends Recipes {

    default void collapseRecipes(){

        Stream.of(CoreRocks.values()).forEach(this::addRock);
    }

    private void addRock(CoreRocks rockType){

        final var BLOCK_MAP = CoreBlocks.ROCK_BLOCKS.get(rockType);
        if (rockType.hasVariants()){
            collapse(
                    BlockIngredient.of(
                        CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rockType).value(),
                        BLOCK_MAP.get(Rock.BlockType.HARDENED).get(),
                        BLOCK_MAP.get(Rock.BlockType.SMOOTH).get(),
                        BLOCK_MAP.get(Rock.BlockType.CRACKED_BRICKS).get()
                    ),
                    BLOCK_MAP.get(Rock.BlockType.COBBLE).get().defaultBlockState()
            );
        } else {
            collapse(
                    BlockIngredient.of(
                            CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rockType).value(),
                            BLOCK_MAP.get(Rock.BlockType.HARDENED).get()
                    ),
                    BLOCK_MAP.get(Rock.BlockType.COBBLE).get().defaultBlockState()
            );
        }
    }

    private void collapse(BlockIngredient ingredient, BlockState output){
        add(new CollapseRecipe(ingredient, output));
    }
}
