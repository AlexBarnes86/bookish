package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Authority;
import com.toastedbits.bookish.repositories.AuthorityRepository;

@Service
public class AuthorityService {
	@Autowired
	private AuthorityRepository authRepo;
	
	@Transactional
	public void addAuthority(String authName) {
		if(authName == null) {
			throw new IllegalArgumentException("Authority cannot be null");
		}
		// check if the group already exists
		Authority auth = authRepo.findByPropertyValue("name", authName);
		
		if (auth == null) {
			auth = new Authority();
			auth.setName(authName);
			authRepo.save(auth);
		}
	}
}
