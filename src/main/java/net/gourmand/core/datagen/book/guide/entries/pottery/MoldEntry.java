package net.gourmand.core.datagen.book.guide.entries.pottery;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.gourmand.core.modonomicon.datagen.BookKnappingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class MoldEntry extends EntryProvider {

    public MoldEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain molds.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.MOLDS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Molds** can be used to **Cast** various tools and implements. (See **Casting** for more info)
                \\
                \\
                Some examples (but not all!) are on the next few pages.
               \s""");

        // page 2: saw knapping recipe.
        this.page("page2", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_saw_blade_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A saw blade mold being knapped out of **Clay**.
               \s""");

        // page 3: sword knapping recipe.
        this.page("page3", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_sword_blade_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A sword blade mold being knapped out of **Clay**.
               \s""");

        // page 4: knife knapping recipe.
        this.page("page4", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_knife_blade_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A knife blade mold being knapped out of **Clay**.
               \s""");

        // page 5: scythe knapping recipe.
        this.page("page5", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_scythe_blade_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A scythe blade mold being knapped out of **Clay**.
               \s""");

        // page 6: knapping recipe.
        this.page("page6", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_pickaxe_head_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A pickaxe head mold being knapped out of **Clay**.
               \s""");

        // page 7: knapping recipe.
        this.page("page7", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_axe_head_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A axe head mold being knapped out of **Clay**.
               \s""");

        // page 8: knapping recipe.
        this.page("page8", () -> BookKnappingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "knapping/ceramic/unfired_hammer_head_mold"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                A hammer head mold being knapped out of **Clay**.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Molds";
    }

    @Override
    protected String entryDescription() {
        return "About Molds";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.MOLDS.get(Metal.ItemType.SAW_BLADE));
    }

    @Override
    protected String entryId() {
        return "mold";
    }
}
