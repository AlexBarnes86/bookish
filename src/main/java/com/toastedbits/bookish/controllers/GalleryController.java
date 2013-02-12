package com.toastedbits.bookish.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.models.Book;
import com.toastedbits.bookish.models.Category;
import com.toastedbits.bookish.services.BookService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value="selected", required=false) Integer id) {
		Map<Long, Book> books = bookService.getBooks(new Category());
		model.put("books", books);
		model.put("curBook", books.get(id));
		
		return "gallery";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(ModelMap model) {
		bookService.insertSampleBook();
		Map<Long, Book> books = bookService.getBooks(new Category());
		model.put("books", books);
		return "gallery";
	}
}