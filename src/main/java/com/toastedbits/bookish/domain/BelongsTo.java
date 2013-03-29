package com.toastedbits.bookish.domain;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

@RelationshipEntity(type=RelTypes.BELONGS_TO)
public class BelongsTo {}
