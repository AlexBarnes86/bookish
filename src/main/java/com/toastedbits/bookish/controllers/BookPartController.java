package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//TODO: Ensure the part belongs to the book
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @PathVariable("bookId") Long bookId, @PathVariable("id") Long id) {
		Book book = bookService.getById(bookId);
		Part part = partService.getById(id);
		if(book == null || part == null) {
			throw new ResourceNotFoundException();
		}
		
		model.put("book", book);
		model.put("part", part);
		
		return "bookPart";
	}
	
	//TODO: Ensure the part belongs to the book
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public @ResponseBody String getContent(@PathVariable("bookId") Long bookId, @PathVariable("id") Long id) {
		Part part = partService.getById(id);
		if(part == null) {
			throw new ResourceNotFoundException();
		}
		
		return part.getContent();
	}
	
	//TODO:Implement child-part creation
	@RequestMapping(method=RequestMethod.POST)
	public String post(@PathVariable("bookId") Long bookId, @PathVariable("id") Long id, Part part) {
		return "redirect:/book/" + bookId + "/part/" + id;
	}
	
	//TODO: Implement part update
	@RequestMapping(method=RequestMethod.PUT)
	public String put(@PathVariable("bookId") Long bookId, @PathVariable("id") Long id, Part part) {
		partService.save(id, part);
		return "redirect:/book/" + bookId + "/part/" + id;
	}
	
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
