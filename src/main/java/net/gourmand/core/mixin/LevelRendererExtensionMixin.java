package net.gourmand.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dries007.tfc.client.overworld.LevelRendererExtension;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.common.OtherWorldlyManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRendererExtension.class)
public class LevelRendererExtensionMixin {

    @WrapOperation(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 1))
    private void changeMoonTexture(int shaderTexture, ResourceLocation textureId, Operation<Void> original){
        if (OtherWorldlyManager.isActive){
            RenderSystem.setShaderTexture(shaderTexture, ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MOD_ID, "textures/environment/evil_moon_phases.png"));
        } else {
            original.call(shaderTexture, textureId);
        }
    }
}
