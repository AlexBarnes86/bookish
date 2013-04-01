package com.toastedbits.bookish.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class NotAuthorizedException extends RuntimeException {
	private static final long serialVersionUID = 7659536134450921729L;
}