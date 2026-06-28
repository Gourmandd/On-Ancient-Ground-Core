package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.compat.patchouli.PatchouliIntegration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.patchouli.common.item.ItemModBook;
import vazkii.patchouli.common.item.PatchouliItems;

public class FieldGuideEntry extends EntryProvider {

    public FieldGuideEntry(CategoryProviderBase parent) {
        super(parent);
    }

    private static final ItemStack BOOK_ITEM = ItemModBook.forBook(PatchouliIntegration.BOOK_ID);

    @Override
    protected void generatePages() {

        // page 1: explain what's going on.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(BOOK_ITEM))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **TerraFirmaCraft** (**TFC**) has its own guide book, which this unfinished one aims to replace, but this one still has useful information.
                \\
                \\
                The TFC field guide can be opened by pressing **'[{}](macro.modpack.open_tfc_guide_key)'**
                \\
                \\
                This book can be open by pressing **'[{}](macro.modpack.open_modpack_guide_key)'**
               \s""");

        // page 2: some alpha version notices.
        this.page("page2", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(PatchouliItems.BOOK))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Please report any inaccuracies, suggestions, typos, or anything else on the discord server, Github Issues, or even the scattered threads in the TerraFirmaCraft, Spectrum or MDK servers.
                \\
                \\
                Have Fun :).
               \s""");
    }

    @Override
    protected String entryName() {
        return "About The Guide Books";
    }

    @Override
    protected String entryDescription() {
        return "Where is my field guide?";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(BOOK_ITEM);
    }

    @Override
    protected String entryId() {
        return "field_guide";
    }
}
