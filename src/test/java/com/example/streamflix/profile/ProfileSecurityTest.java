package com.example.streamflix.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifica que ROLE_ADMIN accede a un método con @PreAuthorize("hasRole('USER')")
 */
@SpringBootTest
class ProfileSecurityTest {

    @Autowired ProfileService service;

    @Test
    @WithMockUser(roles = "ADMIN")   // crea usuario con ROLE_ADMIN
    void adminPuedeListarPerfiles() {
        assertThat(service.getProfiles()).isNotNull();
    }
}