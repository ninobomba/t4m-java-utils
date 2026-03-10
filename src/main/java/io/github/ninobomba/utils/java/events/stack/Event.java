package io.github.ninobomba.utils.java.events.stack;

import java.time.LocalDateTime;

public record Event(
		String id,
		String name,
		String type,
		String entityIdentifier,
		String entityName,
		LocalDateTime timestamp
) {
}
