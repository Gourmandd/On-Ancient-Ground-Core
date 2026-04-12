package net.gourmand.core.datagen.book.guide;

import com.klikli_dev.modonomicon.api.datagen.MultiblockProvider;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.category.CoreOres;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Locale;
import java.util.stream.Stream;

public class CoreMultiblockProvider extends MultiblockProvider {

    public CoreMultiblockProvider(PackOutput packOutput) {
        super(packOutput, AncientGroundCore.MODID);
    }

    @Override
    public void buildMultiblocks() {

        Stream.of(CoreOres.values()).forEach(ore -> {

            if (!ore.hasSpectrumOreType() && ore != CoreOres.METEORIC_IRON){
                addOre(ore.getSerializedName(), CoreTags.Blocks.CORE_ORE_MULTIBLOCK.get(ore));
            }
        });

        Stream.of(Ore.values()).forEach(ore -> {
            addOre(ore.name().toLowerCase(Locale.ROOT), CoreTags.Blocks.TFC_ORE_MULTIBLOCK.get(ore));
        });
    }

    private void addOre(String ore, TagKey<Block> tag){
        this.add(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "ore_preview/" + ore), new DenseMultiblockBuilder()
                .layer("BBB", "B0B", "BBB")
                .tag('B', BlockTags.CANDLES, () -> Blocks.WHITE_CANDLE)
                .block('0', () -> Blocks.SKELETON_SKULL)
                .build(false)
        );
    }
}
