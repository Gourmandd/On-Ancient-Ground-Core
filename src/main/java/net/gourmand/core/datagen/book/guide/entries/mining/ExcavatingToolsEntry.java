package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookForgingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.rtc.hammertime.HammerTime;
import net.rtc.hammertime.common.items.ModItems;

public class ExcavatingToolsEntry extends EntryProvider {

    public ExcavatingToolsEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain excavating tools.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(ModItems.SLEDGEHAMMERS.get(Metal.BRONZE).get()))
        );

        this.pageTitle(entryName());
        this.pageText("""
                 The **Sledgehammer** and **Excavator** can mine 3x3 areas of rock and soil respectfully. This makes them useful for excavating large areas quickly.
                 \\
                 \\
                 Such as when you care about ores but not about quarrying raw rock.
               \s""");

        // page 2: anvil recipe.
        this.page("page2", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(HammerTime.MOD_ID, "anvil/metal/sledgehammer_head/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(HammerTime.MOD_ID, "anvil/metal/sledgehammer_head/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A sledgehammer being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");

        // page 3: anvil recipe.
        this.page("page3", () -> BookForgingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(HammerTime.MOD_ID, "anvil/metal/excavator_head/wrought_iron"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(HammerTime.MOD_ID, "anvil/metal/excavator_head/steel"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                An excavator being forged out of **Wrought Iron** and **Steel** metal. \s
               \s""");

    }

    @Override
    protected String entryName() {
        return "Excavating Tools";
    }

    @Override
    protected String entryDescription() {
        return "About Excavating Tools";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ModItems.SLEDGEHAMMERS.get(Metal.BRONZE).get());
    }

    @Override
    protected String entryId() {
        return "excavating_tools";
    }
}
