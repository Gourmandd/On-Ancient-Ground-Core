package net.gourmand.core.datagen.book.guide.entries.masonry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class QuarryingEntry extends EntryProvider {

    public QuarryingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain quarrying.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.STONES_RAW))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Raw Stone** can be acquired through quarrying. To quarry raw stone, break all blocks around it and it will turn into an item.
                \\
                \\
                Keep in mind, this does not work with the hardened stone found on cave roofs and some boulders.
               """);

        // page 2: hardened stone crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rock/hardened_stone/granite"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               You can make hardened stone out of raw stone. It is always supported, and will not initiate a collapse itself. However if a collapse occurs nearby, it can also collapse.
               """);
    }

    @Override
    protected String entryName() {
        return "Quarrying and Raw Stone";
    }

    @Override
    protected String entryDescription() {
        return "About Quarrying.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.RAW));
    }

    @Override
    protected String entryId() {
        return "quarrying";
    }
}
