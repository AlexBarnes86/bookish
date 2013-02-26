package com.toastedbits.bookish.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Part implements TreeView {
	@GraphId
	@Indexed
	private Long id;
	@Fetch
	private String title;
	private int number;
	private String summary;
	private String content;
	private int displayPriority;
	
	@RelatedTo(type=RelTypes.PART_OF, direction=Direction.OUTGOING)
	private Book book;
	
	@RelatedTo(type=RelTypes.HAS_PART, direction=Direction.OUTGOING)
	@Fetch private Set<Part> parts = new LinkedHashSet<Part>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getDisplayPriority() {
		return displayPriority;
	}
	public void setDisplayPriority(int displayPriority) {
		this.displayPriority = displayPriority;
	}
	public String getDisplayValue() {
		return title;
	}
	public Set<Part> getParts() {
		return parts;
	}
	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}
	public Set<TreeView> getChildren() {
		return new LinkedHashSet<TreeView>(parts);
	}
}
