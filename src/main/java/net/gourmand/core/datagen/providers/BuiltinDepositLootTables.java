package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.blocks.OreDeposit;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.BiConsumer;

public class BuiltinDepositLootTables implements LootTableSubProvider {

    private final HolderLookup.Provider provider;

    public BuiltinDepositLootTables(HolderLookup.Provider provider) {
        this.provider = provider;
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        for (OreDeposit ore : OreDeposit.values()){
            for (CoreRocks rock : CoreRocks.values()){
                add(rock, ore, LootTableBuilders.createDepositPanningTable(ore, rock), output);
            }
        }
    }

    protected static void add(CoreRocks rock, OreDeposit ore, LootTable.Builder builder, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(createKey("deposit/" + ore.name().toLowerCase(Locale.ROOT) + "/" + rock.getSerializedName()), builder);
    }

    private static ResourceKey<LootTable> createKey(String path){
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, path)
        );
    }
}
