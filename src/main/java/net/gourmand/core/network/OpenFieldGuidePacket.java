package net.gourmand.core.network;

import io.netty.buffer.ByteBuf;
import net.dries007.tfc.compat.patchouli.PatchouliIntegration;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record OpenFieldGuidePacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OpenFieldGuidePacket> TYPE = new CustomPacketPayload.Type<>(AncientGroundCore.location("open_tfc_book"));
    public static final StreamCodec<ByteBuf, OpenFieldGuidePacket> CODEC = StreamCodec.unit(new OpenFieldGuidePacket());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }

    void handle(@Nullable ServerPlayer player)
    {
        if (player != null)
        {
            PatchouliIntegration.openGui(player);
        }
    }
}
