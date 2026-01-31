package net.gourmand.AncientGroundCore.registry.blockentities;

import net.dries007.tfc.common.blockentities.SluiceBlockEntity;
import net.gourmand.AncientGroundCore.registry.CoreBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CoreSluiceBlockEntity extends SluiceBlockEntity {

    public CoreSluiceBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return CoreBlockEntities.SLUICE.get();
    }
}
