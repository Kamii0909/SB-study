package com.kien.tft.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.kien.tft.model.*;

public interface TftService {

    public List<Trait> allTraits();
    public List<Unit> allUnits();

    public Unit getUnitFromName(String name);
    public Trait getTraitFromName(String name);

    public Unit invalidUnit();
    public Trait invalidTrait();

    public Collection<Unit> getUnitWith(Predicate<Unit> predicate);
    public Collection<Trait> getTraitWith(Predicate<Trait> predicate);

    public List<Trait> traitOf(Unit unit);
    public List<Unit> unitOf(Trait trait);

    public List<Unit> adjaUnit(Unit unit);

    public LinkedList<Unit> getPathAsList(Unit src, Unit des);
    public int getPathLength(Unit src, Unit des);
    public LinkedHashMap<String, Double> avgPathLength(Collection<Unit> comp);

    public void removeATrait(Trait trait);
    public void removeATrait(Unit unit, Trait trait);
    public void addATrait(Unit unit, Trait trait);
}
