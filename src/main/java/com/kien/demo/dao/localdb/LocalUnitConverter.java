package com.kien.demo.dao.localdb;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;

import com.kien.demo.model.Unit;

public class LocalUnitConverter implements Converter<String, Unit> {
    private TraitData traits; 

    public TraitData getTraits() {
        return traits;
    }

    public void setTraits(TraitData traits) {
        this.traits = traits;
    }

    /**
     * Convert a String in the form of Name,Trait1,Trait2 to an Unit
     */
    @Override
    public Unit convert(String unitData) {
        String[] arr = unitData.split(",");
        List<String> traitNames = Stream.of(arr).collect(Collectors.toList());
        traitNames.remove(0);
        return new Unit(arr[0], traitNames.stream().map(t -> traits.get(t)).collect(Collectors.toList()));
    }
    
}
