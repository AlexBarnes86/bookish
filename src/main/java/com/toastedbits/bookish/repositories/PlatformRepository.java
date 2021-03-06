package com.toastedbits.bookish.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.toastedbits.bookish.domain.PlatformDetails;

@Repository
public interface PlatformRepository extends GraphRepository<PlatformDetails> {}