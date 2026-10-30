package net.gourmand.core.datagen.providers;

import com.eerussianguy.firmalife.FirmaLife;
import net.dries007.tfc.TerraFirmaCraft;
import net.gourmand.core.AncientGroundCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class BuiltinPlacedFeatureTags extends TagsProvider<PlacedFeature>
{

    private static final TagKey<PlacedFeature> DD_ORE_VEINS = TagKey.create(Registries.PLACED_FEATURE, AncientGroundCore.location("deeper_down_ores"));
    private static final TagKey<PlacedFeature> TFC_ORE_VEINS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/veins"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_BUTTES = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/buttes"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_CANYONS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/canyons"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_HIGHLANDS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/highlands"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_HILLS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/hills"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_LAKE = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/lake"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_LOW_CANYONS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/low_canyons"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_LOWLANDS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/lowlands"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_PLAINS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/plains"));
    private static final TagKey<PlacedFeature> SOIL_DISCS_PLATEAU = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "in_biome/soil_discs/plateau"));

    private static final TagKey<PlacedFeature> FEATURE_CROPS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "feature/crops"));
    private static final TagKey<PlacedFeature> FEATURE_FRUIT_TREES = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "feature/fruit_trees"));
    private static final TagKey<PlacedFeature> FEATURE_LAND_PLANTS = TagKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "feature/land_plants"));

    private final ExistingFileHelper.IResourceType resourceType;

    public BuiltinPlacedFeatureTags(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(event.getGenerator().getPackOutput(), Registries.PLACED_FEATURE, lookup, AncientGroundCore.MOD_ID, event.getExistingFileHelper());
        this.resourceType = new ExistingFileHelper.ResourceType(PackType.SERVER_DATA, ".json", Registries.tagsDirPath(registryKey));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(SOIL_DISCS_BUTTES).add(BuiltinPlacedFeatures.EARTHENWARE_PATCH);
        tag(SOIL_DISCS_CANYONS).add(BuiltinPlacedFeatures.EARTHENWARE_PATCH);
        tag(SOIL_DISCS_HIGHLANDS).add(BuiltinPlacedFeatures.EARTHENWARE_PATCH);
        tag(SOIL_DISCS_HILLS).add(BuiltinPlacedFeatures.EARTHENWARE_PATCH);

        tag(SOIL_DISCS_LOW_CANYONS).add(BuiltinPlacedFeatures.YELLOWWARE_PATCH);
        tag(SOIL_DISCS_PLAINS).add(BuiltinPlacedFeatures.YELLOWWARE_PATCH);
        tag(SOIL_DISCS_PLATEAU).add(BuiltinPlacedFeatures.YELLOWWARE_PATCH);

        tag(SOIL_DISCS_LAKE).add(BuiltinPlacedFeatures.YIXING_PATCH);
        tag(SOIL_DISCS_LOWLANDS).add(BuiltinPlacedFeatures.YIXING_PATCH);


        tag(TFC_ORE_VEINS)
                .add(BuiltinPlacedFeatures.MONSTER_ROOM)
                .add(BuiltinPlacedFeatures.AMETHYST_GEODE)
                .add(BuiltinPlacedFeatures.CITRINE_GEODE)
                .add(BuiltinPlacedFeatures.TOPAZ_GEODE)
                .add(BuiltinPlacedFeatures.SHIMMERSTONE)
                .add(BuiltinPlacedFeatures.SHIMMERSTONE_VOLCANIC)
                .add(BuiltinPlacedFeatures.AZURITE)
                .add(BuiltinPlacedFeatures.AZURITE_VOLCANIC)
                .add(BuiltinPlacedFeatures.STRATINE)
                .add(BuiltinPlacedFeatures.PALTAERIA)
                .add(BuiltinPlacedFeatures.BAUXITE)
                .add(BuiltinPlacedFeatures.ANTHRACITE)
                .add(BuiltinPlacedFeatures.SURFACE_GALENA)
                .add(BuiltinPlacedFeatures.NORMAL_GALENA)
                .add(BuiltinPlacedFeatures.TERRACOTTA)
                .add(BuiltinPlacedFeatures.MARLSTONE)
                .add(BuiltinPlacedFeatures.METEORIC_IRON)
                .add(BuiltinPlacedFeatures.QUARTZ)
                .add(BuiltinPlacedFeatures.QUARTZ_SEDIMENTARY);

        tag(FEATURE_CROPS)
                .add(BuiltinPlacedFeatures.COFFEE_PATCH)
                .add(BuiltinPlacedFeatures.COTTON_PATCH);

        tag(FEATURE_FRUIT_TREES)
                .add(BuiltinPlacedFeatures.FIG_TREE)
                .add(BuiltinPlacedFeatures.WALNUT_TREE)
                .add(BuiltinPlacedFeatures.WOLFBERRY_TREE)
                .remove(ResourceLocation.fromNamespaceAndPath(FirmaLife.MOD_ID, "plant/fig"));

        tag(FEATURE_LAND_PLANTS)
                .add(BuiltinPlacedFeatures.CLOVER_PATCH)
                .add(BuiltinPlacedFeatures.FOUR_LEAF_CLOVER_PATCH);

        tag(DD_ORE_VEINS)
                .add(BuiltinPlacedFeatures.BISMUTH_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.CRYOLITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.DIAMOND_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.GARNIERITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.HEMATITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.LIMONITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.MALACHITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.NATIVE_COPPER_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.NATIVE_GOLD_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.NATIVE_SILVER_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.PALTAERIA_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.SHIMERSTONE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.SPHALERITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.STRATINE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.CINNABAR_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.SULFUR_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.SALTPETER_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.EMERALD_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.OPAL_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.CASSITERITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.SYLVITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.LAPIS_LAZULI_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.LIGNITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.BORAX_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.GYPSUM_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.HALITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.GRAPHITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.KAOLINITE_DEEPER_DOWN)
                .add(BuiltinPlacedFeatures.RUBY_DEEPER_DOWN);
    }
}
