package com.toastedbits.bookish.repositories;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Part;

@Repository
public interface PartRepository extends GraphRepository<Part> {
	@Query("start n=node({0}) match n-[:HAS_PART]->p return p order by p.title")
	public Set<Part> getParts(Book book);
}