package com.securetrustbank.registration.customannotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateAgeCheck.class)
public @interface AgeValidator {
    Class<?>[] groups() default {};

    String message() default "Your age should be atLeast 18 years!!";

    Class<? extends Payload>[] payload() default {};
}
