package com.toastedbits.bookish.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.BookishUser;

@Repository
public interface UserRepository extends GraphRepository<BookishUser> {}