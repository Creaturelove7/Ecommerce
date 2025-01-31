package com.creaturelove.storeservice.db.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "products")
public record ProductEntity(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        String category,
        String avatar_url,
        @CreatedDate
        Instant createdAt,
        @LastModifiedDate
        Instant updatedAt
)
{}
