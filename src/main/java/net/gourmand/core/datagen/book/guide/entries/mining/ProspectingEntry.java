package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import io.github.notenoughmail.precisionprospecting.PrecisionProspecting;
import io.github.notenoughmail.precisionprospecting.items.PrecProsItems;
import io.github.notenoughmail.precisionprospecting.items.ProspectorType;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class ProspectingEntry extends EntryProvider {

    public ProspectingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain prospecting.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.PROPICK)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                To prospect for ores, you can look for **Ore Deposits** and **Ore Indicators** but this isn't a reliable method of finding local ore veins, and can't find minerals with these methods.
                \\
                \\
                **Prospecting Tools** help you with this, being able to "scan" areas of varying sizes.
               \s""");

        // page 2: image of an ore deposit.
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/ore_indicator.png"))
        );

        this.pageTitle("Ore Indicator");
        this.pageText("""
                A **Hematite** ore indicator in an **Andesite** region.
               \s""");

        // page 3: explain prospecting pickaxe.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.PROPICK)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Prospecting Pickaxe** scans a 25x25x25 area centered on the block you **Right-Click**.
                \\
                \\
                Different tiers of **Prospecting Tools** have differing chance of getting a false "nothing" reading. (as seen on their tooltip)
                \\
                \\
                It can be casted, or forged.
               \s""");

        // page 4: anvil recipe.
        this.page("page4", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/propick_head/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/propick_head/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A prospecting pickaxe being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");

        // page 5: explain mineral prospector.
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(PrecProsItems.TOOLS.get(Metal.COPPER).get(ProspectorType.MIN_PROS)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Mineral Prospector** scans a 45x45x45 area for minerals, centered on the block you **Right-Click**.
                \\
                \\
                Different tiers of **Prospecting Tools** have differing chance of getting a false "nothing" reading. (as seen on their tooltip)
                \\
                \\
                It can be casted, or forged.
               \s""");

        // page 6: anvil recipe.
        this.page("page6", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/mineral_prospector/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/mineral_prospector/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A mineral prospector being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");

        // page 7: explain prospecting drill.
        this.page("page7", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(PrecProsItems.TOOLS.get(Metal.COPPER).get(ProspectorType.PROS_DRILL)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Prospector Drill** scans a 7x25x7 box where the square faces are parallel to the clicked face. Additionally, the scan area is shifted away from the clicked face by 10 blocks.
                \\
                \\
                Different tiers of **Prospecting Tools** have differing chance of getting a false "nothing" reading. (as seen on their tooltip)
                \\
                \\
                It can be casted, or forged.
               \s""");

        // page 8: anvil recipe.
        this.page("page8", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/prospector_drill/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/prospector_drill/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A prospector's drill being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");

        // page 9: explain prospecting drill.
        this.page("page9", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(PrecProsItems.TOOLS.get(Metal.COPPER).get(ProspectorType.PROS_HAMMER)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 The **Prospecting Hammer** scans a 13x13x13 area centered on the block you **Right-Click**. Allowing you to scan more precisely.
                 \\
                 \\
                 Different tiers of **Prospecting Tools** have differing chance of getting a false "nothing" reading. (as seen on their tooltip)
                 \\
                 \\
                 It can be casted, or forged.
               \s""");

        // page 10: anvil recipe.
        this.page("page10", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/prospector_hammer/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(PrecisionProspecting.ID, "anvil/metal/prospector_hammer/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A prospector's hammer being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");
    }

    @Override
    protected String entryName() {
        return "Prospecting";
    }

    @Override
    protected String entryDescription() {
        return "About Prospecting";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BRONZE).get(Metal.ItemType.PROPICK));
    }

    @Override
    protected String entryId() {
        return "prospecting";
    }
}
