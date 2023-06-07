package com.imdb.imdbbackend.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 4388757358355982778L;

	public UnauthorizedException(Exception exception) {
		super(exception);
	}

	public UnauthorizedException() {
		super();
	}
}