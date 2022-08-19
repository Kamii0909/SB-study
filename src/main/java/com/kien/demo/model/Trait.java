package com.kien.demo.model;

import java.util.ArrayList;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kien.demo.dao.Data;

public class Trait {

    @JsonProperty("name") @NonNull
    private final String name;
    private final ArrayList<Unit> unitsWithThisTrait = new ArrayList<>();
    
    @Override
    public boolean equals(Object otherTrait){
        if(otherTrait.getClass() == Trait.class)
            return this.name.equals(((Trait) otherTrait).name);
        else return false;
    }
    
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
    
    public Trait(@JsonProperty("name") String name) {
        this.name = name;
        if(!Data.allTraits.containsKey(this.hashCode())){
            Data.allTraits.put(this.hashCode(), this);
        }
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public ArrayList<Unit> getUnitsWithThisTrait() {
        return unitsWithThisTrait;
    }
}
