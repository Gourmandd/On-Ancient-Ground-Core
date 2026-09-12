package net.gourmand.core.registry.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.vomiter.survivorsdelight.adapter.cooking_pot.wrap.ICookingPotRecipeFluidAccess;
import com.vomiter.survivorsdelight.registry.recipe.SDCookingPotRecipe;
import de.dafuqs.spectrum.api.recipe.GatedRecipe;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GatedCookingRecipe extends SDCookingPotRecipe implements GatedRecipe<RecipeWrapper> {

    public final String group;
    public final Optional<ResourceLocation> requiredAdvancement;
    public final Optional<ResourceLocation> revealSecretAdvancement;
    protected final ItemStack result;
    private List<? extends Predicate<ItemStack>> inputItems;


    public GatedCookingRecipe
            (
            String group,
            NonNullList<Ingredient> ingredients,
            ItemStack result,
            @Nullable ItemStack container,
            int cookingTime,
            float experience,
            @Nullable FluidIngredient fluid,
            int fluidAmountMb,
            float balanceFactor,
            Optional<ResourceLocation> requiredAdvancement,
            Optional<ResourceLocation> revealSecretAdvancement
            )
    {
        super(group, ingredients, result, container, cookingTime, experience, fluid, fluidAmountMb, balanceFactor);
        this.inputItems = ingredients;
        this.group = group;
        this.requiredAdvancement = requiredAdvancement;
        this.revealSecretAdvancement = revealSecretAdvancement;
        this.result = result;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public Optional<ResourceLocation> getRevealSecretAdvancement() {
        return this.revealSecretAdvancement;
    }

    @Override
    public Optional<ResourceLocation> getRequiredAdvancement() {
        return this.requiredAdvancement;
    }

    @Override
    public @Nullable ResourceLocation getRecipeTypeUnlockIdentifier() {
        return null;
    }

    @Override
    public String getRecipeTypeShortID() {
        return "gated_cooking_pot";
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return CoreRecipeSerializers.GATED_COOKING;
    }

    @Override
    public @NotNull List<ItemStack> getAdditionalResults() {
        return List.of();
    }

    @Override
    public boolean matches(@NotNull RecipeWrapper inv, @NotNull Level level) {
        if (!matchInv(inv, level)) {
            AncientGroundCore.LOGGER.info("FD check: {}", false);
            return false;
        } else if (getFluid() != null && getFluidAmountMb() > 0) {
            if (inv instanceof ICookingPotRecipeFluidAccess access) {
                return access.matchesFluid(getFluid(), getFluidAmountMb());
            } else {
                return false;
            }
        } else {
            AncientGroundCore.LOGGER.info("FD check true: {}", true);
            return true;
        }
    }

    private boolean matchInv(@NotNull RecipeWrapper inv, @NotNull Level level){
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for(int j = 0; j < 6; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        AncientGroundCore.LOGGER.info("size: {}", i == this.inputItems.size() );
        AncientGroundCore.LOGGER.info("null: {}", RecipeMatcher.findMatches(inputs, this.inputItems) == null);

        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    public @NotNull NonNullList<Ingredient> getIngredients() {
        return super.getIngredients();
    }

    public static class Serializer implements RecipeSerializer<GatedCookingRecipe>{

        public static final MapCodec<GatedCookingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(GatedCookingRecipe::getGroup),

                        Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients")
                                .xmap(list -> {
                                    NonNullList<Ingredient> nonNullList = NonNullList.create();
                                    nonNullList.addAll(list);
                                    return nonNullList;
                                }, nonNullList -> nonNullList)
                                .forGetter(GatedCookingRecipe::getIngredients),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(GatedCookingRecipe::getResultStack),
                        ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY)
                                .forGetter(r -> r.getContainerOverride().isEmpty() ? ItemStack.EMPTY : r.getContainerOverride().copy()),
                        Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(GatedCookingRecipe::getCookingTime),
                        Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(GatedCookingRecipe::getExperience),
                        FluidIngredient.CODEC.optionalFieldOf("fluid").forGetter(r -> Optional.ofNullable(r.getFluid())),
                        Codec.INT.optionalFieldOf("fluid_amount", 0).forGetter(GatedCookingRecipe::getFluidAmountMb),
                        Codec.FLOAT.optionalFieldOf("balance_factor", 0.04f).forGetter(GatedCookingRecipe::getBalanceFactor),
                        ResourceLocation.CODEC.optionalFieldOf("required_advancement").forGetter(recipe -> recipe.requiredAdvancement),
                        ResourceLocation.CODEC.optionalFieldOf("reveal_secret_advancement").forGetter(recipe -> recipe.revealSecretAdvancement)
                ).apply(instance,
                        (
                            group,
                            ingredients,
                            resultStack,
                            containerItem,
                            time,
                            exp,
                            optionalFluid,
                            fluidAmount,
                            balanceFactor,
                            requiredAdvancement,
                            revealSecretAdvancement
                        ) ->
                        new GatedCookingRecipe(
                                group,
                                ingredients,
                                resultStack,
                                containerItem.isEmpty() ? null : containerItem,
                                time,
                                exp,
                                optionalFluid.orElse(null),
                                fluidAmount,
                                balanceFactor,
                                requiredAdvancement,
                                revealSecretAdvancement
                        )
                ));

        public static final StreamCodec<RegistryFriendlyByteBuf, GatedCookingRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        private static void toNetwork(RegistryFriendlyByteBuf buf, GatedCookingRecipe recipe) {
            buf.writeUtf(recipe.getGroup());

            buf.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buf, recipe.getResultStack());

            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, recipe.getContainerOverride());

            buf.writeFloat(recipe.getExperience());
            buf.writeVarInt(recipe.getCookingTime());

            buf.writeBoolean(recipe.getFluid() != null);
            if (recipe.getFluid() != null) {
                FluidIngredient.STREAM_CODEC.encode(buf, recipe.getFluid());
            }
            buf.writeVarInt(recipe.getFluidAmountMb());
            buf.writeFloat(recipe.getBalanceFactor());

            buf.writeOptional(recipe.getRequiredAdvancement(), ResourceLocation.STREAM_CODEC);
            buf.writeOptional(recipe.getRevealSecretAdvancement(), ResourceLocation.STREAM_CODEC);
        }

        private static GatedCookingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            String group = buf.readUtf();

            int n = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(n, Ingredient.EMPTY);
            for (int i = 0; i < n; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }

            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);

            float exp = buf.readFloat();
            int time = buf.readVarInt();

            FluidIngredient fluid = null;
            if (buf.readBoolean()) {
                fluid = FluidIngredient.STREAM_CODEC.decode(buf);
            }
            int amt = buf.readVarInt();
            float balanceFactor = buf.readFloat();

            Optional<ResourceLocation> requiredAdvancement = buf.readOptional(ResourceLocation.STREAM_CODEC);
            Optional<ResourceLocation> revealSecretAdvancement = buf.readOptional(ResourceLocation.STREAM_CODEC);

            return new GatedCookingRecipe(
                    group,
                    ingredients,
                    result,
                    container.isEmpty() ? null : container,
                    time,
                    exp,
                    fluid,
                    amt,
                    balanceFactor,
                    requiredAdvancement,
                    revealSecretAdvancement
            );
        }

        @Override
        public @NotNull MapCodec<GatedCookingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, GatedCookingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
