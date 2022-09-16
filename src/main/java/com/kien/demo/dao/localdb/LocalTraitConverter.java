package com.kien.demo.dao.localdb;

import org.springframework.core.convert.converter.Converter;

import com.kien.demo.model.Trait;

public class LocalTraitConverter implements Converter<String, Trait> {
    @Override
    public Trait convert(String name) {
        return new Trait(name);
    }
}
