package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class CookingPotEntry extends EntryProvider {

    public CookingPotEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain cooking pots.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.POT))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Cooking Pots** can be used to cook grains like rice, as well as make **Soup** and **Jam**.
                \\
                \\
                They need to be placed on a **Firepit** or a **Stove**.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_pot"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A cooking pot being knapped out of **Clay**.
                \\
                \\
                Fire it in a **Pit Kiln** or similar to make a cooking pot.
               \s""");

        // page 3: explain cooking pots.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Cooking Pots** have 5 item slots and can hold a fluid. Fluids can be added by **Right-Clicking** with a fluid container like wooden bucket.
                \\
                \\
                To perform a cooking pot recipe: add fluids, add items and then light the fire to start cooking. It will boil until the recipe finishes.
               \s""");

        //page 4: interface
        this.page("page4", () -> BookImagePageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/book/devices/cooking_pot_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Rice grains in the process in cooking. As seen in the cooking pot interface.
               \s""");

        //page 5: recipes
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Useful Recipes");
        this.pageText("""
                The cooking pot can:   \s
                - Boil eggs
                - Cook rice
                - Cure and cook maize.
                - Create **Soup**
                - Turn dye into **Fluid Form**.
                - Create **Jam**
               \s""");
    }

    @Override
    protected String entryName() {
        return "Cooking Pot";
    }

    @Override
    protected String entryDescription() {
        return "About Cooking Pot";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.POT);
    }

    @Override
    protected String entryId() {
        return "cooking_pot";
    }
}
