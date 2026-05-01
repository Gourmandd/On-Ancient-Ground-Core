package net.gourmand.core.datagen.book.guide.entries.carpentry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class PressurePlateEntry extends EntryProvider {

    public PressurePlateEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain pressure plates.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.PRESSURE_PLATE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Pressure Plates** need to be placed on a solid block.
                \\
                \\
                When a entity is on top of them, they emit a strong **Redstone** signal on the block they are on and a weak one to the surrounding blocks.
               \s""");

        // page 2: ash pressure plate recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/pressure_plate/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash pressure plate being made out Ash lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Pressure Plates";
    }

    @Override
    protected String entryDescription() {
        return "About Pressure Plates";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.PRESSURE_PLATE));
    }

    @Override
    protected String entryId() {
        return "pressure_plate";
    }
}
