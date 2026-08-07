package net.gourmand.core.datagen.book.guide.entries.devices;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookImagePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.items.TFCItems;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.datagen.BookHeatingPageModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class FirePitEntry extends EntryProvider {

    public FirePitEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain firepits.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCBlocks.FIREPIT))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The **Fire Pit** is a device that is used to heat items, such as to cook items like meat.
                \\
                \\
                It has a single slot to place items in, and it takes logs as fuel.
               \s""");

        // page 2: crafting recipe.
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "devices/firepit"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
               A lit fire pit.
               \s""");

        // page 3: explain how to make them.
        this.page("page3", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(TFCItems.FIRESTARTER)
        );

        this.pageTitle("How to create a fire pit.");
        this.pageText("""
                To make a fire pit, drop (**Q**) a log item, 3 sticks and optionally up to 5 kindling such as straw and pine cones. Then light the pile of items on fire with a **Firestarter**.
                \\
                \\
                Each kindling item increases the change to light a fire pit by 10%%. If it fails, the items are not taken, meaning you can try again.
               \s""");

        // page 4: interface.
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/book/devices/fire_pit_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
                The fire pit's interface. Open by **Right-Clicking** it.
               \s""");

        // page 5: how to do recipes.
        this.page("page5", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context.pageTitle())
        );

        this.pageTitle("Explaining the interface");
        this.pageText("""
        On the left of the fire pit interface, you can add logs by placing items on the top slot. Outside of the interface you can add items by **Crouch + Right-Clicking** with a log in hand.
        \\
        \\
        Items placed in the centre slot will be heated, and once it heats to a required temperature converts into another item and is placed in one of the two bottom slots.
        \\
        \\
        The gauge on the left shows the fire pit's temperature.
        """);

        // page 6: how to do recipes.
        this.page("page6", () -> BookHeatingPageModel.create()
                .withText(this.context().pageText())
                .withRecipeId1(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "heating/food/cooked_beef"))
                .withRecipeId2(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "heating/torch_from_stick"))
        );

        this.pageText("""
        Useful heating recipes: Cooking meat, and making torches.
        """);

        // page 7: how to do recipes.
        this.page("page7", () -> BookTextPageModel.create()
                .withText(this.context().pageText())
                .withTitle(this.context.pageTitle())
        );

        this.pageTitle("Assorted Facts");
        this.pageText("""
        Fire pits will occasionally convert logs into wood ash, **Crouch + Right-Click** to collect it.
        \\
        \\
        You can extinguish a lit fire pit by **Crouch + Right-Clicking** with a shovel in hand.
        \\
        \\
        You can **Right-Click** to apply accessories such **Cooking Pots** and a **Wrought Iron Grill** on the fire pit.\\
        **Crouch + Right-Clicking** will remove them (and damage the player if they are still hot).
        """);
    }

    @Override
    protected String entryName() {
        return "Fire Pits";
    }

    @Override
    protected String entryDescription() {
        return "About Fire Pits.";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCBlocks.FIREPIT);
    }

    @Override
    protected String entryId() {
        return "fire_pit";
    }
}
