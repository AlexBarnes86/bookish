package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toastedbits.bookish.domain.User;
import com.toastedbits.bookish.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryService catService;
	
	public void createAdminUser() {
		Iterable<User> administrators = userRepo.findAllByPropertyValue("admin", "true");
		if(!administrators.iterator().hasNext()) {
			User admin = new User();
			admin.setName("admin");
			admin.setAdmin(true);
			admin.setCategory(catService.getCategoryRoot());
			userRepo.save(admin);
		}
	}
	
	//TODO: remove
	public User getAdminUser() {
		return userRepo.findAllByPropertyValue("admin","true").single();
	}
}
