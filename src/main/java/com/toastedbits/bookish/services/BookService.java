package com.toastedbits.bookish.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toastedbits.bookish.models.Book;
import com.toastedbits.bookish.models.Category;

@Service
public class BookService {
	@Autowired SessionFactory sessionFactory;
	
	public void insertSampleBook() {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		Book book = new Book();
		book.setImage("http://placekitten.com/200/300");
		book.setLink("#");
		book.setSummary("Hello from Hibernate/HSQLDB");
		book.setTitle("Hibernate");
		session.save(book);
		session.getTransaction().commit();
		session.close();
	}
	
	public Map<Long, Book> getBooks(Category category) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		List<Book> sessionBooks = session.createQuery("from Book").list();
		session.getTransaction().commit();
		session.close();
		
		Map<Long, Book> books = new LinkedHashMap<Long, Book>();
		
		for(Book sessionBook : sessionBooks) {
			books.put(sessionBook.getId(), sessionBook);
		}
		
		return books;
	}
	
	public Book getBook(Long id) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		Book book = (Book)session.get(Book.class, id);
		session.getTransaction().commit();
		session.close();
		
		return book;
	}
	
	public void deleteBook(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Book book = (Book)session.load(Book.class, id);
		session.delete(book);
		session.getTransaction().commit();
		session.close();
	}
}