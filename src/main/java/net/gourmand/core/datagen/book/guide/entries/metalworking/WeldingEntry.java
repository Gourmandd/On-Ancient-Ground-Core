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
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookWeldingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class WeldingEntry extends EntryProvider {

    public WeldingEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain welding.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.ROCK_ANVILS.get(Rock.BASALT)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Welding is a process done using an **Anvil** and **Flux**, to combine two items which are at an appropriate temperature.
                \\
                \\
                For welding metal items, the anvil has to be the tier below the tier of the metal.
               \s""");

        // page 2: welding recipes.
        this.page("page2", () -> BookWeldingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/boots/bronze"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "welding/metal/shears/bronze"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Some example welding recipes.
               \s""");

        // page 3: explain how to weld.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("How To Weld");
        this.pageText("""
                To weld, place two hot items in the anvil, along with flux, and either press the welding button (with a hammer in its proper slot) or **Shift + Right-Click** on the anvil with a hammer.
                \\
                \\
                Keep in mind that the items need to be hot enough to weld, see their tooltip for whether they are hot enough.
               \s""");

        // page 4: anvil interface
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/anvil_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Make sure to have flux in the anvil!
               \s""");
    }

    @Override
    protected String entryName() {
        return "Welding";
    }

    @Override
    protected String entryDescription() {
        return "How To Weld?";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ROCK_ANVILS.get(Rock.BASALT));
    }

    @Override
    protected String entryId() {
        return "welding";
    }
}
