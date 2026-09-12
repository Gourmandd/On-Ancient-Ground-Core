package net.gourmand.core.registry.blockentities;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.gourmand.core.registry.CoreBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CoreTickCounterBlockEntity extends TickCounterBlockEntity {

    public CoreTickCounterBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public static void reset(Level level, BlockPos pos) {
        level.getBlockEntity(pos, CoreBlockEntities.TICK_COUNTING.get()).ifPresent(TickCounterBlockEntity::resetCounter);
    }

    public static void addTicks(Level level, BlockPos pos, long ticks) {
        Optional<CoreTickCounterBlockEntity> entity = level.getBlockEntity(pos, CoreBlockEntities.TICK_COUNTING.get());
        entity.ifPresent(coreTickCounterBlockEntity -> (coreTickCounterBlockEntity).increaseCounter(ticks));
    }

    @Override
    public BlockEntityType<?> getType() {
        return CoreBlockEntities.TICK_COUNTING.get();
    }
}
