package net.gourmand.core;

import com.google.common.collect.ImmutableMap;
import com.klikli_dev.modonomicon.data.LoaderRegistry;
import com.mojang.logging.LogUtils;
import net.gourmand.core.client.ClientEventHandler;
import net.gourmand.core.client.ClientForgeEventHandler;
import net.gourmand.core.datagen.DataEntryPoint;
import net.gourmand.core.event.ServerEventHandler;
import net.gourmand.core.modonomicon.ModonomiconIntegration;
import net.gourmand.core.network.PacketSetup;
import net.gourmand.core.registry.*;
import net.gourmand.core.registry.items.CoreItemCapabilities;
import net.gourmand.core.util.CoreKeyBindings;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AncientGroundCore.MODID)
public class AncientGroundCore {
    public static final String MOD_NAME = "Modpack Core";
    public static final String MODID = "modpack";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AncientGroundCore(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(CoreItemCapabilities::register);
        modEventBus.addListener(DataEntryPoint::gatherData);
        modEventBus.addListener(PacketSetup::setup);

        CoreBlocks.BLOCKS.register(modEventBus);
        CoreItems.ITEMS.register(modEventBus);
        CreativeTabs.CREATIVE_TABS.register(modEventBus);
        CoreWorldGen.BIOME_SOURCES.register(modEventBus);
        CoreWorldGen.CONFIGURED_FEATURE_TYPE.register(modEventBus);
        CoreGemstoneColors.GEMSTONE_COLORS.register(modEventBus);

        CoreFluids.FLUIDS.register(modEventBus);
        CoreFluids.FLUID_TYPES.register(modEventBus);
        CoreBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        ServerEventHandler.init(NeoForge.EVENT_BUS, modContainer);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        if (FMLEnvironment.dist == Dist.CLIENT){
            ClientEventHandler.init(modEventBus, modContainer);
            ClientForgeEventHandler.init();
            ModonomiconIntegration.registerPageRenderers();
        }

        ModonomiconIntegration.registerPages();
    }


    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        Map<String, String> textMacros = ImmutableMap.<String, String>builder()
                .put("macro.modpack.open_modpack_guide_key", CoreKeyBindings.OPEN_MODPACK_GUIDE.getKey().getDisplayName().getString())
                .put("macro.modpack.open_tfc_guide_key", CoreKeyBindings.OPEN_TFC_GUIDE.getKey().getDisplayName().getString())
                .build();

        LoaderRegistry.registerDynamicTextMacroLoader(ModonomiconIntegration.BOOK_ID, () -> textMacros);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static ResourceLocation location(String path){
        return ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, path);
    }

    public static ResourceLocation location(String name, String path){
        return ResourceLocation.fromNamespaceAndPath(name, path);
    }
}
