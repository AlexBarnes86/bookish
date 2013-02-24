package com.toastedbits.bookish.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Chapter {
	@GraphId
	@Indexed
	private Long id;
	private String title;
	private String content;
	private String summary;
	
	@RelatedTo(type=RelTypes.HAS, direction=Direction.OUTGOING)
	private Set<Section> sections;
	
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
	public Set<Section> getSections() {
		return sections;
	}
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
}