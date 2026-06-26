package net.gourmand.core.network;

import net.gourmand.core.AncientGroundCore;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PacketSetup {

    public static void setup(RegisterPayloadHandlersEvent event){

        final PayloadRegistrar register = event.registrar(ModList.get().getModFileById(AncientGroundCore.MODID).versionString());

        register.playToClient(OpenModpackGuidePacket.TYPE, OpenModpackGuidePacket.CODEC, onClient(OpenModpackGuidePacket::handle));
        register.playToServer(OpenFieldGuidePacket.TYPE, OpenFieldGuidePacket.CODEC, onServer(OpenFieldGuidePacket::handle));
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> onClient(Consumer<T> handler)
    {
        return (payload, context) -> context.enqueueWork(() -> handler.accept(payload));
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> onServer(BiConsumer<T, ServerPlayer> handler)
    {
        return (payload, context) -> context.enqueueWork(() -> handler.accept(payload, (ServerPlayer) context.player()));
    }
}
