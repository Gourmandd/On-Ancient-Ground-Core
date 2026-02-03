

package net.gourmand.core.datagen;

import java.util.Set;

import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.providers.BuiltinBlockTags;
import net.gourmand.core.datagen.providers.BuiltinClimateRanges;
import net.gourmand.core.datagen.providers.BuiltinItemTags;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;



public final class DataEntryPoint
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        final PackOutput output = event.getGenerator().getPackOutput();

        final var lookup = add(event, new DatapackBuiltinEntriesProvider(
                event.getGenerator().getPackOutput(), event.getLookupProvider(),
                new RegistrySetBuilder()
                , Set.of(AncientGroundCore.MODID, "minecraft"))).getRegistryProvider();

        add(event, new BuiltinClimateRanges(output, lookup));
        add(event, new BuiltinBlockTags(event, lookup));
        add(event, new BuiltinItemTags(event, lookup));
    }

    private static <T extends DataProvider> T add(GatherDataEvent event, T provider)
    {
        return event.getGenerator().addProvider(true, provider);
    }
}