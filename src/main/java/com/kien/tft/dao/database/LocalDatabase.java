package com.kien.tft.dao.database;

import java.util.List;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.kien.tft.dao.preload.Preload;
import com.kien.tft.dao.preload.Query;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public class LocalDatabase implements Database {


    protected List<Trait> allTraits;
    protected List<Unit> allUnits;
    /**
     * Cache for faster load of task: Find all units with a specific Trait
     */
    protected Multimap<Trait, Unit> mapTraitToUnit;
    /**
     * Cache for faster load of task: Find all traits of an Unit
     */
    protected Multimap<Unit, Trait> mapUnitToTrait;

    /**
     * Cache for faster load of task: Find all Units that share a Trait, ignore self
     */
    protected Multimap<Unit, Unit> mapUnitToAdja;

    LocalDatabase(Preload preload){
        mapTraitToUnit = MultimapBuilder.hashKeys(28).hashSetValues(58).build();
        mapUnitToTrait = MultimapBuilder.hashKeys(58).hashSetValues(28).build();
        mapUnitToAdja = MultimapBuilder.hashKeys(58).hashSetValues(58).build();

        allUnits = preload.getUnits();
        allTraits = preload.getTraits();


        for(Query query: preload.getQueries()){
            query.getTraits().stream().forEach(trait -> {
                mapUnitToAdja.putAll(query.getUnit(), mapTraitToUnit.get(trait));
                mapTraitToUnit.put(trait, query.getUnit());
                mapUnitToTrait.put(query.getUnit(), trait);
            });
        }
    }

    @Override
    public void removeATrait(Trait trait){
        mapTraitToUnit.get(trait).stream().forEach(unit -> mapUnitToTrait.get(unit).remove(trait));
        mapUnitToTrait.removeAll(trait);
    }

    @Override
    public void addATrait(Unit unit, Trait trait){
        mapTraitToUnit.put(trait, unit);
        mapUnitToTrait.put(unit, trait);
    }

    @Override
    public void removeATrait(Unit unit, Trait trait) {
        mapTraitToUnit.remove(trait, unit);
        mapUnitToTrait.remove(unit, trait);
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
    public Multimap<Trait, Unit> getTraitToUnitMapping() {
        return mapTraitToUnit;
    }


    @Override
    public Multimap<Unit, Trait> getUnitToTraitMapping() {
        return mapUnitToTrait;
    }

    @Override
    public List<Unit> adjaUnit(Unit unit){
        return mapUnitToTrait.get(unit).stream().<Unit> flatMap(trait -> mapTraitToUnit.get(trait).stream()).toList();
    }  
}
