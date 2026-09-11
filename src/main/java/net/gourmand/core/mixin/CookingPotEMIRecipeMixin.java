package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.gourmand.core.common.emi.CookingPotEMIRecipeExtraData;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.integration.emi.recipe.CookingPotEmiRecipe;

import static de.dafuqs.spectrum.compat.emi.GatedSpectrumEmiRecipe.SECRET;
import static de.dafuqs.spectrum.compat.emi.GatedSpectrumEmiRecipe.SECRET_HINT;
import static de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipe.HIDDEN_LINE_1;
import static de.dafuqs.spectrum.compat.emi.SpectrumEmiRecipe.HIDDEN_LINE_2;

@Mixin(value = CookingPotEmiRecipe.class, remap = false)
public abstract class CookingPotEMIRecipeMixin implements CookingPotEMIRecipeExtraData
{

    @Unique
    @Nullable
    private ResourceLocation modpack$unlockAdvancement;

    @Unique
    @Nullable
    private ResourceLocation modpack$secretAdvancement;

    @Override
    public void modpack$setAdvancements(@Nullable ResourceLocation unlock, @Nullable ResourceLocation secret)
    {
        modpack$unlockAdvancement = unlock;
        modpack$secretAdvancement = secret;
    }

    @Override
    public @Nullable ResourceLocation modpack$getSecretAdvancement()
    {
        return modpack$secretAdvancement;
    }

    @Override
    public @Nullable ResourceLocation modpack$getUnlockAdvancement()
    {
        return modpack$unlockAdvancement;
    }

    @ModifyReturnValue(method = "getDisplayWidth", at = @At("RETURN"))
    private int modpack$addWidth(int original)
    {
        return original + 12;
    }

    @Shadow
    public abstract int getDisplayHeight();

    @Shadow
    public abstract int getDisplayWidth();

    @Unique
    public boolean modpack$hasAdvancement(@Nullable ResourceLocation advancement)
    {
        Minecraft client = Minecraft.getInstance();
        return AdvancementHelper.hasAdvancement(client.player, advancement);
    }

    @WrapMethod(method = "addWidgets")
    private void modpack$advancementWidget(WidgetHolder widgets, Operation<Void> original)
    {

        if (modpack$unlockAdvancement != null && !modpack$hasAdvancement(modpack$unlockAdvancement))
        {
            widgets.addText(HIDDEN_LINE_1, getDisplayWidth() / 2, getDisplayHeight() / 2 - 8, 0x3f3f3f, false).horizontalAlign(TextWidget.Alignment.CENTER);
            widgets.addText(HIDDEN_LINE_2, getDisplayWidth() / 2, getDisplayHeight() / 2 + 2, 0x3f3f3f, false).horizontalAlign(TextWidget.Alignment.CENTER);
        }
        else
            if (modpack$secretAdvancement != null && !modpack$hasAdvancement(modpack$secretAdvancement))
            {
                widgets.addText(SECRET, getDisplayWidth() / 2, getDisplayHeight() / 2 - 4, 0x3f3f3f, false).horizontalAlign(TextWidget.Alignment.CENTER);
                widgets.addText(SECRET_HINT, getDisplayWidth() / 2, getDisplayHeight() / 2 - 8, 0x3f3f3f, false).horizontalAlign(TextWidget.Alignment.CENTER);
            }
            else
            {
                original.call(widgets);
            }
    }
}
