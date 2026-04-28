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

public class AxeEntry extends EntryProvider {

    public AxeEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain axes.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.AXE_HEAD)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Axes** are required to chop down trees and mine logs, if you mine the bottom of a tree the rest of it will fall. Stone axes are less efficient at logging than metal ones.
                 \\
                 \\
                 They can be knapped, casted or forged.
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
        return "Axes";
    }

    @Override
    protected String entryDescription() {
        return "About Axes";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.ROCK_TOOLS.get(RockCategory.SEDIMENTARY).get(RockCategory.ItemType.AXE));
    }

    @Override
    protected String entryId() {
        return "axe";
    }
}
