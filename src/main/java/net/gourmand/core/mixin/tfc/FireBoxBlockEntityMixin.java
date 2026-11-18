package net.gourmand.core.mixin.tfc;

import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import net.dries007.tfc.common.blockentities.FireboxBlockEntity;
import net.gourmand.core.util.extra.ExtraBlockEntityTicks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FireboxBlockEntity.class, remap = false)
public class FireBoxBlockEntityMixin implements ExtraBlockEntityTicks
{
    @Shadow private float temperature;

    @Unique int modpack$tick = 0;
    @Unique float modpack$previousTemperature = 0;

    public void modpack$updateTick(Level level, BlockPos pos)
    {
        modpack$tick++;
        if (modpack$previousTemperature != temperature)
        {
            modpack$previousTemperature = temperature;
            if (modpack$tick >= modpack$tiggerTick)
            {
                FluidTankBlock.updateBoilerState(level.getBlockState(pos.above()), level, pos.above());
                modpack$tick = 0;
            }
        }
    }

    @Inject(method = "serverTick", at = @At(value = "HEAD"))
    private static void modpack$tick(Level level, BlockPos pos, BlockState state, FireboxBlockEntity box, CallbackInfo ci)
    {
        if (box instanceof ExtraBlockEntityTicks blockEntity)
        {
            blockEntity.modpack$updateTick(level, pos);
        }
    }
}
