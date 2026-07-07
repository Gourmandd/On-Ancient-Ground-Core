package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.data.Deposit;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreRocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class BuiltinDepositData extends DataManagerProvider<Deposit> {

    public BuiltinDepositData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(Deposit.MANAGER, output, lookup);
    }

    @Override
    protected void addData(HolderLookup.Provider provider)
    {
        for (OreDeposit ore : OreDeposit.values()){
            for (CoreRocks rock : CoreRocks.values()){
                add(rock, ore);
            }
        }
    }

    private void add(CoreRocks rock, OreDeposit ore){
        String oreId = ore.name().toLowerCase(Locale.ROOT);
        String rockId = rock.getSerializedName();

        add("%s/%s".formatted(oreId, rockId), new Deposit(
                Ingredient.of(CoreBlocks.ORE_DEPOSITS.get(rock).get(ore).get()),
                ResourceKey.create(Registries.LOOT_TABLE, AncientGroundCore.location("deposit/%s/%s".formatted(oreId, rockId))),
                List.of(
                        AncientGroundCore.location("item/pan/%s/%s_full".formatted(oreId, rockId)),
                        AncientGroundCore.location("item/pan/%s/%s_half".formatted(oreId, rockId)),
                        Helpers.identifier("item/pan/%s/result".formatted(oreId))
                )
        ));
    }
}
