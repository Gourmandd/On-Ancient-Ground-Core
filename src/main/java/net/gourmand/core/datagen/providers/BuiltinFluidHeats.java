package net.gourmand.core.datagen.providers;

import net.dries007.tfc.util.data.FluidHeat;
import net.gourmand.core.registry.CoreFluids;
import net.gourmand.core.registry.category.CoreMetals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class BuiltinFluidHeats extends DataManagerProvider<FluidHeat>{

    public static final float HEAT_CAPACITY = 0.003f;

    public BuiltinFluidHeats(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(FluidHeat.MANAGER, output, lookup);
    }

    @Override
    protected void addData(HolderLookup.Provider provider) {

        add(CoreMetals.MetalType.ALUMINIUM, 0.35f, 780);
        add(CoreMetals.MetalType.ALUMINIUM_BRONZE, 0.35f, 1180);
        add(CoreMetals.MetalType.CAST_IRON_ALLOY, 0.35f, 1535);
        add(CoreMetals.MetalType.LEAD, 0.21f, 320);
        add(CoreMetals.MetalType.ELECTRUM, 0.35f, 1060);
        add(CoreMetals.MetalType.HARDENED_STEEL, 0.35f, 1585);
        add(CoreMetals.MetalType.NETHERSTEEL, 0.35f, 1615);

        Stream.of(DyeColor.values()).forEach(color -> add(color, 0.35f, 1070));
        add("glass/clear", CoreFluids.CLEAR_GLASS.getSource(),0.35f, 1070);
    }

    private void add(String name, Fluid fluid, float baseHeatCapacity, float meltTemperature)
    {
        add(name, new FluidHeat(fluid, meltTemperature, HEAT_CAPACITY / baseHeatCapacity));
    }

    private void add(DyeColor color, float baseHeatCapacity, float meltTemperature)
    {
        add(color.getSerializedName(), new FluidHeat(CoreFluids.COLORED_GLASS.get(color).getSource(), meltTemperature, HEAT_CAPACITY / baseHeatCapacity));
    }

    private void add(CoreMetals.MetalType metal, float baseHeatCapacity, float meltTemperature)
    {
        add(metal.getSerializedName(), new FluidHeat(metal.getFluid(), meltTemperature, HEAT_CAPACITY / baseHeatCapacity));
    }
}
