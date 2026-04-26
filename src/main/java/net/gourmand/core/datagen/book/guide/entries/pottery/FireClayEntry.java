package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.plant.Plant;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FireClayEntry extends EntryProvider {

    public FireClayEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain how to get fire clay.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.PLANTS.get(Plant.BLOOD_LILY)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Kaolin Clay** is an uncommon material that can be made into **Fire Clay**, **Pottery** and **Kaolinite**. It can be found in some regions over 18C and 300mm of rain. (See full conditions in the **Ores** Category)
                \\
                \\
                When it is heated it turns to **Kaolinite Powder**. (with a 20% chance)
               \s""");

        // page 2: .
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "pottery/kaolin_clay_indicators"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                Blood Lily indicator on kaolin clay.\s
               \s""");

        // page 3: explaining fire clay.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.FIRE_CLAY))
        );

        this.pageTitle(entryName());
        this.pageText("""
               **Fire Clay** can be made by combining **Kaolinite** and **Graphite** powder.
               \\
               \\
               With this you can make **Crucibles**, **Fire Bricks** (required by **Blast Furnace**), and **Mold Tables**.
               \s""");

        // page 4: crafting recipe.
        this.page("page4", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/fire_clay"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Tip: This recipe is shapeless, items can be in any order!
               \s""");
    }

    @Override
    protected String entryName() {
        return "Kaolin Clay";
    }

    @Override
    protected String entryDescription() {
        return "About Finding and Using Kaolin Clay";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.KAOLIN_CLAY);
    }

    @Override
    protected String entryId() {
        return "kaolin_clay";
    }
}
