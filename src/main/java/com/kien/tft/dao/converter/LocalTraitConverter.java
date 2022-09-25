package com.kien.tft.dao.converter;

import org.springframework.core.convert.converter.Converter;

import com.kien.tft.model.Trait;

public class LocalTraitConverter implements Converter<String, Trait> {
    @Override
    public Trait convert(String name) {
        return new Trait(name);
    }
}
