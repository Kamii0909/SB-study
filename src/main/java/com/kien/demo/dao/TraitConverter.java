package com.kien.demo.dao;

import org.springframework.core.convert.converter.*;

import com.kien.demo.model.Trait;

public class TraitConverter implements Converter<String, Trait> {

    @Override
    public Trait convert(String arg0) {
        return new Trait(arg0);
    }
    
}
