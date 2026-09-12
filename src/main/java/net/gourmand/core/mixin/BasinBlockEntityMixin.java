package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import net.dries007.tfc.common.blocks.FireboxBlock;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BasinBlockEntity.class)
public abstract class BasinBlockEntityMixin {

    @WrapMethod(method = "getHeatLevelOf")
    private static BlazeBurnerBlock.HeatLevel modpack$getHeatLevelOf(BlockState state, Operation<BlazeBurnerBlock.HeatLevel> original){

        if (state.hasProperty(FireboxBlock.LIT)){
            if (state.getValue(FireboxBlock.LIT)){
                return BlazeBurnerBlock.HeatLevel.KINDLED;
            }
        }
        if (state.hasProperty(CharcoalForgeBlock.HEAT)){
            int heat = state.getValue(CharcoalForgeBlock.HEAT);
            if (heat < 2){
                return BlazeBurnerBlock.HeatLevel.NONE; //lower than ~266C
            }
            if (heat < 6){
                return BlazeBurnerBlock.HeatLevel.KINDLED; //lower than ~1335C
            }
            return BlazeBurnerBlock.HeatLevel.SEETHING; //higher than ~1335C
        }
        return original.call(state);
    }
}
