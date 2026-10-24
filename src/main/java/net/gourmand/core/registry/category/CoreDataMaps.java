package net.gourmand.core.registry.category;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class CoreDataMaps
{
    public record FoundryConversion(ItemStack stack)
    {
        public static final Codec<FoundryConversion> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ItemStack.CODEC.fieldOf("item_stack").forGetter(FoundryConversion::stack)
        ).apply(instance, FoundryConversion::new));
    }

    public static final DataMapType<Item, FoundryConversion> FOUNDRY_CONVERSION = DataMapType.builder(
            AncientGroundCore.location("foundry_conversion"),
            Registries.ITEM,
            FoundryConversion.CODEC
    ).build();

    public static void register(RegisterDataMapTypesEvent event)
    {
        event.register(FOUNDRY_CONVERSION);
    }
}
