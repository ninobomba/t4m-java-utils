package io.github.ninobomba.utils.java.biz.service.accessibility;

/**
 * Handles workflow accessibility concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFAccessibility<I, O, E extends Throwable> {

    /**
     * Applies accessibility handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveAccessibility(I input) throws E;
}