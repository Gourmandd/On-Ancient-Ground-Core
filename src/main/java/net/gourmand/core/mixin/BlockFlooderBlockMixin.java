package net.gourmand.core.mixin;

import de.dafuqs.spectrum.blocks.block_flooder.BlockFlooderBlock;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
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
        this.DEFAULT_BLOCK_STATE = SpectrumBlocks.BLACK_MATERIA.get().defaultBlockState();
    }
}
