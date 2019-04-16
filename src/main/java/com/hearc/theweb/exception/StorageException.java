package com.hearc.theweb.exception;

public class StorageException extends RuntimeException {
	/**
	 * Unique identifier for serialization
	 */
	private static final long serialVersionUID = 222444666L;

	public StorageException(String errorMessage) {
		super(errorMessage);
	}

	public StorageException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
