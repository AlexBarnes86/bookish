package com.toastedbits.bookish.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.toastedbits.bookish.domain.BookishUser;

@Component
public class BookishUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BookishUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookishUser user = (BookishUser)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "emptyField");
		ValidationUtils.rejectIfEmpty(errors, "password", "emptyField");
		if(!user.getPassword().equals(user.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "fieldMismatch");
		}
	}
}