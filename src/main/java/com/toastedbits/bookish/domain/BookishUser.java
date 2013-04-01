package com.toastedbits.bookish.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class BookishUser {
	@Indexed(unique=true)
	private String username; 
	
	private String password;
	
	@GraphId
	private Long id;
	
	@RelatedTo(type=RelTypes.HAS_AUTHORITY, direction=Direction.OUTGOING)
	@Fetch
	private Set<Authority> authorities;
	
	@RelatedTo(type=RelTypes.VIEWING, direction=Direction.OUTGOING)
	private Category category;
	
	public void addAuthority(Authority auth) {
		if(authorities == null) {
			authorities = new HashSet<Authority>();
		}
		authorities.add(auth);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
}