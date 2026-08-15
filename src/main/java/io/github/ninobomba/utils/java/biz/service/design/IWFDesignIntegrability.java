package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow integrability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignIntegrability<I, O, E extends Throwable> {

    /**
     * Applies integrability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O integrate(I input) throws E;
}