package net.gourmand.core.registry.category;

import java.util.Locale;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import net.dries007.tfc.common.TFCTiers;
import net.dries007.tfc.common.blocks.IWeatheringBlock;
import net.dries007.tfc.common.blocks.WeatheringBlock;
import net.dries007.tfc.common.blocks.WeatheringSlabBlock;
import net.dries007.tfc.common.blocks.WeatheringStairBlock;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreFluids;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import net.dries007.tfc.common.LevelTier;
import net.dries007.tfc.common.TFCArmorMaterials;
import net.dries007.tfc.util.registry.RegistryMetal;
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
        public Block getBlock(Metal.BlockType blockType) {
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
        }

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

        public FlowingFluid getFlowingFluid(){
            switch (this){
                case NETHERSTEEL -> {
                    return CBCFluids.MOLTEN_NETHERSTEEL.get();
                }
                case ALUMINIUM_BRONZE -> {
                    return CBCFluids.MOLTEN_BRONZE.get();
                }
                case HARDENED_STEEL -> {
                    return CBCFluids.MOLTEN_STEEL.get();
                }
                case CAST_IRON_ALLOY -> {
                    return CBCFluids.MOLTEN_CAST_IRON.get();
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

    public enum BlockType implements StringRepresentable{
        CUT_BLOCK(PartType.BASE, block(IWeatheringBlock.Age.NONE)),
        EXPOSED_CUT_BLOCK(PartType.BASE_OXIDIZED, block(IWeatheringBlock.Age.EXPOSED)),
        WEATHERED_CUT_BLOCK(PartType.BASE_OXIDIZED, block(IWeatheringBlock.Age.WEATHERED)),
        OXIDIZED_CUT_BLOCK(PartType.BASE_OXIDIZED, block(IWeatheringBlock.Age.OXIDIZED)),
        CUT_BLOCK_SLAB(PartType.BASE, slab(IWeatheringBlock.Age.NONE)),
        EXPOSED_CUT_BLOCK_SLAB(PartType.BASE_OXIDIZED, slab(IWeatheringBlock.Age.EXPOSED)),
        WEATHERED_CUT_BLOCK_SLAB(PartType.BASE_OXIDIZED, slab(IWeatheringBlock.Age.WEATHERED)),
        OXIDIZED_CUT_BLOCK_SLAB(PartType.BASE_OXIDIZED, slab(IWeatheringBlock.Age.OXIDIZED)),
        CUT_BLOCK_STAIRS(PartType.BASE, stairs(Metal.BlockType.BLOCK, IWeatheringBlock.Age.NONE)),
        EXPOSED_CUT_BLOCK_STAIRS(PartType.BASE_OXIDIZED, stairs(Metal.BlockType.EXPOSED_BLOCK, IWeatheringBlock.Age.EXPOSED)),
        WEATHERED_CUT_BLOCK_STAIRS(PartType.BASE_OXIDIZED, stairs(Metal.BlockType.WEATHERED_BLOCK, IWeatheringBlock.Age.WEATHERED)),
        OXIDIZED_CUT_BLOCK_STAIRS(PartType.BASE_OXIDIZED, stairs(Metal.BlockType.OXIDIZED_BLOCK, IWeatheringBlock.Age.OXIDIZED));

        private final Function<RegistryMetal, Block> blockFactory;
        private final BiFunction<Block, Item.Properties, ? extends BlockItem> blockItemFactory;
        private final PartType type;
        private final String serializedName;

        BlockType(PartType type, Function<RegistryMetal, Block> blockFactory) {
            this(type, blockFactory, BlockItem::new);
        }

        BlockType(PartType type, Function<RegistryMetal, Block> blockFactory, BiFunction<Block, Item.Properties, ? extends BlockItem> blockItemFactory) {
            this.type = type;
            this.blockFactory = blockFactory;
            this.blockItemFactory = blockItemFactory;
            this.serializedName = this.name().toLowerCase(Locale.ROOT);
        }

        public Supplier<Block> create(RegistryMetal metal) {
            return () -> (Block)this.blockFactory.apply(metal);
        }

        public Function<Block, BlockItem> createBlockItem(Item.Properties properties) {
            return (block) -> (BlockItem)this.blockItemFactory.apply(block, properties);
        }

        public boolean hasMetal(CoreMetals.MetalType metal){
            switch (this.type){
                case BASE_OXIDIZED -> {
                    if (metal.getLikeMetal().weatheredParts()){
                        return true;
                    }
                }
                case BASE -> {
                    if (metal.getLikeMetal().defaultParts()){
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasMetal(Metal metal){
            switch (this.type){
                case BASE_OXIDIZED -> {
                    if (metal.weatheredParts()){
                        return true;
                    }
                }
                case BASE -> {
                    if (metal.defaultParts()){
                        return true;
                    }
                }
            }
            return false;
        }

        public String createName(RegistryMetal metal) {
            String slab = "_slab";
            if (this.serializedName.contains(slab)) {
                String name = this.serializedName.split(slab)[0];
                return "metal/" + name + "/" + metal.getSerializedName() + slab;
            } else {
                String stairs = "_stairs";
                if (this.serializedName.contains(stairs)) {
                    String name = this.serializedName.split(stairs)[0];
                    return "metal/" + name + "/" + metal.getSerializedName() + stairs;
                } else {
                    return "metal/" + this.serializedName + "/" + metal.getSerializedName();
                }
            }
        }

        private static BlockBehaviour.Properties blockProperties(RegistryMetal metal) {
            return BlockBehaviour.Properties.of().mapColor(metal.mapColor()).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL);
        }

        private static Function<RegistryMetal, Block> block(IWeatheringBlock.Age age) {
            return (metal) -> (metal.weatheredParts() ? new WeatheringBlock(blockProperties(metal), age, metal.weatheringResistance()) : new Block(blockProperties(metal)));
        }

        private static Function<RegistryMetal, Block> slab(IWeatheringBlock.Age age) {
            return (metal) -> (metal.weatheredParts() ? new WeatheringSlabBlock(blockProperties(metal), age, metal.weatheringResistance()) : new SlabBlock(blockProperties(metal)));
        }

        private static Function<RegistryMetal, Block> stairs(Metal.BlockType block, IWeatheringBlock.Age age) {
            return (metal) -> (metal.weatheredParts() ? new WeatheringStairBlock(metal.getBlock(block).defaultBlockState(), blockProperties(metal), age, metal.weatheringResistance()) : new StairBlock(metal.getBlock(block).defaultBlockState(), blockProperties(metal)));
        }

        public boolean isStair(){
            return getSerializedName().contains("stairs");
        }

        public boolean isSlab(){
            return getSerializedName().contains("slab");
        }

        public boolean isBlock(){
            return !getSerializedName().contains("stairs") && !getSerializedName().contains("slab");
        }

        @Override
        public String getSerializedName() {
            return serializedName;
        }
    }
}
