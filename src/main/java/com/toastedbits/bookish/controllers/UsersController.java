package com.toastedbits.bookish.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		
		List<BookishUser> users = userService.getUsers();
		Collections.sort(users, new Comparator<BookishUser>() {
			@Override
			public int compare(BookishUser lhs, BookishUser rhs) {
				return lhs.getUsername().compareTo(rhs.getUsername());
			}
		});
		
		map.addAttribute("users", users);
		map.addAttribute("user", user);
		if(map.get("newUser") == null) {
			map.addAttribute("newUser", new BookishUser());
		}
		
		return "users";
	}
}
