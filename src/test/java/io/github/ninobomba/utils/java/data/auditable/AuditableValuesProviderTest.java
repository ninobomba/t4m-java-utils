package io.github.ninobomba.utils.java.data.auditable;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static io.github.ninobomba.utils.java.data.auditable.AuditableValuesProvider.CRUDConstants.CREATE;
import static io.github.ninobomba.utils.java.data.auditable.AuditableValuesProvider.CRUDConstants.DELETE;
import static io.github.ninobomba.utils.java.data.auditable.AuditableValuesProvider.CRUDConstants.LIST;
import static io.github.ninobomba.utils.java.data.auditable.AuditableValuesProvider.CRUDConstants.READ;
import static io.github.ninobomba.utils.java.data.auditable.AuditableValuesProvider.CRUDConstants.UPDATE;
import static org.assertj.core.api.Assertions.assertThat;

class AuditableValuesProviderTest {

	@Test
	void createShouldSetAuditFields ( ) {
		var entity = new SampleAuditableEntity ( );

		AuditableValuesProvider.setAuditableValues ( entity, "john", CREATE );

		assertThat ( entity.status ).isEqualTo ( "ENABLED" );
		assertThat ( entity.createdBy ).isEqualTo ( "john" );
		assertThat ( entity.lastModifiedBy ).isEqualTo ( "john" );
		assertThat ( entity.createdDate ).isNotNull ( );
		assertThat ( entity.lastModifiedDate ).isNotNull ( );
	}

	@Test
	void updateShouldSetOnlyModificationFields ( ) {
		var entity = new SampleAuditableEntity ( );
		entity.status = "ENABLED";
		entity.createdBy = "creator";
		entity.createdDate = Instant.now ( );

		AuditableValuesProvider.setAuditableValues ( entity, "mary", UPDATE );

		assertThat ( entity.status ).isEqualTo ( "ENABLED" );
		assertThat ( entity.createdBy ).isEqualTo ( "creator" );
		assertThat ( entity.lastModifiedBy ).isEqualTo ( "mary" );
		assertThat ( entity.lastModifiedDate ).isNotNull ( );
	}

	@Test
	void deleteShouldDisableStatusAndSetModificationFields ( ) {
		var entity = new SampleAuditableEntity ( );

		AuditableValuesProvider.setAuditableValues ( entity, "ops", DELETE );

		assertThat ( entity.status ).isEqualTo ( "DISABLED" );
		assertThat ( entity.lastModifiedBy ).isEqualTo ( "ops" );
		assertThat ( entity.lastModifiedDate ).isNotNull ( );
	}

	@Test
	void readAndListShouldNotMutateFields ( ) {
		var entity = new SampleAuditableEntity ( );

		AuditableValuesProvider.setAuditableValues ( entity, "john", READ );
		AuditableValuesProvider.setAuditableValues ( entity, "john", LIST );

		assertThat ( entity.status ).isNull ( );
		assertThat ( entity.createdBy ).isNull ( );
		assertThat ( entity.createdDate ).isNull ( );
		assertThat ( entity.lastModifiedBy ).isNull ( );
		assertThat ( entity.lastModifiedDate ).isNull ( );
	}

	private static final class SampleAuditableEntity {
		private String status;
		private String createdBy;
		private Instant createdDate;
		private String lastModifiedBy;
		private Instant lastModifiedDate;
	}
}
