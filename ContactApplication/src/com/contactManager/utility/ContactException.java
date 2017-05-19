package com.contactManager.utility;

public class ContactException extends Exception  {

	private static final long serialVersionUID = 1L;
	public String message;

	public ContactException() {
		super();
	}

	public ContactException(String message) {
		super(message);
		this.message=message;
	}

	@Override
	public String toString() {
		return message;
	}

}
