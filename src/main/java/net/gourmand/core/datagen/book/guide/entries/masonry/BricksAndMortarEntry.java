package net.gourmand.core.datagen.book.guide.entries.masonry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BricksAndMortarEntry extends EntryProvider {

    public BricksAndMortarEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bricks and mortar.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.BRICKS.get(Rock.GRANITE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bricks** and **Mortar** are two essential materials for masonry, used to make stone and other types of bricks.
               """);

        // page 2: mortar recipe.
        this.page("page2", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/mortar"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               16 Mortar is made from sand and 100mb of limewater. Limewater being made of 500 mb water and one flux or lime.
               """);

        // page 3: stone brick recipe.
        this.page("page3", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/brick/granite"))
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                A stone brick can be made using loose rock and a chisel.
               """);

        // page 4: plaster brick recipe.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/alabaster_brick"))
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                A plaster brick can be made using gypsum and a chisel. Gypsum is found as an ore in most sedimentary rocks.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Brick and Mortar";
    }

    @Override
    protected String entryDescription() {
        return "About Brick and Mortar.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.BRICKS.get(Rock.GRANITE));
    }

    @Override
    protected String entryId() {
        return "bricks_and_mortar";
    }
}
