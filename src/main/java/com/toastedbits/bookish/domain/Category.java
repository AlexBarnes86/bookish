package com.toastedbits.bookish.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Category {
	@GraphId
	private Long id;
	
	@Indexed(unique=true)
	private String name;
	
	@RelatedTo(type="Category", direction=Direction.OUTGOING)
	private Category parent;
	
	@RelatedTo(type="Category", direction=Direction.INCOMING)
	private Set<Category> children;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getParent() {
		return parent;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}
}