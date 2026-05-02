package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.gourmand.core.modonomicon.datagen.BookWeldingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmourEntry extends EntryProvider {

    public ArmourEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain armour.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.CHESTPLATE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Armour** can be placed in your armour slots to protect you from damage. It can be knapped from metal and forged from metal.
               \s""");

        // page 2: leather armour knapping.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/leather_chestplate"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A leather chestplate being knapped out of **Leather**.
               \s""");

        // page 3: anvil recipe.
        this.page("page3", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/unfinished_chestplate/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/unfinished_boots/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An unfinished chestplate being forged out of **Bronze**, and an unfinished boots being forged out of **Bronze**.
               \s""");

        // page 4: welding recipe.
        this.page("page4", () -> BookWeldingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/chestplate/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/boots/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A copper chestplate and bronze boots being finished by **Welding** them with more material.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Armour";
    }

    @Override
    protected String entryDescription() {
        return "About Armour.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.CHESTPLATE));
    }

    @Override
    protected String entryId() {
        return "armour";
    }
}
