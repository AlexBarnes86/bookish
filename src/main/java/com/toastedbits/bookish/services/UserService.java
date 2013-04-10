package com.toastedbits.bookish.services;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Authority;
import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.exceptions.UsernameAlreadyExistsException;
import com.toastedbits.bookish.repositories.AuthorityRepository;
import com.toastedbits.bookish.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Transactional
	public void addUser(String username, String password) throws UsernameAlreadyExistsException {
		BookishUser bookishUser = userRepo.findByPropertyValue("username", username);
		
		if (bookishUser != null) {
			throw new UsernameAlreadyExistsException(username);
		}
		else {
			bookishUser = new BookishUser();
			bookishUser.setUsername(username);
			
			bookishUser.setPassword(getHashedPassword(username, password));
			
			userRepo.save(bookishUser);
		}
	}
	
	// March 2013 - Per our spring security config, this works with username being the salt
	private String getHashedPassword(String username, String password) {
		UserDetails user = new User(username, password, true, true, true, true, new HashSet<GrantedAuthority>());
		return passwordEncoder.encodePassword(password, saltSource.getSalt(user));
	}
	
	@Transactional
	public void grantAuthority(String username, String authority) {
		BookishUser bookishUser = userRepo.findByPropertyValue("username", username);
		
		for(Authority auth : bookishUser.getAuthorities()) {
			if(authority.equals(auth.getName())) {
				return;
			}
		}
		
		Authority auth = authRepo.findByPropertyValue("name", authority);
		bookishUser.addAuthority(auth);
		userRepo.save(bookishUser);
	}
	
	@Transactional
	public void removeUser(String username) throws UsernameNotFoundException {
		BookishUser user = userRepo.findByPropertyValue("username", username);
		userRepo.delete(user);
	}
	
	@Transactional
	public long removeUsers() {
		long numUsers = userRepo.count();
		userRepo.deleteAll();
		return numUsers;
	}
	
	public boolean userExists(String username) {
		return (userRepo.findByPropertyValue("username", username) != null);
	}
	
	@Transactional
	public void createAdminUser(String username, String password) throws UsernameAlreadyExistsException {
		addUser(username, password);
		grantAuthority(username, "ROLE_ADMIN");
	}
	
	public List<BookishUser> getUsers() {
		return userRepo.findAll().as(List.class);
	}

	public BookishUser getUserById(Long id) {
		BookishUser user = userRepo.findOne(id);
		if(user.getUsername() == null) {
			return null;
		}
		
		return user;
	}
	
	@Transactional
	public void save(BookishUser user, String password) {
		user.setPassword(getHashedPassword(user.getUsername(), password));
		userRepo.save(user);
	}
	
	public void delete(BookishUser user) {
		userRepo.delete(user);
	}
	
	public static UserDetails getSecurityContextCurrentUser() {
		try {
			return (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public boolean isCurrentUserOrAdmin(String username) {
		UserDetails details = getSecurityContextCurrentUser();
		if(details != null) {
			if(username.equals(details.getUsername())) {
				return true;
			}
			return details.getAuthorities() != null &&
					details.getAuthorities()
						.contains(authRepo.findByPropertyValue("name", "ROLE_ADMIN"));
		}
		
		return false;
	}
	
	public static boolean currentUserIsAdmin() {
		UserDetails details = getSecurityContextCurrentUser();
		for(GrantedAuthority auth : details.getAuthorities()) {
			if("ROLE_ADMIN".equals(auth.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public BookishUser getCurrentUser() {
		UserDetails details = getSecurityContextCurrentUser();
		if(details == null) {
			return null;
		}
		
		return userRepo.findByPropertyValue("username", details.getUsername());
	}

	public List<BookishUser> getAdminUsers() {
		return userRepo.findAdminUsers();
	}
}
