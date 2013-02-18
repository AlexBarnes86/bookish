package com.toastedbits.bookish.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository catRepo;
	
	public void createCategory(Category cat) {
		catRepo.save(cat);
	}

	public List<Category> getAllCategories() {
		return catRepo.findAll().as(List.class);
	}
	
	public Category getByName(String name) {
		return catRepo.findByPropertyValue("name", name);
	}
	
	@Transactional
	public void deleteById(Long id) {
		catRepo.delete(id);
	}

	public Category getById(Long id) {
		return (id == null ? null : catRepo.findOne(id));
	}
}