package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookCastingPageModel;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ProspectingToolsEntry extends EntryProvider {

    public ProspectingToolsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain them.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.PROPICK_HEAD)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                There are several tools.
                \\
                \\
                **This entry is WORK IN PROGRESS**
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/stone/axe_head/metamorphic"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An axe being knapped out of **Metamorphic** rock. \s
               \s""");

        // page 3: casting recipe.
        this.page("page3", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/copper_axe_head"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/bronze_axe_head"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An axe being cast out of **Copper** and **Bronze** metal. \s
               \s""");

        // page 4: anvil recipe.
        this.page("page4", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/axe_head/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/axe_head/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An axe being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Prospecting Tools";
    }

    @Override
    protected String entryDescription() {
        return "About Prospecting Tools";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.PROPICK));
    }

    @Override
    protected String entryId() {
        return "prospecting_tools";
    }
}
