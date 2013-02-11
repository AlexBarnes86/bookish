package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toastedbits.bookish.models.Category;
import com.toastedbits.bookish.services.BookService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model) {
		model.put("books", bookService.getBooks(new Category()));
		return "gallery";
	}
}