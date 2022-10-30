package com.kien.tft.dao.converter;

import org.springframework.core.convert.converter.Converter;

import com.kien.tft.dao.dao.TftDao;
import com.kien.tft.model.Trait;

public class LocalTraitConverter implements Converter<String, Trait> {
    private TftDao tftDao;

    public LocalTraitConverter(TftDao tftDao){
        this.tftDao = tftDao;
    }

    @Override
    public Trait convert(String name) {
        return tftDao.getTrait(name);
    }
}
