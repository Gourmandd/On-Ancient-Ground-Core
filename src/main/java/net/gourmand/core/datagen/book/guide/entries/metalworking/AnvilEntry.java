package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class AnvilEntry extends EntryProvider {

    public AnvilEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain anvils.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ROCK_ANVILS.get(Rock.BASALT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Anvils** are device that can **Weld** and **Anvil-Work**. You can use **Shift + Right-Click** to place or remove items on the anvil.
                \\
                \\
                They are made out of 7 **Double Ingots**.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/metal/anvil/copper"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Double Ingots** are make through **Welding**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Anvils";
    }

    @Override
    protected String entryDescription() {
        return "About Anvils";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.ANVIL));
    }

    @Override
    protected String entryId() {
        return "anvil";
    }
}
