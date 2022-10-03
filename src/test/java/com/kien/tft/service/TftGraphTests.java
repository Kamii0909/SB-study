package com.kien.tft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
