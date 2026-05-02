package net.gourmand.core.datagen.book.guide.entries.mining;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.OreDeposit;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class OreDepositEntry extends EntryProvider {

    public OreDepositEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain ore deposits.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCTags.Items.ORE_DEPOSITS))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Ore Deposits** can be found in any water bodies such as lakes, and rivers.
                \\
                \\
               These ores types can have ore deposits:   \s
                - **Cassiterite**
                - **Native Gold**
                - **Native Silver**
                - **Native Copper**
               \s""");

        // page 2: image of an ore deposit.
        this.page("page2", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/mining/ore_deposit.png"))
        );

        this.pageTitle("How to set up the sluice");
        this.pageText("""
                A **Native Gold** ore deposit in chert gravel, found in a river.
               \s""");

    }

    @Override
    protected String entryName() {
        return "Ore Deposits";
    }

    @Override
    protected String entryDescription() {
        return "About Ore Deposits";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.ORE_DEPOSITS.get(Rock.CHALK).get(OreDeposit.NATIVE_GOLD));
    }

    @Override
    protected String entryId() {
        return "ore_deposit";
    }
}
