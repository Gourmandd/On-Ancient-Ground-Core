package net.gourmand.core.datagen.providers;

import net.dries007.tfc.common.component.heat.HeatCapability;
import net.dries007.tfc.common.component.heat.HeatDefinition;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class BuiltinItemHeats extends DataManagerProvider<HeatDefinition> implements Accessors
{

    private final CompletableFuture<?> lookup;

    public BuiltinItemHeats(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(HeatCapability.MANAGER, output, lookup);
        this.lookup = lookup;
    }

    @Override
    protected void addData(HolderLookup.Provider provider)
    {
        generateMetal();
        generateCeramics();
        generateMisc();
    }

    private void generateMetal()
    {
        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT).get(), CategoryUtil.HeatCapacities.INGOT);
            add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT).get(), CategoryUtil.HeatCapacities.DOUBLE_INGOT);
            add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET).get(), CategoryUtil.HeatCapacities.SHEET);
            add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET).get(), CategoryUtil.HeatCapacities.DOUBLE_SHEET);
            add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.ROD).get(), CategoryUtil.HeatCapacities.ROD);

            add(CoreBlocks.METALS.get(metalType).get(Metal.BlockType.BLOCK).get(), CategoryUtil.HeatCapacities.INGOT);
            add(CoreBlocks.METALS.get(metalType).get(Metal.BlockType.BLOCK_SLAB).get(), CategoryUtil.HeatCapacities.INGOT);
            add(CoreBlocks.METALS.get(metalType).get(Metal.BlockType.BLOCK_STAIRS).get(), CategoryUtil.HeatCapacities.INGOT);
        }

        /*
        Stream.of(CoreMetals.BlockType.values()).forEach(type -> {
            Stream.of(CoreMetals.MetalType.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    add(CoreBlocks.CORE_CUSTOM_METAL_BLOCKS.get(metal).get(type).get(), CategoryUtil.HeatCapacities.INGOT);
                }
            });

            Stream.of(Metal.values()).forEach(metal -> {
                if (type.hasMetal(metal)){
                    add(CoreBlocks.TFC_CUSTOM_METAL_BLOCKS.get(metal).get(type).get(), CategoryUtil.HeatCapacities.INGOT);
                }
            });
        });
         */

        for (CoreOres oreType : CoreOres.values())
        {
            if (oreType.isGraded())
            {
                for (CoreOres.Grade grade : CoreOres.Grade.values())
                {
                    add(CoreItems.GRADED_ORES.get(oreType).get(grade).get(), 2.857143f, 138.0f, 184.0f);
                }
                add(CoreBlocks.SMALL_ORES.get(oreType).get(), 2.857143f, 138.0f, 184.0f);
            }
        }

        add(CoreItems.ORES.get(CoreOres.METEORIC_IRON).get(), CategoryUtil.HeatCapacities.INGOT);
    }

    private void generateCeramics()
    {
        for (CoreClay clayType : CoreClay.values())
        {
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BRICK).get(), 0.4f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_FLOWER_POT).get(), 0.6f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_JUG).get(), 0.8f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BOWL).get(), 0.4f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_POT).get(), 0.8f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_SPINDLE_HEAD).get(), 0.8f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_PAN).get(), 0.8f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BLOWPIPE).get(), 0.6f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.INGOT).get(), 0.6f);
            add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_LARGE_VESSEL).get(), 0.6f);
        }
    }

    private void generateMisc()
    {
        add(Ingredient.of(CoreTags.Items.MOLTEN_GLASS), 0.8f);
        add(Ingredient.of(CoreTags.Items.LEAD_GLASS), 0.8f);
        add(Ingredient.of(CoreTags.Items.LEAD_GLASS_PANES), 0.2f);
    }

    private void add(ItemLike item, float heatCapacity)
    {
        add(Ingredient.of(item), heatCapacity);
    }

    private void add(ItemLike item, float heatCapacity, float forgingTemperature, float weldingTemperature)
    {
        add(Ingredient.of(item), heatCapacity, forgingTemperature, weldingTemperature);
    }

    private void add(Ingredient item, float heatCapacity)
    {
        add(nameOf(item), new HeatDefinition(item, heatCapacity, 0f, 0f));
    }

    private void add(Ingredient item, float heatCapacity, float forgingTemperature, float weldingTemperature)
    {
        add(nameOf(item), new HeatDefinition(item, heatCapacity, forgingTemperature, weldingTemperature));
    }
}
