package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class BricksEntry extends EntryProvider {

    public BricksEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain bricks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Blocks.BRICKS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Bricks** are made in threes, and need to be fired. You need mortar to craft the brick block.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_brick"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Bricks being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Bricks";
    }

    @Override
    protected String entryDescription() {
        return "About Bricks";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.BRICK);
    }

    @Override
    protected String entryId() {
        return "brick";
    }
}
