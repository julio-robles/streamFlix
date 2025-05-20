package com.example.streamflix.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.streamflix.review.dto.ReviewCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerValidationIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void starsFueraDeRangoDevuelve400() throws Exception {
        ReviewCreateDto dto = new ReviewCreateDto(
                UUID.randomUUID(),   // movieId válido
                6,                   // ❌ fuera de rango 1-5
                "Comentario de prueba"
        );

        mvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}