package net.gourmand.core.mixin;

import de.dafuqs.spectrum.blocks.block_flooder.BlockFlooderBlock;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockFlooderBlock.class)
public abstract class BlockFlooderBlockMixin
{
    @Mutable
    @Final
    @Shadow
    public BlockState DEFAULT_BLOCK_STATE;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void modpack$changeBlockState(BlockBehaviour.Properties settings, CallbackInfo ci)
    {
        this.DEFAULT_BLOCK_STATE = TFCBlocks.SOIL.get(SoilBlockType.DIRT).get(SoilBlockType.Variant.ARIDISOL).get().defaultBlockState();
    }
}
