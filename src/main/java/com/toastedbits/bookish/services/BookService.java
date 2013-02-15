package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	public Book getById(Long id) {
		if(id == null) {
			return null;
		}
		
		return bookRepo.findOne(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		bookRepo.delete(id);
	}
	
	public Iterable<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	@Transactional
	public Book createBook(Book book) {
		book.setImage("http://placekitten.com/100/150");
		return bookRepo.save(book);
	}

	@Transactional
	public void updateById(Long id, Book book) {
		Book upBook = bookRepo.findOne(id);
		upBook.setTitle(book.getTitle());
		upBook.setSummary(book.getSummary());
		bookRepo.save(upBook);
	}
}
