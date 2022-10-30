package com.kien.tft.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "/config/service/service.xml")
public class TftGraphTests {

    @Autowired
    TftService tftService;


    @Test
    void testTraitOf() {
        assertTrue(tftService.traitOf(tftService.getUnitFromName("Daeja")).contains(tftService.getTraitFromName("Dragon")));
    }
}
