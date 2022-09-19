package com.kien.demo.dao.database;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.kien.demo.dao.Path;
import com.kien.demo.dao.preload.Preload;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

public class AbstractDatabase implements Database {
    private Unit INVALID_UNIT;
    private Trait INVALID_TRAIT;

    private List<Trait> allTraits;
    private List<Unit> allUnits;
    /**
     * Cache for faster load of task: Find all units with a specific Trait
     */
    private Multimap<Trait, Unit> mapTraitToUnit;
    /**
     * Cache for faster load of task: Find all traits of an Unit
     */
    private Multimap<Unit, Trait> mapUnitToTrait;

    AbstractDatabase(Preload preload){
        mapTraitToUnit = MultimapBuilder.hashKeys(28).hashSetValues(58).build();
        mapUnitToTrait = MultimapBuilder.hashKeys(58).hashSetValues(28).build();

        allUnits = preload.getUnits();
        allTraits = preload.getTraits();

        INVALID_UNIT = preload.getInvalidUnit();
        INVALID_TRAIT = preload.getInvalidTrait();

        for(String query: preload.getQueries()){
            String[] arr = query.split(":");
            Unit unit = preload.getUnit(arr[0]);
            Stream.of(arr).skip(1).map(name -> preload.getTrait(name)).forEach(trait -> {
                mapTraitToUnit.put(trait, unit);
                mapUnitToTrait.put(unit, trait);
            });
        }
    }




    @Override
    public Unit getUnit(String name) {
        return allUnits.stream().filter(unit -> unit.getName().equals(name)).findFirst().orElse(INVALID_UNIT);
    }

    @Override
    public Trait getTrait(String name) {
        return allTraits.stream().filter(trait -> trait.getName().equals(name)).findFirst().orElse(INVALID_TRAIT);
    }

    @Override
    public List<Unit> allUnits() {
        return allUnits;
    }

    @Override
    public List<Trait> allTraits() {
        return allTraits;
    }

    @Override
    public List<Trait> traitOf(Unit unit) {
        return mapUnitToTrait.get(unit).stream().toList();
    }

    @Override
    public List<Unit> unitOf(Trait trait) {
        return mapTraitToUnit.get(trait).stream().toList();
    }

    @Override
    public void removeTrait(Trait trait) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void allTrait(Unit unit, Trait trait) {
        // TODO Auto-generated method stub
        
    }
    
}
