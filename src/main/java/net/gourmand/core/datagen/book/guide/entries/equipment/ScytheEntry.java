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
import net.gourmand.core.registry.category.CoreTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ScytheEntry extends EntryProvider {

    public ScytheEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain scythes.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(CoreTags.Items.TOOL_HEADS.get(Metal.ItemType.SCYTHE_BLADE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Scythes** can harvest crops, leaves and gras in a 3x3x3 area, saving time and effort.
                \\
                \\
                They also allow you to gain straw from breaking grass and let you pick up flowers and other foliage.
                \\
                 \\
                 They can be casted or forged.
               \s""");

        // page 2: casting recipe.
        this.page("page2", () -> BookCastingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/copper_scythe_blade"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "casting/bronze_scythe_blade"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A hoe being cast out of **Copper** and **Bronze** metal. \s
               \s""");

        // page 3: anvil recipe.
        this.page("page3", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/scythe_blade/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/scythe_blade/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An scythe being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Scythes";
    }

    @Override
    protected String entryDescription() {
        return "About Scythes.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.SCYTHE));
    }

    @Override
    protected String entryId() {
        return "scythe";
    }
}
