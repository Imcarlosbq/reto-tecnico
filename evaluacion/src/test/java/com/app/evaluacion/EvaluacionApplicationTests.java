package com.app.evaluacion;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EvaluacionApplicationTests {

    @Autowired
    MockMvc mock;

    @Test
    void contextLoads() {
    }

    @Order(0)
    @Test
    void testListarClientes() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/cliente/list")).andDo(MockMvcResultHandlers.print());
    }

}
