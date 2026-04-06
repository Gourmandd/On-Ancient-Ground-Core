package net.gourmand.core.datagen.providers;

import com.mojang.serialization.Codec;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.recipes.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BuiltinRecipes extends RecipeProvider implements WeldingRecipes, AnvilRecipes, HeatingRecipes, CastingRecipes, CraftingRecipes, AlloyRecipes, KnappingRecipes {

    final Set<ResourceLocation> removedRecipes = new HashSet<>();
    private final CompletableFuture<?> before;
    RecipeOutput output;
    HolderLookup.Provider lookup;


    // full credit to TFC for this comment and method of doing this wacky stuff that we want to get done.

    // This here, is a dirty hack that allows us to generate a recipe without a 'type' field, which is perfectly legal! As long as
    // we can ensure that the recipe will NEVER pass the condition - aka, if it is using a false condition. This is the most effective
    // way to remove recipes, that generates the least amount of log spam, but NeoForge's condition serializer won't let us not write
    // a real, actual recipe.
    final Codec<Unit> emptyRecipeCodec = Codec.STRING.fieldOf("type")
            .codec()
            .listOf()
            .fieldOf("neoforge:conditions")
            .xmap(l -> Unit.INSTANCE, r -> List.of("neoforge:false"))
            .codec();

    public BuiltinRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, CompletableFuture<?> before, BuiltinItemHeats itemHeat)
    {
        super(output, lookup);
        this.before = CompletableFuture.allOf(before, itemHeat.output());
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        this.output = recipeOutput;

        weldingRecipes();
        anvilRecipes();
        heatingRecipes();
        castingRecipes();
        craftingRecipes();
        alloyingRecipes();
        knappingRecipes();
    }

    @Override
    public HolderLookup.Provider lookup() {
        return lookup;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output, HolderLookup.Provider lookup)
    {
        this.lookup = lookup;
        return before.thenCompose(v -> CompletableFuture.allOf(
                super.run(output, lookup),
                CompletableFuture.allOf(removedRecipes
                        .stream()
                        .map(id -> DataProvider.saveStable(output, lookup, emptyRecipeCodec, Unit.INSTANCE, recipePathProvider.json(id)))
                        .toArray(CompletableFuture[]::new))
        ));
    }

    @Override
    public void add(String prefix, String name, Recipe<?> recipe) {
        output.accept(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, (prefix + "/" + name).toLowerCase(Locale.ROOT)), recipe, null);
    }

    @Override
    public void remove(String... names)
    {
        for (String name : names)
        {
            final ResourceLocation id = ResourceLocation.parse(name);
            assert !removedRecipes.contains(id) : "Recipe " + id + " was already removed";
            removedRecipes.add(id);
        }
    }
}
