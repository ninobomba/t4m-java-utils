package io.github.ninobomba.utils.java.biz.service.persistence;

/**
 * Asserts soft changes on a previously resolved entity and persists them.
 *
 * @param <I> input type
 * @param <O> output type
 * @param <E> checked or unchecked exception type
 */
@FunctionalInterface
public interface IWFPersistenceAssertSoftChangesOnEntities<I, O, E extends Throwable> {
    O assertSoftChangesOnEntities(I input) throws E;
}