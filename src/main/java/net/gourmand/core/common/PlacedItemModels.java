package net.gourmand.core.common;

import de.dafuqs.spectrum.registries.SpectrumItems;
import net.dries007.tfc.client.render.blockentity.PlacedItemBlockEntityRenderer;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreItems;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelResourceLocation;

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
                    PlacedItemBlockEntityRenderer.MODELS.put(CoreItems.CERAMICS.get(clayType).get(itemType).get(), cutout("block/ceramic/" + clayType.getSerializedName() + "/" + itemType.getSerializedName()));
                }
            }
        }

        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.CHEONG.asItem(), translucent("block/placed_items/cheong"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.MERMAIDS_JAM.asItem(), translucent("block/placed_items/mermaids_jam"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.MOONSTRUCK_NECTAR.asItem(), translucent("block/placed_items/moonstruck_nectar"));

        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BOTTLE_OF_FADING.asItem(), translucent("block/placed_items/bottle_of_fading"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BOTTLE_OF_FAILING.asItem(), translucent("block/placed_items/bottle_of_failing"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BOTTLE_OF_RUIN.asItem(), translucent("block/placed_items/bottle_of_ruin"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BOTTLE_OF_FORFEITURE.asItem(), translucent("block/placed_items/bottle_of_forfeiture"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BOTTLE_OF_DECAY_AWAY.asItem(), translucent("block/placed_items/bottle_of_decay_away"));

        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BLUE_PIGMENT.asItem(), translucent("block/placed_items/blue_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.LIGHT_BLUE_PIGMENT.asItem(), translucent("block/placed_items/light_blue_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.GREEN_PIGMENT.asItem(), translucent("block/placed_items/green_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.LIME_PIGMENT.asItem(), translucent("block/placed_items/lime_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.GRAY_PIGMENT.asItem(), translucent("block/placed_items/gray_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.LIGHT_GRAY_PIGMENT.asItem(), translucent("block/placed_items/light_gray_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BLACK_PIGMENT.asItem(), translucent("block/placed_items/black_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.WHITE_PIGMENT.asItem(), translucent("block/placed_items/white_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.ORANGE_PIGMENT.asItem(), translucent("block/placed_items/orange_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.YELLOW_PIGMENT.asItem(), translucent("block/placed_items/yellow_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.RED_PIGMENT.asItem(), translucent("block/placed_items/red_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.PINK_PIGMENT.asItem(), translucent("block/placed_items/pink_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.BROWN_PIGMENT.asItem(), translucent("block/placed_items/brown_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.CYAN_PIGMENT.asItem(), translucent("block/placed_items/cyan_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.MAGENTA_PIGMENT.asItem(), translucent("block/placed_items/magenta_pigment"));
        PlacedItemBlockEntityRenderer.MODELS.put(SpectrumItems.PURPLE_PIGMENT.asItem(), translucent("block/placed_items/purple_pigment"));
    }

    private static PlacedItemBlockEntityRenderer.Provider translucent(String model) {
        return new PlacedItemBlockEntityRenderer.Provider(ModelResourceLocation.standalone(AncientGroundCore.location(model)), RenderType.translucent());
    }

    private static PlacedItemBlockEntityRenderer.Provider cutout(String model) {
        return new PlacedItemBlockEntityRenderer.Provider(ModelResourceLocation.standalone(AncientGroundCore.location(model)), RenderType.cutout());
    }
}
