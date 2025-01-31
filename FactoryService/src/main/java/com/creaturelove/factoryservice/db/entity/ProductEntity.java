package com.creaturelove.factoryservice.db.entity;


import com.creaturelove.factoryservice.db.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
