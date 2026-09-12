package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DripstoneUtils.class)
public class DripstoneUtilsMixin {

    /// Make sure that it places hardened travertine instead of raw.
    @WrapMethod(method = "placeDripstoneBlockIfPossible")
    private static boolean onPlaceDripstoneBlockIfPossible(LevelAccessor level, BlockPos pos, Operation<Boolean> original){

        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(BlockTags.DRIPSTONE_REPLACEABLE)) {
            level.setBlock(pos, CoreBlocks.ROCK_BLOCKS.get(CoreRocks.TRAVERTINE).get(Rock.BlockType.HARDENED).get().defaultBlockState(), 2);
            return true;
        } else {
            return original.call(level, pos);
        }
    }
}
