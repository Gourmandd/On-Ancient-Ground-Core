package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CharcoalGrilledTraitEntry extends EntryProvider {

    public CharcoalGrilledTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain charcoal grilled trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CHARCOAL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Charcoal Grilled Trait** by default, modifies spoilage rate by **1.25**.
                \\
                \\
                It occurs when you cook food using in a **Charcoal Forge** or **Crucible**.
               """);

        // page 2: unfired crucible recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_crucible"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The unfired crucible recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Charcoal Grilled";
    }

    @Override
    protected String entryDescription() {
        return "About The Charcoal Grilled Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.CHARCOAL);
    }

    @Override
    protected String entryId() {
        return "charcoal_grilled_trait";
    }
}
