package com.kien.tft.dao.preload;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalPreloadTest {

    @Test
    public void contextLoad(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config/dao/localdb/preload.xml");

        System.out.println(context.getBean(LocalPreload.class).getTraits());
        context.close();
    }
}
