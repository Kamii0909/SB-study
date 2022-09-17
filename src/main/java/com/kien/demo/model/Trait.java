package com.kien.demo.model;

public class Trait {
    private final String name;

    public Trait(String name) {
        this.name = name;
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
    
    public String getName() {
        return name;
    }
    
}
