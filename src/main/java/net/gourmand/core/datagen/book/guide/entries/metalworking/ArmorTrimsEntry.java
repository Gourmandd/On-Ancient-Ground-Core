package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorTrimsEntry extends EntryProvider {

    public ArmorTrimsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain armor trims.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(ItemTags.TRIM_MATERIALS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Armor trims are applied with a Trim Material and a Smithing Template. All trim materials can be seen in rotation above.
                \\
                \\
                Armor trims are purely cosmetic.
                \\
                \\
                They are made using a **Sewing Table**.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "smithing_table"))
        );

        this.pageTitle(entryName());
        this.pageText("The smithing table can be used to apply armor trims to armor.");
    }

    @Override
    protected String entryName() {
        return "Armor Trims";
    }

    @Override
    protected String entryDescription() {
        return "About Armor Trims";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE);
    }

    @Override
    protected String entryId() {
        return "armor_trim";
    }
}
