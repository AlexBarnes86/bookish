package com.toastedbits.bookish.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.services.BookService;

@Controller
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;
	
	private static final Logger LOG = LoggerFactory.getLogger(BooksController.class);
	
	//Do not turn this into @ModelAttribute, needs to execute after any insert/delete
	private void setBooks(ModelMap model) {
		Map<Long, Book> books = new HashMap<Long, Book>();
		
		for(Book book : bookService.getAllBooks()) {
			books.put(book.getId(), book);
		}
		
		model.put("books", books);
	}
	
	@ModelAttribute("newBook")
	public Book newBook() {
		return new Book();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value="selected", required=false) Long id) {
		Book curBook = bookService.getById(id);
		model.put("curBook", curBook);
		setBooks(model);
		return "gallery";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String putPost(ModelMap model, Book book) {
		bookService.createBook(book);
		model.put("curBook", book);
		setBooks(model);
		
		return "gallery";
	}
}