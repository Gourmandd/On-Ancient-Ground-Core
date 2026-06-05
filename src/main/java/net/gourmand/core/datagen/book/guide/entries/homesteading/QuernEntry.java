package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.eerussianguy.firmalife.FirmaLife;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.modonomicon.datagen.BookHeatingPageModel;
import net.gourmand.core.modonomicon.datagen.BookQuernPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class QuernEntry extends EntryProvider {

    public QuernEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain quern.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.QUERN))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Barrel** is a device that holds items and fluids. The barrel can be used for barrel recipes or to simply store fluid.
                \\
                \\
                Barrels can be **Sealed**, this is toggled with the grey button, or by using **Shift + Right-Click** on the barrel with an empty hand.
               \s""");

        //When nearly full of fluid, barrels in your inventory can cause **Overburdening** and if you hold two filled barrels at once causes **Pinning**.

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/barrel/aspen"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The barrel is made from seven lumber. Requiring a saw to make the lumber.
               \s""");

        // page 3: heating recipe.
        this.page("page3", () -> BookHeatingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "heating/food/wheat_dough_tfc"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "heating/metal/ingot/steel"))
                .withTitle2(this.context().pageTitle())
        );

        this.pageTitle("Steel");
        this.pageText("""
               Test heating recipe.
               \s""");

        // page 4: quern recipe.
        this.page("page4", () -> BookQuernPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/powder/sylvite"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "quern/olive_paste"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Test quern recipe.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Quern";
    }

    @Override
    protected String entryDescription() {
        return "About Querns.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.QUERN);
    }

    @Override
    protected String entryId() {
        return "quern";
    }
}
