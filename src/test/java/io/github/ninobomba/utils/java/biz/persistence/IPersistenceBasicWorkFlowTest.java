package io.github.ninobomba.utils.java.biz.persistence;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link IPersistenceBasicWorkFlow}.
 * Samples:
 *                                                                                                                                                                                                                                  ┃
 *    4. USAGE EXAMPLES                                                                                                                                                                                                              ┃
 *                                                                                                                                                                                                                                   ┃
 *    a)  findById().andThen(update).finallyDo(close)                                                                                                                                                                                ┃
 *                                                                                                                                                                                                                                   ┃
 *    IPersistenceBasicWorkFlow<Long, User, Exception> findById =                                                                                                                                                                    ┃
 *            IPersistenceBasicWorkFlow.of(userRepository::findByIdOrThrow);                                                                                                                                                         ┃
 *                                                                                                                                                                                                                                   ┃
 *    IPersistenceBasicWorkFlow<User, User, Exception> updateUser =                                                                                                                                                                  ┃
 *            IPersistenceBasicWorkFlow.of(userRepository::save);                                                                                                                                                                    ┃
 *                                                                                                                                                                                                                                   ┃
 *    IPersistenceBasicWorkFlow<Long, User, Exception> wf = findById                                                                                                                                                                 ┃
 *            .andThen(user -> {                                                                                                                                                                                                     ┃
 *                user.setActive(true);                                                                                                                                                                                              ┃
 *                return user;                                                                                                                                                                                                       ┃
 *            })                                                                                                                                                                                                                     ┃
 *            .flatMap(user -> updateUser)                                                                                                                                                                                           ┃
 *            .finallyDo(() -> entityManager.clear());                                                                                                                                                                               ┃
 *                                                                                                                                                                                                                                   ┃
 *    User updated = wf.execute(42L);
 *
 *                                                                                                                                                                                                                                      ┃
 *    b) with  onFailure + recover                                                                                                                                                                                                   ┃
 *                                                                                                                                                                                                                                   ┃
 *    IPersistenceBasicWorkFlow<String, Account, Exception> loadAccount =                                                                                                                                                            ┃
 *            IPersistenceBasicWorkFlow.of(accountRepository::findByExternalId);                                                                                                                                                     ┃
 *                                                                                                                                                                                                                                   ┃
 *    Account account = loadAccount                                                                                                                                                                                                  ┃
 *            .onFailure(ex -> log.warn("Failed account lookup", ex))                                                                                                                                                                ┃
 *            .recover(ex -> Account.guest())                                                                                                                                                                                        ┃
 *            .execute("ext-123");                                                                                                                                                                                                   ┃
 *                                                                                                                                                                                                                                   ┃
 *    c) service-layer chain across components (3 ops)
 *
 *    @Transactional                                                                                                                                                                                                                 ┃
 *    public OrderDto placeOrder(PlaceOrderCommand command) throws Exception {                                                                                                                                                       ┃
 *        IPersistenceBasicWorkFlow<PlaceOrderCommand, OrderDraft, Exception> validate =                                                                                                                                             ┃
 *                IPersistenceBasicWorkFlow.of(orderValidationComponent::validate);                                                                                                                                                  ┃
 *                                                                                                                                                                                                                                   ┃
 *        IPersistenceBasicWorkFlow<OrderDraft, Order, Exception> persist =                                                                                                                                                          ┃
 *                IPersistenceBasicWorkFlow.of(orderPersistenceComponent::persist);                                                                                                                                                  ┃
 *                                                                                                                                                                                                                                   ┃
 *        IPersistenceBasicWorkFlow<Order, OrderDto, Exception> toDto =                                                                                                                                                              ┃
 *                IPersistenceBasicWorkFlow.of(orderMapperComponent::toDto);                                                                                                                                                         ┃
 *                                                                                                                                                                                                                                   ┃
 *        return validate                                                                                                                                                                                                            ┃
 *                .flatMap(draft -> persist)                                                                                                                                                                                         ┃
 *                .flatMap(order -> toDto)                                                                                                                                                                                           ┃
 *                .onSuccess(dto -> auditComponent.success(dto.id()))                                                                                                                                                                ┃
 *                .onFailure(ex -> auditComponent.failure(command.customerId(), ex))                                                                                                                                                 ┃
 *                .execute(command);                                                                                                                                                                                                 ┃
 *    }
 */

class IPersistenceBasicWorkFlowTest {

	@Test
	void shouldChainFindByIdAndUpdateWithFinallyDo ( ) throws Exception {
		var persistenceComponent = new UserPersistenceComponent ( );
		var closeCalled = new AtomicBoolean ( false );

		IPersistenceBasicWorkFlow < Long, User, Exception > findById =
				IPersistenceBasicWorkFlow.of ( persistenceComponent::findById );

		IPersistenceBasicWorkFlow < User, User, Exception > update =
				IPersistenceBasicWorkFlow.of ( persistenceComponent::update );

		var workflow = findById
				.andThen ( User::activate )
				.flatMap ( user -> update )
				.finallyDo ( ( ) -> closeCalled.set ( true ) );

		var result = workflow.execute ( 10L );

		assertNotNull ( result );
		assertTrue ( result.active ( ) );
		assertTrue ( closeCalled.get ( ) );
	}

