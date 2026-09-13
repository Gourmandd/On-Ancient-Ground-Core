package net.gourmand.core.datagen.providers;

import architectspalette.core.registry.APTags;
import de.dafuqs.spectrum.SpectrumCommon;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.Accessors;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BuiltinItemTags extends TagsProvider<Item> implements Accessors
{
    final TagKey<Item> SHIMMERSTONE_ORES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpectrumCommon.MOD_ID, "shimmerstone_ores"));
    final TagKey<Item> AZURITE_ORES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpectrumCommon.MOD_ID, "azurite_ores"));
    final TagKey<Item> UPRIGHT_ON_BELT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("create", "upright_on_belt"));

    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinItemTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.ITEM, lookup, AncientGroundCore.MOD_ID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        generateMetal();
        generateOre();
        generateRock();
        generateWood();
        generateGemstones();
        generateCeramics();
        generateMisc();
        generateMultiblock();
    }

    private void generateMetal()
    {
        this.tag(CoreTags.Items.BELLS).add(getKey(TFCBlocks.BRASS_BELL.asItem())).add(getKey(TFCBlocks.BRONZE_BELL.asItem())).add(getKey(Blocks.BELL));

        for (CoreMetals.MetalType metalType : CoreMetals.MetalType.values())
        {
            this.tag(Tags.Items.INGOTS).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT).getKey());
            this.tag(TFCTags.Items.DOUBLE_INGOTS).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT).getKey());
            this.tag(TFCTags.Items.SHEETS).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET).getKey());
            this.tag(TFCTags.Items.DOUBLE_SHEETS).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET).getKey());
            this.tag(Tags.Items.RODS).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.ROD).getKey());

            this.tag(CoreTags.Items.METAL_INGOTS.get(metalType)).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.INGOT).getKey());
            this.tag(CoreTags.Items.METAL_DOUBLE_INGOTS.get(metalType)).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_INGOT).getKey());
            this.tag(CoreTags.Items.METAL_SHEETS.get(metalType)).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.SHEET).getKey());
            this.tag(CoreTags.Items.METAL_DOUBLE_SHEETS.get(metalType)).add(CoreItems.METAL_ITEMS.get(metalType).get(Metal.ItemType.DOUBLE_SHEET).getKey());
        }

        CategoryUtil.getTFCToolMetals().forEach(metal ->
                CategoryUtil.getTFCToolHeads().forEach(tool -> this.tag(CoreTags.Items.TOOL_HEADS.get(tool)).add(TFCItems.METAL_ITEMS.get(metal).get(tool).holder().getKey()))
        );
    }

    private void generateOre()
    {
        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.hasOres())
            {
                for (Ore oreType : Ore.values())
                {
                    if (oreType.hasBlock())
                    {
                        if (oreType.isGraded())
                        {
                            addGradedOreTags(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES, oreType, rockType);
                        }
                        else
                        {
                            addOreTags(CoreBlocks.CUSTOM_ROCK_TFC_ORES, oreType, rockType);
                        }
                    }
                }
            }
        }

        for (CoreOres oreType : CoreOres.values())
        {
            // if it's an ore such as bituminous coal
            if (!oreType.hasBlock())
            {
                this.tag(Tags.Items.ORES).add(getKey(CoreBlocks.BASIC_ORES.get(oreType)));
            }
            else
            {
                for (CoreRocks rockType : CoreRocks.values())
                {
                    if (rockType.hasOres())
                    {
                        if (oreType.isGraded())
                        {
                            addGradedOreTags(CoreBlocks.CUSTOM_ROCK_GRADED_ORES, oreType, rockType);
                        }
                        else
                        {
                            addOreTags(CoreBlocks.CUSTOM_ROCK_ORES, oreType, rockType);
                        }
                    }
                }
                for (Rock rockType : Rock.values())
                {
                    if (oreType.isGraded())
                    {
                        addGradedOreTags(CoreBlocks.GRADED_ORES, oreType, rockType);
                    }
                    else
                    {
                        addOreTags(CoreBlocks.ORES, oreType, rockType);
                    }
                }
            }
        }

        for (Rock rockType : Rock.values())
        {
            this.tag(AZURITE_ORES).add(getKey(CoreBlocks.ORES.get(rockType).get(CoreOres.AZURITE)));
            this.tag(SHIMMERSTONE_ORES).add(getKey(CoreBlocks.ORES.get(rockType).get(CoreOres.SHIMMERSTONE)));
        }

        for (CoreRocks rockType : CoreRocks.values())
        {
            if (rockType.hasOres())
            {
                this.tag(AZURITE_ORES).add(getKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rockType).get(CoreOres.AZURITE)));
                this.tag(SHIMMERSTONE_ORES).add(getKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rockType).get(CoreOres.SHIMMERSTONE)));
                add(CoreBlocks.ORE_DEPOSITS.get(rockType), List.of(TFCTags.Items.ORE_DEPOSITS));
            }
        }
    }

    private void generateRock()
    {
        for (CoreRocks rockType : CoreRocks.values())
        {

            this.tag(Tags.Items.COBBLESTONES_NORMAL).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.COBBLE)));
            this.tag(Tags.Items.COBBLESTONES_MOSSY).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE)));

            this.tag(TFCTags.Items.STONES_HARDENED).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.HARDENED)));

            this.tag(Tags.Items.STONES).add(getKey(CategoryUtil.CoreRock.TO_RAW_BLOCK.get(rockType).value()));

            this.tag(Tags.Items.COBBLESTONES)
                    .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.COBBLE)))
                    .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE)));

            this.tag(ItemTags.STAIRS)
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.COBBLE).stair()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE).stair()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_BRICKS).stair()));

            this.tag(ItemTags.SLABS)
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.COBBLE).slab()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE).slab()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_BRICKS).slab()));

            this.tag(ItemTags.WALLS)
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.COBBLE).wall()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_COBBLE).wall()))
                    .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.MOSSY_BRICKS).wall()));

            this.tag(ItemTags.STONE_BRICKS).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_BRICKS)));
            this.tag(TFCTags.Items.AQUEDUCTS).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.AQUEDUCT)));
            this.tag(Tags.Items.GRAVELS).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.GRAVEL)));

            this.tag(TFCTags.Items.STONES_LOOSE)
                    .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.LOOSE)))
                    .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.MOSSY_LOOSE)));

            if (rockType.hasVariants())
            {
                this.tag(ItemTags.STONE_BRICKS)
                        .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.BRICKS)))
                        .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.CRACKED_BRICKS)))
                        .add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.CHISELED)));

                this.tag(TFCTags.Items.STONES_SMOOTH).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.SMOOTH)));

                this.tag(ItemTags.STAIRS)
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.BRICKS).stair()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.CRACKED_BRICKS).stair()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.SMOOTH).stair()));

                this.tag(ItemTags.SLABS)
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.BRICKS).slab()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.CRACKED_BRICKS).slab()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.SMOOTH).slab()));

                this.tag(ItemTags.WALLS)
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.BRICKS).wall()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.CRACKED_BRICKS).wall()))
                        .add(getKey(CoreBlocks.ROCK_DECORATIONS.get(rockType).get(Rock.BlockType.SMOOTH).wall()));

                this.tag(TFCTags.Items.STONES_PRESSURE_PLATES).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.PRESSURE_PLATE)));

                this.tag(ItemTags.STONE_BUTTONS).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.BUTTON)));
                this.tag(ItemTags.BUTTONS).add(getKey(CoreBlocks.ROCK_BLOCKS.get(rockType).get(Rock.BlockType.BUTTON)));
            }
        }

        add(CoreBlocks.MORTARED_TFC_COBBLE, List.of(CoreTags.Items.MORTARED_COBBLE));
        add(CoreBlocks.MORTARED_CUSTOM_COBBLE, List.of(CoreTags.Items.MORTARED_COBBLE));
    }

    private void generateWood()
    {
        for (SpectrumWood woodType : SpectrumWood.values())
        {
            this.tag(TFCTags.Items.LUMBER).add(getKey(CoreItems.LUMBER.get(woodType).get()));

            this.tag(TFCTags.Items.TWIGS).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.TWIG)));
            this.tag(TFCTags.Items.CAN_BE_LIT_ON_TORCH).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.TWIG)));
            this.tag(Tags.Items.RODS_WOODEN).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.TWIG)));

            this.tag(TFCTags.Items.SUPPORT_BEAMS).add(getKey(CoreItems.SUPPORTS.get(woodType).get()));
            this.tag(TFCTags.Items.SCRIBING_TABLES).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.SCRIBING_TABLE)));
            this.tag(TFCTags.Items.SEWING_TABLES).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.SEWING_TABLE)));
            this.tag(TFCTags.Items.LOOMS).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.LOOM)));
            this.tag(TFCTags.Items.TOOL_RACKS).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.TOOL_RACK)));
            this.tag(TFCTags.Items.SLUICES).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.SLUICE)));
            this.tag(TFCTags.Items.BARRELS).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.BARREL)));
            this.tag(TFCTags.Items.MINECART_HOLDABLE).add(getKey(CoreBlocks.DEEPER_DOWN_WOODS.get(woodType).get(Wood.BlockType.BARREL)));
        }

        add(CoreBlocks.SPECTRUM_WOOD_BOARDS, List.of(APTags.BOARDS_ITEM));
        add(CoreBlocks.TFC_WOOD_BOARDS, List.of(APTags.BOARDS_ITEM));
        add(CoreBlocks.AFC_WOOD_BOARDS, List.of(APTags.BOARDS_ITEM));
    }

    private void generateGemstones()
    {
        //TODO: add relevant gemstone related tags, ie: clusters, budding blocks.
    }

    private void generateCeramics()
    {
        for (CoreClay clayType : CoreClay.values())
        {
            for (CoreClay.ItemType itemType : CoreClay.ItemType.values())
            {
                if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD && itemType != CoreClay.ItemType.INGOT)
                {
                    this.tag(TFCTags.Items.UNFIRED_MOLDS).add(CoreItems.CERAMICS.get(clayType).get(itemType).getKey());
                    this.tag(CoreTags.Items.CLAY_RECYCLING_5.get(clayType)).add(CoreItems.CERAMICS.get(clayType).get(itemType).getKey());
                }
            }

            this.tag(CoreTags.Items.CLAY_BRICKS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BRICK).getKey());
            this.tag(Tags.Items.BRICKS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.BRICK).getKey());

            this.tag(TFCTags.Items.VESSELS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.VESSEL).getKey());
            this.tag(CoreTags.Items.UNFIRED_VESSELS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_VESSEL).getKey());
            this.tag(TFCTags.Items.UNFIRED_VESSELS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_VESSEL).getKey());
            this.tag(TFCTags.Items.FLUID_ITEM_INGREDIENT_EMPTY_CONTAINERS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.JUG).getKey());
            this.tag(UPRIGHT_ON_BELT).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.JUG).getKey());

            this.tag(TFCTags.Items.LARGE_VESSELS)
                    .add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_LARGE_VESSEL).getKey())
                    .add(getKey(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(CoreClay.BlockType.LARGE_VESSEL)));

            this.tag(TFCTags.Items.FIRED_VESSELS).add(getKey(CoreBlocks.CERAMIC_BLOCKS.get(clayType).get(CoreClay.BlockType.LARGE_VESSEL)));
            this.tag(TFCTags.Items.UNFIRED_LARGE_VESSELS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_LARGE_VESSEL).getKey());

            this.tag(CoreTags.Items.CLAY_RECYCLING_5.get(clayType)).add(
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_VESSEL).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_JUG).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_POT).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_SPINDLE_HEAD).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_PAN).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BLOWPIPE).getKey()
            );

            this.tag(CoreTags.Items.CLAY_RECYCLING_1.get(clayType)).add(
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BRICK).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BOWL).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_FLOWER_POT).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.INGOT).getKey()
            );

            this.tag(TFCTags.Items.UNFIRED_POTTERY).add(
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BRICK).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BOWL).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_FLOWER_POT).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_VESSEL).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_JUG).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_POT).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_SPINDLE_HEAD).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_PAN).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_BLOWPIPE).getKey(),
                    CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.UNFIRED_LARGE_VESSEL).getKey()
            );

            if (!clayType.hasReducedSet())
            {
                this.tag(CoreTags.Items.CLAY_BALLS).add(CoreItems.CERAMICS.get(clayType).get(CoreClay.ItemType.CLAY_BALL).getKey());
            }
        }

        this.tag(CoreTags.Items.UNFIRED_VESSELS).add(TFCItems.UNFIRED_VESSEL.key());

        for (DyeColor color : DyeColor.values())
        {
            this.tag(CoreTags.Items.UNFIRED_VESSELS).add(TFCItems.UNFIRED_GLAZED_VESSELS.get(color).key());
        }
    }

    private void generateMisc()
    {
        this.tag(CoreTags.Items.KAOLIN_CLAYS)
                .add(getKey(TFCBlocks.KAOLIN_CLAY_GRASS.asItem()))
                .add(getKey(TFCBlocks.WHITE_KAOLIN_CLAY.asItem()))
                .add(getKey(TFCBlocks.PINK_KAOLIN_CLAY.asItem()))
                .add(getKey(TFCBlocks.RED_KAOLIN_CLAY.asItem()));

        this.tag(CoreTags.Items.MOLTEN_GLASS).add(getKey(CoreBlocks.CLEAR_MOLTEN_GLASS.get()));
        add(CoreBlocks.COLORED_MOLTEN_GLASS, List.of(CoreTags.Items.MOLTEN_GLASS));

        this.tag(CoreTags.Items.LEAD_GLASS).add(getKey(CoreBlocks.CLEAR_LEAD_GLASS.get()));
        add(CoreBlocks.COLOURED_LEAD_GLASS, List.of(CoreTags.Items.LEAD_GLASS));

        this.tag(CoreTags.Items.LEAD_GLASS_PANES).add(getKey(CoreBlocks.CLEAR_LEAD_GLASS_PANE.get()));
        add(CoreBlocks.COLOURED_LEAD_GLASS_PANE, List.of(CoreTags.Items.LEAD_GLASS_PANES));

        this.tag(Tags.Items.HIDDEN_FROM_RECIPE_VIEWERS).add(CoreItems.ORES.get(CoreOres.ANTHRACITE).getKey());

        add(CoreBlocks.FRUIT_TREE_LEAVES, List.of(ItemTags.LEAVES));

        add(CoreBlocks.FRUIT_TREE_SAPLINGS, List.of(ItemTags.SAPLINGS));

        add(CoreBlocks.WILD_CROPS, List.of(TFCTags.Items.WILD_CROPS));
    }

    private void generateMultiblock()
    {
        addMultiBlockOre(Ore.NATIVE_COPPER, List.of(Rock.ANDESITE, Rock.BASALT, Rock.RHYOLITE, Rock.DACITE, CoreRocks.PHONOLITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.MALACHITE, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.DOLOMITE, Rock.MARBLE));
        addMultiBlockOre(Ore.TETRAHEDRITE, List.of(Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, Rock.QUARTZITE, Rock.SCHIST, Rock.PHYLLITE, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE));

        addMultiBlockOre(CoreOres.GALENA, List.of(Rock.LIMESTONE, Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, CoreRocks.BLUESCHIST, CoreRocks.PERIDOTITE, CoreRocks.PHONOLITE, CoreRocks.KOMATIITE));

        addMultiBlockOre(Ore.NATIVE_GOLD, List.of(Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, CoreRocks.PERIDOTITE, Rock.BASALT, Rock.RHYOLITE, Rock.ANDESITE));
        addMultiBlockOre(Ore.NATIVE_SILVER, List.of(Rock.GRANITE, Rock.DIORITE, Rock.SCHIST, Rock.GNEISS, CoreRocks.PERIDOTITE, CoreRocks.KOMATIITE));

        addMultiBlockOre(Ore.CASSITERITE, List.of(Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, CoreRocks.PERIDOTITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.GARNIERITE, List.of(Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, CoreRocks.PERIDOTITE));

        addMultiBlockOre(Ore.HEMATITE, List.of(Rock.ANDESITE, Rock.BASALT, Rock.RHYOLITE, Rock.DACITE, CoreRocks.PHONOLITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.MAGNETITE, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE, CoreRocks.ARGILLITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE));
        addMultiBlockOre(Ore.LIMONITE, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE, CoreRocks.ARGILLITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE));

        addMultiBlockOre(Ore.BISMUTHINITE, List.of(Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE));
        addMultiBlockOre(Ore.SPHALERITE, List.of(Rock.GRANITE, Rock.DIORITE, Rock.GABBRO, Rock.BASALT, Rock.RHYOLITE, Rock.ANDESITE, CoreRocks.KOMATIITE, CoreRocks.BLUESCHIST, CoreRocks.PERIDOTITE));

        addMultiBlockOre(Ore.LIGNITE, TFCBlocks.LIGNITE.get());
        addMultiBlockOre(Ore.BITUMINOUS_COAL, TFCBlocks.BITUMINOUS_COAL.get());
        addMultiBlockOre(CoreOres.ANTHRACITE, CoreBlocks.BASIC_ORES.get(CoreOres.ANTHRACITE).get());

        addMultiBlockOre(CoreOres.BAUXITE, CoreBlocks.BASIC_ORES.get(CoreOres.BAUXITE).get());
        addMultiBlockOre(CoreOres.METEORIC_IRON, CoreBlocks.BASIC_ORES.get(CoreOres.METEORIC_IRON).get());
        addMultiBlockOre(CoreOres.QUARTZ, CoreBlocks.BASIC_ORES.get(CoreOres.QUARTZ).get());

        addMultiBlockOre(Ore.SULFUR, List.of(Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, Rock.QUARTZITE, Rock.SCHIST, Rock.PHYLLITE, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE));
        addMultiBlockOre(Ore.SALTPETER, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE, CoreRocks.ARGILLITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE));
        addMultiBlockOre(Ore.SYLVITE, List.of(Rock.CHERT, Rock.CLAYSTONE, Rock.SHALE, CoreRocks.ARKOSE, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE));
        addMultiBlockOre(Ore.BORAX, List.of(Rock.CHERT, Rock.CLAYSTONE, Rock.SHALE, CoreRocks.ARKOSE, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE));
        addMultiBlockOre(Ore.HALITE, TFCBlocks.HALITE.get());

        addMultiBlockOre(Ore.CINNABAR, List.of(Rock.GNEISS, Rock.SCHIST, Rock.PHYLLITE, Rock.QUARTZITE, CoreRocks.SERPENTINE, CoreRocks.BLUESCHIST));
        addMultiBlockOre(Ore.CRYOLITE, List.of(Rock.GRANITE, Rock.DIORITE, CoreRocks.BLUESCHIST, CoreRocks.PERIDOTITE));
        addMultiBlockOre(Ore.GRAPHITE, List.of(Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, Rock.QUARTZITE, Rock.SCHIST, CoreRocks.BLUESCHIST, CoreRocks.SERPENTINE));

        addMultiBlockOre(Ore.GYPSUM, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE));

        addMultiBlockOre(Ore.DIAMOND, List.of(Rock.GABBRO));
        addMultiBlockOre(Ore.AMETHYST, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, CoreRocks.ARGILLITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE, Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, Rock.QUARTZITE));
        addMultiBlockOre(Ore.TOPAZ, List.of(Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, CoreRocks.SERPENTINE, Rock.GRANITE, Rock.GABBRO, Rock.BASALT, Rock.RHYOLITE, Rock.ANDESITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.PYRITE, List.of(Rock.GRANITE, Rock.GABBRO, Rock.BASALT, Rock.RHYOLITE, Rock.ANDESITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.EMERALD, List.of(Rock.GRANITE, Rock.GABBRO, Rock.DIORITE, CoreRocks.PERIDOTITE));
        addMultiBlockOre(Ore.RUBY, List.of(Rock.GNEISS, Rock.SCHIST));
        addMultiBlockOre(Ore.SAPPHIRE, List.of(Rock.MARBLE, Rock.SCHIST, Rock.GNEISS, CoreRocks.SERPENTINE, Rock.GRANITE, Rock.GABBRO, Rock.BASALT, Rock.RHYOLITE, Rock.ANDESITE, CoreRocks.KOMATIITE));
        addMultiBlockOre(Ore.LAPIS_LAZULI, List.of(Rock.LIMESTONE, Rock.MARBLE, CoreRocks.ARKOSE, CoreRocks.SANDSTONE, CoreRocks.RED_SANDSTONE));
        addMultiBlockOre(Ore.OPAL, List.of(Rock.LIMESTONE, Rock.CHALK, Rock.CHERT, Rock.TUFF, Rock.CLAYSTONE, Rock.CONGLOMERATE, Rock.DOLOMITE, CoreRocks.ARGILLITE, CoreRocks.BRECCIA, CoreRocks.ARGILLITE, CoreRocks.TRAVERTINE, CoreRocks.SANDSTONE));

    }

    //region methods
    protected void addMultiBlockOre(CoreOres ore, Block block)
    {
        this.tag(CoreTags.Items.CORE_ORE_MULTIBLOCK.get(ore)).add(getKey(block));
    }

    protected void addMultiBlockOre(Ore ore, Block block)
    {
        this.tag(CoreTags.Items.TFC_ORE_MULTIBLOCK.get(ore)).add(getKey(block));
    }

    protected void addMultiBlockOre(CoreOres ore, List<RegistryRock> rocks)
    {

        for (RegistryRock rock : rocks)
        {
            if (rock instanceof CoreRocks)
            {
                if (ore.isGraded())
                {
                    this.tag(CoreTags.Items.CORE_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.CUSTOM_ROCK_GRADED_ORES.get(rock).get(ore).get(CoreOres.Grade.RICH).get()));
                }
                else
                {
                    this.tag(CoreTags.Items.CORE_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.CUSTOM_ROCK_ORES.get(rock).get(ore).get()));
                }
            }
            if (rock instanceof Rock)
            {
                if (ore.isGraded())
                {
                    this.tag(CoreTags.Items.CORE_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.GRADED_ORES.get(rock).get(ore).get(CoreOres.Grade.RICH).get()));
                }
                else
                {
                    this.tag(CoreTags.Items.CORE_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.ORES.get(rock).get(ore)));
                }
            }
        }
    }

    protected void addMultiBlockOre(Ore ore, List<RegistryRock> rocks)
    {

        for (RegistryRock rock : rocks)
        {
            if (rock instanceof CoreRocks)
            {
                if (ore.isGraded())
                {
                    this.tag(CoreTags.Items.TFC_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.CUSTOM_ROCK_TFC_GRADED_ORES.get(rock).get(ore).get(CoreOres.Grade.RICH).get()));
                }
                else
                {
                    this.tag(CoreTags.Items.TFC_ORE_MULTIBLOCK.get(ore)).add(getKey(CoreBlocks.CUSTOM_ROCK_TFC_ORES.get(rock).get(ore).get()));
                }
            }
            if (rock instanceof Rock)
            {
                if (ore.isGraded())
                {
                    this.tag(CoreTags.Items.TFC_ORE_MULTIBLOCK.get(ore)).add(getKey(TFCBlocks.GRADED_ORES.get(rock).get(ore).get(Ore.Grade.RICH).get()));
                }
                else
                {
                    this.tag(CoreTags.Items.TFC_ORE_MULTIBLOCK.get(ore)).add(getKey(TFCBlocks.ORES.get(rock).get(ore).get()));
                }
            }
        }
    }

    protected void add(Map<?, DeferredHolder<Block, Block>> map, List<TagKey<Item>> tags)
    {
        for (DeferredHolder<Block, ?> block : map.values())
        {
            for (TagKey<Item> tag : tags)
            {
                this.tag(tag).add(getKey(block));
            }
        }
    }

    protected ResourceKey<Item> getKey(DeferredHolder<Block, ? extends Block> block)
    {
        return block.get().asItem().builtInRegistryHolder().key();
    }

    protected ResourceKey<Item> getKey(Block block)
    {
        return block.asItem().builtInRegistryHolder().key();
    }

    protected ResourceKey<Item> getKey(Item item)
    {
        return item.builtInRegistryHolder().key();
    }

    private <T1 extends RegistryRock, T2> void addOreTags(Map<T1, Map<T2, DeferredHolder<Block, Block>>> map, T2 ore, T1 rock)
    {
        DeferredHolder<Block, Block> block = map.get(rock).get(ore);
        this.tag(Tags.Items.ORES).add(getKey(block));
    }

    private <T1 extends RegistryRock, T2, T3 extends CoreOres.Grade> void addGradedOreTags(Map<T1, Map<T2, Map<T3, DeferredHolder<Block, Block>>>> map, T2 ore, T1 rock)
    {

        for (CoreOres.Grade grade : CoreOres.Grade.values())
        {
            DeferredHolder<Block, Block> block = map.get(rock).get(ore).get(grade);
            this.tag(Tags.Items.ORES).add(getKey(block));
        }
    }
    //endregion
}
