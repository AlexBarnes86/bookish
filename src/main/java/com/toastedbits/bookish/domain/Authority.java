package com.toastedbits.bookish.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Authority {
	public Authority() {}
	public Authority(String name) {
		this.name = name;
	}
	
	@GraphId
	private Long id;
	
	@Indexed
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
