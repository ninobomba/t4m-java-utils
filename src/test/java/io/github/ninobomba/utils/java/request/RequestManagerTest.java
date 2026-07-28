package io.github.ninobomba.utils.java.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RequestManagerTest {

	@Test
	void getInstanceShouldReturnSingleton ( ) {
		var first = RequestManager.getInstance ( );
		var second = RequestManager.getInstance ( );

		assertThat ( first ).isSameAs ( second );
	}

	@Test
	void addShouldIgnoreNullRequest ( ) {
		RequestManager.getInstance ( );

		assertDoesNotThrow ( ( ) -> RequestManager.add ( null ) );
	}

	@Test
	void checkOnQueueShouldHandleEmptyQueue ( ) {
		RequestManager.getInstance ( );

		assertDoesNotThrow ( RequestManager::checkOnQueue );
	}
}
