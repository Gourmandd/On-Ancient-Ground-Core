package net.gourmand.core.registry.category;

import java.util.Locale;
import java.util.Objects;

import net.dries007.tfc.common.TFCTiers;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import net.dries007.tfc.common.LevelTier;
import net.dries007.tfc.common.TFCArmorMaterials;
import net.dries007.tfc.util.registry.RegistryMetal;
import net.dries007.tfc.util.Metal.BlockType;
import rbasamoyai.createbigcannons.index.CBCFluids;


public class CoreMetals {

    public enum MetalType implements StringRepresentable, RegistryMetal {

        ALUMINIUM(0xCDD6DA, MapColor.COLOR_LIGHT_BLUE, Rarity.RARE, -1, TFCTiers.BISMUTH_BRONZE),
        ALUMINIUM_BRONZE(MapColor.COLOR_LIGHT_BLUE, Rarity.RARE, -1, TFCTiers.BLACK_BRONZE),
        CAST_IRON_ALLOY(MapColor.COLOR_LIGHT_BLUE, Rarity.UNCOMMON, -1, TFCTiers.WROUGHT_IRON),
        HARDENED_STEEL(MapColor.COLOR_LIGHT_BLUE, Rarity.RARE, -1, TFCTiers.STEEL),
        NETHERSTEEL(MapColor.COLOR_LIGHT_BLUE, Rarity.EPIC, -1, TFCTiers.RED_STEEL),
        LEAD(0x37304a, MapColor.COLOR_PURPLE, Rarity.COMMON, -1, TFCTiers.COPPER),
        ELECTRUM(0xf4d985, MapColor.COLOR_LIGHT_BLUE, Rarity.COMMON, -1, TFCTiers.COPPER);

        // NETHERSTEEL(0xFF486B72, MapColor.COLOR_LIGHT_BLUE, Rarity.UNCOMMON, -1, TFCTiers.RED_STEEL, TFCArmorMaterials.RED_STEEL, PartType.EQUIPMENT),
        private final String serializedName;
        private final LevelTier toolTier;
        private final TFCArmorMaterials.Id armorMaterial;
        private final MapColor mapColor;
        private final Rarity rarity;
        private final int color;
        private final float weathering;
        private final PartType partType;

        MetalType(MapColor mapColor, Rarity rarity, float weathering, LevelTier tier)
        {
            this(0xFF486B72, mapColor, rarity, weathering, tier, TFCArmorMaterials.COPPER, PartType.BASE);
        }

        MetalType(int color, MapColor mapColor, Rarity rarity, float weathering, LevelTier tier)
        {
            this(color, mapColor, rarity, weathering, tier, TFCArmorMaterials.COPPER, PartType.BASE);
        }

        MetalType(int color, MapColor mapColor, Rarity rarity, float weathering, LevelTier toolTier, TFCArmorMaterials.Id armorTier, PartType partType)
        {
            this.serializedName = name().toLowerCase(Locale.ROOT);
            this.toolTier = toolTier;
            this.armorMaterial = armorTier;
            this.rarity = rarity;
            this.mapColor = mapColor;
            this.color = color;
            this.weathering = weathering;
            this.partType = partType;
        }

        @Override
        public @NotNull LevelTier toolTier() {
            return toolTier;
        }

        @Override
        public @NotNull Holder<ArmorMaterial> armorMaterial() {
            return armorMaterial.holder();
        }

        @Override
        public int armorDurability(ArmorItem.Type type)
        {
            Objects.requireNonNull(armorMaterial, "Tried to get invalid armorMaterial to get its durability");
            return switch (type)
            {
                case HELMET -> armorMaterial.headDamage();
                case BODY, CHESTPLATE -> armorMaterial.chestDamage();
                case LEGGINGS -> armorMaterial.legDamage();
                case BOOTS -> armorMaterial.feetDamage();
            };
        }

        @Override
        public Block getBlock(BlockType blockType) {
            return CoreBlocks.METALS.get(this).get(blockType).get();
        }

        @Override
        public MapColor mapColor() {
            return mapColor;
        }

        public int getColor()
        {
            return color;
        }

        @Override
        public Rarity rarity() {
            return rarity;
        }

        @Override
        public float weatheringResistance() {
            return weathering;
        }

        @Override
        public String getSerializedName() {
            return serializedName;
        }

        public PartType getPartType() {return partType;}

        public boolean hasOtherFluid(){
            switch (this){
                case NETHERSTEEL, ALUMINIUM_BRONZE, HARDENED_STEEL, CAST_IRON_ALLOY -> {
                    return true;
                }
                case null, default -> {
                    return false;
                }
            }
        };

        public Fluid getFluid(){
            switch (this){
                case NETHERSTEEL -> {
                    return CBCFluids.MOLTEN_NETHERSTEEL.get().getSource();
                }
                case ALUMINIUM_BRONZE -> {
                    return CBCFluids.MOLTEN_BRONZE.get().getSource();
                }
                case HARDENED_STEEL -> {
                    return CBCFluids.MOLTEN_STEEL.get().getSource();
                }
                case CAST_IRON_ALLOY -> {
                    return CBCFluids.MOLTEN_CAST_IRON.get().getSource();
                }
                default -> {
                    assert !hasOtherFluid();
                    return CoreFluids.METALS.get(this).getSource();
                }
            }
        }

        public Metal getLikeMetal(){

            if (this.partType == null){
                return Metal.BISMUTH;
            }

            return switch (this.partType)
            {
                case BASE -> Metal.BISMUTH;
                case EQUIPMENT -> Metal.RED_STEEL;
                case BASE_OXIDIZED -> Metal.ROSE_GOLD;
                case EQUIPMENT_OXIDIZED -> Metal.COPPER;
                default -> Metal.BISMUTH;
            };
        }

        public int tier() {
            return this.toolTier != null ? this.toolTier.level() : 0;
        }
    }

    public enum PartType {
        BASE, // ingot, sheet, doubles, rod, plated blocks.
        EQUIPMENT, // BASE + tools, armour, anvil, grate, bars, chain, trapdoor, lantern.
        BASE_OXIDIZED,
        EQUIPMENT_OXIDIZED
    }
}
