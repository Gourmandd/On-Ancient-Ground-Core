package net.gourmand.core.datagen.book.guide.entries.carpentry;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class HangingSignEntry extends EntryProvider {

    public HangingSignEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain hanging sign.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.HANGING_SIGNS.get(Wood.ASH).get(Metal.COPPER)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Hanging Signs** can have 4 rows of text you can input after placing them. To edit the text, you can **Right-Click** them.
                \\
                \\
                You can change the text colour by **Right-Clicking** them with dyes, and make them glowy using glow ink sacs.
               \s""");

        // page 2: ash hanging sign recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/hanging_sign/copper/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash hanging sign being made out Copper chains and Ash planks.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Hanging Signs";
    }

    @Override
    protected String entryDescription() {
        return "About Hanging Signs";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.HANGING_SIGNS.get(Wood.ASH).get(Metal.COPPER));
    }

    @Override
    protected String entryId() {
        return "hanging_sign";
    }
}
