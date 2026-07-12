package net.gourmand.core.datagen.book.guide.entries.homesteading;

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
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookBarrelFluidInstantPageModel;
import net.gourmand.core.modonomicon.datagen.BookBarrelInstantPageModel;
import net.gourmand.core.modonomicon.datagen.BookBarrelSealedPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class BarrelEntry extends EntryProvider {

    public BarrelEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain barrels.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.BARRELS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Barrel** is a device that holds items and fluids. The barrel can be used for barrel recipes or to simply store fluid.
                \\
                \\
                Barrels can be **Sealed**, this is toggled with the grey button, or by using **Shift + Right-Click** on the barrel with an empty hand.
               \s""");

        //When nearly full of fluid, barrels in your inventory can cause **Overburdening** and if you hold two filled barrels at once causes **Pinning**.

        // page 2: crafting recipe.
        this.page("page2", () -> BookCraftingRecipePageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "crafting/wood/barrel/ash"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The barrel is made from seven lumber. Requiring a saw to make the lumber.
               \s""");

        // page 3: explain barrels.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The central slot holds items. Which hold the inputs and outputs of recipes.
                \\
                \\
                Fluids are shown in the tank on the left, and can be added by placing a container, such as a bucket in the top left slot.
                Place an empty fluid container in the same slot to remove fluids. **Right-Clicking** on the block with a container also works.
               \s""");

        // page 4: interface.
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/book/homesteading/barrel_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The barrel interface.
               \s""");

        // page 5: how to do recipes.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context.pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
        To do a Barrel recipe, fill the barrel with the correct ratio of fluid and items. Certain recipes require the barrel to be **Sealed** for a period of time, transforming once the timer runs out.
        \\
        \\
        If the contents do not match the required ratio, the excess fluid or items will be lost.
        """);

        // page 6: how to do recipes.
        this.page("page6", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context.pageTitle())
        );

        this.pageTitle(entryName());
        this.pageText("""
        However if the recipe converts instantly, you will need enough items to fully convert the fluid.
        \\
        \\
        If the the amount of items created by the recipe exceeds 64, the items will buffer, and appear in the slot when it is emptied.
        """);

        // page 7: instant barrel recipes.
        this.page("page7", () -> BookBarrelInstantPageModel.create()
                .withText(this.context().pageText())
                .withTitle1("Limewater")
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/limewater"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/clay_ball"))
        );

        this.pageTitle("Examples");
        this.pageText("""
        Example: of the instant barrel recipes that create **Limewater** and **Clay**.
        """);

        // page 8: instant barrel recipes.
        this.page("page8", () -> BookBarrelSealedPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/jute_fiber"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/glue"))
        );

        this.pageTitle("Examples");
        this.pageText("""
        Example: of the sealed barrel recipes that create **Jute Fiber** and **Glue**.
        """);

        // page 9: instant barrel recipes.
        this.page("page9", () -> BookSpotlightPageModel.create()
                .withText(this.context().pageText())
                .withItem(TFCItems.WOODEN_BUCKET)
        );

        this.pageTitle(entryName());
        this.pageText("""
        Some barrel recipes require the mixing of two fluids.
        \\
        \\
        To do this, place the first fluid into the barrel and attempt to place the second fluid into the barrel.
        """);

        // page 10: instant barrel recipes.
        this.page("page10", () -> BookBarrelFluidInstantPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "barrel/brine"))
                .withTitle1(this.context().pageTitle())
        );

        this.pageTitle("Brine");
        this.pageText("""
        Example: of the fluid instant barrel recipes that create **Brine**.
        """);

        // page 11: barrel tips.
        this.page("page11", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
        );

        this.pageTitle("Barrel Tips");
        this.pageText("""
        Some tips:   \s
        - Empty Barrels can be filled by clicking on a fluid source block.
        - During rain, open barrels will slowly fill up with water.
        - Icicles melting above barrels adds water to them.
        - Sealing a barrel will eject items that are not in the center slot.
        - Barrels can be placed on their side by placing them against the side of a block.
        - Side ways barrels can be stacked using **Barrel Racks**.
        """);

        // page 12: sideways barrels.
        this.page("page12", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/book/homesteading/barrel_drip.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
        Barrels on their side which are **Open**, can drip their contents into fluid containers.
        """);
    }

    @Override
    protected String entryName() {
        return "Barrels";
    }

    @Override
    protected String entryDescription() {
        return "About Barrels.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.WOODS.get(Wood.ASH).get(Wood.BlockType.BARREL));
    }

    @Override
    protected String entryId() {
        return "barrel";
    }
}
