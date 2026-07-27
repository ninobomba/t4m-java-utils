package io.github.ninobomba.utils.java.events.stack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EventFactory {

	private static final TypeReference < HashMap < String, Object > > typeRef = new TypeReference <> ( ) {
	};

	public static Map < String, Object > fromJsonAsMap ( ObjectMapper jsonMapper, String json ) {
		try {
			return jsonMapper.readValue ( json, typeRef );
		} catch ( JsonProcessingException e ) {
			throw new RuntimeException ( e );
		}
	}

	public static Event fromJsonToEvent ( ObjectMapper jsonMapper, String json ) {
		try {
			final var jsonAsMap = jsonMapper.readValue ( json, typeRef );
			if ( jsonAsMap.containsKey ( "entityName" ) ) {
				Object objectEntityName = jsonAsMap.get ( "entityName" );
				Object objectEventType = jsonAsMap.get ( "eventType" );
				Object objectEntityId = jsonAsMap.get ( "entityId" );
				if ( objectEntityName instanceof String entityName &&
						objectEventType instanceof String eventType &&
						objectEntityId instanceof String entityId ) {
					return buildEvent ( eventType, jsonAsMap );
				} else {
					throw new IllegalArgumentException ( "Invalid event JSON structure: " + json );
				}
			}
		} catch ( JsonProcessingException e ) {
			throw new RuntimeException ( e );
		}
		throw new IllegalArgumentException ( "Invalid event JSON: missing required event attributes. payload=" + json );
	}

	@Deprecated ( forRemoval = false )
	public static Event fromJsonToMap ( ObjectMapper jsonMapper, String json ) {
		return fromJsonToEvent ( jsonMapper, json );
	}

	public static Event buildEvent ( String eventType, HashMap < String, Object > jsonAsMap ) {
		return switch ( eventType ) {
			case "A" -> buildCustomEvent ( jsonAsMap );
//			case "V" -> pedidoConfirmadoFrom ( jsonAsMap );
			default -> throw new UnsupportedOperationException ( "Unsupported event type: " + eventType );
		};
	}

	private static Event buildCustomEvent ( HashMap < String, Object > jsonAsMap ) {
		return new Event (
				( String ) jsonAsMap.get ( "id" ),
				( String ) jsonAsMap.get ( "name" ),
				( String ) jsonAsMap.get ( "type" ),
				( String ) jsonAsMap.get ( "entityIdentifier" ),
				( String ) jsonAsMap.get ( "entityName" ),
				parseTimestamp ( jsonAsMap.get ( "timestamp" ) )
		);
	}

	private static LocalDateTime parseTimestamp ( Object val ) {
		if ( val instanceof LocalDateTime ldt ) return ldt;
		if ( val instanceof String str ) return LocalDateTime.parse ( str );
		return null;
	}

}
