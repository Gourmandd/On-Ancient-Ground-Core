package net.gourmand.core.registry.category;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.util.RegistryOre;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum TFCOres implements RegistryOre {

    NATIVE_COPPER(CoreOres.Type.GRADED, Ore.NATIVE_COPPER),
    NATIVE_GOLD(CoreOres.Type.GRADED, Ore.NATIVE_GOLD),
    HEMATITE(CoreOres.Type.GRADED, Ore.HEMATITE),
    NATIVE_SILVER(CoreOres.Type.GRADED, Ore.NATIVE_SILVER),
    CASSITERITE(CoreOres.Type.GRADED, Ore.CASSITERITE),
    BISMUTHINITE(CoreOres.Type.GRADED, Ore.BISMUTHINITE),
    GARNIERITE(CoreOres.Type.GRADED, Ore.GARNIERITE),
    MALACHITE(CoreOres.Type.GRADED, Ore.MALACHITE),
    MAGNETITE(CoreOres.Type.GRADED, Ore.MAGNETITE),
    LIMONITE(CoreOres.Type.GRADED, Ore.LIMONITE),
    SPHALERITE(CoreOres.Type.GRADED, Ore.SPHALERITE),
    TETRAHEDRITE(CoreOres.Type.GRADED, Ore.TETRAHEDRITE),
    GYPSUM(CoreOres.Type.NORMAL, Ore.GYPSUM),
    CINNABAR(CoreOres.Type.NORMAL, Ore.CINNABAR),
    CRYOLITE(CoreOres.Type.NORMAL, Ore.CRYOLITE),
    BORAX(CoreOres.Type.NORMAL, Ore.BORAX),
    GRAPHITE(CoreOres.Type.NORMAL_WITH_POWDER, Ore.GRAPHITE),
    SALTPETER(CoreOres.Type.NORMAL_WITH_POWDER, Ore.SALTPETER),
    SULFUR(CoreOres.Type.NORMAL_WITH_POWDER, Ore.SULFUR),
    SYLVITE(CoreOres.Type.NORMAL_WITH_POWDER, Ore.SYLVITE),
    AMETHYST(CoreOres.Type.GEM, Ore.AMETHYST),
    DIAMOND(CoreOres.Type.GEM, Ore.DIAMOND),
    EMERALD(CoreOres.Type.GEM, Ore.EMERALD),
    LAPIS_LAZULI(CoreOres.Type.GEM, Ore.LAPIS_LAZULI),
    OPAL(CoreOres.Type.GEM, Ore.OPAL),
    PYRITE(CoreOres.Type.GEM, Ore.PYRITE),
    RUBY(CoreOres.Type.GEM, Ore.RUBY),
    SAPPHIRE(CoreOres.Type.GEM, Ore.SAPPHIRE),
    TOPAZ(CoreOres.Type.GEM, Ore.TOPAZ),
    BITUMINOUS_COAL(CoreOres.Type.ITEM_ONLY, Ore.BITUMINOUS_COAL),
    LIGNITE(CoreOres.Type.ITEM_ONLY, Ore.LIGNITE),
    HALITE(CoreOres.Type.ITEM_ONLY, Ore.HALITE);

    private final Ore TFCOre;
    private final CoreOres.Type type;
    private final String serializedName;

    TFCOres(CoreOres.Type type, Ore TFCOre) {
        this.TFCOre = TFCOre;
        this.type = type;
        this.serializedName = name().toLowerCase(Locale.ROOT);
    }

    public boolean isGraded()
    {
        return type == CoreOres.Type.GRADED;
    }

    public boolean isGem()
    {
        return type == CoreOres.Type.GEM;
    }

    public boolean hasPowder()
    {
        return type != CoreOres.Type.NORMAL && type != CoreOres.Type.ITEM_ONLY;
    }

    public boolean hasBlock()
    {
        return type != CoreOres.Type.ITEM_ONLY;
    }

    @Override
    public Block getOreBlock(RegistryRock rock, @Nullable CoreOres.Grade grade) {

        if (this.type == CoreOres.Type.ITEM_ONLY){
            switch (this){
                case LIGNITE -> {
                    return TFCBlocks.LIGNITE.get();
                }
                case BITUMINOUS_COAL -> {
                    return TFCBlocks.BITUMINOUS_COAL.get();
                }
                case HALITE -> {
                    return TFCBlocks.HALITE.get();
                }
            }
        }

        assert rock instanceof Rock || rock instanceof CoreRocks;

        if (rock instanceof Rock){
            if (grade == null){
                return TFCBlocks.ORES.get(rock).get(this.TFCOre).get();
            } else {
                return TFCBlocks.GRADED_ORES.get(rock).get(this.TFCOre).get(CoreOres.getTFCgrade(grade)).get();
            }
        }

        if (rock instanceof CoreRocks){
            if (grade == null){
                return CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(this.TFCOre).get();
            } else {
                return CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(rock).get(this.TFCOre).get(grade).get();
            }
        }

        return Blocks.DIRT; //nicer than a null?
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
