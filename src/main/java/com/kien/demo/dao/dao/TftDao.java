package com.kien.demo.dao.dao;


import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Multimap;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

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

    //Mutator method
    public void removeATrait(Unit unit, Trait trait);
    public void removeATrait(Trait trait);
    public void addATrait(Unit unit, Trait trait);
}