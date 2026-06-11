package net.gourmand.core.client;

import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

public class ClientForgeEventHandler {

    public static void init()
    {

        final IEventBus bus = NeoForge.EVENT_BUS;

        bus.addListener(ClientForgeEventHandler::onClientTick);
    }

    public static void onClientTick(ClientTickEvent.Post event)
    {
        ModonomiconIntegration.openBook();
    }
}
