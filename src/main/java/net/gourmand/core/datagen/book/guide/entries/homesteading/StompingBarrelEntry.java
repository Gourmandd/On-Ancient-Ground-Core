package net.gourmand.core.datagen.book.guide.entries.homesteading;

import com.eerussianguy.firmalife.FirmaLife;
import com.eerussianguy.firmalife.common.FLTags;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.gourmand.core.modonomicon.datagen.BookStompingBarrelPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class StompingBarrelEntry extends EntryProvider {

    public StompingBarrelEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain stomping barrel.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(FLTags.Items.STOMPING_BARRELS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Stomping Barrel** is a device where you can crush items manually by jumping 16 times.
                \\
                \\
                This serves as an alternative to using a **Quern**.
                \\
                \\
                Use **Right-Click** with an item in hand to place items, and with an empty had to remove items.
                """);

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "crafting/wood/stomping_barrel/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The stomping barrel is made from lumber, and glue.
               \s""");

        // page 3: recipe.
        this.page("page3", () -> BookStompingBarrelPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "stomping/food/smashed_red_grapes"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "stomping/food/smashed_white_grapes"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Red and white grapes can be stomped.
               """);

        // page 4: crafting recipe.
        this.page("page4", () -> BookStompingBarrelPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "stomping/food/soybean_paste"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "stomping/powder/charcoal"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               Soybeans and charcoal can also be stomped.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Stomping Barrel";
    }

    @Override
    protected String entryDescription() {
        return "About Stomping Barrels.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(FLBlocks.STOMPING_BARRELS.get(Wood.ASH));
    }

    @Override
    protected String entryId() {
        return "stomping_barrel";
    }
}
