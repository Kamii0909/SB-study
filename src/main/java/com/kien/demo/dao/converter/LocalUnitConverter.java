package com.kien.demo.dao.converter;

import org.springframework.core.convert.converter.Converter;

import com.kien.demo.model.Unit;

public class LocalUnitConverter implements Converter<String, Unit> {
    @Override
    public Unit convert(String name) {
        return new Unit(name);
    }
    
}
