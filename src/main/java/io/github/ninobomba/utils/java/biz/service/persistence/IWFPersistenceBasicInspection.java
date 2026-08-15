package io.github.ninobomba.utils.java.biz.service.persistence;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Immutable, thread-safe orchestrator for persistence workflows.
 * <p>
 * This contract models a synchronous, monadic pipeline similar to a Try-like composition:
 * each combinator returns a new workflow instance and preserves the exception channel {@code E}.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceBasicInspection<I, O, E extends Throwable> extends
        IWFPersistenceResolveEntity<I, O, E>,
        IWFPersistenceUpdateEntity<I, O, E>,
        IWFPersistenceValidateEntity<I, O, E> {

    /**
     * Executes this workflow.
     *
     * @param input workflow input
     * @return workflow output
     * @throws E workflow exception
     */
    O execute(I input) throws E;

    @Override
    default O resolveEntity(I input) throws E {
        return execute(input);
    }

    @Override
    default O updateEntity(I input) throws E {
        return execute(input);
    }

    @Override
    default O validateEntity(I input) throws E {
        return execute(input);
    }

    /**
     * Creates a workflow from a checked function.
     */
    static <I, O, E extends Throwable> IWFPersistenceBasicInspection<I, O, E> of(
            ThrowingFunction<? super I, ? extends O, ? extends E> operation
    ) {
        Objects.requireNonNull(operation, "operation cannot be null");
        return operation::apply;
    }

    /**
     * Chains the next step with the output of this workflow.
     */
    default <R> IWFPersistenceBasicInspection<I, R, E> andThen(
            Function<? super O, ? extends R> next
    ) {
        Objects.requireNonNull(next, "next cannot be null");
        return andThenChecked(next::apply);
    }

    /**
     * Checked variant of {@link #andThen(Function)}.
     */
    default <R> IWFPersistenceBasicInspection<I, R, E> andThenChecked(
            ThrowingFunction<? super O, ? extends R, ? extends E> next
    ) {
        Objects.requireNonNull(next, "next cannot be null");
        return input -> next.apply(execute(input));
    }

    /**
     * Alias for {@link #andThen(Function)}.
     */
    default <R> IWFPersistenceBasicInspection<I, R, E> map(
            Function<? super O, ? extends R> mapper
    ) {
        return andThen(mapper);
    }

    /**
     * Alias for {@link #map(Function)}.
     */
    default <R> IWFPersistenceBasicInspection<I, R, E> transform(
            Function<? super O, ? extends R> transformer
    ) {
        return map(transformer);
    }

    /**
     * Monadic composition where the next workflow is produced from the previous result.
     */
    default <R> IWFPersistenceBasicInspection<I, R, E> flatMap(
            Function<? super O, ? extends IWFPersistenceBasicInspection<O, R, E>> next
    ) {
        Objects.requireNonNull(next, "next cannot be null");
        return input -> {
            O current = execute(input);
            IWFPersistenceBasicInspection<O, R, E> chained = Objects.requireNonNull(
                    next.apply(current), "next workflow cannot be null"
            );
            return chained.execute(current);
        };
    }

    /**
     * Pre-composition of this workflow input.
     */
    default <V> IWFPersistenceBasicInspection<V, O, E> compose(
            Function<? super V, ? extends I> before
    ) {
        Objects.requireNonNull(before, "before cannot be null");
        return input -> execute(before.apply(input));
    }

    /**
     * Side-effect on successful completion.
     */
    default IWFPersistenceBasicInspection<I, O, E> onSuccess(
            Consumer<? super O> action
    ) {
        Objects.requireNonNull(action, "action cannot be null");
        return input -> {
            O result = execute(input);
            action.accept(result);
            return result;
        };
    }

    /**
     * Side-effect on failure.
     */
    default IWFPersistenceBasicInspection<I, O, E> onFailure(
            Consumer<? super Throwable> action
    ) {
        Objects.requireNonNull(action, "action cannot be null");
        return input -> {
            try {
                return execute(input);
            } catch (Throwable throwable) {
                action.accept(throwable);
                throw throwable;
            }
        };
    }

    /**
     * Recovers a failed workflow with a fallback value.
     */
    default IWFPersistenceBasicInspection<I, O, E> recover(
            Function<? super Throwable, ? extends O> fallback
    ) {
        Objects.requireNonNull(fallback, "fallback cannot be null");
        return input -> {
            try {
                return execute(input);
            } catch (Throwable throwable) {
                return fallback.apply(throwable);
            }
        };
    }

    /**
     * Always executes finalizer action. If both workflow and finalizer fail, the finalizer
     * exception is added as suppressed to the original workflow exception.
     */
    default IWFPersistenceBasicInspection<I, O, E> finallyDo(Runnable finalizer) {
        Objects.requireNonNull(finalizer, "finalizer cannot be null");
        return input -> {
            Throwable originalError = null;
            try {
                return execute(input);
            } catch (Throwable throwable) {
                originalError = throwable;
                throw throwable;
            } finally {
                try {
                    finalizer.run();
                } catch (Throwable finalizerError) {
                    if (originalError != null) {
                        originalError.addSuppressed(finalizerError);
                    } else {
                        throw finalizerError;
                    }
                }
            }
        };
    }

    /**
     * Always executes the finalizer action with the input object.
     */
    default IWFPersistenceBasicInspection<I, O, E> finallyDo(Consumer<? super I> finalizer) {
        Objects.requireNonNull(finalizer, "finalizer cannot be null");
        return input -> finallyDo(() -> finalizer.accept(input)).execute(input);
    }

    /**
     * Non-mutating inspection of successful values.
     */
    default IWFPersistenceBasicInspection<I, O, E> peek(Consumer<? super O> inspector) {
        return onSuccess(inspector);
    }

    /**
     * Identity transform helper.
     */
    default IWFPersistenceBasicInspection<I, O, E> identity() {
        return map(UnaryOperator.identity());
    }

    @FunctionalInterface
    interface ThrowingFunction<T, R, E extends Throwable> {
        R apply(T input) throws E;
    }
}