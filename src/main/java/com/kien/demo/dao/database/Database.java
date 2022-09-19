package com.kien.demo.dao.database;

import java.util.*;

import com.kien.demo.dao.Path;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

public interface Database {

    //Accessor methods
    public Unit getUnit(String name);
    public Trait getTrait(String name);
    public List<Unit> allUnits();
    public List<Trait> allTraits();
    public List<Trait> traitOf(Unit unit);
    public List<Unit> unitOf(Trait trait);


    //Mutator methods
    public void removeTrait(Trait trait);
    public void allTrait(Unit unit, Trait trait);



}
