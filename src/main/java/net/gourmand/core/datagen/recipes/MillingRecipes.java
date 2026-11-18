package net.gourmand.core.datagen.recipes;

import architectspalette.core.registry.APItems;
import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.SandstoneBlockType;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.crop.Crop;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.items.Food;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class MillingRecipes extends MillingRecipeGen
{

    public static int NORMAL_TIME = 200;
    public static int LONG_TIME = 400;

    public MillingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        millingRecipes();
    }

    public void millingRecipes()
    {
        for (DyeColor color : DyeColor.values())
        {
            create("concrete/" + color.getSerializedName(), b -> b
                    .require(CategoryUtil.Color.TO_CONCRETE.get(color).asItem())
                    .duration(NORMAL_TIME)
                    .output(CategoryUtil.Color.TO_CONCRETE_POWDER.get(color).asItem())
            );
        }

        for (Ore oreType : Ore.values())
        {
            if (oreType.isGem())
            {
                create("gem/" + oreType.name().toLowerCase(Locale.ROOT), b -> b
                        .require(TFCItems.GEMS.get(oreType))
                        .duration(LONG_TIME)
                        .output(TFCItems.ORE_POWDERS.get(oreType))
                );

                create("ore/" + oreType.name().toLowerCase(Locale.ROOT), b -> b
                        .require(TFCItems.ORES.get(oreType))
                        .duration(LONG_TIME)
                        .output(TFCItems.ORE_POWDERS.get(oreType))
                );
            }
        }

        for (SandBlockType color : SandBlockType.values())
        {
            create("raw_sandstone/" + color.name().toLowerCase(Locale.ROOT), b -> b
                    .require(TFCBlocks.SANDSTONE.get(color).get(SandstoneBlockType.RAW))
                    .duration(NORMAL_TIME)
                    .output(TFCBlocks.SAND.get(color))
            );
        }

        standardRecipe("bauxite", CoreItems.ORES.get(CoreOres.BAUXITE).get(), new ItemStack(TFCItems.POWDERS.get(Powder.KAOLINITE), 4));
        standardRecipe("borax", TFCItems.ORES.get(Ore.BORAX).get(), new ItemStack(TFCItems.POWDERS.get(Powder.FLUX), 8));
        standardRecipe("graphite", TFCItems.ORES.get(Ore.GRAPHITE).get(), new ItemStack(TFCItems.ORE_POWDERS.get(Ore.GRAPHITE), 4));
        standardRecipe("halite", TFCItems.ORES.get(Ore.HALITE).get(), new ItemStack(TFCItems.POWDERS.get(Powder.SALT), 4));
        standardRecipe("saltpeter", TFCItems.ORES.get(Ore.SALTPETER).get(), new ItemStack(TFCItems.ORE_POWDERS.get(Ore.SALTPETER), 4));
        standardRecipe("sulfur", TFCItems.ORES.get(Ore.SULFUR).get(), new ItemStack(TFCItems.ORE_POWDERS.get(Ore.SULFUR), 4));
        standardRecipe("charcoal", Items.CHARCOAL, new ItemStack(TFCItems.POWDERS.get(Powder.CHARCOAL), 4));
        standardRecipe("flux", TFCTags.Items.FLUXSTONE, new ItemStack(TFCItems.POWDERS.get(Powder.FLUX), 4));
        standardRecipe("olive_paste", TFCItems.FOOD.get(Food.OLIVE).asItem(), new ItemStack(TFCItems.OLIVE_PASTE, 2));
        standardRecipe("canola_paste", TFCItems.CROP_SEEDS.get(Crop.CANOLA).asItem(), new ItemStack(TFCItems.CANOLA_PASTE, 2));

        standardRecipe("bone_meal_from_block", Blocks.BONE_BLOCK.asItem(), new ItemStack(Items.BONE_MEAL, 9));
        standardRecipe("bone_meal_from_bone", Items.BONE, new ItemStack(Items.BONE_MEAL, 3));
        standardRecipe("bone_meal_from_withered_bone", APItems.WITHERED_BONE.get(), new ItemStack(Items.BONE_MEAL, 3));
    }

    private void standardRecipe(String name, Item ingredient, ItemStack output)
    {
        create(name, b -> b
                .require(ingredient)
                .duration(NORMAL_TIME)
                .output(output)
        );
    }

    private void standardRecipe(String name, TagKey<Item> ingredient, ItemStack output)
    {
        create(name, b -> b
                .require(ingredient)
                .duration(NORMAL_TIME)
                .output(output)
        );
    }
}
