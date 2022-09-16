package com.kien.demo.model;

import java.util.List;

public class Unit {

    private final List<Trait> traits;

    private final String name; 
    
    public Unit(String name, List<Trait> traits){
        this.name = name;
        this.traits = traits;
        traits.stream().forEach(t -> t.getUnits().add(this));
    }
    
    
    @Override
    public boolean equals(Object otherUnit){
        if(otherUnit == this)
            return true;
        if(otherUnit.getClass() != Unit.class)
            return false;
        return this.name.equals(((Unit) otherUnit).name);
    }
    
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
    
    @Override
    public String toString(){
        return name + ": " + traits.toString();
    }
    
    public List<Trait> getTraits() {
        return traits;
    }
    
    public String getName() {
        return name;
    }
}
