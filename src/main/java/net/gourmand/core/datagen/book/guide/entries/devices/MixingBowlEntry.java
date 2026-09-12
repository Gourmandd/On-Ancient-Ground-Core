package net.gourmand.core.datagen.book.guide.entries.devices;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.modonomicon.datagen.BookMixingBowlPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class MixingBowlEntry extends EntryProvider {

    public MixingBowlEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain mixing bowl.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLBlocks.MIXING_BOWL.asItem()))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Mixing Bowl** is a device letting you mix up to 5 items and a fluid together by hand using a **Spoon**.
                \\
                \\
                Use **Right-Click** with an item or bucket in hand to add items of fluids, and with an empty hand or bucket to remove them.
                Then **Right-Click** with a **Spoon** to start mixing.
                """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/mixing_bowl"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The mixing bowl is made from treated lumber, and glue.
               \s""");

        // page 3: recipe.
        this.page("page3", () -> BookMixingBowlPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "mixing_bowl/food/butter"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Making butter in the mixing bowl.
               """);

        // page 4: recipe.
        this.page("page4", () -> BookMixingBowlPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "mixing_bowl/food/pumpkin_pie_dough"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Pumpkin Pie dough recipe.
               \s""");

        // page 5: recipe.
        this.page("page5", () -> BookMixingBowlPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "mixing_bowl/food/chocolate_chip_cookie_dough"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Chocolate chip cookie dough!
               \s""");
    }

    @Override
    protected String entryName() {
        return "Mixing Bowl";
    }

    @Override
    protected String entryDescription() {
        return "About Mixing Bowls.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.MIXING_BOWL);
    }

    @Override
    protected String entryId() {
        return "mixing_bowl";
    }
}
