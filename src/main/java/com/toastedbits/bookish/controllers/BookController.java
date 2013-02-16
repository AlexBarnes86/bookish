package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.services.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String get(ModelMap model, @PathVariable("id") Long id) {
		model.put("book", bookService.getById(id));
		
		return "book";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(ModelMap model, @PathVariable("id") Long id) {
		bookService.deleteById(id);
		return "redirect:/books";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public String putPost(ModelMap model, @PathVariable("id") Long id, Book book) {
		bookService.updateById(id, book);
		model.put("book", book);
		return "book";
	}
}