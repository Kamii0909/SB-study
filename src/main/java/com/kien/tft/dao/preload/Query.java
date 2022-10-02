package com.kien.tft.dao.preload;

import java.util.List;

import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public class Query {
    private final Unit unit;
    private final List<Trait> traits;
    
    public Query(Unit unit, List<Trait> traits) {
        this.unit = unit;
        this.traits = traits;
    }

    public Unit getUnit() {
        return unit;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    
}
