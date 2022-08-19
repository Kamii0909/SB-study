package com.kien.demo.dao;

import java.util.Collection;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;

import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

@Repository("Local")
public class LocalTftDao implements TftDao{

    @Override
    public Collection<Trait> getAllTraits() {
        return  Data.allTraits.values();
    }

    @Override
    public Collection<Trait> getTraitsWith(Predicate<? super Trait> pred) {
        return Data.allTraits.values().stream().filter(pred).toList();
    }

    @Override
    public int ignoreATrait(Trait trait) {
        if(!Data.allTraits.containsKey(trait.getName().hashCode())){
            return 1;
        }
        else {
            for (Unit u: trait.getUnitsWithThisTrait()){
                u.getTraits().remove(trait);
            }
            Data.initDistance();
        }
        return 0;
    }

    @Override
    public Collection<Unit> getAllUnits() {
        return Data.allUnits.values();
    }

    @Override
    public Collection<Unit> getUnitsWith(Predicate<? super Unit> pred) {
        return Data.allUnits.values().stream().filter(pred).toList();
    }

    @Override
    public int reInit(){
        Data.init();
        return 0;
    }

    @Override
    public int[][] getDistance() {
        return Data.distance;
    }

    
}