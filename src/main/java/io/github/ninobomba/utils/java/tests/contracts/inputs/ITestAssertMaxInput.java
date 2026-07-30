package io.github.ninobomba.utils.java.tests.contracts.inputs;

public interface ITestAssertMaxInput {

    void assertFailureOnMaxInput(String input, int maxLength);

    default void testStringMaxLength(String input, int maxLength) {
        requireNonNull(input);
        int inputLength = input.length();
        requireNotGreaterThanMax(inputLength, maxLength);
    }

    default void testIntegerMaxValue(Integer input, int maxLength) {
        requireNonNull(input);
        requireNotGreaterThanMax(input, maxLength);
    }

    default void testIntMaxValue(int input, int maxLength) {
        requireNotGreaterThanMax(input, maxLength);
    }

    private void requireNonNull(Object input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    private void requireNotGreaterThanMax(int value, int maxLength) {
        if (value > maxLength) {
            throw new IllegalArgumentException("Input exceeds maximum length");
        }
    }
}