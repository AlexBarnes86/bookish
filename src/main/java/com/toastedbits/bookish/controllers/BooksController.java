package com.toastedbits.bookish.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.User;
import com.toastedbits.bookish.services.BookService;
import com.toastedbits.bookish.services.CategoryService;
import com.toastedbits.bookish.services.UserService;

@Controller
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	private void setBooks(ModelMap model) {
		Map<Long, Book> books = new HashMap<Long, Book>();
		
		for(Book book : bookService.getAllBooks()) {
			books.put(book.getId(), book);
		}
		
		model.put("books", books);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value="selected", required=false) Long id, @RequestParam(value="category", required=false) Long catId) {
		User admin = userService.getAdminUser(); //TODO: add proper user control
		
		model.put("curBook", bookService.getById(id));
		model.put("newBook", new Book());
		setBooks(model);
		
		model.put("categoryTree", categoryService.getCategoryTree(catId));
		model.put("categoryRoot", categoryService.getCategoryRoot());
		model.put("user", admin);
		
		return "gallery";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(ModelMap model, Book book) {
		User admin = userService.getAdminUser(); //TODO: add proper user control
		
		book.setCategory(categoryService.fetch(book));
		bookService.createBook(book);
		model.put("curBook", book);
		model.put("newBook", new Book());
		setBooks(model);
		model.put("categoryTree", categoryService.getCategoryTree(null));
		model.put("categoryRoot", categoryService.getCategoryRoot());
		model.put("user", admin);
		
		return "gallery";
	}
}