package com.toastedbits.bookish.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.toastedbits.bookish.domain.User;

public interface UserRepository extends GraphRepository<User> {}