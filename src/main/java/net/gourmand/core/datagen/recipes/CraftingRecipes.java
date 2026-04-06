package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.tags.ItemTags;

import java.util.stream.Stream;

import static net.dries007.tfc.util.DataGenerationHelpers.Builder;

public interface CraftingRecipes extends Recipes {

    default void craftingRecipes(){

        Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {

            final var blocks = CoreBlocks.METALS.get(metal);

            if (metal.getLikeMetal().defaultParts())
            {
                recipe()
                        .input('S', ingredientOf(metal, Metal.ItemType.SHEET))
                        .input('W', ItemTags.PLANKS)
                        .input('H', TFCTags.Items.TOOLS_HAMMER)
                        .pattern(" SH", "SWS", " S ")
                        .damageInputs()
                        .source(0, 2)
                        .shaped(blocks.get(Metal.BlockType.BLOCK).get(), 8);
                recipe()
                        .input('B', ingredientOf(metal, Metal.BlockType.BLOCK))
                        .pattern("BBB")
                        .shaped(blocks.get(Metal.BlockType.BLOCK_SLAB).get(), 6);
                recipe()
                        .input('B', ingredientOf(metal, Metal.BlockType.BLOCK))
                        .pattern("B  ", "BB ", "BBB")
                        .shaped(blocks.get(Metal.BlockType.BLOCK_STAIRS).get(), 8);
            }
        });
    }

    private Builder recipe()
    {
        return new Builder((name, r) -> {
            if (name != null) add(name, r);
            else add(r);
        });
    }
}
