package com.example.streamflix.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.streamflix.rental.dto.RentalCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test void crearAlquiler201() throws Exception {
        RentalCreateDto dto = new RentalCreateDto(UUID.randomUUID(), UUID.randomUUID(), "HD");

        mvc.perform(post("/api/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}