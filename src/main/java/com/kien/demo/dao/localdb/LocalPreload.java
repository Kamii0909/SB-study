package com.kien.demo.dao.localdb;

import java.util.List;

import com.kien.demo.dao.Preload;
import com.kien.demo.model.*;

public class LocalPreload implements Preload {
    private List<Unit> units;
    private List<Trait> traits;
    private List<String> queries;
    private final Unit INVALID_UNIT;
    private final Trait INVALID_TRAIT;

    LocalPreload(Unit invalidUnit, Trait invalidTrait){
        INVALID_UNIT = invalidUnit;
        INVALID_TRAIT = invalidTrait;
    }
    
    @Override
    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
    @Override
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    @Override
    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }
    @Override
    public List<String> getQueries() {
        return queries;
    }
    @Override
    public List<Unit> getUnits() {
        return units;
    }
    @Override
    public Unit getUnit(String name) {
        return units.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(INVALID_UNIT);
    }
    @Override
    public Trait getTrait(String name) {
        return traits.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(INVALID_TRAIT);
    }
}
