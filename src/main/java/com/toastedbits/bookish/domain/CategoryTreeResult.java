package com.toastedbits.bookish.domain;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface CategoryTreeResult {
	@ResultColumn("category")
	public Category getCategory();
	
	@ResultColumn("parent")
	public Category getParent();
}
