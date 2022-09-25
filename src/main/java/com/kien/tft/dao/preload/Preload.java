package com.kien.tft.dao.preload;

import java.util.List;

import com.kien.tft.model.*;

public interface Preload {
    
    public void setUnits(List<Unit> units);
    public void setTraits(List<Trait> traits);
    public void setQueries(List<String> queries);
    public List<String> getQueries();
    public List<Unit> getUnits();
    public List<Trait> getTraits();
    public Unit getUnit(String name);
    public Trait getTrait(String name);
}
