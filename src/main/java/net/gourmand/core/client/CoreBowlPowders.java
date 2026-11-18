package net.gourmand.core.client;

import com.simibubi.create.AllItems;
import net.dries007.tfc.client.render.blockentity.BowlBlockEntityRenderer;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CoreBowlPowders
{
    public static void addPowders()
    {
        add(AllItems.POWDERED_OBSIDIAN.asItem(), "obsidian");
    }

    private static void add(Item item, String name)
    {
        BowlBlockEntityRenderer.addPowderTexture(item, ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "block/powder/" + name));
    }
}
