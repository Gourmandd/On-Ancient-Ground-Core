package net.gourmand.core.datagen.book.guide.entries.metalworking;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.component.forge.ForgingBonus;
import net.dries007.tfc.common.component.forge.ForgingBonusComponent;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class AnvilWorkingEntry extends EntryProvider {

    public AnvilWorkingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain anvil working.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.ANVILS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Anvil Working** is a process done using an **Anvil** and an item at an appropriate temperature, to make another item.
                \\
                \\
                For anvil working metal items, the anvil has to be the tier of the metal.
               \s""");

        // page 2: anvil working recipes.
        this.page("page2", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/unfinished_boots/bronze"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "anvil/metal/pickaxe_head/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Some example anvil working recipes.
               \s""");

        // page 3: explain how to anvil work.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("How To Anvil Work");
        this.pageText("""
                To start anvil working, place a hot item in the anvil, along with a hammer, press the plan button above the hammer slot to choose an item to make.
                \\
                \\
                Keep in mind that the items need to be hot enough to anvil work, see their tooltip for whether they are hot enough.
                \\
                \\
                Continued on next page...
               \s""");

        // page 4: anvil interface
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/anvil_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Make sure to have a hammer in the anvil!
               \s""");

        // page 5: explain how to anvil work.
        this.page("page5", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Targets");
        this.pageText("""
                On the bar at the bottom of the interface there are two pointers.   \s
                The **White** pointer, which is your pointer.   \s
                The **Blue** pointer, is your target.
                \\
                \\
                Your goal is to get your pointer to the target.   \s
                \\
                \\
                 To move your point you can use the **Green** and **Red** action buttons.   \s
                 **Green** actions move your pointer right, and Red actions move your pointer left.  \s
                 Note: if your pointer moves to the red region of the bar, you fail the minigame and lose your material.
               \s""");

        // page 6: explain how to anvil work.
        this.page("page6", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Rules");
        this.pageText("""
                To finish the minigame, you have to match the recipe's rules.
                \\
                \\
                The icons at the top of the interface are the rules. They represent actions you must take, in specific orders, to finish the recipe.
                For example, a rule could be Bend Second Last, meaning the second last action you take has to be a Bend action.
                \\
                \\
                Your last three actions are shown right underneath the rules. When a rule is satisfied it will become green.
               \s""");

        // page 7: explain how to anvil work.
        this.page("page7", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Forging Modifiers");
        this.pageText("""
                Items such as tool heads, when made with a low or minimum amount of steps, will reward you with a Forging Modifier based on how efficiently you forged them.
                \\
                \\
                This modifier will be applied to tools you craft with the forging result, for example, a tool head will make a tool with a modifier.
                \\
                \\
                There are four tiers of forging modifiers:   \s
                 - Poorly Forged
                 - Well Forged
                 - Expertly Forged
                 - Masterfully Forged
               \s""");

        ItemStack pickaxe = new ItemStack(TFCItems.METAL_ITEMS.get(Metal.COPPER).get(Metal.ItemType.PICKAXE).asItem());
        ForgingBonusComponent.set(pickaxe, ForgingBonus.PERFECT);

        // page 8: list modifiers and showcase item with one.
        this.page("page8", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(pickaxe)
        );

        this.pageTitle("Forging Modifiers");
        this.pageText("""
                Items such as tool heads, when made with a low or minimum amount of steps, will reward you with a Forging Modifier based on how efficiently you forged them.
                \\
                \\
                This modifier will be applied to tools you craft with the forging result, for example, a tool head will make a tool with a modifier.
                \\
                \\
                There are four tiers of forging modifiers:   \s
                 - Poorly Forged
                 - Well Forged
                 - Expertly Forged
                 - Masterfully Forged
               \s""");
    }

    @Override
    protected String entryName() {
        return "Anvil Working";
    }

    @Override
    protected String entryDescription() {
        return "How To Anvil Work?";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.ANVIL));
    }

    @Override
    protected String entryId() {
        return "anvil_working";
    }
}
