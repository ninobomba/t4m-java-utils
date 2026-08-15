package io.github.ninobomba.utils.java.biz.service.auditoring;

/**
 * Handles workflow-auditable event persistence concerns.
 *
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface IWFAuditableEventPersistence<I, O, E extends Throwable> {

    /**
     * Applies workflow-auditable event persistence handling to the given input.
     *
     * @param input input to process; null-handling is implementation-specific
     * @return processed output
     */
    O persistAuditableEvent(I input) throws E;
}