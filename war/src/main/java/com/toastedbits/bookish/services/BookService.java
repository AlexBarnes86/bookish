package com.toastedbits.bookish.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Category;
import com.toastedbits.bookish.repositories.BookRepository;
import com.toastedbits.bookish.repositories.CategoryRepository;
import com.toastedbits.bookish.repositories.PartRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private PartRepository partRepo;
	
	public Book getById(Long id) {
		if(id == null) {
			return null;
		}
		
		Book book = bookRepo.findOne(id);
		book.setParts(partRepo.getParts(book));
		return book;
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
		if(StringUtils.isEmpty(book.getImage())) {
			book.setImage("http://placekitten.com/100/150");
		}
//		book.getCategory().setChildren(null);
		return bookRepo.save(book);
	}

	@Transactional
	public void save(Book book) {
		bookRepo.save(book);
	}

	public List<Book> getBooksByCategory(Category category) {
		return bookRepo.getBooks(category);
	}
}
