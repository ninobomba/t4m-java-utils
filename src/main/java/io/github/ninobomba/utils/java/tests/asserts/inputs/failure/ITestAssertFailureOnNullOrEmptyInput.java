package io.github.ninobomba.utils.java.tests.asserts.inputs.failure;

import java.util.Collection;
import java.util.Map;

public interface ITestAssertFailureOnNullOrEmptyInput {

    boolean assertFailureOnNullOrEmptyInput(String input);

    default void checkIsNullOrEmpty(Object input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    default void checkIsNullOrEmpty(String[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(int[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(long[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(double[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(float[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(boolean[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(byte[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(char[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(short[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(Object[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(Collection<?> input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(Map<?, ?> input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    default void checkIsNullOrEmpty(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

}