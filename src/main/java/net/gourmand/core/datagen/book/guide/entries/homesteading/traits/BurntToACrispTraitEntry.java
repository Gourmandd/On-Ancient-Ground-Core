package net.gourmand.core.datagen.book.guide.entries.homesteading.traits;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.modonomicon.datagen.BookHeatingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class BurntToACrispTraitEntry extends EntryProvider {

    public BurntToACrispTraitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain burnt to a crisp trait.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CHARCOAL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Burnt To A Crisp Trait** by default, modifies spoilage rate by **2.5**.
                \\
                \\
                It occurs when you cook food at a very high temperature, usually achieved using a **Charcoal Forge**.
               """);

        // page 2: wrought iron grill recipe.
        this.page("page2", () -> BookHeatingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "heating/burn_bread"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The heating recipe to burn bread.
               """);
    }

    @Override
    protected String entryName() {
        return "Burnt To A Crisp";
    }

    @Override
    protected String entryDescription() {
        return "About The Burnt To A Crisp Trait.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() { return BookIconModel.create(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "textures/gui/book/icons/heating.png")); }

    @Override
    protected String entryId() {
        return "burnt_to_a_crisp_trait";
    }
}
