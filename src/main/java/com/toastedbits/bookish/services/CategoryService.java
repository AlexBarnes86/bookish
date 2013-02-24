package com.toastedbits.bookish.services;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.helpers.Predicate;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.domain.RelTypes;
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

	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		for(Category cat : catRepo.findAllByTraversal(getCategoryRoot(), Traversal.description()
				.breadthFirst()
				.evaluator(Evaluators.fromDepth(1))
				.expand(Traversal
					.expanderForTypes(DynamicRelationshipType
					.withName(RelTypes.BELONGS_TO), 
							  Direction.INCOMING).addNodeFilter(new Predicate<Node>() {
									@Override
									public boolean accept(Node item) {
										return !StringUtils.isEmpty(item.getProperty("name"));
									}
							}
							)))) {
			if(cat != null && cat.getName() != null) { //TODO: Figure out why a null element appears in this traversal
				categories.add(cat);
			}
		}
		return categories;
	}

	public Category fetch(final Book book) {
		Category category = book.getCategory();
		if(category == null || category.getId() == null) {
			category = getCategoryRoot();
		}
		else {
			category = getById(book.getCategory().getId());
			if(category == null) {
				book.setCategory(getCategoryRoot());
			}
		}
		
		return category;
	}
}