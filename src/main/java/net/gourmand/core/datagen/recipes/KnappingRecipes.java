package net.gourmand.core.datagen.recipes;

import net.dries007.tfc.common.recipes.KnappingRecipe;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.data.KnappingPattern;
import net.dries007.tfc.util.data.KnappingType;
import net.gourmand.core.datagen.providers.BuiltInKnappingTypes;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.Optional;

public interface KnappingRecipes extends Recipes
{

    default void knappingRecipes()
    {

        for (CoreClay clayType : CoreClay.values())
        {

            final Map<CoreClay.ItemType, DeferredHolder<Item, Item>> MAP = CoreItems.CERAMICS.get(clayType);

            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_VESSEL, " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_JUG, " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_POT, "X   X", "X   X", "X   X", "XXXXX", " XXX ");
            clayKnapping(clayType, "1", MAP.get(CoreClay.ItemType.UNFIRED_BOWL).get(), 2, false, "X   X", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BOWL, 4, "X   X", " XXX ", "     ", "X   X", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_FLOWER_POT, 2, " X X ", " XXX ", "     ", " X X ", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_SPINDLE_HEAD, 1, "  X  ", "XXXXX", "  X  ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_PAN, 1, "X   X", "XXXXX", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BLOWPIPE, 1, " X X ", " X X ", " XXX ", " XXX ", " XXX ");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_BRICK, 3, "XXXXX", "     ", "XXXXX", "     ", "XXXXX");
            clayKnapping(clayType, CoreClay.ItemType.UNFIRED_LARGE_VESSEL, 1, "X   X", "X   X", "X   X", "X   X", "XXXXX");

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
        }

        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.AXE_HEAD).get(), "X XXX", "    X", "     ", "    X", "X XXX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.CHISEL_HEAD).get(), "XX XX", "XX XX", "XX XX", "XX XX", "XX XX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.HAMMER_HEAD).get(), "XXXXX", "     ", "     ", "XX XX", "XXXXX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.HOE_HEAD).get(), "XXXXX", "     ", "  XXX", "XXXXX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.JAVELIN_HEAD).get(), "   XX", "    X", "     ", "X   X", "XX XX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.KNIFE_BLADE).get(), "XX X", "X  X", "X  X", "X  X", "X  X");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.MACE_HEAD).get(), "XX XX", "X   X", "X   X", "X   X", "XX XX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.PICKAXE_HEAD).get(), "XXXXX", "X   X", " XXX ", "XXXXX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.PROPICK_HEAD).get(), "XXXXX", "    X", " XXX ", " XXXX", "XXXXX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.SAW_BLADE).get(), "  XXX", "   XX", "X   X", "X    ", "XXX  ");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.SHOVEL_HEAD).get(), "X   X", "X   X", "X   X", "X   X", "XX XX");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.SWORD_BLADE).get(), "  XXX", "   XX", "X   X", "XX  X", "XXXX ");
        fireClayKnapping(CoreItems.UNFIRED_FIRE_MOLDS.get(Metal.ItemType.SCYTHE_BLADE).get(), "XXXXX", "X    ", "    X", "  XXX", "XXXXX");
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
        add(nameOf(output) + (suffix.isEmpty() ? "" : "_" + suffix),
                new KnappingRecipe(
                        KnappingType.MANAGER.getCheckedReference(Helpers.identifier(clayType.getSerializedName())),
                        KnappingPattern.from(defaultOn, pattern),
                        Optional.empty(),
                        new ItemStack(output, count)
                )
        );
    }
    private void fireClayKnapping(ItemLike output, String... pattern)
    {
        add(nameOf(output), new KnappingRecipe(
                KnappingType.MANAGER.getCheckedReference(BuiltInKnappingTypes.FIRE_CLAY),
                KnappingPattern.from(true, pattern),
                Optional.empty(),
                new ItemStack(output, 1)
        ));
    }
}
