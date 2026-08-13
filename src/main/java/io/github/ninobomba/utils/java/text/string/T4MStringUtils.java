package io.github.ninobomba.utils.java.text.string;

import org.apache.commons.lang3.StringUtils;

public final class T4MStringUtils {

    private T4MStringUtils() {
        // Private constructor to prevent instantiation
    }

    public static String normalize(String input) {
        if (input == null || input.isBlank()) {
            throw new RuntimeException("input value cannot be blank");
        }
        return StringUtils.normalizeSpace(input);
    }
}