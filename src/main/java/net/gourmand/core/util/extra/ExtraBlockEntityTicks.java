package net.gourmand.core.util.extra;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ExtraBlockEntityTicks
{
    int modpack$tiggerTick = 20 * 3; //roughly every 3 seconds

    void modpack$updateTick(Level level, BlockPos pos);
}
