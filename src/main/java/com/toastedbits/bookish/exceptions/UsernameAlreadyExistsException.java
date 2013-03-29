package com.toastedbits.bookish.exceptions;

public class UsernameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 360331512759728867L;

	public UsernameAlreadyExistsException(String username) {
		super("Username " + username + " already exists");
	}
}