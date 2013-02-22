package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository catRepo;
	
	public static final String ROOT_NAME = "CategoryRoot";
	
	public void createCategoryRoot() {
		Category cat = getByName(ROOT_NAME);
		if(cat == null) {
			cat = new Category();
			cat.setName(ROOT_NAME);
			catRepo.save(cat);
		}
	}
	
	public Category getCategoryRoot() {
		return catRepo.findByPropertyValue("name", ROOT_NAME);
	}
	
	public void createCategory(Category cat) {
		if(cat.getParent() == null) {
			Category root = getCategoryRoot();
			if(root == null) {
				throw new IllegalStateException("Missing Category Root");
			}
			cat.setParent(root);
		}
		
		catRepo.save(cat);
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
	
	public Category getCategoryTree(Long rootId) {
		if(rootId != null) {
			return catRepo.findOne(rootId);
		}
		else {
			return getCategoryRoot();
		}
	}
}