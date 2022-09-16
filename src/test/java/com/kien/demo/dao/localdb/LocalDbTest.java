package com.kien.demo.dao.localdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.kien.demo.dao.Database;

public class LocalDbTest {

    @Test
    @DisplayName("Load context")
    void testInit(){

        FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext("src/main/resources/dao/db.xml");
        Database db = appContext.getBean(Database.class);
        System.out.println(Arrays.deepToString(db.getDistance()[][]));
        
    }
    
}
