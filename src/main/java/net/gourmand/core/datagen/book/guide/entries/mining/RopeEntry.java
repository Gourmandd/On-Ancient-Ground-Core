package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class RopeEntry extends EntryProvider {

    public RopeEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain ropes.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.ROPE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ropes** are used to climb down from above, **Right-Click** rope on the tip of a rock spike or a steel rock anchor to tie it there.
                \\
                \\
                **Right-Click** again to throw a rope in the direction you are facing. **Right-Clicking** the anchor will return the rope to your inventory.
                \\
                \\
                Length is dictated by the amount of rope items.
               \s""");

        // page 2: ash sluice recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/rope"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/steel_rope_anchor"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Steel rock anchors can be moved.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Ropes";
    }

    @Override
    protected String entryDescription() {
        return "About Ropes";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.ROPE);
    }

    @Override
    protected String entryId() {
        return "rope";
    }
}
