package net.gourmand.core.mixin;

import de.dafuqs.spectrum.blocks.conditional.CloakedOreBlock;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.stream.Stream;

//TODO: REMOVE WHEN SPECTRUM RELEASES A NEW RELEASE WITH THE GEODE CHANGES.
@Mixin(CloakedOreBlock.class)
public class CloakedOreBlockMixin {

    @Inject(method = "getBlockStateCloaks", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    void onGetBlockStateCloaks(CallbackInfoReturnable<Map<BlockState, BlockState>> cir){
        Map<BlockState, BlockState> map = cir.getReturnValue();

        Stream.of(
                SpectrumBlocks.AMETHYST_ORE.get().defaultBlockState(),
                SpectrumBlocks.DEEPSLATE_AMETHYST_ORE.get().defaultBlockState(),
                SpectrumBlocks.BLACKSLAG_AMETHYST_ORE.get().defaultBlockState(),
                SpectrumBlocks.CITRINE_ORE.get().defaultBlockState(),
                SpectrumBlocks.DEEPSLATE_CITRINE_ORE.get().defaultBlockState(),
                SpectrumBlocks.BLACKSLAG_CITRINE_ORE.get().defaultBlockState(),
                SpectrumBlocks.TOPAZ_ORE.get().defaultBlockState(),
                SpectrumBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState(),
                SpectrumBlocks.BLACKSLAG_TOPAZ_ORE.get().defaultBlockState(),
                SpectrumBlocks.ONYX_ORE.get().defaultBlockState(),
                SpectrumBlocks.DEEPSLATE_ONYX_ORE.get().defaultBlockState(),
                SpectrumBlocks.BLACKSLAG_ONYX_ORE.get().defaultBlockState(),
                SpectrumBlocks.MOONSTONE_ORE.get().defaultBlockState(),
                SpectrumBlocks.DEEPSLATE_MOONSTONE_ORE.get().defaultBlockState(),
                SpectrumBlocks.BLACKSLAG_MOONSTONE_ORE.get().defaultBlockState()
        ).forEach(blockState -> {
            if (map.containsKey(blockState)){
                cir.setReturnValue(Map.of(blockState, Blocks.SMOOTH_BASALT.defaultBlockState()));
            }
        });
    }
}
