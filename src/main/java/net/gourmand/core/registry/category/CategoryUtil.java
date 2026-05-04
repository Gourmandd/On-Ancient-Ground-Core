package net.gourmand.core.registry.category;

import architectspalette.core.registry.APBlocks;
import com.google.common.collect.ImmutableMap;
import com.simibubi.create.Create;
import com.teammoeg.caupona.CPMain;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.registry.CoreBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.violetmoon.quark.base.Quark;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import static net.dries007.tfc.common.blocks.rock.Ore.*;
import static net.dries007.tfc.common.blocks.rock.Rock.*;
import static net.gourmand.core.registry.category.CoreCrops.*;
import static net.gourmand.core.registry.category.CoreOres.*;
import static net.gourmand.core.registry.category.CoreRocks.*;

public class CategoryUtil {

    public static ArrayList<String> getCropNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreCrops crop : CoreCrops.values()){
            list.add(crop.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getFruitTreeNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreFruitTrees tree : CoreFruitTrees.values()){
            list.add(tree.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getStationaryBushNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreStationaryBushes bush : CoreStationaryBushes.values()){
            list.add(bush.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getSpreadingBushNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreSpreadingBushes bush : CoreSpreadingBushes.values()){
            list.add(bush.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getMetalNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreMetals.MetalType metal : CoreMetals.MetalType.values()){
            list.add(metal.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getAllOreNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreOres ore : CoreOres.values()){
            list.add(ore.name().toLowerCase(Locale.ROOT));
        }

        return list;
    }

    public static ArrayList<String> getNonGradedOreNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreOres ore : CoreOres.values()){
            if (!ore.isGraded()){
                list.add(ore.name().toLowerCase(Locale.ROOT));
            }
        }

        return list;
    }

    public static ArrayList<String> getGradedOreNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreOres ore : CoreOres.values()){
            if (ore.isGraded()){
                list.add(ore.name().toLowerCase(Locale.ROOT));
            }
        }

        return list;
    }

    public static ArrayList<String> GetGemOreNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreOres ore : CoreOres.values()){
            if (ore.isGem()){
                list.add(ore.name().toLowerCase(Locale.ROOT));
            }
        }

        return list;
    }

    public static ArrayList<String> getPastelOreNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreOres ore : CoreOres.values()){
            if (ore.hasSpectrumOreType()){
                list.add(ore.name().toLowerCase(Locale.ROOT));
            }
        }

        return list;
    }

    public static ArrayList<String> getAllRockNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreRocks rock : CoreRocks.values()){
            list.add(rock.name().toLowerCase(Locale.ROOT));
        }

        return list;
    }

    public static ArrayList<String> getAllVariantRockNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreRocks rock : CoreRocks.values()){
            if (rock.hasVariants()){
                list.add(rock.name().toLowerCase(Locale.ROOT));
            }
        }

        return list;
    }

    public static ArrayList<String> getPastelWoodNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreDeeperDownWood wood : CoreDeeperDownWood.values()){
            list.add(wood.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getClayNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreClay clay : CoreClay.values()){
            list.add(clay.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getClayItemTypeNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreClay.ItemType type : CoreClay.ItemType.values()){
            list.add(type.getSerializedName());
        }

        return list;
    }

    public static ArrayList<String> getClayMoldNames(){
        ArrayList<String> list = new ArrayList<>();

        for (CoreClay.ItemType type : CoreClay.ItemType.values()){
            if (type.getType() == CoreClay.ItemPartType.UNFIRED_MOLD){
                list.add(type.getSerializedName());
            }
        }

        return list;
    }

    public static Metal.ItemType[] getTFCTools(){
        return new Metal.ItemType[] {
            Metal.ItemType.PICKAXE,
                    Metal.ItemType.PROPICK,
                    Metal.ItemType.AXE,
                    Metal.ItemType.SHOVEL,
                    Metal.ItemType.HOE,
                    Metal.ItemType.CHISEL,
                    Metal.ItemType.HAMMER,
                    Metal.ItemType.SAW,
                    Metal.ItemType.JAVELIN,
                    Metal.ItemType.SWORD,
                    Metal.ItemType.MACE,
                    Metal.ItemType.KNIFE,
                    Metal.ItemType.SCYTHE
        };
    }
    public static ArrayList<Metal.ItemType> getTFCToolHeads(){
        ArrayList<Metal.ItemType> list = new ArrayList<Metal.ItemType>();

        for (Metal.ItemType type : Metal.ItemType.values()){
            if (type.hasMold() && type != Metal.ItemType.INGOT){
                list.add(type);
            }
        }

        return list;
    }


    public static ArrayList<Metal> getTFCToolMetals(){
        ArrayList<Metal> list = new ArrayList<Metal>();

        for (Metal metal : Metal.values()){
            if (Metal.ItemType.PICKAXE.has(metal)){
                list.add(metal);
            }
        }

        return list;
    }

    public static class HeatCapacities {
        public final static float DOUBLE_SHEET = 11.428572f;
        public final static float SHEET = 5.714286f;
        public final static float DOUBLE_INGOT = 5.714286f;
        public final static float INGOT = 2.857143f;
        public final static float ROD = 1.4285715f;
        public final static float ANVIL = 40.0f;
    }

    public static class CoreCrop {

        public static final Map<CoreCrops, Item> TO_CROP_PRODUCT = ImmutableMap.<CoreCrops, Item>builder()
                .put(COFFEE, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("rusticdelight", "coffee_beans")))
                .put(COTTON, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("rusticdelight", "cotton_boll")))
                .put(GLISTERING_MELON, SpectrumBlocks.GLISTERING_MELON.asItem())
                .put(AMARANTH, SpectrumBlocks.AMARANTH_BUSHEL.asItem())
                .put(WART, Items.NETHER_WART)
                .build();
    }

    public static class Tools {

        // ItemType has to be of CoreClay.ItemPartType.UNFIRED_MOLD.
        public static final Map<CoreClay.ItemType, TagKey<Item>> MOLD_TO_TOOL_BLADE_TAG = ImmutableMap.<CoreClay.ItemType, TagKey<Item>>builder()
                .put(CoreClay.ItemType.PICKAXE_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.PICKAXE_HEAD))
                .put(CoreClay.ItemType.PROPICK_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.PROPICK_HEAD))
                .put(CoreClay.ItemType.AXE_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.AXE_HEAD))
                .put(CoreClay.ItemType.SHOVEL_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SHOVEL_HEAD))
                .put(CoreClay.ItemType.HOE_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.HOE_HEAD))
                .put(CoreClay.ItemType.CHISEL_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.CHISEL_HEAD))
                .put(CoreClay.ItemType.HAMMER_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.HAMMER_HEAD))
                .put(CoreClay.ItemType.SAW_BLADE, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SAW_BLADE))
                .put(CoreClay.ItemType.JAVELIN_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.JAVELIN_HEAD))
                .put(CoreClay.ItemType.SWORD_BLADE, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SWORD_BLADE))
                .put(CoreClay.ItemType.MACE_HEAD, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.MACE_HEAD))
                .put(CoreClay.ItemType.KNIFE_BLADE, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.KNIFE_BLADE))
                .put(CoreClay.ItemType.SCYTHE_BLADE, CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SCYTHE_BLADE))
                .put(CoreClay.ItemType.BELL, CoreTags.Items.BELLS)
                .put(CoreClay.ItemType.INGOT, Tags.Items.INGOTS)
                .build();

        public static final Map<CoreClay.ItemType, Item> UNFIRED_MOLD_TYPE_TO_FIRED = ImmutableMap.<CoreClay.ItemType, Item>builder()
                .put(CoreClay.ItemType.PICKAXE_HEAD, TFCItems.MOLDS.get(Metal.ItemType.PICKAXE_HEAD).asItem())
                .put(CoreClay.ItemType.PROPICK_HEAD, TFCItems.MOLDS.get(Metal.ItemType.PROPICK_HEAD).asItem())
                .put(CoreClay.ItemType.AXE_HEAD, TFCItems.MOLDS.get(Metal.ItemType.AXE_HEAD).asItem())
                .put(CoreClay.ItemType.SHOVEL_HEAD, TFCItems.MOLDS.get(Metal.ItemType.SHOVEL_HEAD).asItem())
                .put(CoreClay.ItemType.HOE_HEAD, TFCItems.MOLDS.get(Metal.ItemType.HOE_HEAD).asItem())
                .put(CoreClay.ItemType.CHISEL_HEAD, TFCItems.MOLDS.get(Metal.ItemType.CHISEL_HEAD).asItem())
                .put(CoreClay.ItemType.HAMMER_HEAD, TFCItems.MOLDS.get(Metal.ItemType.HAMMER_HEAD).asItem())
                .put(CoreClay.ItemType.SAW_BLADE, TFCItems.MOLDS.get(Metal.ItemType.SAW_BLADE).asItem())
                .put(CoreClay.ItemType.JAVELIN_HEAD, TFCItems.MOLDS.get(Metal.ItemType.JAVELIN_HEAD).asItem())
                .put(CoreClay.ItemType.SWORD_BLADE, TFCItems.MOLDS.get(Metal.ItemType.SWORD_BLADE).asItem())
                .put(CoreClay.ItemType.MACE_HEAD, TFCItems.MOLDS.get(Metal.ItemType.MACE_HEAD).asItem())
                .put(CoreClay.ItemType.KNIFE_BLADE, TFCItems.MOLDS.get(Metal.ItemType.KNIFE_BLADE).asItem())
                .put(CoreClay.ItemType.SCYTHE_BLADE, TFCItems.MOLDS.get(Metal.ItemType.SCYTHE_BLADE).asItem())
                .put(CoreClay.ItemType.BELL, TFCItems.BELL_MOLD.asItem())
                .put(CoreClay.ItemType.INGOT, TFCItems.MOLDS.get(Metal.ItemType.INGOT).asItem())
                .build();
    }

    public static class CoreRock {

        public static final Map<CoreRocks, SandBlockType> TO_SAND_COLOR = ImmutableMap.<CoreRocks, SandBlockType>builder()
                .put(SERPENTINE, SandBlockType.GREEN)
                .put(PERIDOTITE, SandBlockType.GREEN)
                .put(SOAPSTONE, SandBlockType.WHITE)
                .put(RED_SANDSTONE, SandBlockType.RED)
                .put(NEPHELINITE, SandBlockType.BLACK)
                .put(BLACKSLAG, SandBlockType.BLACK)
                .put(PICRITE_BASALT, SandBlockType.BLACK)
                .put(SUEVITE, SandBlockType.BLACK)
                .put(PHONOLITE, SandBlockType.BLACK)
                .put(BLUESCHIST, SandBlockType.BLACK)
                .put(KOMATIITE, SandBlockType.BROWN)
                .put(TRAVERTINE, SandBlockType.BROWN)
                .put(ARGILLITE, SandBlockType.BROWN)
                .put(BRECCIA, SandBlockType.YELLOW)
                .put(SANDSTONE, SandBlockType.YELLOW)
                .put(ARKOSE, SandBlockType.YELLOW)
                .build();

        public static final Map<CoreRocks, Holder.Reference<Block>> TO_RAW_BLOCK = ImmutableMap.<CoreRocks, Holder.Reference<Block>>builder()
                .put(SERPENTINE, getReference(CoreBlocks.ROCK_BLOCKS.get(SERPENTINE).get(BlockType.RAW).get()))
                .put(PERIDOTITE, getReference(CoreBlocks.ROCK_BLOCKS.get(PERIDOTITE).get(BlockType.RAW).get()))
                .put(BLUESCHIST, getReference(CoreBlocks.ROCK_BLOCKS.get(BLUESCHIST).get(BlockType.RAW).get()))
                .put(SOAPSTONE, getReference(getRawRockId(SOAPSTONE).getNamespace(), getRawRockId(SOAPSTONE).getPath()))
                .put(SANDSTONE, getReference(getRawRockId(SANDSTONE).getNamespace(), getRawRockId(SANDSTONE).getPath()))
                .put(SUEVITE, getReference(getRawRockId(SUEVITE).getNamespace(), getRawRockId(SUEVITE).getPath()))
                .put(KOMATIITE, getReference(getRawRockId(KOMATIITE).getNamespace(), getRawRockId(KOMATIITE).getPath()))
                .put(RED_SANDSTONE, getReference(getRawRockId(RED_SANDSTONE).getNamespace(), getRawRockId(RED_SANDSTONE).getPath()))
                .put(PHONOLITE, getReference(getRawRockId(PHONOLITE).getNamespace(), getRawRockId(PHONOLITE).getPath()))
                .put(ARKOSE, getReference(getRawRockId(ARKOSE).getNamespace(), getRawRockId(ARKOSE).getPath()))
                .put(BLACKSLAG, getReference(SpectrumBlocks.BLACKSLAG.get()))
                .put(PICRITE_BASALT, getReference(SpectrumBlocks.BASAL_MARBLE.get()))
                .put(TRAVERTINE, getReference(Blocks.DRIPSTONE_BLOCK))
                .put(ARGILLITE, getReference(Blocks.STONE))
                .put(NEPHELINITE, getReference(Blocks.DEEPSLATE))
                .put(BRECCIA, getReference(getRawRockId(BRECCIA).getNamespace(), getRawRockId(BRECCIA).getPath()))
                .build();

        public static final Map<CoreRocks, Holder.Reference<Block>> TO_BRICK_BLOCK = ImmutableMap.<CoreRocks, Holder.Reference<Block>>builder()
                .put(SERPENTINE, getReference(CoreBlocks.ROCK_BLOCKS.get(SERPENTINE).get(BlockType.BRICKS).get()))
                .put(PERIDOTITE, getReference(CoreBlocks.ROCK_BLOCKS.get(PERIDOTITE).get(BlockType.BRICKS).get()))
                .put(BLUESCHIST, getReference(CoreBlocks.ROCK_BLOCKS.get(BLUESCHIST).get(BlockType.BRICKS).get()))
                .put(SOAPSTONE, getReference(Create.ID, "cut_" + getRawRockId(SOAPSTONE).getPath() + "_bricks"))
                .put(SANDSTONE, getReference(Create.ID, "cut_" + getRawRockId(SANDSTONE).getPath() + "_bricks"))
                .put(SUEVITE, getReference(Create.ID, "cut_" + getRawRockId(SUEVITE).getPath() + "_bricks"))
                .put(KOMATIITE, getReference(Create.ID, "cut_" + getRawRockId(KOMATIITE).getPath() + "_bricks"))
                .put(RED_SANDSTONE, getReference(Quark.MOD_ID, getRawRockId(RED_SANDSTONE).getPath() + "_bricks"))
                .put(PHONOLITE, getReference(Quark.MOD_ID, getRawRockId(PHONOLITE).getPath() + "_bricks"))
                .put(ARKOSE, getReference(Quark.MOD_ID, getRawRockId(ARKOSE).getPath() + "_bricks"))
                .put(BLACKSLAG, getReference(SpectrumBlocks.BLACKSLAG_BRICKS.get()))
                .put(PICRITE_BASALT, getReference(SpectrumBlocks.BASAL_MARBLE_BRICKS.get()))
                .put(TRAVERTINE, getReference(APBlocks.DRIPSTONE_BRICKS.get()))
                .put(ARGILLITE, getReference(Blocks.STONE_BRICKS))
                .put(NEPHELINITE, getReference(Blocks.DEEPSLATE_BRICKS))
                .put(BRECCIA, getReference(CPMain.MODID, getRawRockId(BRECCIA).getPath() + "_bricks"))
                .build();

        public static ResourceLocation getRawRockId(CoreRocks rock)
        {
            if (rock.hasVariants()){
                return CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).getId();
            } else {
                switch (rock){
                    case BRECCIA -> {
                        return ResourceLocation.fromNamespaceAndPath(CPMain.MODID, "felsic_tuff");
                    }
                    case KOMATIITE -> {
                        return ResourceLocation.fromNamespaceAndPath(Create.ID, "scoria");
                    }
                    case ARGILLITE -> {
                        return ResourceLocation.fromNamespaceAndPath("minecraft", "stone");
                    }
                    case NEPHELINITE -> {
                        return ResourceLocation.fromNamespaceAndPath("minecraft", "deepslate");
                    }
                    case TRAVERTINE-> {
                        return ResourceLocation.fromNamespaceAndPath("minecraft", "dripstone_block");
                    }
                    case SANDSTONE -> {
                        return ResourceLocation.fromNamespaceAndPath(Create.ID, "ochrum");
                    }
                    case RED_SANDSTONE -> {
                        return ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "jasper");
                    }
                    case SUEVITE -> {
                        return ResourceLocation.fromNamespaceAndPath(Create.ID, "scorchia");
                    }
                    case PHONOLITE -> {
                        return ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "shale");
                    }
                    case ARKOSE -> {
                        return ResourceLocation.fromNamespaceAndPath(Quark.MOD_ID, "limestone");
                    }
                    case SOAPSTONE -> {
                        return ResourceLocation.fromNamespaceAndPath(Create.ID, "limestone");
                    }
                    case null, default -> throw new AssertionError("no raw rock for this rock type");
                }
            }
        }
    }

    public static class TFCRock {

        public static final Map<Rock, SandBlockType> TO_SAND_COLOR = ImmutableMap.<Rock, SandBlockType>builder()
                .put(GRANITE, SandBlockType.YELLOW)
                .put(DIORITE, SandBlockType.RED)
                .put(GABBRO, SandBlockType.BLACK)
                .put(RHYOLITE, SandBlockType.YELLOW)
                .put(DACITE, SandBlockType.RED)
                .put(ANDESITE, SandBlockType.RED)
                .put(BASALT, SandBlockType.BLACK)
                .put(SHALE, SandBlockType.BROWN)
                .put(CLAYSTONE, SandBlockType.BROWN)
                .put(LIMESTONE, SandBlockType.WHITE)
                .put(CONGLOMERATE, SandBlockType.BROWN)
                .put(DOLOMITE, SandBlockType.WHITE)
                .put(CHERT, SandBlockType.RED)
                .put(CHALK, SandBlockType.WHITE)
                .put(TUFF, SandBlockType.GREEN)
                .put(QUARTZITE, SandBlockType.WHITE)
                .put(SLATE, SandBlockType.YELLOW)
                .put(PHYLLITE, SandBlockType.YELLOW)
                .put(SCHIST, SandBlockType.YELLOW)
                .put(GNEISS, SandBlockType.YELLOW)
                .put(MARBLE, SandBlockType.WHITE)
                .build();
    }

    public static class Ores {

        public static final Map<CoreOres, TagKey<Block>> CORE_ORE_TO_MINING_TIER_TAG = ImmutableMap.<CoreOres, TagKey<Block>>builder()
                .put(GALENA, BlockTags.NEEDS_STONE_TOOL)
                .put(METEORIC_IRON, BlockTags.NEEDS_STONE_TOOL)
                .put(ANTHRACITE, BlockTags.NEEDS_STONE_TOOL)
                .put(BAUXITE, BlockTags.NEEDS_STONE_TOOL)
                .put(QUARTZ, BlockTags.NEEDS_STONE_TOOL)
                .put(SHIMMERSTONE, BlockTags.NEEDS_STONE_TOOL)
                .put(AZURITE, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(PALTAERIA, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(STRATINE, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(CoreOres.MALACHITE, Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .build();

        public static final Map<Ore, TagKey<Block>> TFC_ORES_TO_MINING_TIER_TAG = ImmutableMap.<Ore, TagKey<Block>>builder()
                .put(NATIVE_COPPER,BlockTags.NEEDS_STONE_TOOL)
                .put(NATIVE_GOLD, BlockTags.NEEDS_STONE_TOOL)
                .put(HEMATITE, BlockTags.NEEDS_STONE_TOOL)
                .put(NATIVE_SILVER, BlockTags.NEEDS_STONE_TOOL)
                .put(CASSITERITE, BlockTags.NEEDS_STONE_TOOL)
                .put(BISMUTHINITE, BlockTags.NEEDS_STONE_TOOL)
                .put(GARNIERITE, BlockTags.NEEDS_IRON_TOOL)
                .put(Ore.MALACHITE, BlockTags.NEEDS_STONE_TOOL)
                .put(MAGNETITE, BlockTags.NEEDS_STONE_TOOL)
                .put(LIMONITE, BlockTags.NEEDS_STONE_TOOL)
                .put(SPHALERITE, BlockTags.NEEDS_STONE_TOOL)
                .put(TETRAHEDRITE, BlockTags.NEEDS_STONE_TOOL)
                .put(GYPSUM, BlockTags.NEEDS_STONE_TOOL)
                .put(CINNABAR, BlockTags.NEEDS_IRON_TOOL)
                .put(CRYOLITE, BlockTags.NEEDS_IRON_TOOL)
                .put(BORAX, BlockTags.NEEDS_STONE_TOOL)
                .put(GRAPHITE, BlockTags.NEEDS_STONE_TOOL)
                .put(SALTPETER, BlockTags.NEEDS_STONE_TOOL)
                .put(SULFUR, BlockTags.NEEDS_STONE_TOOL)
                .put(SYLVITE, BlockTags.NEEDS_STONE_TOOL)
                .put(AMETHYST, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(DIAMOND, Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .put(EMERALD, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(LAPIS_LAZULI, BlockTags.NEEDS_IRON_TOOL)
                .put(OPAL, BlockTags.NEEDS_IRON_TOOL)
                .put(PYRITE, BlockTags.NEEDS_STONE_TOOL)
                .put(RUBY, Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .put(SAPPHIRE, Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .put(TOPAZ, BlockTags.NEEDS_DIAMOND_TOOL)
                .put(BITUMINOUS_COAL, BlockTags.NEEDS_STONE_TOOL)
                .put(LIGNITE, BlockTags.NEEDS_STONE_TOOL)
                .put(HALITE, BlockTags.NEEDS_STONE_TOOL)
                .build();
    }

    public static final Map<Metal, Integer> TFC_METAL_TO_TEMPERATURE = ImmutableMap.<Metal, Integer>builder()
            .put(Metal.BISMUTH, 270)
            .put(Metal.BISMUTH_BRONZE, 985)
            .put(Metal.BLACK_BRONZE, 1070)
            .put(Metal.BRASS, 930)
            .put(Metal.COPPER, 1080)
            .put(Metal.GOLD, 1060)
            .put(Metal.NICKEL, 1453)
            .put(Metal.ROSE_GOLD, 960)
            .put(Metal.SILVER, 961)
            .put(Metal.TIN,230)
            .put(Metal.ZINC, 420)
            .put(Metal.STERLING_SILVER, 950)
            .put(Metal.WROUGHT_IRON, 1535)
            .put(Metal.CAST_IRON, 1535)
            .put(Metal.PIG_IRON, 1535)
            .put(Metal.STEEL, 1540)
            .put(Metal.BLACK_STEEL, 1485)
            .put(Metal.BLUE_STEEL, 1540)
            .put(Metal.RED_STEEL, 1540)
            .put(Metal.WEAK_STEEL, 1540)
            .put(Metal.WEAK_BLUE_STEEL, 1540)
            .put(Metal.WEAK_RED_STEEL, 1540)
            .put(Metal.HIGH_CARBON_STEEL, 1540)
            .put(Metal.HIGH_CARBON_BLACK_STEEL, 1540)
            .put(Metal.HIGH_CARBON_BLUE_STEEL, 1540)
            .put(Metal.HIGH_CARBON_RED_STEEL, 1540)
            .put(Metal.BRONZE, 950)
            .put(Metal.UNKNOWN, 400)
            .build();

    private static Holder.Reference<Block> getReference(String namespace, String path){
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(namespace, path)).builtInRegistryHolder();
    }

    private static Holder.Reference<Block> getReference(Block block){
        return block.builtInRegistryHolder();
    }
}
