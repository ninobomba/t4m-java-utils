package io.github.ninobomba.utils.java.biz.service;

/**
 * Handles workflow resilience concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWorkflowResilience<I, O, E extends Throwable> {

    /**
     * Applies resilience handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O hardenResilience(I input) throws E;
}