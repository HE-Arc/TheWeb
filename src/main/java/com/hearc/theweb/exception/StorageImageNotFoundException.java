package com.hearc.theweb.exception;

public class StorageImageNotFoundException  extends StorageException {
	/**
	 * Unique identifier for serialization
	 */
	private static final long serialVersionUID = 222444888L;

	public StorageImageNotFoundException(String errorMessage) {
		super(errorMessage);
	}

	public StorageImageNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
