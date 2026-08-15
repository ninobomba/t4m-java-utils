package io.github.ninobomba.utils.java.biz.service.design;

/**
 * Handles workflow cohesion concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFDesignCohesion<I, O, E extends Throwable> {

    /**
     * Applies cohesion handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O improveCohesion(I input) throws E;
}