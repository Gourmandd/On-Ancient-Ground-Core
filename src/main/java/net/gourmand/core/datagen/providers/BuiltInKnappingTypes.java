package net.gourmand.core.datagen.providers;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;
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

public class BuiltInKnappingTypes extends DataManagerProvider<KnappingType>
{
    public static final ResourceLocation FIRE_CLAY = Helpers.identifier("fire_clay");

    public BuiltInKnappingTypes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(KnappingType.MANAGER, output, lookup);
    }

    @Override
    protected void addData(HolderLookup.Provider provider)
    {
        for (CoreClay clayType : CoreClay.values())
        {
            addClay(clayType);
        }

        add(
                FIRE_CLAY,
                TFCTags.Items.FIRE_CLAY_KNAPPING,
                5,
                5,
                TFCSounds.KNAP_CLAY,
                true,
                true,
                false,
                TFCItems.FIRE_CLAY
        );
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
