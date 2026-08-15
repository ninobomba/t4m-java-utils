package io.github.ninobomba.utils.java.biz.service.configuration;

/**
 * Handles workflow customizability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFConfigurationCustomizability<I, O, E extends Throwable> {

    /**
     * Applies customizability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O customize(I input) throws E;
}