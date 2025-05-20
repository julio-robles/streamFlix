package com.example.streamflix.payment;

import com.example.streamflix.payment.dto.PaymentDto;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void pagoConTarjetaValido() {
        PaymentDto dto = new PaymentDto(PaymentMethod.CARD, "1234567812345678", null);
        Set<ConstraintViolation<PaymentDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void pagoPaypalValido() {
        PaymentDto dto = new PaymentDto(PaymentMethod.PAYPAL, null, "user@example.com");
        Set<ConstraintViolation<PaymentDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void pagoInvalidoTarjetaConEmail() {
        PaymentDto dto = new PaymentDto(PaymentMethod.CARD, null, "user@example.com");
        Set<ConstraintViolation<PaymentDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
    }
}