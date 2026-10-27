package net.gourmand.core.registry.blocks;

import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.common.blockentities.ToolRackBlockEntity;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.wood.ToolRackBlock;
import net.dries007.tfc.util.Helpers;
import net.gourmand.core.registry.CoreBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

public class CoreToolRackBlock extends ToolRackBlock
{

    public CoreToolRackBlock(ExtendedProperties properties)
    {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        final @Nullable ToolRackBlockEntity toolRack = level.getBlockEntity(pos, CoreBlockEntities.TOOL_RACK.get()).orElse(null);
        if (toolRack != null)
        {
            final IItemHandler inventory = toolRack.getInventory();
            final int slot = getSlotFromPos(state, hitResult.getLocation().subtract(Vec3.atLowerCornerOf(pos)));
            final boolean canInsert = inventory.isItemValid(slot, stack);
            if (!canInsert && inventory.getStackInSlot(slot).isEmpty())
            {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!level.isClientSide)
            {
                final ItemStack extracted = inventory.extractItem(slot, 1, false);
                if (canInsert)
                {
                    inventory.insertItem(slot, stack.split(1), false);
                    Helpers.playSound(level, pos, TFCSounds.TOOL_RACK_PLACE.get());
                }
                if (stack.isEmpty())
                {
                    player.setItemInHand(hand, extracted);
                }
                else
                {
                    ItemHandlerHelper.giveItemToPlayer(player, extracted);
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
