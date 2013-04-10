package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toastedbits.bookish.domain.Authority;
import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.exceptions.NotAuthorizedException;
import com.toastedbits.bookish.exceptions.UsernameAlreadyExistsException;
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
	public String putUser(ModelMap map, @ModelAttribute("user") BookishUser user, BindingResult binding, @PathVariable Long id) {
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
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String postUser(ModelMap map, @ModelAttribute("newUser") BookishUser newUser, BindingResult binding) { //ModelAttribute specified to direct BindingResult command name
		if(!UserService.currentUserIsAdmin()) {
			throw new NotAuthorizedException();
		}
		
		validator.validate(newUser, binding);
		if(binding.hasErrors()) {
			map.addAttribute("newUser", newUser);
			map.addAttribute("user", userService.getCurrentUser());
			map.addAttribute("users", userService.getUsers());
			return "/users";
		}
		
		try {
			userService.addUser(newUser.getUsername(), newUser.getPassword());
		} catch (UsernameAlreadyExistsException e) {
			map.addAttribute("newUser", newUser);
			map.addAttribute("user", userService.getCurrentUser());
			map.addAttribute("users", userService.getUsers());
			binding.addError(new FieldError("newUser", "username", newUser.getUsername(), false, new String[]{"usernameTaken"}, null, "Username already taken!"));
		}
		
		return "/users";
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public String deleteUser(ModelMap map, @PathVariable Long id) {
		BookishUser dbUser = userService.getUserById(id);
		
		if(dbUser.hasAuthority(Authority.ROLE_ADMIN) && userService.getAdminUsers().size() == 1) {
			return "forward:/users";
		}
		
		userService.delete(dbUser);
		
		return "redirect:/users";
	}
}