package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class DeployingRecipes extends DeployingRecipeGen {

    public DeployingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AncientGroundCore.MODID);
        compactingRecipes();
    }

    public void compactingRecipes(){


        Stream.of(CoreClay.values()).forEach(clayType -> {

            final DeferredHolder<Item, Item> CLAY_BALL = clayType.getClayBallItem();
            final DeferredHolder<Block, Block> CLAY_BLOCK = clayType.getClayBlock();

            Stream.of(CoreClay.ItemType.values()).forEach(itemType -> {

                if (itemType.getType() == CoreClay.ItemPartType.UNFIRED_MOLD && itemType.hasType(clayType)){

                    final DeferredHolder<Item, Item> MOLD = CoreItems.CERAMICS.get(clayType).get(itemType);

                    create(MOLD.getId().getPath(), b -> b.require(CLAY_BLOCK.get())
                            .toolNotConsumed()
                            .require(getMoldOutputTag(itemType))
                            .output(MOLD.get())
                    );
                }
            });
        });
    }

    private TagKey<Item> getMoldOutputTag(CoreClay.ItemType itemType){

        if (itemType.getType() != CoreClay.ItemPartType.UNFIRED_MOLD){
            return null;
        }

        switch (itemType){
            case PICKAXE_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.PICKAXE_HEAD);
            }
            case PROPICK_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.PROPICK_HEAD);
            }
            case AXE_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.AXE_HEAD);
            }
            case SHOVEL_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SHOVEL_HEAD);
            }
            case HOE_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.HOE_HEAD);
            }
            case CHISEL_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.CHISEL_HEAD);
            }
            case HAMMER_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.HAMMER_HEAD);
            }
            case SAW_BLADE -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SAW_BLADE);
            }
            case JAVELIN_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.JAVELIN_HEAD);
            }
            case SWORD_BLADE -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SWORD_BLADE);
            }
            case MACE_HEAD -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.MACE_HEAD);
            }
            case KNIFE_BLADE -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.KNIFE_BLADE);
            }
            case SCYTHE_BLADE -> {
                return CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SCYTHE_BLADE);
            }
            case BELL -> {
                return CoreTags.Items.BELLS;
            }
            case INGOT -> {
                return Tags.Items.INGOTS;
            }
            case null, default -> {
                throw new AssertionError("CoreClay.ItemType has no corresponding tag set.");
            }
        }
    }
}
