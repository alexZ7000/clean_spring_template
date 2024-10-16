package com.example.template.shared.helpers.validators.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ValidationUtil {
    public static <T> Map<String, String> validateAndGetViolations(final Validator validator, final T entity) {
        final Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            throw new AssertionError("Expected validation errors, but none were found.");
        }

        final Map<String, String> violationMessages = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            final String propertyPath = violation.getPropertyPath().toString();
            final String message = violation.getMessage();
            violationMessages.put(propertyPath, message);
            System.out.println("Field: " + propertyPath + ", Error: " + message);
        }
        return violationMessages;
    }
}
