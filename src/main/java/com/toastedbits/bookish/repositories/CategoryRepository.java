package com.toastedbits.bookish.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.Category;

@Repository
public interface CategoryRepository extends GraphRepository<Category> {
	@Query("start cat=node:Category match (Category)<-(Category)")
	public Iterable<Category> getTree(Category root);
}
