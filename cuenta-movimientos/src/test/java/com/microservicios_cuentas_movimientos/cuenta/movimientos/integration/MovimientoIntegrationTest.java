package com.microservicios_cuentas_movimientos.cuenta.movimientos.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCrearMovimiento() throws Exception {

        String movimientoJson = "{ \"accountNumber\": \"123456789\", \"amount\": 100.0 }";


        mockMvc.perform(post("/api/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movimientoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.type").value("CREDITO"));
    }
}
