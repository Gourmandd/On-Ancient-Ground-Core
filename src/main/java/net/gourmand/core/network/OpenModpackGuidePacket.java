package net.gourmand.core.network;

import io.netty.buffer.ByteBuf;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record OpenModpackGuidePacket() implements CustomPacketPayload {

    public static final Type<OpenModpackGuidePacket> TYPE = new Type<>(AncientGroundCore.location("open_modpack_book"));
    public static final StreamCodec<ByteBuf, OpenModpackGuidePacket> CODEC = StreamCodec.unit(new OpenModpackGuidePacket());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }

    void handle()
    {
        ModonomiconIntegration.openBook();
    }
}
