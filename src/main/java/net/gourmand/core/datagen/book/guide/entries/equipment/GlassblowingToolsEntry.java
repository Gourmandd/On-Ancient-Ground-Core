package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class GlassblowingToolsEntry extends EntryProvider {

    public GlassblowingToolsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain blowpipe.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.CERAMIC_BLOWPIPE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Blowpipes** can be used for glass blowing. See the **Glass Making** category for more info.
                \\
                \\
                **Ceramic Blowpipes** can break after some use.
                \\
                \\
                **Brass Blowpipes** don't break after use.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_blowpipe"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A ceramic blowpipe being knapped out of **Clay**.
               \s""");

        // page 3: explain jacks.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.JACKS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Jacks** are used for the **Pinch** glassblowing operation. See the **Glass Making** category for more info.
               \s""");

        // page 3: explain paddle.
        this.page("page4", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.PADDLE ))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Paddles** are used for the **Flatten** glassblowing operation. See the **Glass Making** category for more info.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Glassblowing Tools";
    }

    @Override
    protected String entryDescription() {
        return "About Glassblowing Tools";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.CERAMIC_BLOWPIPE);
    }

    @Override
    protected String entryId() {
        return "glassblowing_tools";
    }
}
