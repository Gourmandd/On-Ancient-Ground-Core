package net.gourmand.core.datagen.book.guide.entries.ores;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.util.TextUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class RockEntry extends EntryProvider {

    public final String ID;
    public final BookIconModel ICON;
    public final String text;
    public final Item item;

    public RockEntry(CategoryProviderBase parent, String id, Item item, String text) {
        super(parent);
        this.ID = id;
        this.ICON = BookIconModel.create(item);
        this.text = text;
        this.item = item;
    }

    @Override
    protected void generatePages() {

        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(item))
        );

        this.pageTitle(entryName());
        this.pageText(text);
    }

    @Override
    protected String entryName() {
        return TextUtil.getName(ID);
    }

    @Override
    protected String entryDescription() {
        return "Where to find: " + TextUtil.getName(ID);
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return ICON;
    }

    @Override
    protected String entryId() {
        return ID;
    }
}
