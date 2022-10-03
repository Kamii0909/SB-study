package com.kien.tft.dao.preload;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.kien.tft.model.Unit;

@SpringJUnitConfig(locations = "/config/dao/localdb/preload.xml")
public class LocalPreloadTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoad(){
        assertTrue(applicationContext.getBean(LocalPreload.class).getUnits().contains(new Unit("Volibear")));
    }
}
