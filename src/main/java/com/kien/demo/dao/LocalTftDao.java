package com.kien.demo.dao;

import java.util.Collection;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;

import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

@Repository("Local")
public class LocalTftDao implements TftDao{
    LocalDatabase localDatabase;

    @Override
    public Collection<Trait> getAllTraits() {
        return  LocalDatabase.allTraits.values();
    }

    @Override
    public Collection<Trait> getTraitsWith(Predicate<Trait> pred) {
        return LocalDatabase.allTraits.values().stream().filter(pred).toList();
    }

    @Override
    public int ignoreATrait(Trait trait) {
        if(!LocalDatabase.allTraits.containsKey(trait.getName().hashCode())){
            return 1;
        }
        else {
            for (Unit u: trait.getUnitsWithThisTrait()){
                u.getTraits().remove(trait);
            }
            LocalDatabase.initDistance();
        }
        return 0;
    }

    @Override
    public Collection<Unit> getAllUnits() {
        return LocalDatabase.allUnits.values();
    }

    @Override
    public Collection<Unit> getUnitsWith(Predicate<Unit> pred) {
        return LocalDatabase.allUnits.values().stream().filter(pred).toList();
    }

    @Override
    public int reInit(){
        LocalDatabase.init();
        return 0;
    }

    @Override
    public int[][] getDistance() {
        return LocalDatabase.distance;
    }

    
}