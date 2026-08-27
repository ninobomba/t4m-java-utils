package io.github.ninobomba.utils.java.patterns.process;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

public sealed interface OperationResult<T>
        permits OperationResult.Success, OperationResult.Failure {

    record Success<T>(T data) implements OperationResult<T> {
    }

    record Failure<T>(String message) implements OperationResult<T> {
    }

    static <T> OperationResult<T> success(T value) {
        return new Success<>(value);
    }

    static <T> OperationResult<T> success() {
        return new Success<T>(null);
    }

    static <T> OperationResult<T> failure(String message) {
        return new Failure<>(message);
    }

    default boolean isFailure() {
        return this instanceof Failure<?>;
    }

    default boolean isSuccess() {
        return this instanceof Success<?>;
    }

    default T get() {
        return getOrNull();
    }

    default String getError() {
        return getFailureMessage();
    }

    default String getFailureMessage() {
        if (this instanceof Failure<T>(String message)) return message;
        return null;
    }

    default T getOrNull() {
        if (this instanceof Success<T>(T data)) return data;
        return null;
    }

    default T getOrElse(T fallback) {
        if (this instanceof Success<T>(T data)) return data;
        return fallback;
    }

    default T getOrThrow() {
        return switch (this) {
            case Success<T> s -> s.data();
            case Failure<T> f -> throw OperationFailedException.of(extractErrorMessages(f.message()));
        };
    }

    private static List<String> extractErrorMessages(String message) {
        if (message == null || message.isBlank()) {
            return List.of("Operation failed");
        }
        return List.of(message);
    }

    @Getter
    final class OperationFailedException extends RuntimeException {
        private final List<String> messages;

        public OperationFailedException(List<String> messages) {
            super(String.join("; ", Objects.requireNonNull(messages, "messages")));
            this.messages = List.copyOf(messages);
        }

        public static OperationFailedException of(List<String> messages) {
            return new OperationFailedException(messages);
        }
    }
}