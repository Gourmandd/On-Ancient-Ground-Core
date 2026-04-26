package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FireBricksEntry extends EntryProvider {

    public FireBricksEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain fire bricks.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.FIRE_BRICKS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Fire Bricks** are made in threes, and need to be fired. You need mortar to craft the brick block.
                \\
                \\
                These are required to construct **Blast Furnaces**. (See that page for more details)
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_fire_brick"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Fire Bricks being knapped out of **Fire Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Fire Bricks";
    }

    @Override
    protected String entryDescription() {
        return "About Fire Bricks";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.FIRE_BRICK);
    }

    @Override
    protected String entryId() {
        return "fire_brick";
    }
}
