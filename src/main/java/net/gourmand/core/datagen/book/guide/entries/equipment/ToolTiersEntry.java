package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.crafting.Ingredient;

public class ToolTiersEntry extends EntryProvider {

    public ToolTiersEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain tool tiers.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.TOOLS_BLACK_STEEL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Tools that break blocks have **Tool Tiers** which are used to give blocks different hardness levels.
                \\
                \\
                There are 6 tiers of tools.   \s
                - Stone. (same level as fist)
                - Copper.
                - Bronze.
                - Wrought Iron.
                - Steel.
                - Black Steel.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Tool Tiers";
    }

    @Override
    protected String entryDescription() {
        return "About Tool Tiers";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.PICKAXE));
    }

    @Override
    protected String entryId() {
        return "tool_tiers";
    }
}
