package net.gourmand.core.registry.items;

import net.gourmand.core.common.OtherWorldlyManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class InfluenceDebugItem extends Item {

    public InfluenceDebugItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //Only the client needs to know the value for now, while this is all WIP.
        if (level.isClientSide){
            OtherWorldlyManager.isActive = !OtherWorldlyManager.isActive;
        }
        return super.use(level, player, usedHand);
    }
}
