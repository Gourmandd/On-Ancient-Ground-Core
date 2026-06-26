package net.gourmand.core.client;

import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.network.OpenFieldGuidePacket;
import net.gourmand.core.util.CoreKeyBindings;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClientForgeEventHandler {

    public static void init()
    {

        final IEventBus bus = NeoForge.EVENT_BUS;

        bus.addListener(ClientForgeEventHandler::onKeyEvent);
    }

    public static void onKeyEvent(InputEvent.Key event)
    {
        if (CoreKeyBindings.OPEN_MODPACK_GUIDE.consumeClick() && Minecraft.getInstance().isWindowActive()){
            ModonomiconIntegration.openBook();
        }
        if (CoreKeyBindings.OPEN_TFC_GUIDE.isDown()){
            PacketDistributor.sendToServer(new OpenFieldGuidePacket());
        }
    }
}
