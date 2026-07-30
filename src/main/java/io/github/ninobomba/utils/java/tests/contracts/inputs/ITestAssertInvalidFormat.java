package io.github.ninobomba.utils.java.tests.contracts.inputs;

public interface ITestAssertInvalidFormat {

    void assertFailureOnInvalidFormat(String input, String regex);

    default void testFormat(String input, String regex) {
        requireInputAndRegex(input, regex);

        boolean matchesInvalidRegex = input.matches(regex);
        if (!matchesInvalidRegex) {
            throw new AssertionError("Input does not matches the regex. Input: %s, Regex: %s".formatted(input, regex));
        }
    }

    private void requireInputAndRegex(String input, String regex) {
        if (input == null || regex == null) {
            throw new IllegalArgumentException("Input and regex must not be null");
        }
        if(regex.isEmpty()) {
            throw new IllegalArgumentException("Regex must not be empty");
        }
        if(input.isEmpty()) {
            throw new IllegalArgumentException("Input must not be empty");
        }
    }

}