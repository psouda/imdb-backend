package com.imdb.imdbbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Sort direction enumeration
 */
public enum SortDirection {
	ASC("asc"),
	DESC("desc");

	private String value;

	SortDirection(String value) {
		this.value = value;
	}

	@JsonCreator
	public static SortDirection fromString(String value) {
		try {
			return SortDirection.valueOf(value.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format(
					"Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
		}
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
