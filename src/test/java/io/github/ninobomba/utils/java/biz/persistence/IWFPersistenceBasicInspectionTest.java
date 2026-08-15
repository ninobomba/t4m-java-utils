package io.github.ninobomba.utils.java.biz.persistence;

import io.github.ninobomba.utils.java.biz.service.persistence.inspections.IWFPersistenceBasicInspection;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link IWFPersistenceBasicInspection}.
 * Samples:
 * ┃
 * 4. USAGE EXAMPLES                                                                                                                                                                                                              ┃
 * ┃
 * a)  findById().andThen(update).finallyDo(close)                                                                                                                                                                                ┃
 * ┃
 * IWFPersistenceBasicInspection<Long, User, Exception> findById =                                                                                                                                                                    ┃
 * IWFPersistenceBasicInspection.of(userRepository::findByIdOrThrow);                                                                                                                                                         ┃
 * ┃
 * IWFPersistenceBasicInspection<User, User, Exception> updateUser =                                                                                                                                                                  ┃
 * IWFPersistenceBasicInspection.of(userRepository::save);                                                                                                                                                                    ┃
 * ┃
 * IWFPersistenceBasicInspection<Long, User, Exception> wf = findById                                                                                                                                                                 ┃
 * .andThen(user -> {                                                                                                                                                                                                     ┃
 * user.setActive(true);                                                                                                                                                                                              ┃
 * return user;                                                                                                                                                                                                       ┃
 * })                                                                                                                                                                                                                     ┃
 * .flatMap(user -> updateUser)                                                                                                                                                                                           ┃
 * .finallyDo(() -> entityManager.clear());                                                                                                                                                                               ┃
 * ┃
 * User updated = wf.execute(42L);
 * <p>
 * ┃
 * b) with  onFailure + recover                                                                                                                                                                                                   ┃
 * ┃
 * IWFPersistenceBasicInspection<String, Account, Exception> loadAccount =                                                                                                                                                            ┃
 * IWFPersistenceBasicInspection.of(accountRepository::findByExternalId);                                                                                                                                                     ┃
 * ┃
 * Account account = loadAccount                                                                                                                                                                                                  ┃
 * .onFailure(ex -> log.warn("Failed account lookup", ex))                                                                                                                                                                ┃
 * .recover(ex -> Account.guest())                                                                                                                                                                                        ┃
 * .execute("ext-123");                                                                                                                                                                                                   ┃
 * ┃
 * c) service-layer chain across components (3 ops)
 *
 * @Transactional ┃
 * public OrderDto placeOrder(PlaceOrderCommand command) throws Exception {                                                                                                                                                       ┃
 * IWFPersistenceBasicInspection<PlaceOrderCommand, OrderDraft, Exception> validate =                                                                                                                                             ┃
 * IWFPersistenceBasicInspection.of(orderValidationComponent::validate);                                                                                                                                                  ┃
 * ┃
 * IWFPersistenceBasicInspection<OrderDraft, Order, Exception> persist =                                                                                                                                                          ┃
 * IWFPersistenceBasicInspection.of(orderPersistenceComponent::persist);                                                                                                                                                  ┃
 * ┃
 * IWFPersistenceBasicInspection<Order, OrderDto, Exception> toDto =                                                                                                                                                              ┃
 * IWFPersistenceBasicInspection.of(orderMapperComponent::toDto);                                                                                                                                                         ┃
 * ┃
 * return validate                                                                                                                                                                                                            ┃
 * .flatMap(draft -> persist)                                                                                                                                                                                         ┃
 * .flatMap(order -> toDto)                                                                                                                                                                                           ┃
 * .onSuccess(dto -> auditComponent.success(dto.id()))                                                                                                                                                                ┃
 * .onFailure(ex -> auditComponent.failure(command.customerId(), ex))                                                                                                                                                 ┃
 * .execute(command);                                                                                                                                                                                                 ┃
 * }
 */

class IWFPersistenceBasicInspectionTest {

