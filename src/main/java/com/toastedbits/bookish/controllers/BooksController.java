package com.toastedbits.bookish.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.exceptions.ResourceNotFoundException;
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
	
	@Autowired
	private CategoryService catService;
	
	private void setBooks(ModelMap model, Category category) {
		List<Book> bookList = bookService.getBooksByCategory(category);
		Map<Long, Book> books = new HashMap<Long, Book>();
		
		for(Book book : bookList) {
			books.put(book.getId(), book);
		}
		
		model.put("books", books);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value="selected", required=false) Long bookId, @RequestParam(value="category", required=false) Long catId) {
		if(bookId != null) {
			Book curBook = bookService.getById(bookId);
			if(curBook == null) {
				throw new ResourceNotFoundException();
			}
			model.put("curBook", curBook);
		}
		
		if(catId != null) {
			Category category = catService.getById(catId);
			if(category == null) {
				throw new ResourceNotFoundException();
			}
			setBooks(model, category);
		}
		
		model.put("newBook", new Book());
		
		model.put("categoryTree", categoryService.getCategoryTree(catId));
		model.put("categoryRoot", categoryService.getCategoryRoot());
		
		return "gallery";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(ModelMap model, Book book) {
		
		book.setCategory(categoryService.fetch(book));
		bookService.createBook(book);
		model.put("curBook", book);
		model.put("newBook", new Book());
		model.put("books", null);
		model.put("categoryTree", categoryService.getCategoryTree(null));
		model.put("categoryRoot", categoryService.getCategoryRoot());
		
		return "gallery";
	}
}