package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ShovelEntry extends EntryProvider {

    public ShovelEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain shovels.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.SHOVEL)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Shovels** make it much faster to mine dirt, grass, sand, gravel and clay.\s
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/stone/shovel_head/igneous_intrusive"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A shovel being knapped out of **Igneous Intrusivee** rock. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Shovels";
    }

    @Override
    protected String entryDescription() {
        return "About Shovels.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.SHOVEL));
    }

    @Override
    protected String entryId() {
        return "shovels";
    }
}
