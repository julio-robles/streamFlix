package com.example.streamflix.payment.dto;

import com.example.streamflix.payment.PaymentMethod;
import com.example.streamflix.payment.validation.ValidPayment;
import jakarta.validation.constraints.*;

@ValidPayment
public record PaymentDto(

        @NotNull
        PaymentMethod method,

        /* CARD */
        String cardNumber,

        /* PAYPAL */
        String paypalEmail
) {}