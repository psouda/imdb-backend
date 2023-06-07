package com.imdb.imdbbackend.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3507924147967278835L;

	public EntityNotFoundException(Exception exception) {
		super(exception);
	}

	public EntityNotFoundException(String msg) {
		super(msg);
	}

	public EntityNotFoundException() {
		super();
	}
}