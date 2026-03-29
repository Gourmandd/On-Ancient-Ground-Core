package net.gourmand.core.event;

import de.dafuqs.spectrum.particle.SpectrumParticleTypes;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

import java.util.ArrayList;
import java.util.List;

public class ServerEventHandler {

    public static final AABB ITEM_CHECK_AABB = Block.box(0.0F, 11.0F, 0.0F, 16.0F, 32.0F, 16.0F).toAabbs().getFirst();
    private static final List<ItemEntity> itemsToDiscard = new ArrayList<>();

    public static void init(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(ServerEventHandler::onUseItemOnBlock);
    }

    public static void onUseItemOnBlock(UseItemOnBlockEvent event){
        liquidCrystalConversion(event);
    }


    public static void liquidCrystalConversion(UseItemOnBlockEvent event){
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState block = level.getBlockState(pos);
        Player player = event.getPlayer();

        if (player != null){

            ItemStack mainHandStack = player.getMainHandItem();

            if (block.is(CoreTags.Blocks.GEMSTONE_BLOCKS) && mainHandStack.getItem() == SpectrumItems.PAINTBRUSH.get() && mainHandStack.getCount() >= 1){

                AABB aabb = ITEM_CHECK_AABB.move(pos.getX(), pos.getY(), pos.getZ());

                List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);

                if (
                    checkForItem(items, SpectrumItems.MERMAIDS_GEM.get(), 1, true) &&
                    checkForItem(items, SpectrumItems.SHIMMERSTONE_GEM.get(), 1, true)
                    )
                {
                    discardItemList();
                    level.setBlockAndUpdate(pos, SpectrumBlocks.LIQUID_CRYSTAL.get().defaultBlockState());
                    level.playLocalSound(pos, SoundType.AMETHYST.getStepSound(), SoundSource.BLOCKS, 1, 1, false);

                    for (int i = 1; i <= 10; i++){
                        level.addParticle(SpectrumParticleTypes.LIQUID_CRYSTAL_SPARKLE, pos.getX(), pos.getY(), pos.getZ(), (Math.random() - 0.5) / 4, 0.10, (Math.random() - 0.5)/ 4);
                    }
                }
            }
        }
    }

    private static boolean checkForItem(List<ItemEntity> items, Item item, int count, boolean delete){

        for (ItemEntity itemEntity : items){

            ItemStack stack = itemEntity.getItem();

            if (stack.getItem() == item && stack.getCount() >= count){

                if (delete && stack.getCount() == count){
                    itemsToDiscard.add(itemEntity);
                } else {
                    // if larger than count.
                    stack.setCount(stack.getCount() - count);
                }
                return true;
            }
        }
        return false;
    }

    private static void discardItemList(){
        for (ItemEntity itemEntity : itemsToDiscard){
            itemEntity.remove(Entity.RemovalReason.DISCARDED);
        }
    }
}
