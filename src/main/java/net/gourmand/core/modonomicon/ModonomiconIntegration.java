package net.gourmand.core.modonomicon;

import com.klikli_dev.modonomicon.client.gui.BookGuiManager;
import com.klikli_dev.modonomicon.client.gui.book.BookAddress;
import com.klikli_dev.modonomicon.client.render.page.PageRendererRegistry;
import com.klikli_dev.modonomicon.data.BookPageJsonLoader;
import com.klikli_dev.modonomicon.data.LoaderRegistry;
import com.klikli_dev.modonomicon.fluid.NeoFluidHolder;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.modonomicon.client.pages.*;
import net.gourmand.core.modonomicon.pages.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

public class ModonomiconIntegration {

    public static boolean openBook = false;
    public static final String ID = "guide";
    public static final ResourceLocation BOOK_ID = AncientGroundCore.location(ID);

    // Page Types
    public static final ResourceLocation KNAPPING_PAGE = AncientGroundCore.location("knapping");
    public static final ResourceLocation CASTING_PAGE = AncientGroundCore.location("casting");
    public static final ResourceLocation ANVIL_WORKING_PAGE = AncientGroundCore.location("anvil_working");
    public static final ResourceLocation WELDING_PAGE = AncientGroundCore.location("welding");
    public static final ResourceLocation ALLOYING_PAGE = AncientGroundCore.location("alloying");
    public static final ResourceLocation BARREL_INSTANT_PAGE = AncientGroundCore.location("barrel_instant");
    public static final ResourceLocation BARREL_SEALED_PAGE = AncientGroundCore.location("barrel_sealed");
    public static final ResourceLocation BARREL_FLUID_INSTANT_PAGE = AncientGroundCore.location("barrel_fluid_instant");
    public static final ResourceLocation QUERN_PAGE = AncientGroundCore.location("quern");
    public static final ResourceLocation HEATING_PAGE = AncientGroundCore.location("heating");
    public static final ResourceLocation STOMPING_BARREL_PAGE = AncientGroundCore.location("stomping_barrel");

    public static void registerPages(){
        LoaderRegistry.registerPageLoader(KNAPPING_PAGE, (BookPageJsonLoader<?>) BookKnappingPage::fromJson, BookKnappingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(CASTING_PAGE, (BookPageJsonLoader<?>) BookCastingPage::fromJson, BookCastingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(ANVIL_WORKING_PAGE, (BookPageJsonLoader<?>) BookAnvilWorkingPage::fromJson, BookAnvilWorkingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(WELDING_PAGE, (BookPageJsonLoader<?>) BookWeldingPage::fromJson, BookWeldingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(ALLOYING_PAGE, (BookPageJsonLoader<?>) BookAlloyingPage::fromJson, BookAlloyingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(BARREL_INSTANT_PAGE, (BookPageJsonLoader<?>) BookBarrelInstantPage::fromJson, BookBarrelInstantPage::fromNetwork);
        LoaderRegistry.registerPageLoader(BARREL_SEALED_PAGE, (BookPageJsonLoader<?>) BookBarrelSealedPage::fromJson, BookBarrelSealedPage::fromNetwork);
        LoaderRegistry.registerPageLoader(BARREL_FLUID_INSTANT_PAGE, (BookPageJsonLoader<?>) BookBarrelInstantFluidPage::fromJson, BookBarrelInstantFluidPage::fromNetwork);
        LoaderRegistry.registerPageLoader(QUERN_PAGE, (BookPageJsonLoader<?>) BookQuernPage::fromJson, BookQuernPage::fromNetwork);
        LoaderRegistry.registerPageLoader(HEATING_PAGE, (BookPageJsonLoader<?>) BookHeatingPage::fromJson, BookHeatingPage::fromNetwork);
        LoaderRegistry.registerPageLoader(STOMPING_BARREL_PAGE, (BookPageJsonLoader<?>) BookStompingBarrelPage::fromJson, BookStompingBarrelPage::fromNetwork);
    }

    public static void registerPageRenderers() {
        PageRendererRegistry.registerPageRenderer(KNAPPING_PAGE, p -> new BookKnappingPageRenderer((BookKnappingPage) p));
        PageRendererRegistry.registerPageRenderer(CASTING_PAGE, p -> new BookCastingPageRenderer((BookCastingPage) p));
        PageRendererRegistry.registerPageRenderer(ANVIL_WORKING_PAGE, p -> new BookAnvilWorkingPageRenderer((BookAnvilWorkingPage) p));
        PageRendererRegistry.registerPageRenderer(WELDING_PAGE, p -> new BookWeldingPageRenderer((BookWeldingPage) p));
        PageRendererRegistry.registerPageRenderer(ALLOYING_PAGE, p -> new BookAlloyingPageRenderer((BookAlloyingPage) p));
        PageRendererRegistry.registerPageRenderer(BARREL_INSTANT_PAGE, p -> new BookBarrelInstantPageRenderer((BookBarrelInstantPage) p));
        PageRendererRegistry.registerPageRenderer(BARREL_SEALED_PAGE, p -> new BookBarrelSealedPageRenderer((BookBarrelSealedPage) p));
        PageRendererRegistry.registerPageRenderer(BARREL_FLUID_INSTANT_PAGE, p -> new BookBarrelFluidInstantPageRenderer((BookBarrelInstantFluidPage) p));
        PageRendererRegistry.registerPageRenderer(QUERN_PAGE, p -> new BookQuernPageRenderer((BookQuernPage) p));
        PageRendererRegistry.registerPageRenderer(HEATING_PAGE, p -> new BookHeatingPageRenderer((BookHeatingPage) p));
        PageRendererRegistry.registerPageRenderer(STOMPING_BARREL_PAGE, p -> new BookStompingBarrelPageRenderer((BookStompingBarrelPage) p));
    }

    public static NeoFluidHolder getFluidHolder(Fluid fluid, int amount){
        return new NeoFluidHolder(new FluidStack(fluid, amount));
    }

    public static NeoFluidHolder getFluidHolder(FluidStack fluid){
        return new NeoFluidHolder(new FluidStack(fluid.getFluid(), fluid.getAmount()));
    }

    public static void openBook(){
        if (openBook){
            BookGuiManager.get().openBook(BookAddress.defaultFor(BOOK_ID));
            openBook = false;
        }
    }
}
