package com.toastedbits.bookish.repositories;

//import java.util.List;

//import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.Category;
//import com.toastedbits.bookish.domain.CategoryTreeResult;

@Repository
public interface CategoryRepository extends GraphRepository<Category> {
//** Attempt to optimize generating the category view, abandoning for now
//* Retrieve children of a category
//*/
//	@Query("start cat=node:Category(name={0}) " +
//			"match cat<-[:BELONGS_TO]-child " +
//			"return child " +
//			"order by child.name")
//	public List<Category> getChildren(String root);

//** Attempt to optimize generating the category view, abandoning for now
//* Retrieve ancestors and siblings of ancestors
//*/
//	@Query("START cat=node:Category(name={0}) " +
//			"match parent<-[:BELONGS_TO*]-cat " +
//			"with parent " +
//			"match parent<-[:BELONGS_TO]-sib " +
//			"return sib, parent")
//	public List<CategoryTreeResult> getAncestorTree(String root);
}