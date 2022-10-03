package com.kien.tft.model;


public class Unit {

    public static final Unit INVALID = new Unit("N/A");

    private final String name; 
    
    public Unit(String name){
        this.name = name;
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
        return name;
    }
    
    public String getName() {
        return name;
    }
}
