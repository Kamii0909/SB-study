package com.kien.demo.dao.localdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kien.demo.dao.TftGraph;
import com.kien.demo.dao.preload.Preload;
import com.kien.demo.model.Unit;

public class LocalDbTest {

    @Test
    @DisplayName("Load context")
    void testInit(){

        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext("src/main/resources/dao/db.xml");
        TftGraph db = appContext.getBean(TftGraph.class);
        Preload preload = appContext.getBean(Preload.class);
        System.out.println(db.getPathAsList(preload.getUnit("Shen"), preload.getUnit("Shi Oh Yu")));
        
    }
    
}
