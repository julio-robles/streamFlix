package com.example.streamflix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Simula 101 peticiones desde la misma IP y espera 429 en la última.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RateLimitInterceptorIT {

    @Autowired MockMvc mvc;

    @Test
    void limiteDe100PeticionesPorMinuto() throws Exception {
        // 100 peticiones OK
        for (int i = 0; i < 100; i++) {
            mvc.perform(get("/api/v1/rentals")  // usa cualquier endpoint existente
                            .param("userId", "00000000-0000-0000-0000-000000000000")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }
        // 101 => 429
        mvc.perform(get("/api/v1/rentals")
                        .param("userId", "00000000-0000-0000-0000-000000000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests());
    }
}