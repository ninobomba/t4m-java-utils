package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Asserts hard changes on a previously resolved entity and persists them.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceAssertHardChangesOnEntities<I, O, E extends Throwable> {
    O assertHardChangesOnEntities(I input) throws E;
}