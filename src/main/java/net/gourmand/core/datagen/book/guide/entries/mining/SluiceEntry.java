package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SluiceEntry extends EntryProvider {

    public SluiceEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain sluice.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SLUICE)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Sluices** can be used to process gravel ore deposits. it requires flowing water to function. Automating the process of **Panning**.
               \s""");

        // page 2: ash sluice recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/sluice/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash sluice being made out Ash lumber.
               \s""");

        // page 3: how to set it up.
        this.page("page3", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                When placed, the sluice takes up two blocks.
                The water flow must "stop" just before the sluice.
                The sluice will have water visually on it when set up correctly.
                \\
                \\
                There has to be an empty block below the bottom of the sluice for water to flow into.
               \s""");

        // page 4: image of the setup.
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/sluice_cross.png"))
        );

        this.pageTitle("How to set up the sluice");
        this.pageText("""
                A correct sluice setup.
               \s""");

        // page 5: how to use it.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle("How to use the sluice");
        this.pageText("""
                To use the sluice, drop items onto the sluice.
                They will appear on the sluice, and eventually will be processed into items and dumped into the lower water stream.
               \s""");

        // page 6: image of that.
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/sluice_in_use.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A sluice with items inside of it.
               \s""");

        // page 7: products.
        this.page("page7", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context().pageTitle())
        );

        this.pageTitle("Products of Panning");
        this.pageText("""
                Sluicing can give three products:   \s
                - Ore: 55%
                - Loose Rock: 22.5%
                - Gem: 0.9%   \s
                The gem that can drop depends on rock type of the ore deposit.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Sluices";
    }

    @Override
    protected String entryDescription() {
        return "About Sluices";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.SLUICE));
    }

    @Override
    protected String entryId() {
        return "sluice";
    }
}
