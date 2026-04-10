package net.gourmand.core.registry;

import de.dafuqs.spectrum.api.item.GemstoneColor;
import de.dafuqs.spectrum.registries.SpectrumRegistries;
import net.dries007.tfc.util.Helpers;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CoreGemstones;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;

public class CoreGemstoneColors {

    public static final DeferredRegister<GemstoneColor> GEMSTONE_COLORS = DeferredRegister.create(SpectrumRegistries.GEMSTONE_COLOR, AncientGroundCore.MODID);

    public static final Map<CoreGemstones, DeferredHolder<GemstoneColor, CoreGemstones>> GEMSTONES = Helpers.mapOf(CoreGemstones.class, gem ->
        register(gem.getSerializedName(), gem)
    );

    private static DeferredHolder<GemstoneColor, CoreGemstones> register(String name, CoreGemstones gem){
        return GEMSTONE_COLORS.register(name, () -> gem);
    }
}
