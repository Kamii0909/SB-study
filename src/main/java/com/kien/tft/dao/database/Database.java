package com.kien.tft.dao.database;

import java.util.*;

import com.google.common.collect.Multimap;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public interface Database {

    //Accessor methods
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
