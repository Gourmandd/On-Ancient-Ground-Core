package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.blocks.CoreDecorationBlockHolder;
import net.gourmand.core.registry.category.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
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

                recipe()
                        .input(CoreBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK).get());

                recipe()
                        .input(CoreBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK_SLAB).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK_SLAB).get());

                recipe()
                        .input(CoreBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK_STAIRS).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK_STAIRS).get());
            }
        });

        Stream.of(Metal.values()).forEach(metal -> {
            if (metal.defaultParts())
            {
                recipe()
                        .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK).get());

                recipe()
                        .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK_SLAB).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK_SLAB).get());

                recipe()
                        .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK_STAIRS).get())
                        .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                        .damageInputs()
                        .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.CUT_BLOCK_STAIRS).get());

                if (metal.weatheredParts()){
                    // exposed
                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.EXPOSED_BLOCK).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.EXPOSED_CUT_BLOCK).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.EXPOSED_BLOCK_SLAB).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.EXPOSED_CUT_BLOCK_SLAB).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.EXPOSED_BLOCK_STAIRS).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.EXPOSED_CUT_BLOCK_STAIRS).get());

                    // weathered
                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.WEATHERED_BLOCK).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.WEATHERED_CUT_BLOCK).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.WEATHERED_BLOCK_SLAB).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.WEATHERED_CUT_BLOCK_SLAB).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.WEATHERED_BLOCK_STAIRS).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.WEATHERED_CUT_BLOCK_STAIRS).get());

                    // oxidized
                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.OXIDIZED_BLOCK).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.OXIDIZED_CUT_BLOCK).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.OXIDIZED_BLOCK_SLAB).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.OXIDIZED_CUT_BLOCK_SLAB).get());

                    recipe()
                            .input(TFCBlocks.METALS.get(metal).get(Metal.BlockType.OXIDIZED_BLOCK_STAIRS).get())
                            .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                            .damageInputs()
                            .shapeless(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(CoreMetals.BlockType.OXIDIZED_CUT_BLOCK_STAIRS).get());
                }
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

        Stream.of(CoreDeeperDownWood.values()).forEach(woodType -> {

            final var blocks = CoreBlocks.DEEPER_DOWN_WOODS.get(woodType);
            final var lumber = CoreItems.LUMBER.get(woodType).get();
            final var planks = woodType.getPlanks();

            /*
            recipe()
                    .input('L', lumber)
                    .pattern("L L", "L L", "LLL")
                    .shaped(blocks.get(Wood.BlockType.BARREL).get());

             */

            recipe()
                    .input('P', planks)
                    .input('L', lumber)
                    .pattern("PLP", "PLP")
                    .shaped(woodType.getPlankFence(), 8);
            recipe()
                    .input('P', planks)
                    .input('L', lumber)
                    .pattern("LPL", "LPL")
                    .shaped(woodType.getPlankFenceGate(), 2);
            /*
            recipe()
                    .input('L', lumber)
                    .input('B', blocks.get(Wood.BlockType.BOOKSHELF))
                    .pattern("LLL", " B ", " L ")
                    .shaped(blocks.get(Wood.BlockType.LECTERN));

             */
            /*
            recipe()
                    .input('P', blocks.get(Wood.BlockType.LOG))
                    .input('L', lumber)
                    .pattern("PLP", "PLP")
                    .shaped(blocks.get(Wood.BlockType.LOG_FENCE), 8);

             */
            recipe()
                    .input('L', lumber)
                    .input('S', Tags.Items.RODS_WOODEN)
                    .pattern("LLL", "LSL", "L L")
                    .shaped(blocks.get(Wood.BlockType.LOOM).get());

            recipe("from_logs")
                    .inputIsPrimary(TFCTags.Items.TOOLS_SAW)
                    .input(logsTagOf(woodType))
                    .damageInputs()
                    .shapeless(lumber, 8);
            recipe("from_planks")
                    .inputIsPrimary(TFCTags.Items.TOOLS_SAW)
                    .input(planks)
                    .damageInputs()
                    .shapeless(lumber, 4);
            recipe("from_stairs")
                    .inputIsPrimary(TFCTags.Items.TOOLS_SAW)
                    .input(woodType.getPlankStairs())
                    .damageInputs()
                    .shapeless(lumber, 3);
            recipe("from_slabs")
                    .inputIsPrimary(TFCTags.Items.TOOLS_SAW)
                    .input(woodType.getPlankSlab())
                    .damageInputs()
                    .shapeless(lumber, 2);
            recipe().to2x2(lumber, planks, 1);
            recipe()
                    .input('L', lumber)
                    .pattern("LL")
                    .shaped(woodType.getPressurePlate());
            recipe()
                    .input('F', Tags.Items.FEATHERS)
                    .input('D', Tags.Items.DYES_BLACK)
                    .input('S', woodType.getPlankSlab())
                    .input('W', planks)
                    .pattern("F D", "SSS", "W W")
                    .shaped(blocks.get(Wood.BlockType.SCRIBING_TABLE).get());
            recipe()
                    .input('S', Tags.Items.TOOLS_SHEAR)
                    .input('L', Tags.Items.LEATHERS)
                    .input('P', planks)
                    .input('G', woodType.getLog())
                    .pattern(" LS", "PPP", "G G")
                    .shaped(blocks.get(Wood.BlockType.SEWING_TABLE).get());
            recipe()
                    .input('L', lumber)
                    .input('P', planks)
                    .input('S', Tags.Items.RODS_WOODEN)
                    .pattern("PPP", "L L", "S S")
                    .shaped(blocks.get(Wood.BlockType.SHELF).get(), 2);
            recipe()
                    .input('#', planks)
                    .pattern("###")
                    .shaped(woodType.getPlankSlab(), 6);
            recipe()
                    .input('#', planks)
                    .pattern("#  ", "## ", "###")
                    .shaped(woodType.getPlankStairs(), 8);
            recipe()
                    .input('L', lumber)
                    .input('S', Tags.Items.RODS_WOODEN)
                    .pattern("  S", " SL", "SLL")
                    .shaped(blocks.get(Wood.BlockType.SLUICE).get());
            recipe()
                    .input('L', logsTagOf(woodType))
                    .input('S', TFCTags.Items.TOOLS_SAW)
                    .pattern("LS", "L ")
                    .damageInputs()
                    .source(0, 1)
                    .shaped(CoreItems.SUPPORTS.get(woodType).get(), 8);
            recipe()
                    .input('L', lumber)
                    .pattern("LLL", "   ", "LLL")
                    .shaped(blocks.get(Wood.BlockType.TOOL_RACK).get());
            //recipe().to2x2(planks, blocks.get(Wood.BlockType.WORKBENCH), 1);

            if (woodType.isNoxfungi() || woodType == CoreDeeperDownWood.WEEPING_GALA){
                recipe()
                        .input('L', lumber)
                        .pattern("LL", "LL", "LL")
                        .shaped(woodType.getDoor());
                recipe()
                        .input('L', lumber)
                        .pattern("LLL", "LLL")
                        .shaped(woodType.getTrapdoor(), 2);
            }

            if (woodType.isNoxfungi() && woodType != CoreDeeperDownWood.WEEPING_GALA){
                final String name = woodType.getSerializedName().replace("_noxwood", "");
                remove("spectrum:crafting_table/noxwood/" + name + "_planks");
                remove("spectrum:crafting_table/noxwood/" + name + "_stairs");
                remove("spectrum:crafting_table/noxwood/" + name + "_slab");
                remove("spectrum:crafting_table/noxwood/" + name + "_door");
                remove("spectrum:crafting_table/noxwood/" + name + "_trapdoor");
                remove("spectrum:crafting_table/noxwood/" + name + "_fence");
                remove("spectrum:crafting_table/noxwood/" + name + "_fence_gate");
                remove("spectrum:crafting_table/noxwood/" + name + "_pressure_plate");
            }

            if (woodType == CoreDeeperDownWood.WEEPING_GALA){
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/planks");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/stairs");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/slab");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/door");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/trapdoor");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/fence");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/gate");
                remove("spectrum:crafting_table/" + woodType.getSerializedName() + "/pressure_plate");
            }

            if (!woodType.isNoxfungi()){
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_planks");
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_plank_stairs");
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_plank_slab");
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_plank_fence");
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_plank_fence_gate");
                remove("spectrum:crafting_table/colored_wood/" + woodType.getSerializedName() + "_plank_pressure_plate");
            }
        });

        Stream.of(CoreRocks.values()).forEach(rockType -> {

            final var MAP = CoreBlocks.ROCK_BLOCKS.get(rockType);
            final var BRICK_ITEM = CoreItems.BRICKS.get(rockType).get();
            final var RAW_BLOCK = CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rockType).value();

            recipe()
                    .input('X', BRICK_ITEM)
                    .input('M', TFCItems.MORTAR)
                    .pattern("X X", "MXM")
                    .shaped(MAP.get(Rock.BlockType.AQUEDUCT).get(), 2);
            recipe()
                    .input(Ingredient.of(
                            MAP.get(Rock.BlockType.LOOSE).get(),
                            MAP.get(Rock.BlockType.MOSSY_LOOSE).get()
                            )
                    )
                    .inputIsPrimary(TFCTags.Items.TOOLS_CHISEL)
                    .damageInputs()
                    .shapeless(BRICK_ITEM);
            recipe().bricksWithMortar(BRICK_ITEM, CategoryUtil.CoreRock.TO_BRICK_BLOCK.get(rockType).value(), 4);
            recipe()
                    .input(MAP.get(Rock.BlockType.COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.LOOSE).get(), 4);
            recipe().bricksWithMortar(RAW_BLOCK, MAP.get(Rock.BlockType.HARDENED).get(), 2);
            recipe().to2x2(MAP.get(Rock.BlockType.LOOSE).get(), MAP.get(Rock.BlockType.COBBLE).get(), 1);
            recipe()
                    .input(MAP.get(Rock.BlockType.MOSSY_COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.MOSSY_LOOSE).get(), 4);
            recipe().to2x2(MAP.get(Rock.BlockType.MOSSY_LOOSE).get(), MAP.get(Rock.BlockType.MOSSY_COBBLE).get(), 1);

            CoreBlocks.ROCK_DECORATIONS.get(rockType).forEach((type, decorations) -> addDecorations(MAP.get(type).get(), decorations));

            recipe("from_slabs")
                    .input(rockType.getSlab(Rock.BlockType.COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.LOOSE).get(), 2);
            recipe("from_slabs")
                    .input(rockType.getSlab(Rock.BlockType.MOSSY_COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.MOSSY_LOOSE).get(), 2);
            recipe("from_stairs")
                    .input(rockType.getStair(Rock.BlockType.COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.LOOSE).get(), 3);
            recipe("from_stairs")
                    .input(rockType.getStair(Rock.BlockType.MOSSY_COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.MOSSY_LOOSE).get(), 3);
            recipe("from_walls")
                    .input(rockType.getWall(Rock.BlockType.COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.LOOSE).get(), 4);
            recipe("from_walls")
                    .input(rockType.getWall(Rock.BlockType.MOSSY_COBBLE).get())
                    .shapeless(MAP.get(Rock.BlockType.MOSSY_LOOSE).get(), 4);

            if (rockType.hasVariants()){
                recipe().useTool(TFCTags.Items.TOOLS_CHISEL, BRICK_ITEM, MAP.get(Rock.BlockType.BUTTON).get());
                recipe().useTool(TFCTags.Items.TOOLS_CHISEL, MAP.get(Rock.BlockType.BRICKS).get(), MAP.get(Rock.BlockType.CHISELED).get());
                recipe().useTool(TFCTags.Items.TOOLS_CHISEL, MAP.get(Rock.BlockType.RAW).get(), MAP.get(Rock.BlockType.SMOOTH).get());
                recipe().useTool(TFCTags.Items.TOOLS_HAMMER, MAP.get(Rock.BlockType.BRICKS).get(), MAP.get(Rock.BlockType.CRACKED_BRICKS).get());

                recipe()
                        .input('C', TFCTags.Items.TOOLS_CHISEL)
                        .input('X', BRICK_ITEM)
                        .pattern(" C", "XX")
                        .damageInputs()
                        .source(0, 1)
                        .shaped(MAP.get(Rock.BlockType.PRESSURE_PLATE).get());
            }
        });
    }

    private void addDecorations(ItemLike input, CoreDecorationBlockHolder output)
    {
        recipe()
                .input('#', input)
                .pattern("###")
                .shaped(output.slab().get(), 6);
        recipe()
                .input('#', input)
                .pattern("#  ", "## ")
                .shaped(output.stair().get(), 4);
        recipe()
                .input('#', input)
                .pattern("###", "###")
                .shaped(output.wall().get(), 6);
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
