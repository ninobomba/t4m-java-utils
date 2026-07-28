package io.github.ninobomba.utils.java.id.generators;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorHashSetSupportTest {

	@Test
	void singletonAccessorsShouldReturnSameInstance ( ) {
		var first = IdGeneratorHashSetSupport.getInstance ( );
		var second = IdGeneratorHashSetSupport.getINSTANCE ( );

		assertThat ( first ).isSameAs ( second );
	}

	@Test
	void getNextIdShouldReturnPositiveValues ( ) {
		var id = IdGeneratorHashSetSupport.getInstance ( ).getNextId ( );

		assertThat ( id ).isPositive ( );
	}

	@Test
	void getNextIdShouldProvideUniqueIdsInBatch ( ) {
		var ids = new HashSet < Long > ( );

		IntStream.range ( 0, 2_000 )
				.mapToLong ( index -> IdGeneratorHashSetSupport.getInstance ( ).getNextId ( ) )
				.forEach ( ids::add );

		assertThat ( ids ).hasSize ( 2_000 );
	}
}
