package net.gourmand.core.registry.category;

import de.dafuqs.spectrum.api.energy.color.InkColor;
import de.dafuqs.spectrum.api.energy.color.InkColors;
import de.dafuqs.spectrum.api.item.GemstoneColor;
import de.dafuqs.spectrum.blocks.crystallarieum.SpectrumClusterBlock;
import de.dafuqs.spectrum.blocks.gemstone.SpectrumBuddingBlock;
import de.dafuqs.spectrum.blocks.gemstone.SpectrumGemstoneBlock;
import de.dafuqs.spectrum.registries.SpectrumSoundEvents;
import de.dafuqs.spectrum.registries.SpectrumSoundTypes;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public enum CoreGemstones implements GemstoneColor, StringRepresentable {

    //CYAN("cyan", InkColors.CYAN, 0),
    AQUAMARINE(InkColors.BLUE, 5),
    SPINEL(InkColors.RED, 6),
    JADE(InkColors.GREEN, 7);

    private final int color;
    private final InkColor inkColor;
    private final int offset;
    private final String serializedName;
    private final Supplier<DeferredHolder<Item, Item>> powder;
    
    CoreGemstones(InkColor inkColor, int offset){
        this.color = inkColor.getColorInt();
        this.inkColor = inkColor;
        this.offset = offset;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        this.powder = () -> CoreItems.GEMSTONE_ITEMS.get(this).get(GemstoneItems.POWDER);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public Item getGemstonePowderItem() {
        return powder.get().get(); // looks odd but its getting value from supplier then getting item from the holder.
    }

    public InkColor getInkColor() {
        return inkColor;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }

    public DyeColor getDyeColor(){
        if (inkColor.getDyeColor().isPresent()){
            return inkColor.getDyeColor().get();
        } else {
            throw new RuntimeException("inkColor did not correspond to any dyeColor");
        }
    }

    public MapColor getMapColor(){
        return getDyeColor().getMapColor();
    }

    public DeferredHolder<Block, Block> getBlock(GemstoneBlocks blockType){
        return CoreBlocks.GEMSTONE_BLOCKS.get(this).get(blockType);
    }

    public enum GemstoneBlocks implements StringRepresentable {

        BLOCK((gem) -> new SpectrumGemstoneBlock(gemstoneBlockSettings(gem), SpectrumSoundEvents.BLOCK_ONYX_BLOCK_HIT, SpectrumSoundEvents.BLOCK_ONYX_BLOCK_CHIME)),
        PILLAR((gem) -> new Block(gemstoneBlockSettings(gem))),
        POWDER_BLOCK((gem) -> new ColoredFallingBlock(new ColorRGBA(gem.getDyeColor().getFireworkColor()), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(gem.getMapColor()))),
        CLUSTER((gem) -> new SpectrumClusterBlock(clusterBlockSettings(gem), SpectrumClusterBlock.GrowthStage.CLUSTER), true),
        LARGE_CLUSTER((gem) -> new SpectrumClusterBlock(clusterBlockSettings(gem), SpectrumClusterBlock.GrowthStage.LARGE), true),
        MEDIUM_CLUSTER((gem) -> new SpectrumClusterBlock(clusterBlockSettings(gem), SpectrumClusterBlock.GrowthStage.MEDIUM), true),
        SMALL_CLUSTER((gem) -> new SpectrumClusterBlock(clusterBlockSettings(gem), SpectrumClusterBlock.GrowthStage.SMALL), true),
        BUDDING_BLOCK((gem) -> new SpectrumBuddingBlock(gemstoneBlockSettings(gem).pushReaction(PushReaction.DESTROY).randomTicks(), CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(SMALL_CLUSTER).get(), CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(MEDIUM_CLUSTER).get(),CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(LARGE_CLUSTER).get(), CoreBlocks.GEMSTONE_BLOCKS.get(gem).get(CLUSTER).get(), SpectrumSoundEvents.BLOCK_ONYX_BLOCK_HIT, SpectrumSoundEvents.BLOCK_ONYX_BLOCK_CHIME));

        private final String serializedName;
        private final Function<CoreGemstones, Block> blockFactory;
        private final boolean isCluster;

        GemstoneBlocks(Function<CoreGemstones, Block> blockFactory) {
            this(blockFactory, false);
        }

        GemstoneBlocks(Function<CoreGemstones, Block> blockFactory, boolean isCluster) {
            this.isCluster = isCluster;
            this.blockFactory = blockFactory;
            this.serializedName = this.name().toLowerCase(Locale.ROOT);
        }

        private static BlockBehaviour.Properties gemstoneBlockSettings(CoreGemstones gem){
            return BlockBehaviour.Properties.of().mapColor(gem.getMapColor()).sound(SoundType.AMETHYST).strength(1.5f);
        }

        private static BlockBehaviour.Properties clusterBlockSettings(CoreGemstones gem){
            return BlockBehaviour.Properties.of().mapColor(gem.getMapColor()).sound(SpectrumSoundTypes.MOONSTONE_CLUSTER).strength(1.5f).lightLevel((state) -> 6);
        }

        public Block create(CoreGemstones gem){
            return blockFactory.apply(gem);
        }

        @Override
        public @NotNull String getSerializedName() {
            return serializedName;
        }

        public boolean isCluster(){
            return isCluster;
        }
    }

    public enum GemstoneItems implements StringRepresentable {
        SHARD((gem) -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON))),
        POWDER((gem) -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

        private final String serializedName;
        private final Function<CoreGemstones, Item> itemFactory;

        GemstoneItems(Function<CoreGemstones, Item> itemFactory) {
            this.itemFactory = itemFactory;
            this.serializedName = this.name().toLowerCase(Locale.ROOT);
        }

        @Override
        public String getSerializedName() {
            return serializedName;
        }

        public Item create(CoreGemstones gem){
            return itemFactory.apply(gem);
        }
    }
}
