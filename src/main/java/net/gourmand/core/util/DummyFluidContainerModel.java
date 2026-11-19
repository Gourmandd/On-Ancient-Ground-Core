package net.gourmand.core.util;

import net.dries007.tfc.util.Helpers;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DummyFluidContainerModel <T extends ModelBuilder<T>> extends CustomLoaderBuilder<T>
{
    protected DummyFluidContainerModel(T parent, ExistingFileHelper existingFileHelper)
    {
        super(Helpers.identifier("fluid_container"), parent, existingFileHelper, false);
    }

    public static <T extends ModelBuilder<T>> CustomLoaderBuilder<T> create(T parent, ExistingFileHelper existingFileHelper)
    {
        return new DummyFluidContainerModel<>(parent, existingFileHelper);
    }
}