	@Test
	void shouldHandleOnFailureAndRecover ( ) throws Exception {
		var capturedError = new AtomicReference < String > ( );

		IPersistenceBasicWorkFlow < Long, User, Exception > loadUser =
				IPersistenceBasicWorkFlow.< Long, User, Exception >of ( id -> {
					throw new Exception ( "entity not found" );
				} )
						.onFailure ( error -> capturedError.set ( error.getMessage ( ) ) )
						.recover ( error -> new User ( -1L, false ) );

		var recovered = loadUser.execute ( 99L );

		assertEquals ( "entity not found", capturedError.get ( ) );
		assertEquals ( -1L, recovered.id ( ) );
		assertTrue ( !recovered.active ( ) );
	}

	@Test
	void shouldChainServiceOperationsFromComponents ( ) throws Exception {
		var validationComponent = new ValidationComponent ( );
		var persistenceComponent = new OrderPersistenceComponent ( );
		var mapperComponent = new OrderMapperComponent ( );

		IPersistenceBasicWorkFlow < PlaceOrderCommand, OrderDraft, Exception > validate =
				IPersistenceBasicWorkFlow.of ( validationComponent::validate );

		IPersistenceBasicWorkFlow < OrderDraft, Order, Exception > persist =
				IPersistenceBasicWorkFlow.of ( persistenceComponent::persist );

		IPersistenceBasicWorkFlow < Order, OrderDto, Exception > mapToDto =
				IPersistenceBasicWorkFlow.of ( mapperComponent::toDto );

		var workflow = validate
				.flatMap ( draft -> persist )
				.flatMap ( order -> mapToDto )
				.peek ( dto -> assertNotNull ( dto.reference ( ) ) );

		var result = workflow.execute ( new PlaceOrderCommand ( "CUST-1", 2 ) );

		assertEquals ( "ORDER-CUST-1", result.reference ( ) );
		assertEquals ( 2, result.totalItems ( ) );
	}

	@Test
	void shouldPreserveOriginalExceptionWhenFinallyFails ( ) {
		var cleanupCalled = new AtomicBoolean ( false );

		IPersistenceBasicWorkFlow < Long, User, Exception > workflow =
				IPersistenceBasicWorkFlow.< Long, User, Exception >of ( id -> {
					throw new IllegalStateException ( "primary failure" );
				} )
						.finallyDo ( ( ) -> {
							cleanupCalled.set ( true );
							throw new IllegalArgumentException ( "cleanup failure" );
						} );

		var exception = assertThrows ( IllegalStateException.class, ( ) -> workflow.execute ( 1L ) );

		assertTrue ( cleanupCalled.get ( ) );
		assertEquals ( 1, exception.getSuppressed ( ).length );
		assertEquals ( "cleanup failure", exception.getSuppressed ( ) [ 0 ].getMessage ( ) );
	}

	private record User ( Long id, boolean active ) {
		private User activate ( ) {
			return new User ( id, true );
		}
	}

	private static final class UserPersistenceComponent {
		private User findById ( Long id ) throws Exception {
			if ( id == null || id <= 0 ) {
				throw new Exception ( "invalid id" );
			}
			return new User ( id, false );
		}

		private User update ( User user ) throws Exception {
			if ( user == null ) {
				throw new Exception ( "user is required" );
			}
			return user;
		}
	}

	private record PlaceOrderCommand ( String customerId, int items ) {
	}

	private record OrderDraft ( String customerId, int items ) {
	}

	private record Order ( String reference, int totalItems ) {
	}

	private record OrderDto ( String reference, int totalItems ) {
	}

	private static final class ValidationComponent {
		private OrderDraft validate ( PlaceOrderCommand command ) throws Exception {
			if ( command == null || command.items ( ) <= 0 ) {
				throw new Exception ( "invalid order command" );
			}
			return new OrderDraft ( command.customerId ( ), command.items ( ) );
		}
	}

	private static final class OrderPersistenceComponent {
		private Order persist ( OrderDraft draft ) throws Exception {
			if ( draft == null ) {
				throw new Exception ( "draft is required" );
			}
			return new Order ( "ORDER-" + draft.customerId ( ), draft.items ( ) );
		}
	}

	private static final class OrderMapperComponent {
		private OrderDto toDto ( Order order ) throws Exception {
			if ( order == null ) {
				throw new Exception ( "order is required" );
			}
			return new OrderDto ( order.reference ( ), order.totalItems ( ) );
		}
	}
}