package com.example.streamflix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración básica de Spring Security.
 * Crea la jerarquía: ROLE_ADMIN > ROLE_USER
 */
@Configuration
@EnableMethodSecurity   // habilita @PreAuthorize, @PostAuthorize, etc.
public class SecurityConfig {

    /** Jerarquía de roles (ADMIN incluye USER) */
    @Bean
    RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl rh = new RoleHierarchyImpl();
        // ADMIN hereda PREMIUM y USER; PREMIUM hereda USER
        rh.setHierarchy("""
        ROLE_ADMIN   > ROLE_PREMIUM
        ROLE_PREMIUM > ROLE_USER
        """);
        return rh;
    }

    /** Política HTTP mínima (todo autenticado) */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }
}
