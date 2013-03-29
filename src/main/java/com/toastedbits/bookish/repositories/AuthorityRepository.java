package com.toastedbits.bookish.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.Authority;

@Repository
public interface AuthorityRepository extends GraphRepository<Authority> {}
