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
public class Category implements Viewable {
	public Category() {}
	
	public Category(Category c) {
		this.name = c.name;
		this.parent = c.parent;
		this.children = c.children;
	}
	
	@GraphId
	private Long id;
	
	@Indexed(unique=true)
	private String name;
	
	@RelatedTo(type=RelTypes.BELONGS_TO, direction=Direction.OUTGOING)
	@Fetch private Category parent;
	
	@RelatedTo(type=RelTypes.BELONGS_TO, direction=Direction.INCOMING)
	@Fetch private Set<Category> children = new HashSet<Category>();
	
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
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Category)) {
			return false;
		}
		Category rhs = (Category)obj;
		return id == rhs.id;
	}
	
	@Override
	public int hashCode() {
		return id == null ? 0 : id.intValue();
	}
}