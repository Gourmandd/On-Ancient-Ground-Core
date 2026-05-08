package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.crafting.Ingredient;

public class AlloyingEntry extends EntryProvider {

    public AlloyingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain alloying.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.VESSEL.asItem()))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Alloying** is the mixing of metals to make metal alloys. It can be done in fluid containers like **Vessels** and **Crucibles**.
                \\
                \\
                The alloying recipe has percentages of each metal which need to be satisfied in the molten mix, otherwise the container will output **Unknown** metal which serves no purpose and cannot be recycled.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Alloying";
    }

    @Override
    protected String entryDescription() {
        return "About Alloying";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.INGOT));
    }

    @Override
    protected String entryId() {
        return "alloying";
    }
}
