package io.github.ninobomba.utils.java.biz.persistence;

/**
 * Validates an entity or command prior to persistence actions.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IPersistenceWorkFlowValidateEntity<I, O, E extends Throwable> {
    O validateEntity(I input) throws E;
}