package com.kien.tft.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServiceTests {
    
    @Test
    void testService(){
        FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext("src/main/resources/service/service.xml");
        TftService tftService = fs.getBean(TftService.class);

        System.out.println(tftService.avgPathLength(tftService.allUnits())
        .entrySet().stream()
        .max((e1, e2) -> Double.compare(e1.getValue(), e2.getValue()))
        .orElse(null).getKey()
        );
        fs.close();
    }
}
