package com.kien.tft.dao.preload;

import java.util.ArrayList;
import java.util.List;

import com.kien.tft.model.*;

public abstract class AbstractPreload implements Preload {
    
    protected final List<Unit> units;
    protected final List<Trait> traits;

    protected AbstractPreload(){
        this.units = new ArrayList<>();
        this.traits = new ArrayList<>();
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }

    @Override
    public List<Trait> getTraits(){
        return traits;
    }

}
