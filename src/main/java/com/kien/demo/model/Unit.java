package com.kien.demo.model;

import java.util.ArrayList;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kien.demo.dao.Data;
import com.kien.demo.dao.LocalTftDao;
import com.kien.demo.dao.TftDao;

public class Unit {

    private final ArrayList<Trait> traits;

    @JsonProperty("name") @NonNull
    private final String name; 

    private final int position; 

    private static int numberOfUnits = 0;

    static final TftDao tftDao = new LocalTftDao();

    public Unit(@JsonProperty("name") String name){
        this.name = name;
        position = -1;
        traits = new ArrayList<>();
    }
    
    public Unit(String name, ArrayList<Trait> traits){
        this.name = name;
        this.traits = traits;
        this.position = numberOfUnits++;

        Data.allUnits.put(this.hashCode(), this);
        
        for (Trait trait: traits) {
            trait.getUnitsWithThisTrait().add(this);
        }
    }
    
    public ArrayList<Unit> getConnectedUnit(){
        ArrayList<Unit> connectedUnit = new ArrayList<>();
        
        for(Trait trait: traits){
            for(Unit u: trait.getUnitsWithThisTrait()){
                if(!u.equals(this))
                    connectedUnit.add(u);
            }
        }
        
        return connectedUnit;
    }
    
    public int getPosition(){
        return position;
    }
    
    @Override
    public boolean equals(Object otherUnit){
        if(otherUnit.getClass() == Unit.class)
            return this.name.equals(((Unit) otherUnit).name);
        else return false;
    }
    
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
    
    @Override
    public String toString(){
        return name + ": " + traits.toString();
    }
    
    public ArrayList<Trait> getTraits() {
        return traits;
    }
    
    public String getName() {
        return name;
    }
}
