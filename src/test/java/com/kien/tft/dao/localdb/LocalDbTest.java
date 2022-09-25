package com.kien.tft.dao.localdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LocalDbTest {

    @Test
    @DisplayName("Load context")
    void testInit(){

        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext("src/main/resources/dao/db.xml");
        appContext.close();
        
    }
    
}
