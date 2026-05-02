package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookCastingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public class ShieldEntry extends EntryProvider {

    public ShieldEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain shields.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Tags.Items.TOOLS_SHIELD))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Shields** are a defensive tool, allowing you to defend against some damage when blocking.   \s
                To do so hold them in either hand and hold **Right-Click*.
                \\
                \\
                They can be forged out of a double sheet of any metal.
               \s""");

        // page 2: forging recipe.
        this.page("page2", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/shield/copper"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/shield/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A shield being forged out of **Copper** and **Bronze** metal. \s
               \s""");

        // page 3: vanilla shield.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.SHIELD))
        );

        this.pageTitle(entryName());
        this.pageText("""
               **Wooden Shields** can also be made out of wood, lasting a shorter amount of time.
               \s""");

        // page 4: vanilla shield recipe.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath("minecraft", "shield"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A shield being made out of wood, requiring glue and lumber.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Shields";
    }

    @Override
    protected String entryDescription() {
        return "About Shields.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.SHIELD));
    }

    @Override
    protected String entryId() {
        return "shield";
    }
}
