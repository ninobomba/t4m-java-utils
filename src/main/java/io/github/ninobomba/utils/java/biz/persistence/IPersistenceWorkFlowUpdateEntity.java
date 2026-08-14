package io.github.ninobomba.utils.java.biz.persistence;

/**
 * Updates and persists a previously resolved entity.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IPersistenceWorkFlowUpdateEntity<I, O, E extends Throwable> {
    O updateEntity(I input) throws E;
}