package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class KnifeEntry extends EntryProvider {

    public KnifeEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain knives.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.KNIFE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Knives** are fast at harvesting wild crops, leaves and allow you to gain straw from breaking grass. As well as letting you pick up flowers and other foliage.\s
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/getting_started/knapping_knife.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                This shows a knife being knapped out of **Phyllite** rock. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Knives";
    }

    @Override
    protected String entryDescription() {
        return "About Knives.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.KNIFE));
    }

    @Override
    protected String entryId() {
        return "knife";
    }
}
