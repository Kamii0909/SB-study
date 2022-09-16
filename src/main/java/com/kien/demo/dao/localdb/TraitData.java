package com.kien.demo.dao.localdb;

import java.util.LinkedHashMap;
import java.util.*;
import java.util.stream.Collectors;
import com.kien.demo.model.Trait;

public class TraitData {
    private final LinkedHashMap<String, Trait> traits;
    private Collection<Trait> cache = null;

    public TraitData(LinkedHashMap<String, Trait> traits){
        this.traits = traits;
    }

    public Trait get(String name){
        return traits.get(name);
    }

    public List<Trait> all(){
        if(cache == null)
            cache = traits.values();

        return cache.stream().collect(Collectors.toList());
    }
}
