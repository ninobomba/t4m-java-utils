package io.github.ninobomba.utils.java.api.response.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * Represents a response returned by an API.
 */
@Data
public class ApiResponseSuccess implements Serializable {

	private final String id;
	private final String message;
	private final Object data;

}
