package com.kien.tft.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(locations = "/config/dispatcher-config.xml")
public class TftRestControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testContext(){
        assertTrue(context.containsBean("restController"));
    }

    @Test
    void testAdjaUnits() throws Exception {
        mockMvc.perform(get("/unit/adja").param("name", "Leesin", "Diana")).andExpect(status().isOk());
    }

    @Test
    void testGetPathAsList() throws Exception{
        mockMvc.perform(get("/path/list").param("src", "Leesin").param("des", "Daeja")).andDo(print()).andExpect(status().isOk());
    }
}
