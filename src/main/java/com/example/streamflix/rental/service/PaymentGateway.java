package com.example.streamflix.rental.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGateway {
    void charge(BigDecimal amount, UUID userId);
}
