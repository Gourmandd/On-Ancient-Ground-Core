package net.gourmand.core.datagen.book.guide.entries.ores;

import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class OreEntry extends EntryProvider {

    public final String ID;
    public final BookIconModel ICON;
    public final String text;
    public final Item item;
    public final TagKey<Item> ores;

    public OreEntry(CategoryProvider parent, String id, Item item, String text, TagKey<Item> ores) {
        super(parent);
        this.ID = id;
        this.ICON = BookIconModel.create(item);
        this.text = text;
        this.item = item;
        this.ores = ores;
    }


    @Override
    protected void generatePages() {
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(ores))
        );

        this.pageTitle(entryName());
        this.pageText(text);

        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "ore_preview/" + ID))
                .withVisualizeButton(false)
        );


        this.pageTitle(entryName());
        this.pageText(entryName() + " in ground.");
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
