package com.kien.demo.dao;


import java.util.Collection;
import java.util.function.Predicate;

import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;

public interface TftDao{
    Collection<Trait> getAllTraits();
    Collection<Trait> getTraitsWith(Predicate<? super Trait> pred);

    /** Ignore a specific trait from other tasks
     * @param trait Trait to be ignored. The trait won't be deleted, but all units will be stripped from the trait
     * @return 0 in case of a successful operation, 1 otherwise
     */
    int ignoreATrait(Trait trait);

    Collection<Unit> getAllUnits();
    Collection<Unit> getUnitsWith(Predicate<? super Unit> pred);

    int[][] getDistance();

    int reInit();
}