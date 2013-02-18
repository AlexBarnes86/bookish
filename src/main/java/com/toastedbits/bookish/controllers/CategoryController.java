package com.toastedbits.bookish.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService catService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(ModelMap model, @RequestParam("name") String name, @RequestParam(value="parent", required=false) Long parent) {
		Category cat = new Category();
		cat.setName(name);
		cat.setParent(catService.getById(parent));
		catService.createCategory(cat);
		return "redirect:/books";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String delete(ModelMap model, @PathVariable("id") Long id) {
		catService.deleteById(id);
		return "redirect:/books";
	}
}