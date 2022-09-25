package com.kien.tft.dao.dao;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Multimap;
import com.kien.tft.dao.database.Database;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public abstract class AbstractTftDao implements TftDao {
    protected Database database;

    protected Unit INVALID_UNIT = new Unit("N/A");
    protected Trait INVALID_TRAIT = new Trait("N/A");

    protected AbstractTftDao(Database database){
        this.database = database;
    }

    @Override
    public void addATrait(Unit unit, Trait trait) {
        database.addATrait(unit, trait);
    }

    @Override
    public List<Unit> adjaUnit(Unit unit) {
        return database.adjaUnit(unit);
    }

    @Override
    public List<Trait> allTraits() {
        return database.allTraits();
    }

    @Override
    public List<Unit> allUnits() {
        return database.allUnits();
    }

    @Override
    public Multimap<Trait, Unit> getTraitToUnitMapping() {
        return database.getTraitToUnitMapping();
    }

    @Override
    public List<Trait> getTraitWith(Predicate<Trait> pred) {
        return database.allTraits().stream().filter(pred).toList();
    }

    @Override
    public Multimap<Unit, Trait> getUnitToTraitMapping() {
        return database.getUnitToTraitMapping();
    }

    @Override
    public List<Unit> getUnitWith(Predicate<Unit> pred) {
        return database.allUnits().stream().filter(pred).toList();
    }

    @Override
    public void removeATrait(Unit unit, Trait trait) {
        database.removeATrait(unit, trait);
    }

    @Override
    public void removeATrait(Trait trait) {
        database.removeATrait(trait);
    }

    @Override
    public List<Trait> traitOf(Unit unit) {
        return database.traitOf(unit);
    }

    @Override
    public List<Unit> unitOf(Trait trait) {
        return database.unitOf(trait);
    }

    @Override
    public Trait getTrait(String name) {
        return database.allTraits().stream().filter(trait -> trait.getName().equals(name)).findFirst().orElse(INVALID_TRAIT);
    }

    @Override
    public Unit getUnit(String name) {
        return database.allUnits().stream().filter(unit -> unit.getName().equals(name)).findFirst().orElse(INVALID_UNIT);
    }
}
