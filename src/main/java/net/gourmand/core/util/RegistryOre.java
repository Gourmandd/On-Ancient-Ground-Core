package net.gourmand.core.util;

import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.registry.category.CoreOres;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface RegistryOre extends StringRepresentable {

    boolean isGem();

    boolean isGraded();

    boolean hasPowder();

    boolean hasBlock();

    Block getOreBlock(RegistryRock rock, @Nullable CoreOres.Grade grade);
}
