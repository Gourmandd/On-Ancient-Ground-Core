package net.gourmand.core.registry.category;

import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.registries.SpectrumConfiguredFeatureKeys;
import de.dafuqs.spectrum.registries.SpectrumTreeGrowers;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.devices.SluiceBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.registry.RegistryWood;
import net.gourmand.core.registry.CoreBlockEntities;
import net.gourmand.core.registry.blockentities.CoreLoomBlockEntity;
import net.gourmand.core.registry.blockentities.CoreSluiceBlockEntity;
import net.gourmand.core.registry.blocks.CoreLoomBlock;
import net.gourmand.core.registry.blocks.CoreSaplingBlock;
import net.gourmand.core.registry.blocks.CoreShelfBlock;
import net.gourmand.core.registry.blocks.CoreToolRackBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public enum CoreDeeperDownWood implements RegistryWood {
    CHESTNUT_NOXWOOD(SpectrumWoodType.NOXWOOD, MapColor.CRIMSON_NYLIUM, MapColor.CRIMSON_NYLIUM, SpectrumConfiguredFeatureKeys.CHESTNUT_NOXFUNGUS),
    SLATE_NOXWOOD(SpectrumWoodType.NOXWOOD, MapColor.COLOR_GRAY, MapColor.COLOR_GRAY, SpectrumConfiguredFeatureKeys.SLATE_NOXFUNGUS),
    EBONY_NOXWOOD(SpectrumWoodType.NOXWOOD, MapColor.TERRACOTTA_BLACK, MapColor.TERRACOTTA_BLACK, SpectrumConfiguredFeatureKeys.EBONY_NOXFUNGUS),
    IVORY_NOXWOOD(SpectrumWoodType.NOXWOOD, MapColor.QUARTZ, MapColor.QUARTZ, SpectrumConfiguredFeatureKeys.IVORY_NOXFUNGUS),
    WEEPING_GALA(SpectrumWoodType.WEEPING_GALA, MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SpectrumTreeGrowers.WEEPING_GALA_SAPLING_GENERATOR),
    WHITE(SpectrumWoodType.COLORED_TREE, MapColor.SNOW, MapColor.SNOW, SpectrumTreeGrowers.WHITE_COLORED_SAPLING_GENERATOR),
    ORANGE(SpectrumWoodType.COLORED_TREE, MapColor.COLOR_ORANGE, MapColor.COLOR_ORANGE, SpectrumTreeGrowers.ORANGE_COLORED_SAPLING_GENERATOR),
    MAGENTA(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_MAGENTA, MapColor.COLOR_MAGENTA, SpectrumTreeGrowers.MAGENTA_COLORED_SAPLING_GENERATOR),
    LIGHT_BLUE(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_LIGHT_BLUE, MapColor.COLOR_LIGHT_BLUE, SpectrumTreeGrowers.LIGHT_BLUE_COLORED_SAPLING_GENERATOR),
    YELLOW(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_YELLOW, MapColor.COLOR_YELLOW, SpectrumTreeGrowers.YELLOW_COLORED_SAPLING_GENERATOR),
    LIME(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_LIGHT_GREEN, SpectrumTreeGrowers.LIME_COLORED_SAPLING_GENERATOR),
    PINK(SpectrumWoodType.COLORED_TREE, MapColor.COLOR_PINK, MapColor.COLOR_PINK, SpectrumTreeGrowers.PINK_COLORED_SAPLING_GENERATOR),
    GRAY(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_GRAY, MapColor.COLOR_GRAY, SpectrumTreeGrowers.GRAY_COLORED_SAPLING_GENERATOR),
    LIGHT_GRAY(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_LIGHT_GRAY, SpectrumTreeGrowers.LIGHT_GRAY_COLORED_SAPLING_GENERATOR),
    CYAN(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_CYAN, MapColor.COLOR_CYAN, SpectrumTreeGrowers.CYAN_COLORED_SAPLING_GENERATOR),
    PURPLE(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE, SpectrumTreeGrowers.PURPLE_COLORED_SAPLING_GENERATOR),
    BLUE(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_BLUE, MapColor.COLOR_BLUE, SpectrumTreeGrowers.BLUE_COLORED_SAPLING_GENERATOR),
    BROWN(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, SpectrumTreeGrowers.BROWN_COLORED_SAPLING_GENERATOR),
    GREEN(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_GREEN, MapColor.COLOR_GREEN, SpectrumTreeGrowers.GREEN_COLORED_SAPLING_GENERATOR),
    RED(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_RED, MapColor.COLOR_RED, SpectrumTreeGrowers.RED_COLORED_SAPLING_GENERATOR),
    BLACK(SpectrumWoodType.COLORED_TREE,  MapColor.COLOR_BLACK, MapColor.COLOR_BLACK, SpectrumTreeGrowers.BLACK_COLORED_SAPLING_GENERATOR);

    private final SpectrumWoodType spectrumWoodType;
    private final String serializedName;
    private final MapColor woodColor;
    private final MapColor barkColor;
    private final BlockSetType blockSet;
    private final WoodType woodType;
    private final ResourceLocation planksTextures;
    private final TreeGrower tree;
    private final int daysToGrow;

    CoreDeeperDownWood(SpectrumWoodType spectrumWoodType, MapColor woodColor, MapColor barkColor, ResourceKey<ConfiguredFeature<?, ?>> feature){
        this(spectrumWoodType, woodColor, barkColor, new TreeGrower(feature.toString().replace("spectrum:", "/"), Optional.empty(), Optional.of(feature), Optional.empty()));
    }

    CoreDeeperDownWood(SpectrumWoodType spectrumWoodType, MapColor woodColor, MapColor barkColor, TreeGrower tree){
        this.spectrumWoodType = spectrumWoodType;
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.blockSet = new BlockSetType(serializedName);
        this.woodType = new WoodType(Helpers.identifier(serializedName).toString(), blockSet);
        this.planksTextures = ResourceLocation.parse(SpectrumCommon.MOD_ID + ":block/" + serializedName + "_planks");
        this.tree = tree;
        this.daysToGrow = 7;
    }

    @Override
    public MapColor woodColor() {
        return woodColor;
    }

    public boolean isNoxfungi() { return spectrumWoodType == SpectrumWoodType.NOXWOOD; }

    public SpectrumWoodType getSpectrumWoodType() { return spectrumWoodType; }

    @Override
    public MapColor barkColor() {
        return barkColor;
    }

    public boolean hasBlockType(Wood.BlockType type){
        return switch (type) {
            case TWIG, SEWING_TABLE, SCRIBING_TABLE, VERTICAL_SUPPORT, HORIZONTAL_SUPPORT, LOOM, TOOL_RACK, SLUICE, SHELF, SAPLING -> true;
            default -> false;
        };
    }

    @Override
    public TreeGrower tree() {
        return tree;
    }

    @Override
    public @NotNull Supplier<Integer> ticksToGrow() {
        return () -> (daysToGrow * ICalendar.CALENDAR_TICKS_IN_DAY); //7 days
    }

    @Override
    public int autumnIndex() {
        return 0;
    }

    @Override
    public Supplier<Block> getBlock(Wood.BlockType blockType) {
        return null;
    }

    @Override
    public BlockSetType getBlockSet() {
        return blockSet;
    }

    @Override
    public WoodType getVanillaWoodType() {
        return woodType;
    }

    @Override
    public boolean isConifer() {
        return false;
    }

    @Override
    public float getFlowerOffset() {
        return 0;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public ResourceLocation getPlanksTexture() {
        return planksTextures;
    }

    public static Supplier<Block> create(Wood.BlockType type, CoreDeeperDownWood wood) {

        switch (type){
            case TOOL_RACK -> {
                return () -> new CoreToolRackBlock(ExtendedProperties.of().sound(SoundType.WOOD).strength(2.0F).noOcclusion().blockEntity(CoreBlockEntities.TOOL_RACK));
            }
            case LOOM -> {
                return () -> new CoreLoomBlock(ExtendedProperties.of().sound(SoundType.WOOD).strength(2.5F).noOcclusion().flammableLikePlanks().blockEntity(CoreBlockEntities.LOOM).ticks(CoreLoomBlockEntity::tick), wood.getPlanksTexture());
            }
            case SLUICE -> {
                return () -> new SluiceBlock(ExtendedProperties.of().sound(SoundType.WOOD).strength(3F).noOcclusion().flammableLikeLogs().blockEntity(CoreBlockEntities.SLUICE).serverTicks(CoreSluiceBlockEntity::serverTick));
            }
            case SHELF -> {
                return () -> new CoreShelfBlock(ExtendedProperties.of().sound(SoundType.WOOD).noOcclusion().strength(2.5f).flammableLikePlanks().blockEntity(CoreBlockEntities.SHELF), false);
            }
            case SAPLING -> {
                return () -> new CoreSaplingBlock(wood.tree(), ExtendedProperties.of(MapColor.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.GRASS).flammableLikeLeaves().blockEntity(CoreBlockEntities.TICK_COUNTING), wood.ticksToGrow(), false);
            }
            default -> {
                return type.create(wood);
            }

        }
    }

    public Block getPlanks(){
        return getSpectrumWoodBlock("planks");
    }

    public Block getPlankSlab(){
        return getSpectrumWoodBlock("slab");
    }

    public Block getPlankStairs(){
        return getSpectrumWoodBlock("stairs");
    }

    public Block getPlankFence(){
        return getSpectrumWoodBlock("fence");
    }

    public Block getPlankFenceGate(){
        return getSpectrumWoodBlock("fence_gate");
    }

    public Block getDoor(){
        return getSpectrumWoodBlock("door");
    }

    public Block getPressurePlate(){
        return getSpectrumWoodBlock("pressure_plate");
    }

    public Block getButton(){
        return getSpectrumWoodBlock("button");
    }

    public Block getTrapdoor(){
        return getSpectrumWoodBlock("trapdoor");
    }

    public Block getLog(){
        if (spectrumWoodType == SpectrumWoodType.NOXWOOD){
            return getSpectrumWoodBlock("stem", "noxwood", "noxcap");
        } else {
            return getSpectrumWoodBlock("log");
        }
    }

    public Block getStrippedLog(){
        if (spectrumWoodType == SpectrumWoodType.NOXWOOD){
            return getSpectrumWoodBlock("stripped_stem", "noxwood", "noxcap");
        } else {
            return getSpectrumWoodBlock("stripped_log");
        }
    }

    private Block getSpectrumWoodBlock(String suffix){
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpectrumCommon.MOD_ID, this.getSerializedName() + "_" + suffix));
    }

    private Block getSpectrumWoodBlock(String suffix, String replace, String replaceWith){
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(SpectrumCommon.MOD_ID, this.getSerializedName().replace(replace, replaceWith) + "_" + suffix));
    }

    public enum SpectrumWoodType {
        NOXWOOD,
        WEEPING_GALA,
        COLORED_TREE
    }
}
