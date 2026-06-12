package net.gourmand.core.mixin;

import de.dafuqs.spectrum.blocks.decay.DecayAwayBlock;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecayAwayBlock.TargetConversion.class)
public class DecayAwayMixin {

    @Inject(method = "getTargetState", at = @At(value = "RETURN", ordinal = 3), cancellable = true)
    void onGetTargetState(Level world, CallbackInfoReturnable<BlockState> cir){
        if (cir.getReturnValue().is(Blocks.DIRT)){
            cir.setReturnValue(TFCBlocks.SOIL.get(SoilBlockType.DIRT).get(SoilBlockType.Variant.ARIDISOL).get().defaultBlockState());
        }
    }
}
