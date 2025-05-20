package com.example.streamflix.payment.validation;

import com.example.streamflix.payment.PaymentMethod;
import com.example.streamflix.payment.dto.PaymentDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PaymentValidator implements ConstraintValidator<ValidPayment, PaymentDto> {

    private static final Pattern CARD_REGEX = Pattern.compile("\\d{16}");

    @Override
    public boolean isValid(PaymentDto dto, ConstraintValidatorContext ctx) {
        if (dto == null) return true;          // @NotNull irá en la capa superior

        boolean valid = switch (dto.method()) {
            case CARD   -> CARD_REGEX.matcher(dto.cardNumber() == null ? "" : dto.cardNumber()).matches();
            case PAYPAL -> dto.paypalEmail() != null && dto.paypalEmail().matches("[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}");
        };

        if (!valid) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("Campos de pago incompatibles con method")
                    .addConstraintViolation();
        }
        return valid;
    }
}