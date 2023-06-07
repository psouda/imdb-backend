package com.imdb.imdbbackend.exception;

public class ServerException extends RuntimeException {

	private static final long serialVersionUID = -1134766413902872861L;

	public ServerException(Exception exception) {
		super(exception);
	}

	public ServerException(String message) {
		super(message);
	}
}
