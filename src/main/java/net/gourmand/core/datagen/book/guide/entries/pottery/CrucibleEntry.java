package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class CrucibleEntry extends EntryProvider {

    public CrucibleEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain crucibles.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.CRUCIBLE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Crucibles** are an advanced device for melting, casting and alloying metals.
                \\
                \\
                It is much more convenient at scale for this than using small vessels.
               \s""");

        // page 2: knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_crucible"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A crucible being knapped out of **Fire Clay**.
               \s""");

        // page 3: explain setup.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Crucibles** need a source of heat such as a **Charcoal Forge** under them.
                \\
                \\
                You can place **Bellows** pointing at them to boost the **Charcoal Forge** underneath.
               \s""");

        // page 4: multiblock.
        this.page("page4", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "pottery/crucible"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                A **Crucible** over a **Charcoal Forge** with a **Bellows**.
               \s""");

        // page 5: explain functions of the crucible.
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.CRUCIBLE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Crucibles** can take heat from **Charcoal Forges** under them and heat up 9 items at once.
                \\
                \\
                They can hold 4000 mb of molten metal.
               \s""");

        // page 6: crucible interface.
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/pottery/crucible_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The interface of the **Crucible**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Crucible";
    }

    @Override
    protected String entryDescription() {
        return "About Crucibles";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.CRUCIBLE);
    }

    @Override
    protected String entryId() {
        return "crucible";
    }
}
