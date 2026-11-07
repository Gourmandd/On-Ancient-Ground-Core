package net.gourmand.core.datagen.recipes;

import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CategoryUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class SequencedAssemblyRecipes extends SequencedAssemblyRecipeGen
{

    public SequencedAssemblyRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, AncientGroundCore.MOD_ID);
        sequencedAssemblyRecipes();
    }

    public void sequencedAssemblyRecipes()
    {

        for (DyeColor color : DyeColor.values())
        {
            create("sequenced_assembly/lens/" + color.getSerializedName(), b -> b
                    .require(CoreBlocks.COLORED_MOLTEN_GLASS.get(color).get())
                    .transitionTo(CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(color))
                    .loops(3)
                    .addStep(DeployerApplicationRecipe::new, r -> r
                            .require(TFCItems.POWDERS.get(Powder.SODA_ASH))
                    )
                    .addStep(DeployerApplicationRecipe::new, r -> r
                            .require(TFCBlocks.BELLOWS).toolNotConsumed()
                    )
                    .addStep(DeployerApplicationRecipe::new, r -> r
                            .require(TFCItems.PADDLE).toolNotConsumed()
                    )
                    .addStep(DeployerApplicationRecipe::new, r -> r
                            .require(TFCItems.WOOL_CLOTH).toolNotConsumed()
                    )
                    .addStep(DeployerApplicationRecipe::new, r -> r
                            .require(TFCItems.GEM_SAW).toolNotConsumed()
                    )
                    .addOutput(CoreItems.COLORED_LENS.get(color).get(), 1)
            );
        }
        create("sequenced_assembly/lens/clear", b -> b
                .require(CoreBlocks.CLEAR_MOLTEN_GLASS.get())
                .transitionTo(CategoryUtil.Glass.COLOR_TO_VANILLA_GLASS_PANE.get(DyeColor.WHITE))
                .loops(3)
                .addStep(DeployerApplicationRecipe::new, r -> r
                        .require(TFCItems.POWDERS.get(Powder.SODA_ASH))
                )
                .addStep(DeployerApplicationRecipe::new, r -> r
                        .require(TFCBlocks.BELLOWS).toolNotConsumed()
                )
                .addStep(DeployerApplicationRecipe::new, r -> r
                        .require(TFCItems.PADDLE).toolNotConsumed()
                )
                .addStep(DeployerApplicationRecipe::new, r -> r
                        .require(TFCItems.WOOL_CLOTH).toolNotConsumed()
                )
                .addStep(DeployerApplicationRecipe::new, r -> r
                        .require(TFCItems.GEM_SAW).toolNotConsumed()
                )
                .addOutput(TFCItems.LENS, 1)
        );

        createRefinementRecipe("bloom", TFCItems.RAW_IRON_BLOOM.get(), TFCItems.REFINED_IRON_BLOOM.asItem());
        createRefinementRecipe("wrought_iron", TFCItems.REFINED_IRON_BLOOM.asItem(), TFCItems.METAL_ITEMS.get(Metal.WROUGHT_IRON).get(Metal.ItemType.INGOT).asItem());
        createRefinementRecipe("steel", TFCItems.METAL_ITEMS.get(Metal.HIGH_CARBON_STEEL).get(Metal.ItemType.INGOT).asItem(), TFCItems.METAL_ITEMS.get(Metal.STEEL).get(Metal.ItemType.INGOT).asItem());
        createRefinementRecipe("black_steel", TFCItems.METAL_ITEMS.get(Metal.HIGH_CARBON_BLACK_STEEL).get(Metal.ItemType.INGOT).asItem(), TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.INGOT).asItem());
        createRefinementRecipe("red_steel", TFCItems.METAL_ITEMS.get(Metal.HIGH_CARBON_RED_STEEL).get(Metal.ItemType.INGOT).asItem(), TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT).asItem());
        createRefinementRecipe("blue_steel", TFCItems.METAL_ITEMS.get(Metal.HIGH_CARBON_BLUE_STEEL).get(Metal.ItemType.INGOT).asItem(), TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.INGOT).asItem());
    }

    private void createRefinementRecipe(String name, Item input, Item output)
    {
        create("sequenced_assembly/refine/" + name, b -> b
                .require(input)
                .transitionTo(input)
                .loops(5)
                .addStep(PressingRecipe::new, r -> r)
                .addOutput(output, 1)
        );
    }
}
