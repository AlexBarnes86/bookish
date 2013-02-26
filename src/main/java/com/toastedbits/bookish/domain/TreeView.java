package com.toastedbits.bookish.domain;

import java.util.Set;

public interface TreeView {
	public Set<TreeView> getChildren();
	public int getDisplayPriority();
	public String getDisplayValue();
	public Long getId();
}