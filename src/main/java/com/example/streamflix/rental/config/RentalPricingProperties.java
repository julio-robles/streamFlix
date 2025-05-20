package com.example.streamflix.rental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "rental.price")
public record RentalPricingProperties(BigDecimal hd, BigDecimal uhd) {}
