package io.github.ninobomba.utils.java.biz.service.processing;

/**
 * Handles workflow orchestration concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFProcessingOrchestration<I, O, E extends Throwable> {

    /**
     * Applies orchestration handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O orchestrate(I input) throws E;
}