package net.gourmand.core.modonomicon;

import com.klikli_dev.modonomicon.client.render.page.PageRendererRegistry;
import com.klikli_dev.modonomicon.data.BookPageJsonLoader;
import com.klikli_dev.modonomicon.data.LoaderRegistry;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.client.pages.BookKnappingPageRenderer;
import net.gourmand.core.modonomicon.pages.BookKnappingPage;
import net.minecraft.resources.ResourceLocation;

public class ModonomiconIntegration {

    // Page Types
    public static final ResourceLocation KNAPPING_PAGE = AncientGroundCore.location("knapping");

    public static void registerPages(){
        LoaderRegistry.registerPageLoader(KNAPPING_PAGE, (BookPageJsonLoader<?>) BookKnappingPage::fromJson, BookKnappingPage::fromNetwork);
    }

    public static void registerPageRenderers() {
        PageRendererRegistry.registerPageRenderer(KNAPPING_PAGE, p -> new BookKnappingPageRenderer((BookKnappingPage) p));
    }
}
