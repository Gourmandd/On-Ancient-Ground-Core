package net.gourmand.core.mixin.create;

import com.simibubi.create.content.fluids.spout.FillingBySpout;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.common.component.fluid.FluidComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FillingBySpout.class)
public class FillingBySpoutMixin
{
    @Inject(method = "canItemBeFilled", at = @At(value = "HEAD"), cancellable = true)
    private static void modpack$checkForContainers(Level world, ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        if (stack.has(TFCComponents.FLUID))
        {
            if (!FluidComponent.EMPTY.equals(stack.get(TFCComponents.FLUID)))
            {
                cir.setReturnValue(false);
            }
        }
    }
}
