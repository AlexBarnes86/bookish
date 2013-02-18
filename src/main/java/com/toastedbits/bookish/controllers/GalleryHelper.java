package com.toastedbits.bookish.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.services.BookService;

@Component
public class GalleryHelper {
	@Autowired
	private BookService bookService;
	
	public void setModelAttributes(ModelMap model) {
		setBooks(model);
		model.put("newBook", new Book());
	}
	
	private void setBooks(ModelMap model) {
		Map<Long, Book> books = new HashMap<Long, Book>();
		
		for(Book book : bookService.getAllBooks()) {
			books.put(book.getId(), book);
		}
		
		model.put("books", books);
	}
}