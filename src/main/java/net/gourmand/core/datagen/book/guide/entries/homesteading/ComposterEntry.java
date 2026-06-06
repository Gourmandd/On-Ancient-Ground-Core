package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ComposterEntry extends EntryProvider {

    public ComposterEntry(CategoryProviderBase parent) {
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
                The composter is a device used for making compost, a fertiliser.
                \\
                \\
                Use can use **Right-Click** to add items to them, different items add varying amounts of "Green" and "Brown" to the composter, needing enough of both Green and Brown to start.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/composter"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The composter is made from lumber and dirt.
               \\
               \\
               Adding items like meat and bone with create rotten compost instead, when used on a crop, it instantly kills it.
               \s""");

        // page 3: how to use
        this.page("page3", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("How to use it");
        this.pageText("""
                The composter takes 12 days to complete in standard conditions. When it's ready, gray particles will appear above it.
                \\
                \\
                The compost can removed with **Shift + Right-Click** and an empty hand.
                \\
                \\
                Rotten compost will emit gross particles.
                """);

        // page 4: how to use
        this.page("page4", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("How to use it");
        this.pageText("""
                Composters operate slowing in some conditions:   \s
                - In regions of less than 150mm or greater than 350mm of rainfall.
                - The previous effect is stronger the closer to the maximum and minimum rainfall.
                - Composters touching other composters work slower.
                """);

        // page 5: compost greens low
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_GREENS_LOW))
        );

        this.pageTitle("Compost Greens (low)");
        this.pageText("""
                These green items contribute little to the composter, you need 16 of them to fill a composter.
                """);

        // page 6: compost browns low
        this.page("page6", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_BROWNS_LOW))
        );

        this.pageTitle("Compost Browns (low)");
        this.pageText("""
                These brown items contribute little to the composter, you need 16 of them to fill a composter.
                """);

        // page 7: compost greens medium
        this.page("page7", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_GREENS_MEDIUM))
        );

        this.pageTitle("Compost Greens (medium)");
        this.pageText("""
                These green items contribute reasonably to the composter, you need 8 of them to fill a composter.
                """);

        // page 8: compost browns medium
        this.page("page8", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_BROWNS_MEDIUM))
        );

        this.pageTitle("Compost Browns (medium)");
        this.pageText("""
                These brown items contribute reasonably to the composter, you need 8 of them to fill a composter.
                """);

        // page 9: compost greens high
        this.page("page9", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_GREENS_HIGH))
        );

        this.pageTitle("Compost Greens (high)");
        this.pageText("""
                These green items contribute generously to the composter, you need 4 of them to fill a composter.
                """);

        // page 10: compost browns high
        this.page("page10", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
                .withItem(Ingredient.of(TFCTags.Items.COMPOST_BROWNS_MEDIUM))
        );

        this.pageTitle("Compost Browns (high)");
        this.pageText("""
                These brown items contribute generously to the composter, you need 4 of them to fill a composter.
                """);
    }

    @Override
    protected String entryName() {
        return "Composter";
    }

    @Override
    protected String entryDescription() {
        return "About Composters.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.COMPOSTER);
    }

    @Override
    protected String entryId() {
        return "composter";
    }
}
