package io.github.ninobomba.utils.java.tests.contracts.inputs;

public interface ITestAssertMinInput {

    void assertFailureOnMinInput(String input, String expectedErrorMessage);

    default void testMinInput(String input, int minLength) {
        requireNonNull(input);

        int inputLength = input.length();
        requireAtLeastMin(inputLength, minLength, "Input must be at least %d characters long");;
    }

    default void testIntegerMinValue(Integer input, int minValue) {
        requireNonNull(input);
        requireAtLeastMin(input, minValue, "Input must be at least %d");
    }

    default void testIntMinValue(int input, int minValue) {
        requireAtLeastMin(input, minValue, "Input must be at least %d");
    }

    private void requireNonNull(Object input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    private void requireAtLeastMin(int value, int minValue, String errorMessageTemplate) {
        if (value < minValue) {
            throw new IllegalArgumentException(errorMessageTemplate.formatted(minValue));
        }
    }
}