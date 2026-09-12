package net.gourmand.core.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class CoreKeyBindings {

    public static final KeyMapping OPEN_MODPACK_GUIDE = new KeyMapping("modpack.key.open_modpack_guide", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_GRAVE_ACCENT, AncientGroundCore.MOD_NAME);
    public static final KeyMapping OPEN_TFC_GUIDE = new KeyMapping("modpack.key.open_tfc_guide", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, AncientGroundCore.MOD_NAME);
}
