package com.kien.tft.dao.localdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kien.tft.model.Unit;
import com.kien.tft.service.TftGraph;

public class LocalDbTest {

    @Test
    @DisplayName("Load context")
    void testInit(){

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("config/service/service.xml");
        System.out.println(appContext.getBean(TftGraph.class).getPathAsList(new Unit("Zoe"), new Unit("Leesin")));
        appContext.close();
        
    }
    
}
