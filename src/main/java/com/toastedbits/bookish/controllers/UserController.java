package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.exceptions.NotAuthorizedException;
import com.toastedbits.bookish.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{id}")
	public String getUser(ModelMap map, @PathVariable("id") Long id) {
		BookishUser user = userService.getUserById(id);
		
		if(user == null || userService.isCurrentUserOrAdmin(user.getUsername())) {
			throw new NotAuthorizedException(); //Dont reveal any details about existence of other accounts - no 404s
		}
		
		map.addAttribute("user", userService.getUserById(id));
		return "user";
	}
}
