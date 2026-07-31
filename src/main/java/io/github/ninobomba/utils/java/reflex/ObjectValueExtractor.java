package io.github.ninobomba.utils.java.reflex;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public final class ObjectValueExtractor {

    private static final String MISSING_REQUIRED_KEY_MESSAGE =
            "extractRequiredValue() - Missing required key: {}";
    private static final String INVALID_TYPE_MESSAGE =
            "extractRequiredValue() - Invalid type for key: {}. Expected: {}, actual: {}";

    private ObjectValueExtractor() {
    }

    public static <T> Optional<T> extractRequiredValue(
            Map<String, Object> valuesByKey,
            String key,
            Class<T> expectedType
    ) {
        var value = valuesByKey.get(key);

        if (isMissingRequiredValue(key, value)) {
            return Optional.empty();
        }

        if (isInvalidType(key, expectedType, value)) {
            return Optional.empty();
        }

        return Optional.of(expectedType.cast(value));
    }

    private static boolean isMissingRequiredValue(String key, Object value) {
        if (value != null) {
            return false;
        }

        log.warn(MISSING_REQUIRED_KEY_MESSAGE, key);
        return true;
    }

    private static <T> boolean isInvalidType(String key, Class<T> expectedType, Object value) {
        if (expectedType.isInstance(value)) {
            return false;
        }

        log.warn(
                INVALID_TYPE_MESSAGE,
                key,
                expectedType.getSimpleName(),
                value.getClass().getSimpleName()
        );
        return true;
    }
}