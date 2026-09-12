package net.gourmand.core.common.create;

import com.simibubi.create.api.boiler.BoilerHeater;
import net.dries007.tfc.common.blockentities.CharcoalForgeBlockEntity;
import net.dries007.tfc.common.blockentities.CrucibleBlockEntity;
import net.dries007.tfc.common.blockentities.FireboxBlockEntity;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CoreBoilerHeaters {

    static public void register(){
        BoilerHeater.REGISTRY.register(TFCBlocks.FIREBOX.get(), FIREBOX);
        BoilerHeater.REGISTRY.register(TFCBlocks.CHARCOAL_FORGE.get(), CHARCOAL_FORGE);
        BoilerHeater.REGISTRY.register(TFCBlocks.CRUCIBLE.get(), CRUCIBLE);
    }

    static BoilerHeater FIREBOX = CoreBoilerHeaters::FireboxDevice;
    static BoilerHeater CHARCOAL_FORGE = CoreBoilerHeaters::CharcoalForgeDevice;
    static BoilerHeater CRUCIBLE = CoreBoilerHeaters::CrucibleDevice;

    public static int FireboxDevice(Level level, BlockPos pos, BlockState state) {

        Optional<FireboxBlockEntity> firebox = level.getBlockEntity(pos, TFCBlockEntities.FIREBOX.get());

        if (firebox.isPresent()){
            return getHeatValue((int) firebox.get().getTemperature());
        }

        return BoilerHeater.NO_HEAT;
    }

    public static int CharcoalForgeDevice(Level level, BlockPos pos, BlockState state) {

        Optional<CharcoalForgeBlockEntity> forge = level.getBlockEntity(pos, TFCBlockEntities.CHARCOAL_FORGE.get());

        if (forge.isPresent()){
            return getHeatValue((int) forge.get().getTemperature());
        }

        return BoilerHeater.NO_HEAT;
    }

    public static int CrucibleDevice(Level level, BlockPos pos, BlockState state) {

        Optional<CrucibleBlockEntity> crucible = level.getBlockEntity(pos, TFCBlockEntities.CRUCIBLE.get());

        if (crucible.isPresent()){
            return getHeatValue((int) crucible.get().getTemperature());
        }

        return BoilerHeater.NO_HEAT;
    }

    private static int getHeatValue(int temperature){

        if (temperature <= 90){
            return BoilerHeater.NO_HEAT; // -1 / ignored
        }
        if (temperature <= 210){
            return BoilerHeater.PASSIVE_HEAT; // 0
        }
        if (temperature < 960){
            return 1; // equal to heated blaze burner
        }
        return 2; // greater than 960, equal to seething blaze burner
    }
}
