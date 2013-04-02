package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.exceptions.NotAuthorizedException;
import com.toastedbits.bookish.services.UserService;
import com.toastedbits.bookish.validators.BookishUserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookishUserValidator validator;
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String getUser(ModelMap map, @PathVariable("id") Long id) {
		BookishUser currentUser = userService.getCurrentUser();
		BookishUser dbUser = userService.getUserById(id);
		
		if( dbUser == null ||
			currentUser == null ||
			!(currentUser.getId().equals(id) ||
			UserService.currentUserIsAdmin())) {
			
			throw new NotAuthorizedException(); //Dont reveal any details about existence of other accounts - no 404s
		}
		
		map.addAttribute("user", userService.getUserById(id));
		return "user";
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public String getUser(ModelMap map, BookishUser user, @PathVariable Long id, BindingResult binding) {
		BookishUser currentUser = userService.getCurrentUser();
		BookishUser dbUser = userService.getUserById(id);
		
		if( dbUser == null ||
			currentUser == null ||
			!(currentUser.getId().equals(id) ||
			UserService.currentUserIsAdmin())) {
			
			throw new NotAuthorizedException();
		}
		user.setUsername(dbUser.getUsername());
		validator.validate(user, binding);
		if(!binding.hasErrors()) {
			userService.save(dbUser, user.getPassword());
		}
		
		map.addAttribute("user", dbUser);
		
		return "user";
	}
}