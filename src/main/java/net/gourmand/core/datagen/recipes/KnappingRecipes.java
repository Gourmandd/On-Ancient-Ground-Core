package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.dries007.tfc.util.data.KnappingPattern;
import net.dries007.tfc.util.data.KnappingType;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface KnappingRecipes extends Recipes {

    default void knappingRecipes(){

        Stream.of(CoreClay.values()).forEach(clayType -> {

            final Map<CoreClay.ItemType, DeferredHolder<Item, Item>> MAP = CoreItems.CERAMICS.get(clayType);

            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_VESSEL, " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ");
            //clayKnapping(clayType, TFCItems.UNFIRED_LARGE_VESSEL, "X   X", "X   X", "X   X", "X   X", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_JUG, " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_POT, "X   X", "X   X", "X   X", "XXXXX", " XXX ");
            clayKnapping(clayType, "1", MAP.get(CoreClay.ItemType.UNFIRED_BOWL).get(), 2, false, "X   X", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BOWL, 4, "X   X", " XXX ", "     ", "X   X", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_FLOWER_POT, 2, " X X ", " XXX ", "     ", " X X ", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_SPINDLE_HEAD, 1, "  X  ", "XXXXX", "  X  ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_PAN, 1, "X   X", "XXXXX", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BLOWPIPE, 1, " X X ", " X X ", " XXX ", " XXX ", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BRICK, 3, "XXXXX", "     ", "XXXXX", "     ", "XXXXX");

            clayKnapping(clayType, CoreClay.ItemType.INGOT, "XXXX", "X  X", "X  X", "X  X", "XXXX");
            clayKnapping(clayType, CoreClay.ItemType.AXE_HEAD, "X XXX", "    X", "     ", "    X", "X XXX");
            clayKnapping(clayType, CoreClay.ItemType.CHISEL_HEAD, "XX XX", "XX XX", "XX XX", "XX XX", "XX XX");
            clayKnapping(clayType, CoreClay.ItemType.HAMMER_HEAD, "XXXXX", "     ", "     ", "XX XX", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.HOE_HEAD, "XXXXX", "     ", "  XXX", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.JAVELIN_HEAD, "   XX", "    X", "     ", "X   X", "XX XX");
            clayKnapping(clayType, CoreClay.ItemType.KNIFE_BLADE, "XX X", "X  X", "X  X", "X  X", "X  X");
            clayKnapping(clayType, CoreClay.ItemType.MACE_HEAD, "XX XX", "X   X", "X   X", "X   X", "XX XX");
            clayKnapping(clayType, CoreClay.ItemType.PICKAXE_HEAD, "XXXXX", "X   X", " XXX ", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.PROPICK_HEAD, "XXXXX", "    X", " XXX ", " XXXX", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.SAW_BLADE, "  XXX", "   XX", "X   X", "X    ", "XXX  ");
            clayKnapping(clayType, CoreClay.ItemType.SHOVEL_HEAD, "X   X", "X   X", "X   X", "X   X", "XX XX");
            clayKnapping(clayType, CoreClay.ItemType.SWORD_BLADE, "  XXX", "   XX", "X   X", "XX  X", "XXXX ");
            clayKnapping(clayType, CoreClay.ItemType.SCYTHE_BLADE, "XXXXX", "X    ", "    X", "  XXX", "XXXXX");
            clayKnapping(clayType, MAP.get(CoreClay.ItemType.BELL).get(), 1, "XXXXX", "XX XX", "X   X", "X   X", "X   X");
        });
    }

    private void clayKnapping(CoreClay clayType, CoreClay.ItemType output, String... pattern)
    {
        clayKnapping(clayType, "", CoreItems.CERAMICS.get(clayType).get(output).get(), output == CoreClay.ItemType.INGOT ? 2 : 1, true, pattern);
    }

    private void clayKnapping(CoreClay clayType, ItemLike output, String... pattern)
    {
        clayKnapping(clayType, output, 1, pattern);
    }

    private void clayKnapping(CoreClay clayType, CoreClay.ItemType output, int count, String... pattern)
    {
        clayKnapping(clayType, "", CoreItems.CERAMICS.get(clayType).get(output).get(), count, false, pattern);
    }

    private void clayKnapping(CoreClay clayType, ItemLike output, int count, String... pattern)
    {
        clayKnapping(clayType, "", output, count, false, pattern);
    }

    private void clayKnapping(CoreClay clayType, String suffix, ItemLike output, int count, boolean defaultOn, String... pattern)
    {
        add(nameOf(output) + (suffix.isEmpty() ? "" : "_" + suffix), new KnappingRecipe(
                KnappingType.MANAGER.getCheckedReference(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, clayType.getSerializedName())),
                KnappingPattern.from(defaultOn, pattern),
                Optional.empty(),
                new ItemStack(output, count)
        ));
    }
}
