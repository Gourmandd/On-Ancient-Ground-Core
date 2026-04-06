package net.gourmand.core.datagen.providers;

import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.datagen.recipes.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class BuiltinRecipes extends RecipeProvider implements WeldingRecipes, AnvilRecipes, HeatingRecipes, CastingRecipes, CraftingRecipes, AlloyRecipes, KnappingRecipes {

    RecipeOutput output;
    HolderLookup.Provider lookup;

    public BuiltinRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
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

    public CompletableFuture<?> run(CachedOutput output, HolderLookup.Provider registries){
        this.lookup = registries;
        return CompletableFuture.allOf(super.run(output, lookup));
    }

    @Override
    public void add(String prefix, String name, Recipe<?> recipe) {
        output.accept(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, (prefix + "/" + name).toLowerCase(Locale.ROOT)), recipe, null);
    }
}
