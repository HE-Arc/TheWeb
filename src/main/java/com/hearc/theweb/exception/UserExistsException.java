package com.hearc.theweb.exception;

/**
 * UserExistsException
 * 
 * @author kim.biloni
 *
 */
public class UserExistsException extends Exception {

	/**
	 * Unique identifier for serialization
	 */
	private static final long serialVersionUID = 333666999L;

	public UserExistsException(String errorMessage) {
		super(errorMessage);
	}

	public UserExistsException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
