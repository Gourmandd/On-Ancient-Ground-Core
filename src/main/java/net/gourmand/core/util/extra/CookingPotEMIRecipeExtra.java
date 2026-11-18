package net.gourmand.core.util.extra;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface CookingPotEMIRecipeExtra
{

    @Nullable ResourceLocation modpack$getUnlockAdvancement();

    @Nullable ResourceLocation modpack$getSecretAdvancement();

    void modpack$setAdvancements(@Nullable ResourceLocation unlock, @Nullable ResourceLocation secret);
}
