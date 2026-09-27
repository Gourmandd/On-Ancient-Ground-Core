package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import de.dafuqs.spectrum.blocks.idols.FirestarterIdolBlock;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FirestarterIdolBlock.class)
public class FirestarterIdolBlockMixin
{
    @WrapMethod(method = "addBlockSmeltingRecipes")
    private static void modpack$removeSmeltingRecipes(MinecraftServer server, Operation<Void> original)
    {} //This makes the method not run, no idea if this is compatible with outside mixins.
}
