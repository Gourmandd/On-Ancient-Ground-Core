package net.gourmand.core.datagen;

import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreDeeperDownWood;
import net.gourmand.core.registry.category.CoreMetals;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.data.FluidHeat;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;

public interface Accessors
{

    default Ingredient ingredientOf(Metal metal, Metal.ItemType type)
    {
        return Ingredient.of(TFCItems.METAL_ITEMS.get(metal).get(type).get());
    }

    default Ingredient ingredientOf(CoreMetals.MetalType metal, Metal.ItemType type)
    {
        return Ingredient.of(CoreItems.METAL_ITEMS.get(metal).get(type).get());
    }

    default Ingredient ingredientOf(CoreMetals.MetalType metal, Metal.BlockType type)
    {
        return Ingredient.of(CoreBlocks.METALS.get(metal).get(type).get());
    }

    default Item itemOf(ResourceLocation name)
    {
        assert BuiltInRegistries.ITEM.containsKey(name) : "No item '" + name + "'";
        return BuiltInRegistries.ITEM.get(name);
    }

    default Fluid fluidOf(Metal metal)
    {
        return TFCFluids.METALS.get(metal).getSource();
    }

    default Fluid fluidOf(CoreMetals.MetalType metal)
    {
        return metal.getFluid();
    }

    default String nameOf(ItemLike item)
    {
        assert item.asItem() != Items.AIR : "Should never get name of Items.AIR";
        assert item.asItem() != Items.BARRIER : "Should never get name of Items.BARRIER";
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }

    default String nameOf(Ingredient ingredient)
    {
        if (ingredient.getCustomIngredient() instanceof CompoundIngredient ing) return nameOf(ing.children().get(0));
        final Ingredient.Value value = ingredient.getValues()[0];
        if (value instanceof Ingredient.TagValue(TagKey<Item> tag)) return tag.location().getPath();
        if (value instanceof Ingredient.ItemValue(ItemStack item)) return nameOf(item.getItem());
        throw new AssertionError("Unknown ingredient value");
    }

    default int units(Metal.ItemType type)
    {
        return switch (type)
        {
            case ROD -> 50;
            default -> 100;
            case DOUBLE_INGOT, SHEET, FISH_HOOK, FISHING_ROD, SWORD, SWORD_BLADE, MACE, MACE_HEAD, SHEARS, UNFINISHED_BOOTS -> 200;
            case DOUBLE_SHEET, TUYERE, UNFINISHED_HELMET, UNFINISHED_CHESTPLATE, UNFINISHED_GREAVES, SHIELD, BOOTS -> 400;
            case HELMET, GREAVES -> 600;
            case CHESTPLATE -> 800;
            case HORSE_ARMOR -> 1200;
        };
    }

    default int units(Metal.BlockType type)
    {
        return switch (type)
        {
            case ANVIL -> 1400;
            case BLOCK, EXPOSED_BLOCK, WEATHERED_BLOCK, OXIDIZED_BLOCK, LAMP, GRATE, EXPOSED_GRATE, WEATHERED_GRATE, OXIDIZED_GRATE -> 100;
            case BLOCK_SLAB, EXPOSED_BLOCK_SLAB, WEATHERED_BLOCK_SLAB, OXIDIZED_BLOCK_SLAB -> 50;
            case BLOCK_STAIRS, EXPOSED_BLOCK_STAIRS, WEATHERED_BLOCK_STAIRS, OXIDIZED_BLOCK_STAIRS -> 75;
            case BARS -> 25;
            case CHAIN -> 6;
            case TRAPDOOR -> 200;
        };
    }

    default int units(CoreMetals.BlockType type)
    {
        return switch (type)
        {
            case CUT_BLOCK, EXPOSED_CUT_BLOCK, WEATHERED_CUT_BLOCK, OXIDIZED_CUT_BLOCK -> 100;
            case CUT_BLOCK_SLAB, EXPOSED_CUT_BLOCK_SLAB, WEATHERED_CUT_BLOCK_SLAB, OXIDIZED_CUT_BLOCK_SLAB -> 50;
            case CUT_BLOCK_STAIRS, EXPOSED_CUT_BLOCK_STAIRS, WEATHERED_CUT_BLOCK_STAIRS, OXIDIZED_CUT_BLOCK_STAIRS -> 75;
        };
    }

    default float temperatureOf(Metal metal)
    {
        return CategoryUtil.TFC_METAL_TO_TEMPERATURE.get(metal);
    }

    default float temperatureOf(CoreMetals.MetalType metal)
    {
        return FluidHeat.MANAGER.getOrThrow(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, metal.getSerializedName())).meltTemperature();
    }

    default TagKey<Item> logsTagOf(CoreDeeperDownWood woodType){
        if (!woodType.isNoxfungi() && woodType != CoreDeeperDownWood.WEEPING_GALA){
            return CoreTags.Items.SPECTRUM_COLORED_LOGS.get(woodType);
        } else {
            switch (woodType){
                case SLATE_NOXWOOD -> {
                    return CoreTags.Items.SLATE_NOXCAP_STEMS;
                }
                case CHESTNUT_NOXWOOD -> {
                    return CoreTags.Items.CHESTNUT_NOXCAP_STEMS;
                }
                case IVORY_NOXWOOD -> {
                    return CoreTags.Items.IVORY_NOXCAP_STEMS;
                }
                case EBONY_NOXWOOD -> {
                    return CoreTags.Items.EBONY_NOXCAP_STEMS;
                }
                case WEEPING_GALA -> {
                    return CoreTags.Items.WEEPING_GALA_LOGS;
                }
            }
        }
        return null;
    }
}