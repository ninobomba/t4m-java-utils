package io.github.ninobomba.utils.java.data.auditable;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuditableConstantsTest {

	@Test
	void shouldExposeDefaultStatusAndUsers ( ) {
		var constants = new AuditableConstants ( );

		assertThat ( constants.getStatusCode ( ) ).isEqualTo ( "ENABLED" );
		assertThat ( constants.getStatusCodeDisable ( ) ).isEqualTo ( "DISABLED" );
		assertThat ( constants.getCreatedBy ( ) ).isEqualTo ( "SYSTEM" );
		assertThat ( constants.getLastModifiedBy ( ) ).isEqualTo ( "SYSTEM" );
	}

	@Test
	void shouldGenerateDynamicDates ( ) {
		var constants = new AuditableConstants ( );

		var createdDate = constants.getCreatedDate ( );
		var lastModifiedDate = constants.getLastModifiedDate ( );

		assertThat ( createdDate ).isNotNull ( );
		assertThat ( lastModifiedDate ).isNotNull ( );
		assertThat ( lastModifiedDate ).isAfterOrEqualTo ( createdDate );
	}
}
