package com.kien.tft.dao.preload;

import java.util.List;

import com.kien.tft.model.*;

public abstract class AbstractPreload implements Preload {
    
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
        return units.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }
    
    @Override
    public Trait getTrait(String name) {
        return traits.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

}
