package io.github.ninobomba.utils.java.env;

public final class EnvUtils {

    private EnvUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getPropertyOrEnvVar(String varName, String defaultValue) {
        if (varName == null || varName.isBlank()) {
            throw new IllegalArgumentException("Variable name must not be null or blank");
        }

        String value = System.getProperty(varName);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = System.getenv(varName);
        if (value != null && !value.isBlank()) {
            return value;
        }

        return defaultValue;
    }
}