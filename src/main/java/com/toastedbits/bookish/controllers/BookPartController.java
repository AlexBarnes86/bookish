package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Part;
import com.toastedbits.bookish.exceptions.ResourceNotFoundException;
import com.toastedbits.bookish.services.BookService;
import com.toastedbits.bookish.services.PartService;

@Controller
@RequestMapping("/book/{bookId}/part/{id}")
public class BookPartController {
	@Autowired
	BookService bookService;
	
	@Autowired
	PartService partService;
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String delete(@PathVariable("bookId") Long bookId, @PathVariable("id") Long id) {
		Book book = bookService.getById(bookId);
		Part part = partService.getById(id);
		if(book == null || part == null) {
			throw new ResourceNotFoundException();
		}
		
		partService.delete(id);
		return "redirect:/book/" + bookId;
	}
}
