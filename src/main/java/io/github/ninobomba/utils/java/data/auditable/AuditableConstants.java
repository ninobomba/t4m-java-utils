package io.github.ninobomba.utils.java.data.auditable;

import lombok.Getter;

import java.time.Instant;


@Getter
public final class AuditableConstants {

	private final String statusCode = "ENABLED";
	private final String statusCodeDisable = "DISABLED";

	private final String createdBy = "SYSTEM";
	private final String lastModifiedBy = "SYSTEM";

	public Instant getCreatedDate ( ) {
		return Instant.now ( );
	}

	public Instant getLastModifiedDate ( ) {
		return Instant.now ( );
	}

}
