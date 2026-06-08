package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookAnvilWorkingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class WoodGrilledTraitEntry extends EntryProvider {

    public WoodGrilledTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain wood grilled trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.LOG_PILE_LOGS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Wood Grilled Trait** by default, modifies spoilage rate by **0.8**.
                \\
                \\
                It occurs when you cook food using a **Grill** on a firepit.
               """);

        // page 2: wrought iron grill recipe.
        this.page("page2", () -> BookAnvilWorkingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/wrought_iron_grill"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The wrought iron grill recipe.
               """);
    }

    @Override
    protected String entryName() {
        return "Wood Grilled";
    }

    @Override
    protected String entryDescription() {
        return "About The Wood Grilled Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() { return BookIconModel.create(TFCItems.WROUGHT_IRON_GRILL); }

    @Override
    protected String entryId() {
        return "wood_grilled_trait";
    }
}
