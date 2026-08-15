package io.github.ninobomba.utils.java.biz.service.security;

/**
 * Handles workflow security concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFSecurity<I, O, E extends Throwable> {

    /**
     * Applies security handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O secure(I input) throws E;
}