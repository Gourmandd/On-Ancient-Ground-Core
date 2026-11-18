package net.gourmand.core.registry;

import com.simibubi.create.AllItems;
import net.dries007.tfc.common.component.glass.GlassOperation;
import net.dries007.tfc.util.Helpers;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CoreGlassworkingOperations
{
    public static final ResourceKey<Registry<GlassOperation>> KEY = ResourceKey.createRegistryKey(Helpers.identifier("glass_operation"));
    public static final DeferredRegister<GlassOperation> OPERATIONS = DeferredRegister.create(KEY, AncientGroundCore.MOD_ID);

    public static final DeferredHolder<GlassOperation, GlassOperation> OBSIDIAN = powder("obsidian", AllItems.POWDERED_OBSIDIAN);

    private static DeferredHolder<GlassOperation, GlassOperation> powder(String name, DeferredHolder<Item, Item> item)
    {
        return OPERATIONS.register(name, () -> new GlassOperation(item, true));
    }
}
