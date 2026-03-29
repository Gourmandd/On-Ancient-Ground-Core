package net.gourmand.core.registry.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MeltableBLock extends Block {

    private final BlockState meltsInto;

    public MeltableBLock(Properties properties, BlockState meltsInto) {
        super(properties);
        this.meltsInto = meltsInto;
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, te, stack);
        BlockState blockstate = level.getBlockState(pos.below());
        if (blockstate.blocksMotion() || blockstate.liquid()) {
            level.setBlockAndUpdate(pos, meltsIntoState());
        }
    }

    public BlockState meltsIntoState() {
        return meltsInto;
    }
}
