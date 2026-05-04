package net.gourmand.core.client;

import net.dries007.tfc.client.IGhostBlockHandler;
import net.dries007.tfc.client.TFCColors;
import net.dries007.tfc.client.extensions.FluidRendererExtension;
import net.dries007.tfc.client.model.ContainedFluidModel;
import net.dries007.tfc.client.render.blockentity.LoomBlockEntityRenderer;
import net.dries007.tfc.client.render.blockentity.PlacedItemBlockEntityRenderer;
import net.dries007.tfc.client.render.blockentity.SluiceBlockEntityRenderer;
import net.dries007.tfc.client.render.blockentity.ToolRackBlockEntityRenderer;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.gourmand.core.registry.CoreBlockEntities;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.CoreGemstones;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.dries007.tfc.client.ClientEventHandler.MOLTEN_FLOW;
import static net.dries007.tfc.client.ClientEventHandler.MOLTEN_STILL;
import static net.dries007.tfc.common.blocks.wood.Wood.BlockType.*;

public class ClientEventHandler {

    public static void init(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(ClientEventHandler::registerColorHandlerBlocks);
        modEventBus.addListener(ClientEventHandler::clientSetup);
        modEventBus.addListener(ClientEventHandler::registerColorHandlerItems);
        modEventBus.addListener(ClientEventHandler::registerEntityRenderers);
        modEventBus.addListener(ClientEventHandler::registerExtensions);
    }

