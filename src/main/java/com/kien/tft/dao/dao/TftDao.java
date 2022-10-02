package com.kien.tft.dao.dao;


import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Multimap;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public interface TftDao {

    //Accessor methods
    public Unit getUnit(String name);
    public List<Unit> getUnitWith(Predicate<Unit> pred);
    public Trait getTrait(String name);
    public List<Trait> getTraitWith(Predicate<Trait> pred);
    public List<Unit> allUnits();
    public List<Trait> allTraits();
    public List<Trait> traitOf(Unit unit);
    public List<Unit> unitOf(Trait trait);
    public List<Unit> adjaUnit(Unit unit);
    public Multimap<Trait, Unit> getTraitToUnitMapping();
    public Multimap<Unit, Trait> getUnitToTraitMapping();
    public Unit invalidUnit();
    public Trait invalidTrait();

    //Mutator method
    public void removeATrait(Unit unit, Trait trait);
    public void removeATrait(Trait trait);
    public void addATrait(Unit unit, Trait trait);
}