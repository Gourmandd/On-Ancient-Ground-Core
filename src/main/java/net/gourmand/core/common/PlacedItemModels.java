package net.gourmand.core.common;

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
    }

    private static PlacedItemBlockEntityRenderer.Provider cutout(String model) {
        return new PlacedItemBlockEntityRenderer.Provider(ModelResourceLocation.standalone(AncientGroundCore.location(model)), RenderType.cutout());
    }
}
