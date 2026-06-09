package net.gourmand.core.datagen.book.guide.entries.carpentry;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class JarbnetEntry extends EntryProvider {

    public JarbnetEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain jarnbets
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLBlocks.JARBNETS.get(Wood.ASH)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Jarbnets** can hold jars, candles, and jugs with aesthetic purpose. **Right-Click** to add or remove items, (with a filler or empty hang).
                \\
                \\
                It can be opened and closed with **Crouch + Right-Click**. Candles in the **Jarbnet** can be lit.
               \s""");

        // page 2: ash door recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/jarbnet/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash jarbnet being made out Ash lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Jarbnet";
    }

    @Override
    protected String entryDescription() {
        return "About Jarbnets";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.JARBNETS.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "jarbnet";
    }
}
