package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.registry.category.CoreDataMaps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class FoundryConversionProvider extends DataMapProvider
{
    public FoundryConversionProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        Builder<CoreDataMaps.FoundryConversion, Item> builder = builder(CoreDataMaps.FOUNDRY_CONVERSION);

        for (Ore ore : Ore.values())
        {
            if (ore.isGraded())
            {
                builder.add(
                        TFCItems.GRADED_ORES.get(ore).get(Ore.Grade.RICH).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 7)),
                        false
                );
                builder.add(
                        TFCItems.GRADED_ORES.get(ore).get(Ore.Grade.NORMAL).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 5)),
                        false
                );
                builder.add(
                        TFCItems.GRADED_ORES.get(ore).get(Ore.Grade.POOR).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 3)),
                        false
                );
            }
        }

        for (SoilBlockType.Variant soilType : SoilBlockType.Variant.values())
        {
            builder.add(TFCBlocks.SOIL.get(SoilBlockType.DRYING_BRICKS).get(soilType).get().asItem().builtInRegistryHolder(),
                    new CoreDataMaps.FoundryConversion(new ItemStack(soilType.mudBrick().asItem(), 1)),
                    false
            );
        }

        // TODO: Turns out that galena does not have a powder item
        /*
        for (CoreOres ore : CoreOres.values())
        {
            if (ore.isGraded())
            {
                builder.add(
                        CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.RICH).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(CoreItems.ORE_POWDERS.get(ore), 7)),
                        false
                );
                builder.add(
                        CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.NORMAL).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 5)),
                        false
                );
                builder.add(
                        CoreItems.GRADED_ORES.get(ore).get(CoreOres.Grade.POOR).key(),
                        new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 3)),
                        false
                );
            }
        }
        */

        // neither does chromium have a powder
        /*
        builder.add(
                FLItems.CHROMIUM_ORES.get(Ore.Grade.RICH).key(),
                new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 7)),
                false
        );
        builder.add(
                FLItems.CHROMIUM_ORES.get(Ore.Grade.NORMAL).key(),
                new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 5)),
                false
        );
        builder.add(
                FLItems.CHROMIUM_ORES.get(Ore.Grade.POOR).key(),
                new CoreDataMaps.FoundryConversion(new ItemStack(TFCItems.ORE_POWDERS.get(ore), 3)),
                false
        );
        */
    }
}

