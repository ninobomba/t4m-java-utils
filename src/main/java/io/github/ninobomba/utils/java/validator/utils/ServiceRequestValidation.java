package io.github.ninobomba.utils.java.validator.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public final class ServiceRequestValidation {

    private ServiceRequestValidation() {
    }

    public static <T> ValidationResult validate(
            T request,
            Predicate<T> isValidPredicate,
            Function<T, Object> maskedRequestSupplier,
            Function<T, Iterable<String>> validationErrorsSupplier
    ) {
        if (request == null) {
            log.warn("validate - Invalid request: null");
            return ValidationResult.invalidResult("Request is null");
        }

        if (isValidPredicate.test(request)) {
            return ValidationResult.validResult();
        }

        log.warn("validate - Invalid request: {}", maskedRequestSupplier.apply(request));

        var validationErrors = validationErrorsSupplier.apply(request);
        if (validationErrors != null) {
            for (var error : validationErrors) {
                log.warn("validate - Validation error: {}", error);
            }
        }

        return ValidationResult.invalidResult(joinValidationErrors(validationErrors));
    }

    private static String joinValidationErrors(Iterable<String> validationErrors) {
        if (validationErrors == null) {
            return "Validation details unavailable";
        }

        var iterator = validationErrors.iterator();
        if (!iterator.hasNext()) {
            return "Validation details unavailable";
        }

        var builder = new StringBuilder();
        appendFirst(iterator, builder);
        appendRemaining(iterator, builder);
        return builder.toString();
    }

    private static void appendFirst(Iterator<String> iterator, StringBuilder builder) {
        builder.append(iterator.next());
    }

    private static void appendRemaining(Iterator<String> iterator, StringBuilder builder) {
        while (iterator.hasNext()) {
            builder.append(System.lineSeparator()).append(iterator.next());
        }
    }

    public record ValidationResult(boolean valid, String validationErrorsAsString) {

        public static ValidationResult validResult() {
            return new ValidationResult(true, "");
        }

        public static ValidationResult invalidResult(String validationErrorsAsString) {
            return new ValidationResult(false, validationErrorsAsString);
        }
    }
}