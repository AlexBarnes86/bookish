package com.toastedbits.bookish.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Category;

@Repository
public interface BookRepository extends GraphRepository<Book> {
	@Query("start category=node({0}) match category<-[:BELONGS_TO]-book return book order by book.title")
	List<Book> getBooks(Category category);
}
