package net.gourmand.core.common;

import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.client.render.blockentity.PlacedItemBlockEntityRenderer;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;

public class PlacedItemModels
{
    public static void init()
    {
        for (CoreClay clayType : CoreClay.values())
        {
            for (CoreClay.ItemType itemType : CoreClay.ItemType.values())
            {
                if (itemType.hasType(clayType) && itemType.hasPlacedModel())
                {
                    add(CoreItems.CERAMICS.get(clayType).get(itemType).get(), cutout("block/ceramic/" + clayType.getSerializedName() + "/" + itemType.getSerializedName()));
                }
            }
        }

        add(SpectrumItems.CHEONG.asItem(), translucent("block/placed_items/cheong"));
        add(SpectrumItems.MERMAIDS_JAM.asItem(), translucent("block/placed_items/mermaids_jam"));
        add(SpectrumItems.MOONSTRUCK_NECTAR.asItem(), translucent("block/placed_items/moonstruck_nectar"));

        add(SpectrumItems.BOTTLE_OF_FADING.asItem(), translucent("block/placed_items/bottle_of_fading"));
        add(SpectrumItems.BOTTLE_OF_FAILING.asItem(), translucent("block/placed_items/bottle_of_failing"));
        add(SpectrumItems.BOTTLE_OF_RUIN.asItem(), translucent("block/placed_items/bottle_of_ruin"));
        add(SpectrumItems.BOTTLE_OF_FORFEITURE.asItem(), translucent("block/placed_items/bottle_of_forfeiture"));
        add(SpectrumItems.BOTTLE_OF_DECAY_AWAY.asItem(), translucent("block/placed_items/bottle_of_decay_away"));

        add(SpectrumItems.BLUE_PIGMENT.asItem(), translucent("block/placed_items/blue_pigment"));
        add(SpectrumItems.LIGHT_BLUE_PIGMENT.asItem(), translucent("block/placed_items/light_blue_pigment"));
        add(SpectrumItems.GREEN_PIGMENT.asItem(), translucent("block/placed_items/green_pigment"));
        add(SpectrumItems.LIME_PIGMENT.asItem(), translucent("block/placed_items/lime_pigment"));
        add(SpectrumItems.GRAY_PIGMENT.asItem(), translucent("block/placed_items/gray_pigment"));
        add(SpectrumItems.LIGHT_GRAY_PIGMENT.asItem(), translucent("block/placed_items/light_gray_pigment"));
        add(SpectrumItems.BLACK_PIGMENT.asItem(), translucent("block/placed_items/black_pigment"));
        add(SpectrumItems.WHITE_PIGMENT.asItem(), translucent("block/placed_items/white_pigment"));
        add(SpectrumItems.ORANGE_PIGMENT.asItem(), translucent("block/placed_items/orange_pigment"));
        add(SpectrumItems.YELLOW_PIGMENT.asItem(), translucent("block/placed_items/yellow_pigment"));
        add(SpectrumItems.RED_PIGMENT.asItem(), translucent("block/placed_items/red_pigment"));
        add(SpectrumItems.PINK_PIGMENT.asItem(), translucent("block/placed_items/pink_pigment"));
        add(SpectrumItems.BROWN_PIGMENT.asItem(), translucent("block/placed_items/brown_pigment"));
        add(SpectrumItems.CYAN_PIGMENT.asItem(), translucent("block/placed_items/cyan_pigment"));
        add(SpectrumItems.MAGENTA_PIGMENT.asItem(), translucent("block/placed_items/magenta_pigment"));
        add(SpectrumItems.PURPLE_PIGMENT.asItem(), translucent("block/placed_items/purple_pigment"));
    }

    private static void add(Item item, PlacedItemBlockEntityRenderer.Provider model) {
        PlacedItemBlockEntityRenderer.MODELS.put(item, model);
    }

    private static PlacedItemBlockEntityRenderer.Provider translucent(String model) {
        return new PlacedItemBlockEntityRenderer.Provider(ModelResourceLocation.standalone(AncientGroundCore.location(model)), RenderType.translucent());
    }

    private static PlacedItemBlockEntityRenderer.Provider cutout(String model) {
        return new PlacedItemBlockEntityRenderer.Provider(ModelResourceLocation.standalone(AncientGroundCore.location(model)), RenderType.cutout());
    }
}
