package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow reusability concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignReusability<I, O, E extends Throwable> {

    /**
     * Applies reusability handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O reuse(I input) throws E;
}