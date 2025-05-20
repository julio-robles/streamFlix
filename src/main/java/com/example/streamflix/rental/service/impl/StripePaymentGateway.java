package com.example.streamflix.rental.service.impl;

import com.stripe.Stripe;
import com.example.streamflix.rental.service.PaymentGateway;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Implementación (stub) de Stripe.
 * Registrada como bean "stripe" para @Qualifier("stripe").
 */
@Component("stripe")
@Slf4j
public class StripePaymentGateway implements PaymentGateway {

    /** Clave secreta de Stripe (inyectada desde application.yml o variable de entorno) */
    @Value("${stripe.api-key:}")   // ← no final → no va en el constructor
    private String apiKey;

    @PostConstruct
    void init() {
        Stripe.apiKey = apiKey;
        log.info("Stripe gateway inicializado (key presente: {})", !apiKey.isBlank());
    }

    @Override
    public void charge(BigDecimal amount, UUID userId) {
        // Stub: solo logeamos. Sustituye por la llamada real a Stripe si lo necesitas.
        log.info("COBRO → {} € al usuario {} mediante Stripe (simulado)", amount, userId);
    }
}