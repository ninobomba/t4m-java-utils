package io.github.ninobomba.utils.java.biz.service.configuration;

/**
 * Handles workflow configuration concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFConfiguration<I, O, E extends Throwable> {

    /**
     * Applies configuration handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O configure(I input) throws E;
}