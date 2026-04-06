package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.blocks.CoreDecorationBlockHolder;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
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
                        .pattern("B  ", "BB ")
                        .shaped(blocks.get(Metal.BlockType.BLOCK_STAIRS).get(), 4);
            }
        });

        Stream.of(CoreClay.values()).forEach(clayType -> {

            final Map<CoreClay.ItemType, DeferredHolder<Item, Item>> ITEM_MAP = CoreItems.CERAMICS.get(clayType);
            final Map<CoreClay.BlockType, DeferredHolder<Block, Block>> BLOCK_MAP = CoreBlocks.CERAMIC_BLOCKS.get(clayType);
            final CoreDecorationBlockHolder BRICK_BLOCKS = CoreBlocks.CERAMIC_DECORATION_BLOCKS.get(clayType).get(CoreClay.BlockType.BRICKS);

            recipe()
                    .input('B', ITEM_MAP.get(CoreClay.ItemType.BRICK).get())
                    .input('M', TFCItems.MORTAR.asItem())
                    .pattern("BMB", "MBM", "BMB")
                    .shaped(BLOCK_MAP.get(CoreClay.BlockType.BRICKS).get(), 2);
            recipe()
                    .input('B', BLOCK_MAP.get(CoreClay.BlockType.BRICKS).get())
                    .pattern("BBB")
                    .shaped(BRICK_BLOCKS.slab().get(), 6);
            recipe()
                    .input('B', BLOCK_MAP.get(CoreClay.BlockType.BRICKS).get())
                    .pattern("B  ", "BB ")
                    .shaped(BRICK_BLOCKS.stair().get(), 4);
            recipe()
                    .input('B', BLOCK_MAP.get(CoreClay.BlockType.BRICKS).get())
                    .pattern("BBB", "BBB")
                    .shaped(BRICK_BLOCKS.wall().get(), 6);

            recipe("recycling_1")
                    .input(CoreTags.Items.CLAY_RECYCLING_1.get(clayType))
                    .shapeless(clayType.getClayBallItem().get(), 1);

            recipe("recycling_5")
                    .input(CoreTags.Items.CLAY_RECYCLING_5.get(clayType))
                    .shapeless(clayType.getClayBallItem().get(), 4);

            if (!clayType.hasReducedSet()){
                recipe()
                        .input('B', clayType.getClayBallItem().get())
                        .pattern("BB", "BB")
                        .shaped(BLOCK_MAP.get(CoreClay.BlockType.CLAY_BLOCK).get(), 1);
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

    private Builder recipe(String suffix)
    {
        return new Builder((name, r) -> {
            assert !suffix.startsWith("_") : "recipe(String suffix) shouldn't start with an '_', it is added for you!";
            assert name == null : "Cannot use a named recipe and recipe(String suffix) at the same time!";
            add(nameOf(r.getResultItem(lookup()).getItem()) + "_" + suffix, r);
        });
    }
}
