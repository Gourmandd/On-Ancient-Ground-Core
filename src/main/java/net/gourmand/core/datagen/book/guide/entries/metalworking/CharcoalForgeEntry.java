package net.gourmand.core.datagen.book.guide.entries.metalworking;

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
import net.gourmand.core.AncientGroundCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CharcoalForgeEntry extends EntryProvider {

    public CharcoalForgeEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain charcoal forges.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(Items.CHARCOAL))
        );

        this.pageTitle(entryName());
        this.pageText("""
                **Charcoal Forges** are a simple, yet effective way to heat up items using coal fuels.
                \\
                \\
                To create one, light a **Charcoal Pile** with 7-8 layers on fire, which is insulated by stone-like blocks. It needs skylight access (See multiblock).
                \\
                \\
                You can insert coal in the interface, and by dropping fuel items on top of the forge.
               \s""");

        // page 2: making it
        this.page("page2", () -> BookMultiblockPageModel.create()
                .withText(this.context().pageText())
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "metalworking/charcoal_forge"))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
        this.pageText("""
                A set up charcoal forge. Any **one** of the blocks indicated with glass need skylight access.
               \s""");

        // page 3: explain building it.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                The charcoal forge interface has 5 slots for fuel (dark), 5 for items (light) and 4 slots on the side for containers like molds and vessels.
                \\
                \\
                When items melt, they will melt into the container in the highest slot, remainer going to the container under it or being **destroyed** if no container is found.
               \s""");

        // page 4: making it
        this.page("page4", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/charcoal_forge_interface.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The charcoal forge interface.
               \s""");

        // page 5: explain building it.
        this.page("page5", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle(entryName());
        this.pageText("""
                Bellows can be placed adjacent to the block above it to increase the temperatures the forge can reach.
                \\
                \\
                A charcoal forge can heat up a **Crucible** place above it.
               \s""");

        // page 6: fire spread range
        this.page("page6", () -> BookImagePageModel.create()
                .withText(this.context().pageText())
                .withImages(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "textures/book/metalworking/charcoal_forge_range.png"))
        );

        this.pageTitle(entryName());
        this.pageText("""
               The range in which is safe from fire spread from the charcoal forge.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Charcoal Forge";
    }

    @Override
    protected String entryDescription() {
        return "About Charcoal Forges";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "textures/block/devices/charcoal_forge/lit_static.png"));
    }

    @Override
    protected String entryId() {
        return "charcoal_forge";
    }
}
