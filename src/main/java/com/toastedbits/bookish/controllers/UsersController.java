package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.exceptions.ResourceNotFoundException;
import com.toastedbits.bookish.services.UserService;

@Controller
public class UsersController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public String getUsers(ModelMap map, @RequestParam(value="selected", required=false) Long selected) {
		BookishUser user = null;
		
		if(selected != null && UserService.currentUserIsAdmin()) {
			user = userService.getUserById(selected);
		}
		else {
			user = userService.getCurrentUser();
		}
		
		if(user == null) {
			throw new ResourceNotFoundException();
		}
			
		map.addAttribute("users", userService.getUsers());
		map.addAttribute("user", user);
		
		return "users";
	}
}
