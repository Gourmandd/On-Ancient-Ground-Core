package net.gourmand.core.util;

import net.minecraft.util.StringRepresentable;

public interface RegistryOre extends StringRepresentable {

    public boolean isGem();

    public boolean isGraded();

    public boolean hasPowder();

    public boolean hasBlock();
}
