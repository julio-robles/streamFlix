package com.example.streamflix.stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Test MockMvc: ROLE_PREMIUM 200, ROLE_USER 403 en /api/v1/stream/{movieId}

@SpringBootTest
@AutoConfigureMockMvc
class StreamAuthorizationIT {

    @Autowired MockMvc mvc;

    @Test
    @WithMockUser(roles = "PREMIUM")
    void premiumPuedeHacerStreaming() throws Exception {
        mvc.perform(get("/api/v1/stream/abc123")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userNormalRecibe403() throws Exception {
        mvc.perform(get("/api/v1/stream/abc123")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isForbidden());
    }
}
