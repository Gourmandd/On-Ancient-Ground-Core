package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.rock.RockCategory;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookCastingPageModel;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class HammerEntry extends EntryProvider {

    public HammerEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain hammer.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.HAMMER_HEAD)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 **Hammers** can be used in recipes, and are required for **Welding**, **Anvil Forging** and **Chiseling**.
                \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/stone/hammer_head/igneous_intrusive"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A hammer being knapped out of **Igneous Intrusive** rock. \s
               \s""");

        // page 3: casting recipe.
        this.page("page3", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/copper_hammer_head"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/bronze_hammer_head"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A hammer being cast out of **Copper** and **Bronze** metal. \s
               \s""");

        // page 4: anvil recipe.
        this.page("page4", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/hammer_head/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/hammer_head/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An hammer being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Hammers";
    }

    @Override
    protected String entryDescription() {
        return "About Hammers.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.HAMMER));
    }

    @Override
    protected String entryId() {
        return "hammer";
    }
}
