package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toastedbits.bookish.services.UserService;

@Controller
public class UsersController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public String getUsers(ModelMap map) {
		map.addAttribute("users", userService.getUsers());
		return "users";
	}
}
