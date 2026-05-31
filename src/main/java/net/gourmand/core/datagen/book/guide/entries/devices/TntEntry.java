package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class TntEntry extends EntryProvider {

    public TntEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain tnt.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Blocks.TNT))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Tnt** is a block which can be ignited using any type of firestarters, and by adjacent fire.
                \\
                \\
                It turns into a movable entity, and explodes after a few seconds.
               \s""");

        // page 2: crafting.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "tnt"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Be careful with that match!
               \s""");
    }

    @Override
    protected String entryName() {
        return "Tnt";
    }

    @Override
    protected String entryDescription() {
        return "About Tnt.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Blocks.TNT);
    }

    @Override
    protected String entryId() {
        return "tnt";
    }
}
