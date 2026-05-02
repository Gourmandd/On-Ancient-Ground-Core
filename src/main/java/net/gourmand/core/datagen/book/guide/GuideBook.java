package net.gourmand.core.datagen.book.guide;

import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.book.BookFrameOverlay;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.book.guide.categories.*;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class GuideBook extends SingleBookSubProvider {

    public static final String ID = "guide";

    public GuideBook(BiConsumer<String, String> defaultLang) {
        super(ID, AncientGroundCore.MODID, defaultLang);
    }

    @Override
    protected void registerDefaultMacros() {

    }

    @Override
    protected void generateCategories() {
        var gettingStartedCategory = this.add(new GettingStartedCategory(this).generate());
        var equipmentCategory = this.add(new EquipmentCategory(this).generate());
        var miningCategory = this.add(new MiningCategory(this).generate());
        var carpentryCategory = this.add(new CarpentryCategory(this).generate());
        var masonryCategory = this.add(new MasonryCategory(this).generate());
        var metalworkingCategory = this.add(new MetalworkingCategory(this).generate());
        var potteryCategory = this.add(new PotteryCategory(this).generate());
        var glassworkingCategory = this.add(new GlassworkingCategory(this).generate());
        var oresCategory = this.add(new OresCategory(this).generate());
        var geologyCategory = this.add(new GeologyCategory(this).generate());
    }

    @Override
    protected String bookName() {
        return "Guide To This World";
    }

    @Override
    protected String bookTooltip() {
        return "Helping you, one page at a time!";
    }

    @Override
    public String bookId() {
        return super.bookId();
    }

    @Override
    protected BookModel additionalSetup(BookModel book) {
        return super.additionalSetup(book)
                .withFont(ResourceLocation.fromNamespaceAndPath("minecraft", "default"))
                .withBookContentTexture(ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_entry.png"))
                .withFrameTexture(ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_frame.png"))
                .withBookOverviewTexture(ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_icons.png"))
                .withTopFrameOverride(new BookFrameOverlay(
                                ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_overlay_top.png"),
                                256,
                                256,
                                106,
                                11,
                                0,
                                4
                        )
                )
                .withBottomFrameOverride(new BookFrameOverlay(
                                ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_overlay_bottom.png"),
                                256,
                                256,
                                80,
                                8,
                                0,
                                -4
                        )
                )
                .withLeftFrameOverride(new BookFrameOverlay(
                                ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_overlay_left.png"),
                                256,
                                256,
                                9,
                                124,
                                3,
                                0
                        )
                )
                .withRightFrameOverride(new BookFrameOverlay(
                                ResourceLocation.parse("spectrum:textures/gui/modonomicon/guidebook_overlay_right.png"),
                                256,
                                256,
                                9,
                                124,
                                -4,
                                0
                        )
                );
    }
}
