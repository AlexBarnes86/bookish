package com.toastedbits.bookish.services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.Authority;
import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		BookishUser bookishUser = userRepo.findByPropertyValue("username", username);
		
		if (bookishUser == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		for(Authority auth : bookishUser.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(auth.getName()));
		}
		
		UserDetails user = new User(username, bookishUser.getPassword(), true, true, true, true, authorities);
		
		return user;
	}
}
