package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.services.BookService;
import com.toastedbits.bookish.services.CategoryService;

@Controller
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GalleryHelper helper;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(ModelMap model, @RequestParam(value="selected", required=false) Long id) {
		model.put("curBook", bookService.getById(id));
		helper.setModelAttributes(model);
		model.put("categories", categoryService.getAllCategories());
		return "gallery";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(ModelMap model, Book book) {
		if(book.getCategory() != null) {
			book.setCategory(categoryService.getByName(book.getCategory().getName()));
		}
		bookService.createBook(book);
		model.put("curBook", book);
		helper.setModelAttributes(model);
		model.put("categories", categoryService.getAllCategories());
		return "gallery";
	}
}