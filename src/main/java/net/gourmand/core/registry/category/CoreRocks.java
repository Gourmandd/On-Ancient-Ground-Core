package net.gourmand.core.registry.category;

import java.util.Locale;
import java.util.function.Supplier;

import de.dafuqs.spectrum.registries.SpectrumBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockDisplayCategory;
import net.gourmand.core.registry.CoreBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;

import net.dries007.tfc.common.Lore;
import net.dries007.tfc.util.registry.RegistryRock;
import org.violetmoon.quark.content.world.module.NewStoneTypesModule;

public enum CoreRocks implements RegistryRock
{
    ARGILLITE(RockDisplayCategory.SEDIMENTARY, MapColor.STONE, false), //vanilla cobble
    NEPHELINITE(RockDisplayCategory.MAFIC_IGNEOUS_EXTRUSIVE, MapColor.DEEPSLATE, false), //deepslate
    BLACKSLAG(RockDisplayCategory.FELSIC_IGNEOUS_EXTRUSIVE, MapColor.TERRACOTTA_BLACK, false),
    PICRITE_BASALT(RockDisplayCategory.MAFIC_IGNEOUS_EXTRUSIVE, MapColor.TERRACOTTA_LIGHT_GRAY, false), // Pastel's basal marble
    TRAVERTINE(RockDisplayCategory.SEDIMENTARY, MapColor.TERRACOTTA_BROWN, false),
    BRECCIA(RockDisplayCategory.SEDIMENTARY, MapColor.SNOW, false), // Caupona's felsic tuff
    KOMATIITE(RockDisplayCategory.MAFIC_IGNEOUS_EXTRUSIVE, MapColor.TERRACOTTA_BROWN, false), // Create scoria
    SANDSTONE(RockDisplayCategory.SEDIMENTARY, MapColor.SAND, false), // Create orchum
    RED_SANDSTONE(RockDisplayCategory.SEDIMENTARY, MapColor.TERRACOTTA_RED, false), // Quark jasper
    SUEVITE(RockDisplayCategory.METAMORPHIC, MapColor.TERRACOTTA_BLACK, false), // Create scorchia
    PHONOLITE(RockDisplayCategory.INTERMEDIATE_IGNEOUS_EXTRUSIVE, MapColor.TERRACOTTA_LIGHT_GRAY, false), // Quark shale
    ARKOSE(RockDisplayCategory.SEDIMENTARY, MapColor.STONE, false), // Quark limestone
    SOAPSTONE(RockDisplayCategory.METAMORPHIC, MapColor.TERRACOTTA_WHITE, false), // Create limestone
    BLUESCHIST(RockDisplayCategory.METAMORPHIC, MapColor.TERRACOTTA_BLUE, true),
    PERIDOTITE(RockDisplayCategory.MAFIC_IGNEOUS_INTRUSIVE, MapColor.TERRACOTTA_LIGHT_GREEN, true),
    SERPENTINE(RockDisplayCategory.METAMORPHIC, MapColor.TERRACOTTA_GREEN, true);

    public static final CoreRocks[] VALUES = values();

    private final String serializedName;
    private final RockDisplayCategory category;
    private final MapColor color;
    private final boolean hasVariants;

