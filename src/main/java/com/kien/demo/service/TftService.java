package com.kien.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kien.demo.dao.Data;
import com.kien.demo.dao.TftDao;
import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;


@Service
public class TftService {

    private final TftDao tftDao;

    @Autowired
    public TftService(@Qualifier("Local") TftDao tftDao) {
        this.tftDao = tftDao;
    }

    public HashMap<String, Double> getAverageConnectedScore(List<Unit> units) {
        int[][] distance = tftDao.getDistance();
        int total = units.size() - 1;
        HashMap<String, Double> avgCS = new HashMap<>();

        for(Unit u: units){
            avgCS.compute(u.getName(), (k, v)-> {
                v = Double.valueOf(0);
                for(Unit other: units) 
                    v += distance[u.getPosition()][other.getPosition()];
                return v/total;
            });
        }

        avgCS.put("Total", avgCS.values().stream().mapToDouble(s-> s).average().orElse(0));
        
        return avgCS;
    }

    public HashMap<String, Double> getAverageConnectedScore() {
        HashMap<String, Double> avgCS = new HashMap<>();
        for(Unit u: Data.allUnits.values())
            avgCS.put(u.getName(), Arrays.stream(Data.distance[u.getPosition()]).average().orElse(-1)) ;
        return avgCS;
    }

    public Collection<Trait> getAllTraits(){
        return tftDao.getAllTraits();
    }

    public Collection<Trait> getTraitsWith(Predicate<? super Trait> pred){
        return tftDao.getTraitsWith(pred);
    }

    public Collection<Trait> getTraitsContain(String name){
        return getTraitsWith(t -> t.getName().contains(name));
    }

    public Optional<Trait> getTraitsExact(String name){
        return getTraitsWith(t -> t.getName().equals(name)).stream().findFirst();
    }

    public Collection<Trait> getTraitsContain(Unit unit){
        return getTraitsWith(t -> t.getUnitsWithThisTrait().contains(unit));
    }

    public Collection<Trait> getTraitsContain(Unit... units){
        return getTraitsWith(t -> t.getUnitsWithThisTrait().containsAll(Arrays.asList(units)));
    }

    public String ignoreATrait(Trait trait){
        return tftDao.ignoreATrait(getTraitsExact(trait.getName()).orElse(null)) == 1 ?
         "No trait matching argument" : "Success";
    }

    public Collection<Unit> getAllUnits(){
        return tftDao.getAllUnits();
    }

    public Collection<Unit> getUnitsWith(Predicate <? super Unit> pred){
        return tftDao.getUnitsWith(pred);
    }

    public Collection<Unit> getUnitsContain(String name){
        return getUnitsWith(u -> u.getName().contains(name));
    }

    public Optional<Unit> getUnitsExact(String name){
        return getUnitsWith(u -> u.getName().equals(name)).stream().findFirst();
    }
    
    public Collection<Unit> getUnitsContain(Trait trait){
        return getUnitsWith(u -> u.getTraits().contains(trait));
    }

    public Collection<Unit> getUnitsContain(Trait...traits){
        return getUnitsWith(u -> u.getTraits().containsAll(Arrays.asList(traits)));
    }

    public Collection<Unit> getUnitsConnectedWith(Unit unit){
        return getUnitsWith(u -> u.getConnectedUnit().contains(unit));
    }

    public Collection<Unit> getUnitsConnectedWith(Unit...units){
        return getUnitsWith(u -> u.getConnectedUnit().containsAll(Arrays.asList(units)));
    }
}
