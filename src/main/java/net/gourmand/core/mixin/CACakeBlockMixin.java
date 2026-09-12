package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mrh0.createaddition.blocks.cake.CACakeBlock;
import net.dries007.tfc.common.component.food.FoodData;
import net.dries007.tfc.common.player.IPlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CACakeBlock.class)
public class CACakeBlockMixin {

    @WrapMethod(method = "eat")
    private static InteractionResult onEat(LevelAccessor world, BlockPos pos, BlockState state, Player player, Operation<InteractionResult> original){
        if (player.canEat(false)){
            IPlayerInfo.get(player).eat(FoodData.CAKE);
        }
        return original.call(world, pos, state, player);
    }
}