    @Test
    void shouldChainFindByIdAndUpdateWithFinallyDo() throws Exception {
        var persistenceComponent = new UserPersistenceComponent();
        var closeCalled = new AtomicBoolean(false);

        IWFPersistenceBasicInspection<Long, User, Exception> findById =
                IWFPersistenceBasicInspection.of(persistenceComponent::findById);

        IWFPersistenceBasicInspection<User, User, Exception> update =
                IWFPersistenceBasicInspection.of(persistenceComponent::update);

        var workflow = findById
                .andThen(User::activate)
                .flatMap(user -> update)
                .finallyDo(() -> closeCalled.set(true));

        var result = workflow.execute(10L);

        assertNotNull(result);
        assertTrue(result.active());
        assertTrue(closeCalled.get());
    }

    @Test
    void shouldHandleOnFailureAndRecover() throws Exception {
        var capturedError = new AtomicReference<String>();

        IWFPersistenceBasicInspection<Long, User, Exception> loadUser =
                IWFPersistenceBasicInspection.<Long, User, Exception>of(id -> {
                            throw new Exception("entity not found");
                        })
                        .onFailure(error -> capturedError.set(error.getMessage()))
                        .recover(error -> new User(-1L, false));

        var recovered = loadUser.execute(99L);

        assertEquals("entity not found", capturedError.get());
        assertEquals(-1L, recovered.id());
        assertTrue(!recovered.active());
    }

    @Test
    void shouldChainServiceOperationsFromComponents() throws Exception {
        var validationComponent = new ValidationComponent();
        var persistenceComponent = new OrderPersistenceComponent();
        var mapperComponent = new OrderMapperComponent();

        IWFPersistenceBasicInspection<PlaceOrderCommand, OrderDraft, Exception> validate =
                IWFPersistenceBasicInspection.of(validationComponent::validate);

        IWFPersistenceBasicInspection<OrderDraft, Order, Exception> persist =
                IWFPersistenceBasicInspection.of(persistenceComponent::persist);

        IWFPersistenceBasicInspection<Order, OrderDto, Exception> mapToDto =
                IWFPersistenceBasicInspection.of(mapperComponent::toDto);

        var workflow = validate
                .flatMap(draft -> persist)
                .flatMap(order -> mapToDto)
                .peek(dto -> assertNotNull(dto.reference()));

        var result = workflow.execute(new PlaceOrderCommand("CUST-1", 2));

        assertEquals("ORDER-CUST-1", result.reference());
        assertEquals(2, result.totalItems());
    }

    @Test
    void shouldPreserveOriginalExceptionWhenFinallyFails() {
        var cleanupCalled = new AtomicBoolean(false);

        IWFPersistenceBasicInspection<Long, User, Exception> workflow =
                IWFPersistenceBasicInspection.<Long, User, Exception>of(id -> {
                            throw new IllegalStateException("primary failure");
                        })
                        .finallyDo(() -> {
                            cleanupCalled.set(true);
                            throw new IllegalArgumentException("cleanup failure");
                        });

        var exception = assertThrows(IllegalStateException.class, () -> workflow.execute(1L));

        assertTrue(cleanupCalled.get());
        assertEquals(1, exception.getSuppressed().length);
        assertEquals("cleanup failure", exception.getSuppressed()[0].getMessage());
    }

    private record User(Long id, boolean active) {
        private User activate() {
            return new User(id, true);
        }
    }

    private static final class UserPersistenceComponent {
        private User findById(Long id) throws Exception {
            if (id == null || id <= 0) {
                throw new Exception("invalid id");
            }
            return new User(id, false);
        }

        private User update(User user) throws Exception {
            if (user == null) {
                throw new Exception("user is required");
            }
            return user;
        }
    }

    private record PlaceOrderCommand(String customerId, int items) {
    }

    private record OrderDraft(String customerId, int items) {
    }

    private record Order(String reference, int totalItems) {
    }

    private record OrderDto(String reference, int totalItems) {
    }

    private static final class ValidationComponent {
        private OrderDraft validate(PlaceOrderCommand command) throws Exception {
            if (command == null || command.items() <= 0) {
                throw new Exception("invalid order command");
            }
            return new OrderDraft(command.customerId(), command.items());
        }
    }

    private static final class OrderPersistenceComponent {
        private Order persist(OrderDraft draft) throws Exception {
            if (draft == null) {
                throw new Exception("draft is required");
            }
            return new Order("ORDER-" + draft.customerId(), draft.items());
        }
    }

    private static final class OrderMapperComponent {
        private OrderDto toDto(Order order) throws Exception {
            if (order == null) {
                throw new Exception("order is required");
            }
            return new OrderDto(order.reference(), order.totalItems());
        }
    }
}