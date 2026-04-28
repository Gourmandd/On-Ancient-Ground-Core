package net.gourmand.core.modonomicon;

import com.klikli_dev.modonomicon.client.render.page.PageRendererRegistry;
import com.klikli_dev.modonomicon.data.BookPageJsonLoader;
import com.klikli_dev.modonomicon.data.LoaderRegistry;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.client.pages.BookCastingPageRenderer;
import net.gourmand.core.modonomicon.client.pages.BookForgingPageRenderer;
import net.gourmand.core.modonomicon.client.pages.BookKnappingPageRenderer;
import net.gourmand.core.modonomicon.client.pages.BookWeldingPageRenderer;
import net.gourmand.core.modonomicon.pages.BookCastingPage;
import net.gourmand.core.modonomicon.pages.BookForgingPage;
import net.gourmand.core.modonomicon.pages.BookKnappingPage;
import net.gourmand.core.modonomicon.pages.BookWeldingPage;
import net.minecraft.resources.ResourceLocation;

public class ModonomiconIntegration {

    // Page Types
    public static final ResourceLocation KNAPPING_PAGE = AncientGroundCore.location("knapping");
    public static final ResourceLocation CASTING_PAGE = AncientGroundCore.location("casting");
    public static final ResourceLocation FORGING_PAGE = AncientGroundCore.location("forging");
    public static final ResourceLocation WELDING_PAGE = AncientGroundCore.location("welding");

    public static void registerPages(){
        LoaderRegistry.registerPageLoader(KNAPPING_PAGE, (BookPageJsonLoader<?>) BookKnappingPage::fromJson, BookKnappingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(CASTING_PAGE, (BookPageJsonLoader<?>) BookCastingPage::fromJson, BookCastingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(FORGING_PAGE, (BookPageJsonLoader<?>) BookForgingPage::fromJson, BookForgingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(WELDING_PAGE, (BookPageJsonLoader<?>) BookWeldingPage::fromJson, BookWeldingPage::fromNetwork);
    }

    public static void registerPageRenderers() {
        PageRendererRegistry.registerPageRenderer(KNAPPING_PAGE, p -> new BookKnappingPageRenderer((BookKnappingPage) p));
        PageRendererRegistry.registerPageRenderer(CASTING_PAGE, p -> new BookCastingPageRenderer((BookCastingPage) p));
        PageRendererRegistry.registerPageRenderer(FORGING_PAGE, p -> new BookForgingPageRenderer((BookForgingPage) p));
        PageRendererRegistry.registerPageRenderer(WELDING_PAGE, p -> new BookWeldingPageRenderer((BookWeldingPage) p));
    }
}
