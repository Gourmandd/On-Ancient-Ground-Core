package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature$LargeDripstone")
public class LargeDripstoneFeatureMixin {

    @WrapOperation(method = "placeBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", ordinal = 0))
    private boolean changePlacedBlock(WorldGenLevel level, BlockPos blockPos, BlockState blockState, int i, Operation<Boolean> original){
        return level.setBlock(blockPos, CoreBlocks.ROCK_BLOCKS.get(CoreRocks.TRAVERTINE).get(Rock.BlockType.HARDENED).get().defaultBlockState(), 2);
    }
}