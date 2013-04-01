package com.toastedbits.bookish.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class PlatformDetails {
	public static final String SINGLETON_NAME = "platformSingletonName";
	
	@Indexed
	private String platformSingletonName = SINGLETON_NAME;
	
	@GraphId
	private Long id;
	
	private boolean firstTimeSetup;
	
	public boolean isFirstTimeSetup() {
		return firstTimeSetup;
	}
	
	public void setFirstTimeSetup(boolean firstTimeSetup) {
		this.firstTimeSetup = firstTimeSetup;
	}

	public String getPlatformSingletonName() {
		return platformSingletonName;
	}

	public void setPlatformSingletonName(String platformSingletonName) {
		this.platformSingletonName = platformSingletonName;
	}
}