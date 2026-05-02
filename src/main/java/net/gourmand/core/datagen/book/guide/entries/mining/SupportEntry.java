package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class SupportEntry extends EntryProvider {

    public SupportEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain support.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.SUPPORTS.get(Wood.ASH)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Supports** are a block that can prevent collapses and landslides from occurring nearby.
               \s""");

        // page 2: ash support recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/support/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Ash supports being made out Ash lumber and planks.
               \s""");

        // page 3: explain support.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.STONES_RAW))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Blocks such as raw rock, ores, smooth rock and spikes can collapse when triggered by mining a nearby raw rock block.
                \\
                \\
                The roofs of caves are supported by hardened rock, which can fall if a collapse triggers but cannot trigger a collapse itself.
               \s""");

        // page 4: explain support.
        this.page("page4", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Supporting rock");
        this.pageText("""
                Blocks cannot collapse if there is a non-collapsing or supported block under it.
                \\
                \\
                Blocks that are not full block (such as slabs) cannot support collapse-able blocks.
               \s""");

        // page 5: explain support.
        this.page("page5", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Supporting rock");
        this.pageText("""
                Supports can support rock in a 9x5x9 area around a horizontal support. To place a horizontal support place supports in between two vertical supports.
                \\
                \\
                Blocks in the supported area are considered supported and cannot trigger a collapse.
               \s""");

        // page 6: support multiblock.
        this.page("page6", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "mining/support_range"))
                .withVisualizeButton(false)
        );

        this.pageTitle("Supporting rock");
        this.pageText("""
               Range of the horizontal support in the middle.   \s
               (hold **Shift** to freeze)
               \s""");

        // page 7: explain conversions.
        this.page("page7", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Block Conversions");
        this.pageText("""
                When rock or mineral ores collapse they turn into cobble which can **Landslide** afterwards.
                \\
                \\
                Graded ores degrade down a tier.   \s
                Rich -> Normal -> Poor -> Cobble.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Supports";
    }

    @Override
    protected String entryDescription() {
        return "About Supports";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.SUPPORTS.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "supports";
    }
}
