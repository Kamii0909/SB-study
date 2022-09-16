package com.kien.demo.dao.localdb;

import java.util.LinkedHashMap;
import java.util.*;
import java.util.stream.Collectors;

import com.kien.demo.model.Unit;

public class UnitData {
    private final LinkedHashMap<String, Unit> units;
    private Collection<Unit> cache = null;

    UnitData(LinkedHashMap<String, Unit> units){
        this.units = units;
    }

    public Unit get(String name){
        return units.get(name);
    }


    /**
     * @return the map that back the db 
     */
    public LinkedHashMap<String, Unit> map(){
        return units;
    }

    /**
     * @return mutable {@code List} of all units
     */
    public List<Unit> all() {
        if(cache == null)
            cache = units.values();
        return cache.stream().collect(Collectors.toList());
    }
    
}
