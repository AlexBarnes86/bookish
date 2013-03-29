package com.toastedbits.bookish.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class PlatformDetails {
	public static final String PLATFORM_DETAILS_SINGLETON_ID = "PlatformDetailsSingleton";
	
	@GraphId
	private Long id;
	
	private boolean firstTimeSetup = true;
	
	public boolean isFirstTimeSetup() {
		return firstTimeSetup;
	}
	
	public void setFirstTimeSetup(boolean firstTimeSetup) {
		this.firstTimeSetup = firstTimeSetup;
	}
}
