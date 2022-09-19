package com.kien.demo.dao.preload;

import java.util.List;

import com.kien.demo.model.*;

public abstract class AbstractPreload implements Preload {
    protected final Unit INVALID_UNIT = new Unit("N/A");
    protected final Trait INVALID_TRAIT = new Trait("N/A");
    
    protected List<Unit> units;
    protected List<Trait> traits;

    @Override
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    @Override
    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }

    @Override
    public List<Trait> getTraits(){
        return traits;
    }

    @Override
    public Unit getUnit(String name) {
        return units.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(INVALID_UNIT);
    }
    
    @Override
    public Trait getTrait(String name) {
        return traits.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(INVALID_TRAIT);
    }
    
    @Override
    public Trait getInvalidTrait() {
        return INVALID_TRAIT;
    }

    @Override
    public Unit getInvalidUnit() {
        return INVALID_UNIT;
    }
}
