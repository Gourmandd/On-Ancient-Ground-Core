package net.gourmand.core.registry;

import de.dafuqs.spectrum.api.energy.color.InkColor;
import de.dafuqs.spectrum.api.energy.color.InkColors;
import de.dafuqs.spectrum.api.item.GemstoneColor;
import de.dafuqs.spectrum.registries.SpectrumRegistries;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public enum CoreGemstoneColors implements GemstoneColor, StringRepresentable {
    //CYAN("cyan", InkColors.CYAN, 0),
    BLUE(InkColors.BLUE, 5, "blue", TFCItems.ORE_POWDERS.get(Ore.SAPPHIRE).holder()),
    RED(InkColors.BLUE, 5, "red", TFCItems.ORE_POWDERS.get(Ore.RUBY).holder()),
    GREEN(InkColors.BLUE, 5, "green", TFCItems.ORE_POWDERS.get(Ore.EMERALD).holder());

    private final int color;
    private final InkColor inkColor;
    private final int offset;
    private final String name;
    private final DeferredHolder<Item, Item> powder;
    
    CoreGemstoneColors(InkColor inkColor, int offset, String name, DeferredHolder<Item, Item> powder){
        this.color = inkColor.getColorInt();
        this.inkColor = inkColor;
        this.offset = offset;
        this.name = name;
        this.powder = powder;

        Registry.register(SpectrumRegistries.GEMSTONE_COLOR, ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, name), this);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public Item getGemstonePowderItem() {
        return powder.get();
    }

    public Item getPowder() {
        return powder.get();
    }

    public InkColor getInkColor() {
        return inkColor;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
