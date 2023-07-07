package com.intheeast.springframe.dao;

@SuppressWarnings("serial")
public class DuplicateUserIdException extends RuntimeException {
	public DuplicateUserIdException(Throwable cause) {
		super(cause);
	}
}
