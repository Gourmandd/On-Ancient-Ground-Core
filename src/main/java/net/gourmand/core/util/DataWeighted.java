package net.gourmand.core.util;

import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.util.collections.IWeighted;

import java.util.*;
import java.util.stream.Collectors;

public class DataWeighted<E> implements IWeighted<E> {

    // A IWeighted implementation similar to TFC's Weighted with changes allowing for data gen utilising IWeighted.

    private final NavigableMap<Double, E> backingMap = new TreeMap<>();
    private double totalWeight = 0;
    private List<Pair<E, Double>> weightedValues;

    public DataWeighted(List<Pair<E, Double>> parallelWeightedList)
    {
        parallelWeightedList.forEach(pair -> add(pair.getSecond(), pair.getFirst()));
    }

    @Override
    public void add(double weight, E result)
    {
        if (weight > 0)
        {
            totalWeight += weight;
            backingMap.put(weight, result);
        }
    }

    @Override
    public E get(double random)
    {
        double value = random * totalWeight;
        return backingMap.higherEntry(value).getValue();
    }

    @Override
    public Collection<E> values()
    {
        return backingMap.values();
    }

    @Override
    public List<Pair<E, Double>> weightedValues()
    {
        if (weightedValues == null)
        {
            weightedValues = backingMap.entrySet().stream().map(e -> Pair.of(e.getValue(), e.getKey())).collect(Collectors.toList());
        }
        return weightedValues;
    }

    @Override
    public boolean isEmpty()
    {
        return backingMap.isEmpty();
    }

    @Override
    public Iterator<E> iterator()
    {
        return backingMap.values().iterator();
    }
}