    private static void registerExtensions(RegisterClientExtensionsEvent event) {
        CoreFluids.METALS.forEach((metal, holder) -> {
            if (!metal.hasOtherFluid()){
                event.registerFluidType(
                        new FluidRendererExtension(TFCFluids.ALPHA_MASK | metal.getColor(), MOLTEN_STILL, MOLTEN_FLOW, null, null),
                        holder.getType()
                );
            }
        });

        CoreFluids.COLORED_GLASS.forEach((color, holder) -> {
            event.registerFluidType(
                    new FluidRendererExtension(TFCFluids.ALPHA_MASK | color.getTextureDiffuseColor(), MOLTEN_STILL, MOLTEN_FLOW, null, null),
                    holder.getType()
            );
        });

        // clear glass
        event.registerFluidType(
                new FluidRendererExtension(TFCFluids.ALPHA_MASK | 0xD4FBFB, MOLTEN_STILL, MOLTEN_FLOW, null, null),
                CoreFluids.CLEAR_GLASS.getType()
        );
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CoreBlockEntities.LOOM.get(), ctx -> new LoomBlockEntityRenderer());
        event.registerBlockEntityRenderer(CoreBlockEntities.SLUICE.get(), ctx -> new SluiceBlockEntityRenderer());
        event.registerBlockEntityRenderer(CoreBlockEntities.TOOL_RACK.get(), ctx -> new ToolRackBlockEntityRenderer());
        event.registerBlockEntityRenderer(CoreBlockEntities.SHELF.get(), ctx -> new PlacedItemBlockEntityRenderer<>());
    }

    @SuppressWarnings("deprecation")
    public static void clientSetup(FMLClientSetupEvent event){

        final RenderType solid = RenderType.solid();
        final RenderType cutout = RenderType.cutout();
        final RenderType cutoutMipped = RenderType.cutoutMipped();
        final RenderType translucent = RenderType.translucent();
        final Predicate<RenderType> ghostBlock = rt -> rt == cutoutMipped || rt == Sheets.translucentCullBlockSheet();

        CoreBlocks.METALS.values().forEach(map -> map.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout)));

        CoreBlocks.CROPS.values().forEach(reg -> {
            if (reg.get() instanceof IGhostBlockHandler)
            {
                ItemBlockRenderTypes.setRenderLayer(reg.get(), ghostBlock);
            }
            else
            {
                ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout);
            }
        });
        CoreBlocks.DEAD_CROPS.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout));

        CoreBlocks.DEAD_CROPS.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout));
        CoreBlocks.WILD_CROPS.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout));

        CoreBlocks.SPREADING_BUSHES.values().forEach(bush -> ItemBlockRenderTypes.setRenderLayer(bush.get(), cutoutMipped));
        CoreBlocks.SPREADING_CANES.values().forEach(bush -> ItemBlockRenderTypes.setRenderLayer(bush.get(), cutoutMipped));
        CoreBlocks.STATIONARY_BUSHES.values().forEach(bush -> ItemBlockRenderTypes.setRenderLayer(bush.get(), cutoutMipped));
        CoreBlocks.FRUIT_TREE_LEAVES.values().forEach(leaves -> ItemBlockRenderTypes.setRenderLayer(leaves.get(), cutoutMipped));
        CoreBlocks.FRUIT_TREE_SAPLINGS.values().forEach(leaves -> ItemBlockRenderTypes.setRenderLayer(leaves.get(), cutout));
        CoreBlocks.FRUIT_TREE_POTTED_SAPLINGS.values().forEach(leaves -> ItemBlockRenderTypes.setRenderLayer(leaves.get(), cutout));

        CoreBlocks.ORES.values().forEach(map -> map.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout)));
        CoreBlocks.GRADED_ORES.values().forEach(map -> map.values().forEach(inner -> inner.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout))));

        CoreBlocks.CUSTOM_ROCK_ORES.values().forEach(map -> map.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout)));
        CoreBlocks.CUSTOM_ROCK_GRADED_ORES.values().forEach(map -> map.values().forEach(inner -> inner.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout))));

        CoreBlocks.CUSTOM_ROCK_TFC_ORES.values().forEach(map -> map.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout)));
        CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.values().forEach(map -> map.values().forEach(inner -> inner.values().forEach(reg -> ItemBlockRenderTypes.setRenderLayer(reg.get(), cutout))));

        CoreBlocks.DEEPER_DOWN_WOODS.values().forEach(map -> {
            Stream.of(SCRIBING_TABLE, SEWING_TABLE).forEach(type -> ItemBlockRenderTypes.setRenderLayer(map.get(type).get(), cutout));
        });

        for (CoreRocks rock : CoreRocks.values()){
            for (Rock.BlockType type : Rock.BlockType.values()){
                if (makeMossyVariantCutout(type, rock)){
                    ItemBlockRenderTypes.setRenderLayer(CoreBlocks.ROCK_BLOCKS.get(rock).get(type).get(), cutoutMipped);
                    ItemBlockRenderTypes.setRenderLayer(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).slab().get(), cutoutMipped);
                    ItemBlockRenderTypes.setRenderLayer(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).stair().get(), cutoutMipped);
                    ItemBlockRenderTypes.setRenderLayer(CoreBlocks.ROCK_DECORATIONS.get(rock).get(type).wall().get(), cutoutMipped);
                }
            }
        }

        Stream.of(CoreRocks.values()).forEach(rock -> {
            Stream.of(OreDeposit.values()).forEach(ore -> {
                ItemBlockRenderTypes.setRenderLayer(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore).get(), cutoutMipped);
            });
        });

        Stream.of(CoreGemstones.values()).forEach(gem -> {
            Stream.of(CoreGemstones.GemstoneBlocks.values()).forEach(blockType -> {
                if (blockType.isCluster()){
                    ItemBlockRenderTypes.setRenderLayer(CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(blockType).get(), cutoutMipped);
                }
            });
        });

        ItemBlockRenderTypes.setRenderLayer(CoreBlocks.CLEAR_LEAD_GLASS.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get(), translucent);
        Stream.of(DyeColor.values()).forEach(color -> {
            ItemBlockRenderTypes.setRenderLayer(CoreBlocks.COLOURED_LEAD_GLASS.get(color).get(), translucent);
            ItemBlockRenderTypes.setRenderLayer(CoreBlocks.COLOURED_LEAD_GLASS_PANE.get(color).get(), translucent);
        });

    }

    public static void registerColorHandlerBlocks(RegisterColorHandlersEvent.Block event){

        final BlockColor grassColor = (state, level, pos, tintIndex) -> TFCColors.getGrassColor(pos, tintIndex);

        CoreBlocks.WILD_CROPS.forEach((crop, reg) -> event.register(grassColor, reg.get()));

        Stream.of(DyeColor.values()).forEach(color -> {
            final BlockColor glassColor = (state, level, pos, tintIndex) -> color.getTextureDiffuseColor();
            event.register(glassColor, CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get());
        });

        final BlockColor clearGlassColor = (state, level, pos, tintIndex) -> 14611449;
        event.register(clearGlassColor, CoreBlocks.CLEAR_MOLTEN_GLASS.get());
    }

    private static void registerColorHandlerItems(RegisterColorHandlersEvent.Item event) {

        final ItemColor grassColor = (stack, tintIndex) -> TFCColors.getGrassColor(null, tintIndex);

        CoreBlocks.WILD_CROPS.values().forEach((block) -> event.register(grassColor, block.get().asItem()));

        for (CoreClay clay : CoreClay.values()){;
            event.register(ContainedFluidModel.COLOR, CoreItems.CERAMICS.get(clay).get(CoreClay.ItemType.JUG).get());
        }

        event.register(ContainedFluidModel.COLOR,
                CoreItems.GLASS_MOLD.get(),
                CoreItems.GLASS_PANE_MOLD.get(),
                CoreItems.WROUGHT_IRON_BUCKET.get()
        );

        // buckets
        CoreFluids.METALS.forEach((metal, holder) -> {
            event.register(new DynamicFluidContainerModel.Colors(), holder.getSource().getBucket());
        });

        CoreFluids.COLORED_GLASS.forEach((color, holder) -> {
            event.register(new DynamicFluidContainerModel.Colors(), holder.getSource().getBucket());
        });

        event.register(new DynamicFluidContainerModel.Colors(), CoreFluids.CLEAR_GLASS.getSource().getBucket());
    }

    private static boolean makeMossyVariantCutout(Rock.BlockType type, CoreRocks rock){
        if (type == Rock.BlockType.MOSSY_BRICKS){
            switch (rock){
                case BLACKSLAG, BRECCIA, KOMATIITE, TRAVERTINE, PICRITE_BASALT, NEPHELINITE, RED_SANDSTONE, SANDSTONE, ARKOSE, SUEVITE, PHONOLITE, SOAPSTONE -> {return true;}
                default -> {return false;}
            }
        }
        if (type == Rock.BlockType.MOSSY_COBBLE){
            switch (rock){
                case BLACKSLAG, NEPHELINITE -> {return true;}
                default -> {return false;}
            }
        }
        return false;
    }
}
