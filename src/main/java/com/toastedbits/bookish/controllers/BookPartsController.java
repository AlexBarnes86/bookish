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
@RequestMapping("/book/{bookId}/parts")
public class BookPartsController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PartService partService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(@PathVariable("bookId") Long bookId, Part part) {
		Book book = bookService.getById(bookId);
		if(book == null) {
			throw new ResourceNotFoundException();
		}
		partService.createPart(book, part);
		return "redirect:/book/" + bookId;
	}
}