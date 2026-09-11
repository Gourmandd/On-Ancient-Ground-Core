package net.gourmand.core.common.emi;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface CookingPotEMIRecipeExtraData
{

    @Nullable ResourceLocation modpack$getUnlockAdvancement();

    @Nullable ResourceLocation modpack$getSecretAdvancement();

    void modpack$setAdvancements(@Nullable ResourceLocation unlock, @Nullable ResourceLocation secret);
}
