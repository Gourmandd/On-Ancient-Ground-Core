package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.dries007.tfc.client.screen.button.PlayerInventoryTabButton;
import net.dries007.tfc.network.SwitchInventoryTabPacket;
import net.gourmand.core.network.OpenModpackGuidePacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SwitchInventoryTabPacket.class)
public class SwitchInventoryTabPacketMixin {

    @Shadow @Final private PlayerInventoryTabButton.Tab tab;

    @WrapMethod(method = "handle")
    void onHandle(ServerPlayer player, Operation<Void> original){
        if ((player != null) && (this.tab == PlayerInventoryTabButton.Tab.BOOK)) {
            player.doCloseContainer();
            PacketDistributor.sendToPlayer(player, new OpenModpackGuidePacket());
        } else {
            original.call(player);
        }
    }
}
