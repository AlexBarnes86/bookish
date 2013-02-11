package com.toastedbits.bookish.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toastedbits.bookish.models.Book;
import com.toastedbits.bookish.models.Category;

@Service
public class BookService {
	public List<Book> getBooks(Category category) {
		List<Book> books = new ArrayList<Book>();
		
		Book sample = new Book();
		sample.setImage("http://placekitten.com/200/300");
		sample.setLink("#");
		sample.setTitle("Calculus");
		sample.setSummary("summary");
		books.add(sample);
		
		sample = new Book();
		sample.setImage("http://placekitten.com/200/300");
		sample.setLink("#");
		sample.setTitle("Physics");
		sample.setSummary("summary");
		books.add(sample);
		
		return books;
	}
}