package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Handles workflow persistence concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFPersistencePersist<I, O, E extends Throwable> {

    /**
     * Applies persistence handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O persist(I input) throws E;
}