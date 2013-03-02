package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Book;
import com.toastedbits.bookish.domain.Part;
import com.toastedbits.bookish.repositories.BookRepository;
import com.toastedbits.bookish.repositories.PartRepository;

@Service
public class PartService {
	@Autowired
	private PartRepository partRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Transactional
	public void createPart(Book book, Part part) {
		part.setBook(book);
		partRepo.save(part);
		book.getParts().add(part);
		bookRepo.save(book);
	}
	
	@Transactional
	public void delete(Long id) {
		partRepo.delete(id);
	}

	public Part getById(Long id) {
		return partRepo.findOne(id);
	}

	public void save(Long id, Part part) {
		Part p = partRepo.findOne(id);
		
		if(part.getContent() != null) {
			p.setContent(part.getContent());
		}
		if(part.getSummary() != null) {
			p.setSummary(part.getSummary());
		}
		
		partRepo.save(p);
	}
}