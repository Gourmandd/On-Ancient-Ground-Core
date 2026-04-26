package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BowlEntry extends EntryProvider {

    public BowlEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bowls.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.CERAMIC_BOWL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ceramic Bowls** can be used to make **Soup** in the **Cooking Pot**.
                \\
                \\
                They can also be placed on the ground, and can hold powder in them. (with gunpowder being ignitable!)
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_bowl"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A ceramic bowl being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Ceramic Bowl";
    }

    @Override
    protected String entryDescription() {
        return "About Ceramic Bowls";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.CERAMIC_BOWL);
    }

    @Override
    protected String entryId() {
        return "ceramic_bowl";
    }
}
