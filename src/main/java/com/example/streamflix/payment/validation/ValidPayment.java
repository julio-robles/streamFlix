package com.example.streamflix.payment.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPayment {

    String message() default "Payment data invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}