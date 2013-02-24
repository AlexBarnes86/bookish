package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.exceptions.ResourceNotFoundException;
import com.toastedbits.bookish.services.BookService;
import com.toastedbits.bookish.services.CategoryService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService catService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String get(ModelMap model, @PathVariable("id") Long id) {
		Book book = bookService.getById(id);
		if(book == null) {
			throw new ResourceNotFoundException();
		}
		model.put("book", book);
		model.put("categories", catService.getAll());
		return "book";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(ModelMap model, @PathVariable("id") Long id) {
		if(bookService.getById(id) == null) {
			throw new ResourceNotFoundException();
		}
		bookService.deleteById(id);
		return "redirect:/books";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public String put(ModelMap model, @PathVariable("id") Long id, Book book) {
		if(id == null || bookService.getById(id) == null) {
			throw new ResourceNotFoundException();
		}
		
		book.setCategory(catService.fetch(book));
		bookService.updateById(id, book);
		return "redirect:/book/" + id;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/content")
	public @ResponseBody String getContent(@PathVariable("id") Long id) {
		Book book = bookService.getById(id);
		if(book == null || book.getContent() == null) {
			throw new ResourceNotFoundException();
		}
		return book.getContent();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/summary")
	public @ResponseBody String getSummary(@PathVariable("id") Long id) {
		Book book = bookService.getById(id);
		if(book == null || book.getSummary() == null) {
			throw new ResourceNotFoundException();
		}
		return book.getSummary();
	}
}