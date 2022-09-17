package com.kien.demo.dao;

import java.util.List;

import com.kien.demo.model.*;

public interface Preload {
    public void setUnits(List<Unit> units);
    public void setTraits(List<Trait> traits);
    public void setQueries(List<String> queries);
    public List<String> getQueries();
    public List<Unit> getUnits();
    public Unit getUnit(String name);
    public Trait getTrait(String name);
}
