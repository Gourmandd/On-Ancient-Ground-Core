package net.gourmand.core.common;

import de.dafuqs.spectrum.blocks.idols.FirestarterIdolBlock;
import de.dafuqs.spectrum.blocks.idols.FreezingIdolBlock;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.block.Blocks;

public class HeatingIdolModification
{
    public static void init()
    {
        FreezingIdolBlock.FREEZING_MAP.clear();
        FreezingIdolBlock.FREEZING_MAP.put(Blocks.ICE, new Tuple<>(Blocks.PACKED_ICE.defaultBlockState(), 0.25f));
        FreezingIdolBlock.FREEZING_MAP.put(Blocks.PACKED_ICE, new Tuple<>(Blocks.BLUE_ICE.defaultBlockState(), 0.1f));
        FreezingIdolBlock.FREEZING_MAP.put(SpectrumBlocks.BLAZING_CRYSTAL.get(), new Tuple<>(SpectrumBlocks.FROSTBITE_CRYSTAL.get().defaultBlockState(), 0.1f));
        FreezingIdolBlock.FREEZING_MAP.put(Blocks.SNOW, new Tuple<>(Blocks.POWDER_SNOW.defaultBlockState(), 0.25F));
        FreezingIdolBlock.FREEZING_MAP.put(Blocks.POWDER_SNOW, new Tuple<>(Blocks.SNOW_BLOCK.defaultBlockState(), 0.5F));
        FreezingIdolBlock.FREEZING_MAP.put(Blocks.WATER, new Tuple<>(Blocks.ICE.defaultBlockState(), 1.0F));

        FirestarterIdolBlock.BURNING_MAP.clear();
        FirestarterIdolBlock.BURNING_MAP.put(Blocks.CALCITE, new Tuple<>(Blocks.BASALT.defaultBlockState(), 0.5F));
        FirestarterIdolBlock.BURNING_MAP.put(Rock.BASALT.getBlock(Rock.BlockType.RAW).get(), new Tuple<>(TFCBlocks.MAGMA_BLOCKS.get(Rock.BASALT).get().defaultBlockState(), 0.25F));
        FirestarterIdolBlock.BURNING_MAP.put(TFCBlocks.MAGMA_BLOCKS.get(Rock.BASALT).get(), new Tuple<>(Blocks.LAVA.defaultBlockState(), 0.5F));
        FirestarterIdolBlock.BURNING_MAP.put(SpectrumBlocks.FROSTBITE_CRYSTAL.get(), new Tuple<>((SpectrumBlocks.BLAZING_CRYSTAL.get()).defaultBlockState(), 0.5F));
    }
}
