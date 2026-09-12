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
import net.gourmand.core.modonomicon.datagen.BookWeldingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ShearsEntry extends EntryProvider {

    public ShearsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain shear.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.SHEARS)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Knives** are fast at breaking wool and related blocks.
                \\
                \\
                They also allow you to shear woolly animals.
                \\
                 \\
                 They are welded from two knife blades.
               \s""");


        // page 3: welding recipe.
        this.page("page3", () -> BookWeldingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/shears/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/shears/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A shears being welded out of **Copper** and **Bronze** metal.
               \s""");

    }

    @Override
    protected String entryName() {
        return "Shears";
    }

    @Override
    protected String entryDescription() {
        return "About Shears.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.SHEARS));
    }

    @Override
    protected String entryId() {
        return "shears";
    }
}
