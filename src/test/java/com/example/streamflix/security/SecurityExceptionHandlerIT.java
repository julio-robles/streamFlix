package com.example.streamflix.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityExceptionHandlerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void invalidApiKeyDevuelve401YHeader() throws Exception {
        String json = mvc.perform(get("/test/invalid-key"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string(HttpHeaders.WWW_AUTHENTICATE, "ApiKey"))
                .andReturn().getResponse().getContentAsString();

        JsonNode body = mapper.readTree(json);
        assertThat(body.get("error").asText()).isEqualTo("unauthorized");
    }

    @Test
    void rateLimitDevuelve429YHeader() throws Exception {
        String json = mvc.perform(get("/test/rate-limit"))
                .andExpect(status().isTooManyRequests())
                .andExpect(header().string(HttpHeaders.RETRY_AFTER, "60"))
                .andReturn().getResponse().getContentAsString();

        JsonNode body = mapper.readTree(json);
        assertThat(body.get("error").asText()).isEqualTo("too_many_requests");
    }
}