    CoreRocks(RockDisplayCategory category, MapColor color)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.category = category;
        this.color = color;
        this.hasVariants = true;
    }

    CoreRocks(RockDisplayCategory category, MapColor color, boolean hasVariants)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.category = category;
        this.color = color;
        this.hasVariants = hasVariants;
    }

    public Item.Properties createItemProperties()
    {
        return new Item.Properties().component(Lore.TYPE, Lore.ROCK_DISPLAY_CATEGORIES.get(category));
    }

    @Override
    public RockDisplayCategory displayCategory()
    {
        return category;
    }

    @Override
    public MapColor color()
    {
        return color;
    }

    public boolean hasVariants()
    {
        return hasVariants;
    }

    public boolean hasVariant(Rock.BlockType blockType){
        if (hasVariants){
            return true;
        }

        switch (blockType){
            case RAW, BRICKS, SMOOTH, CRACKED_BRICKS, CHISELED, PRESSURE_PLATE, BUTTON  -> {
                return false;
            }
            case HARDENED, GRAVEL, SPIKE, MOSSY_BRICKS, COBBLE, MOSSY_COBBLE, MOSSY_LOOSE, LOOSE, AQUEDUCT -> {
                return true;
            }
        }
        return false;
    }

    @Override
    public Supplier<? extends Block> getBlock(Rock.BlockType type)
    {
        if (type == Rock.BlockType.RAW){
            return () -> getRawRock(this);
        } else {
            return CoreBlocks.ROCK_BLOCKS.get(this).get(type);
        }
    }

    @Override
    public Supplier<? extends Block> getAnvil() {
        return null;
    }

    @Override
    public Supplier<? extends SlabBlock> getSlab(Rock.BlockType type)
    {
        return CoreBlocks.ROCK_DECORATIONS.get(this).get(type).slab();
    }

    @Override
    public Supplier<? extends StairBlock> getStair(Rock.BlockType type)
    {
        return CoreBlocks.ROCK_DECORATIONS.get(this).get(type).stair();
    }

    @Override
    public Supplier<? extends WallBlock> getWall(Rock.BlockType type)
    {
        return CoreBlocks.ROCK_DECORATIONS.get(this).get(type).wall();
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }

    public static Block getRawRock(CoreRocks rock)
    {
        if (rock.hasVariants()){
            return CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get();
        } else {
            switch (rock){
                case BRECCIA -> {
                    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("caupona", "felsic_tuff"));
                }
                case KOMATIITE -> {
                    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "scoria"));
                }
                case ARGILLITE -> {
                    return Blocks.STONE;
                }
                case NEPHELINITE -> {
                    return Blocks.DEEPSLATE;
                }
                case TRAVERTINE-> {
                    return Blocks.DRIPSTONE_BLOCK;
                }
                case BLACKSLAG -> {
                    return SpectrumBlocks.BLACKSLAG.get();
                }
                case PICRITE_BASALT -> {
                    return SpectrumBlocks.BASAL_MARBLE.get();
                }
                case SANDSTONE -> {
                    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "ochrum"));
                }
                case RED_SANDSTONE -> {
                    return NewStoneTypesModule.jasperBlock;
                }
                case SUEVITE -> {
                    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "scorchia"));
                }
                case PHONOLITE -> {
                    return NewStoneTypesModule.shaleBlock;
                }
                case ARKOSE -> {
                    return NewStoneTypesModule.limestoneBlock;
                }
                case SOAPSTONE -> {
                    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "limestone"));
                }
                case null, default -> {
                    throw new AssertionError("no raw rock for this rock type " + rock.getSerializedName());
                }
            }
        }
    }

    public static ResourceLocation getRawRockId(CoreRocks rock)
    {
        if (rock.hasVariants()){
            return CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).getId();
        } else {
            switch (rock){
                case BRECCIA -> {
                    return ResourceLocation.fromNamespaceAndPath("caupona", "felsic_tuff");
                }
                case KOMATIITE -> {
                    return ResourceLocation.fromNamespaceAndPath("create", "scoria");
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
                    return ResourceLocation.fromNamespaceAndPath("create", "ochrum");
                }
                case RED_SANDSTONE -> {
                    return ResourceLocation.fromNamespaceAndPath("quark", "jasper");
                }
                case SUEVITE -> {
                    return ResourceLocation.fromNamespaceAndPath("create", "scorchia");
                }
                case PHONOLITE -> {
                    return ResourceLocation.fromNamespaceAndPath("quark", "shale");
                }
                case ARKOSE -> {
                    return ResourceLocation.fromNamespaceAndPath("quark", "limestone");
                }
                case SOAPSTONE -> {
                    return ResourceLocation.fromNamespaceAndPath("create", "limestone");
                }
                case null, default -> throw new AssertionError("no raw rock for this rock type");
            }
        }
    }
}