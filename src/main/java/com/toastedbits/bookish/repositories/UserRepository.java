package com.toastedbits.bookish.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.BookishUser;

@Repository
public interface UserRepository extends GraphRepository<BookishUser> {
	@Query("START user=node:BookishUser('*:*') MATCH user-[:HAS_AUTHORITY]->auth WHERE auth.name='ROLE_ADMIN' RETURN user")
	List<BookishUser> findAdminUsers();
}