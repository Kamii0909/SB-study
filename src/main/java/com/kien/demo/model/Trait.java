package com.kien.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Trait {
    private final String name;
    private final List<Unit> units;

    public Trait(String name) {
        this.name = name;
        units = new ArrayList<>();
    }
    
    @Override
    public boolean equals(Object otherTrait){
        if(otherTrait == this)
            return true;
        if(otherTrait.getClass() != Trait.class)
            return false;
        return ((Trait) otherTrait).getName().equals(name);
    }
    
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }

    public List<Unit> getUnits(){
        return units;
    }
    
    public String getName() {
        return name;
    }
    
}
