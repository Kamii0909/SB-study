package com.kien.tft.dao.converter;

import org.springframework.core.convert.converter.Converter;

import com.kien.tft.dao.dao.TftDao;
import com.kien.tft.model.Unit;

public class LocalUnitConverter implements Converter<String, Unit> {
    private TftDao tftDao;

    public LocalUnitConverter(TftDao tftDao){
        this.tftDao = tftDao;
    }

    @Override
    public Unit convert(String name) {
        return tftDao.getUnit(name);
    }
    
}
