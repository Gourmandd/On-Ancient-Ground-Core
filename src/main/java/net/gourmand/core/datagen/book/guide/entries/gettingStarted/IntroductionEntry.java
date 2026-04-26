package net.gourmand.core.datagen.book.guide.entries.gettingStarted;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.*;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class IntroductionEntry extends EntryProvider {

    public IntroductionEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: getting sticks and stones.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.ENCHANTED_BOOK))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The first step in a new world to is pick up the the sticks and stones on the ground around you. You can **Right Click** on them or break them to collect them.\s
               \s""");

        // page 2: multiblock showing sticks and stones.
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "getting_started/stick_and_stones"))
                .withText(this.context().pageText())
                .withVisualizeButton(false)
        );

        this.pageText("""
                A Scattering of various rocks and twigs.\s
               \s""");

        // page 3: explaining knapping.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.STONES_LOOSE))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Once you have sticks and stones, you can **Knap** the stones together to shape them into another item.\s
               \\
               \\
               To do so, you need to have two or more rocks in your hand and **Right Click** in the air.\s
               \s""");

        // page 4: knapping GUI.
        this.page("page4", () -> BookImagePageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/getting_started/rock_knapping.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Rock Knapping GUI.\s
                \\
                \\
                Tip: The "Rock Knapping" category in EMI shows everything you can make by knapping stones!
               \s""");

        // page 5: knapping a tool
        this.page("page5", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.TOOLS_STONE))
        );

        this.pageTitle(entryName());
        this.pageText("""
                To shape the rock, **Left Click** (You can also hold) to remove the "tiles" to remove them. Until you create the desired shape.
                \\
                \\
                This will start the crafting process and use up one of your rocks.
               \s""");

        // page 6: image showing an axe being knapped.
        this.page("page6", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/stone/axe_head/metamorphic"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An axe being knapped out of **Metamorphic** rock. \s
               \s""");

        // page 7: making the tool (description)
        this.page("page7", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                To craft the stone tool itself, you can craft it with a stick like such.
                \\
                \\
                Tools from different rock categories have slightly different durabilities.
               \s""");

        // page 8: making the tool (recipes)
        this.page("page8", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1("tfc:crafting/stone/knife/sedimentary")
                .withRecipeId2("tfc:crafting/stone/axe/sedimentary")
        );
    }

    @Override
    protected String entryName() {
        return "Introduction";
    }

    @Override
    protected String entryDescription() {
        return "Introducing you to this world!";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.ENCHANTED_BOOK);
    }

    @Override
    protected String entryId() {
        return "introduction";
    }
}
