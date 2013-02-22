package com.toastedbits.bookish.background;

import org.springframework.beans.factory.annotation.Autowired;

import com.toastedbits.bookish.services.CategoryService;
import com.toastedbits.bookish.services.UserService;

public class InitDB {
	@Autowired
	private CategoryService cs;
	
	@Autowired
	private UserService us;
	
	public void start() {
		cs.createCategoryRoot();
		us.createAdminUser();
	}
}