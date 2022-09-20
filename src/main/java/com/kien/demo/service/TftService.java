package com.kien.demo.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.kien.demo.model.*;

public interface TftService {

    public List<Trait> allTraits();
    public List<Unit> allUnits();

    public List<Trait> traitOf(Unit unit);
    public List<Unit> unitOf(Trait trait);

    public List<Unit> adjaUnit(Unit unit);

    public LinkedList<Unit> getPathAsList(Unit src, Unit des);
    public int getPathLength(Unit src, Unit des);
    public LinkedHashMap<String, Double> avgPathLength(Collection<Unit> comp);

    public void removeATrait(Trait trait);
    public void removeATrait(Unit unit, Trait trait);
    public void addATrait(Unit unit, Trait trait);
}
