package net.gourmand.core.datagen.providers;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.util.data.KnappingType;
import net.dries007.tfc.util.registry.HolderHolder;
import net.gourmand.core.registry.category.CoreClay;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class BuiltInKnappingTypes extends DataManagerProvider<KnappingType>{

    public BuiltInKnappingTypes( PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(KnappingType.MANAGER, output, lookup);
    }

    @Override
    protected void addData(HolderLookup.Provider provider) {
        Stream.of(CoreClay.values()).forEach(this::addClay);
    }

    private void addClay(CoreClay clayType)
    {
        add(
            ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, clayType.getSerializedName()),
            new KnappingType(
                    new SizedIngredient(Ingredient.of(clayType.getClayBallItem().get()), 5),
                    Optional.empty(),
                    TFCSounds.KNAP_CLAY.holder(),
                    true,
                    true,
                    false,
                    new ItemStack(clayType.getClayBallItem().get())
            )
        );
    }

    private void add(ResourceLocation name, TagKey<Item> item, int amount, int consumeAmount, HolderHolder<SoundEvent> sound, boolean consumeAfterComplete, boolean useDisabledTexture, boolean spawnsParticles, ItemLike jeiIcon)
    {
        add(
            name,
            new KnappingType(
                    new SizedIngredient(Ingredient.of(item), amount),
                    amount == consumeAmount ? Optional.empty() : Optional.of(consumeAmount),
                    sound.holder(),
                    consumeAfterComplete,
                    useDisabledTexture,
                    spawnsParticles,
                    new ItemStack(jeiIcon)
            )
        );
    }
}
