package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookBarrelFluidInstantPageModel;
import net.gourmand.core.modonomicon.datagen.BookBarrelInstantPageModel;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BarrelEntry extends EntryProvider {

    public BarrelEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain barrels.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.BARRELS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The Barrel is a device that can hold both items and fluids.
                The central slot is used to hold items.
                Fluids are shown in the tank on the left side, and can be added to the barrel by placing a filled bucket or jug in the top left slot.
                They can be removed by placing an empty fluid container in the same slot. Using Right Click on the block with a bucket also works.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/barrel/aspen"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The barrel is made from seven lumber. Requiring a saw to make the lumber.
               \s""");

        // page 3: anvil recipe.
        this.page("page3", () -> BookBarrelInstantPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/limewater"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/clay_ball"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Testing Instant (item) barrel recipes.
               \s""");

        // page 4: welding recipe.
        this.page("page4", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/glue"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/jute_fiber"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Testing Sealed barrel recipes.
               \s""");

        // page 5: welding recipe.
        this.page("page5", () -> BookBarrelFluidInstantPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/brine"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Testing Instant (fluid) barrel recipes
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
