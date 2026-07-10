package io.github.ninobomba.utils.java.env;

public final class EnvUtils {

    private static final String INVALID_VARIABLE_NAME_MESSAGE = "Variable name must not be null or blank";

    private EnvUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getProperty(String varName, String defaultValue) {
        validateVariableName(varName);
        String value = System.getProperty(varName);
        return hasText(value) ? value : defaultValue;
    }

    public static String getEnvVar(String varName, String defaultValue) {
        validateVariableName(varName);
        String value = System.getenv(varName);
        return hasText(value) ? value : defaultValue;
    }

    public static String getPropertyOrEnvVar(String varName, String defaultValue) {
        validateVariableName(varName);
        String propertyValue = System.getProperty(varName);
        if (hasText(propertyValue)) {
            return propertyValue;
        }
        String envValue = System.getenv(varName);
        return hasText(envValue) ? envValue : defaultValue;
    }

    private static void validateVariableName(String varName) {
        if (!hasText(varName)) {
            throw new IllegalArgumentException(INVALID_VARIABLE_NAME_MESSAGE);
        }
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}