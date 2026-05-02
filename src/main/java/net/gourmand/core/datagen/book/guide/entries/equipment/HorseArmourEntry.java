package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class HorseArmourEntry extends EntryProvider {

    public HorseArmourEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain horse armour.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.HORSE_ARMOR)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Horse Armour** can be placed in the armour slot of a horse to protect it from damage. (open the menu with Shift + **Right-Click**)
                \\
                \\
                Metal horse armour is crafted with double sheets of any metal, while leather horse armour is knapped.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/horse_armor/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/horse_armor/bronze"))
        );

        // page 3: leather horse armour knapping.
        this.page("page3", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/leather_horse_armor"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Horse armour being knapped out of **Leather**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Horse Armour";
    }

    @Override
    protected String entryDescription() {
        return "About Horse Armour.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.HORSE_ARMOR));
    }

    @Override
    protected String entryId() {
        return "horse_armour";
    }
}
