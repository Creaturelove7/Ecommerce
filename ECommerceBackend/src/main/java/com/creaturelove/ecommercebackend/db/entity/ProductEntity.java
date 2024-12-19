package com.creaturelove.ecommercebackend.db.entity;


import com.creaturelove.ecommercebackend.db.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Document(collection = "products")
public record ProductEntity(
        @Id
        String id,
        String name,
        String description,
        Double price,
        String category,
        String avatar_url,
        List<String> fetures,
        Timestamp createdAt,
        Timestamp updatedAt
)
{

}
