package net.gourmand.core.registry.category;

import de.dafuqs.spectrum.blocks.conditional.CloakedOreBlock;
import de.dafuqs.spectrum.blocks.geology.AzuriteOreBlock;
import de.dafuqs.spectrum.blocks.geology.ShimmerstoneOreBlock;
import de.dafuqs.spectrum.registries.SpectrumAdvancements;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.util.RegistryOre;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;

public enum CoreOres implements RegistryOre {

    SHIMMERSTONE(Type.NORMAL, SpectrumOreType.SHIMMERSTONE),
    AZURITE(Type.NORMAL, SpectrumOreType.AZURITE),
    STRATINE(Type.NORMAL, SpectrumOreType.STRATINE),
    PALTAERIA(Type.NORMAL, SpectrumOreType.PALTAERIA),
    MALACHITE(Type.NORMAL, SpectrumOreType.MALACHITE),
    GALENA(Type.GRADED, CoreMetals.MetalType.LEAD),
    METEORIC_IRON(Type.ITEM_ONLY),
    QUARTZ(Type.ITEM_ONLY),
    ANTHRACITE(Type.ITEM_ONLY),
    BAUXITE(Type.ITEM_ONLY);

    private final CoreMetals.MetalType metal;
    private final Type type;
    private final SpectrumOreType spectrumOreType;
    private final String serializedName;

    CoreOres(Type type)
    {
        this(type, SpectrumOreType.NONE);
    }

    CoreOres(Type type, CoreMetals.MetalType metal)
    {
        this(type, SpectrumOreType.NONE, metal);
    }

    CoreOres(Type type, SpectrumOreType spectrumOreType)
    {
        this(type, spectrumOreType, null);
    }

    CoreOres(Type type, SpectrumOreType spectrumOreType, CoreMetals.MetalType metal)
    {
        this.metal = metal;
        this.type = type;
        this.spectrumOreType = spectrumOreType;
        this.serializedName = name().toLowerCase(Locale.ROOT);
    }

    public boolean isGraded()
    {
        return type == Type.GRADED;
    }

    public boolean isGem()
    {
        return type == Type.GEM;
    }

    public boolean hasPowder()
    {
        return type != Type.NORMAL && type != Type.ITEM_ONLY;
    }

    public CoreMetals.MetalType metal()
    {
        return metal;
    }

    public boolean hasBlock()
    {
        return type != Type.ITEM_ONLY;
    }

    public boolean hasSpectrumOreType()
    {
        return this.spectrumOreType != SpectrumOreType.NONE;
    }

    public Block create(RegistryRock rock)
    {
        final BlockBehaviour.Properties properties = Block.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(rock.category().hardness(6.5f), 10).requiresCorrectToolForDrops();
        final Block block;

        switch (spectrumOreType){
            case SpectrumOreType.SHIMMERSTONE -> block = new ShimmerstoneOreBlock( UniformInt.of(2, 4), properties, SpectrumAdvancements.REVEAL_SHIMMERSTONE, getRockBlockState(rock));
            case SpectrumOreType.AZURITE -> block = new AzuriteOreBlock(UniformInt.of(4, 7), properties, SpectrumAdvancements.REVEAL_AZURITE, getRockBlockState(rock));
            case SpectrumOreType.STRATINE -> block = new CloakedOreBlock(UniformInt.of(3, 5), properties, SpectrumAdvancements.REVEAL_STRATINE, getRockBlockState(rock));
            case SpectrumOreType.PALTAERIA -> block = new CloakedOreBlock(UniformInt.of(2, 4), properties, SpectrumAdvancements.REVEAL_PALTAERIA, getRockBlockState(rock));
            case SpectrumOreType.MALACHITE -> block = new CloakedOreBlock(UniformInt.of(7, 11), properties, SpectrumAdvancements.REVEAL_MALACHITE, getRockBlockState(rock));
            case SpectrumOreType.NONE -> block = new Block(properties);
            default -> throw new AssertionError("spectrumOreType of CoreOre did not match any PastelOreType enum entry");
        }
        return block;
    }

    private BlockState getRockBlockState(RegistryRock rock){
        if (rock instanceof Rock){
            return TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get().defaultBlockState();
        }
        if (rock instanceof CoreRocks) {
            return CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rock).value().defaultBlockState();
        }
        return Blocks.STONE.defaultBlockState();
    }

    public Item getPastelOre(){

        final Item item;

        switch (spectrumOreType){
            case SpectrumOreType.SHIMMERSTONE -> item = SpectrumItems.SHIMMERSTONE_GEM.asItem();
            case SpectrumOreType.AZURITE -> item = SpectrumItems.RAW_AZURITE.asItem();
            case SpectrumOreType.STRATINE -> item = SpectrumItems.STRATINE_FRAGMENTS.asItem();
            case SpectrumOreType.PALTAERIA -> item = SpectrumItems.PALTAERIA_FRAGMENTS.asItem();
            case SpectrumOreType.MALACHITE -> item = SpectrumItems.RAW_MALACHITE.asItem();
            case SpectrumOreType.NONE -> throw new AssertionError("spectrumOre of CoreOre is NONE, only use the method if you are sure spectrumOreType isnt NONE");
            default -> throw new AssertionError("spectrumOre of CoreOre did not match any SpectrumOreType enum entry");
        }
        return item;
    }

    public static Ore.Grade getTFCgrade(Grade grade){
        switch (grade){
            case POOR -> {
                return Ore.Grade.POOR;
            }
            case NORMAL ->{
                return Ore.Grade.NORMAL;
            }
            case RICH -> {
                return Ore.Grade.RICH;
            }
            default -> {
                return Ore.Grade.NORMAL;
            }
        }
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public enum Grade
    {
        POOR, NORMAL, RICH
    }

    enum Type
    {
        GRADED, NORMAL, NORMAL_WITH_POWDER, GEM, ITEM_ONLY
    }

    enum SpectrumOreType
    {
        NONE, SHIMMERSTONE, STRATINE, AZURITE, PALTAERIA, MALACHITE
    }
}
