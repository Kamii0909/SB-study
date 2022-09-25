package com.kien.tft.dao.converter;

import org.springframework.core.convert.converter.Converter;

import com.kien.tft.model.Unit;

public class LocalUnitConverter implements Converter<String, Unit> {
    @Override
    public Unit convert(String name) {
        return new Unit(name);
    }
    
}
