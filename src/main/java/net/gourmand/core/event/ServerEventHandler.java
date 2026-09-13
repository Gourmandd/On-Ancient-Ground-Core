package net.gourmand.core.event;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.SpectrumWood;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class ServerEventHandler
{
    public static void addToBlockEntities(BlockEntityTypeAddBlocksEvent event)
    {
        for (CoreClay clay : CoreClay.values())
        {
            event.modify(TFCBlockEntities.LARGE_VESSEL.get(), CoreBlocks.CERAMIC_BLOCKS.get(clay).get(CoreClay.BlockType.LARGE_VESSEL).get());
        }
        for (SpectrumWood woodType : SpectrumWood.values())
        {
            event.modify(TFCBlockEntities.BARREL.get(), CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.BARREL).get());
        }
    }
}
