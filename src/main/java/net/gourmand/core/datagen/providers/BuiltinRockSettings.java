package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.blocks.SandstoneBlockType;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.world.settings.RockSettings;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;

public class BuiltinRockSettings {

    public static void bootstrap(BootstrapContext<RockSettings> ctx){
        for (CoreRocks rock : CoreRocks.values())
        {
            if (rock.hasOres()){
                ctx.register(rockKey(rock), makeSetting(rock));
            }
        }
    }

    public static RockSettings makeSetting(CoreRocks rock)
    {
        final var blocks = CoreBlocks.ROCK_BLOCKS.get(rock);
        final var color = CategoryUtil.CoreRock.TO_SAND_COLOR.get(rock);
        return new RockSettings(
                rock.getBlock(Rock.BlockType.RAW).get(),
                blocks.get(Rock.BlockType.HARDENED).get(),
                blocks.get(Rock.BlockType.GRAVEL).get(),
                blocks.get(Rock.BlockType.COBBLE).get(),
                TFCBlocks.SAND.get(color).get(),
                TFCBlocks.SANDSTONE.get(color).get(SandstoneBlockType.RAW).get(),
                Optional.of(blocks.get(Rock.BlockType.SPIKE).get()),
                Optional.of(blocks.get(Rock.BlockType.LOOSE).get()),
                Optional.of(blocks.get(Rock.BlockType.MOSSY_LOOSE).get()),
                Optional.of(false), //none of our rocks are karst.
                Optional.of(false) //some of ours are mafic, but they don't use black sand.
        );
    }

    public static ResourceKey<RockSettings> rockKey(CoreRocks rock)
    {
        return ResourceKey.create(RockSettings.KEY, AncientGroundCore.location(rock.getSerializedName()));
    }
}
