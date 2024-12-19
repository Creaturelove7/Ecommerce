package com.creaturelove.ecommercebackend.db.repository;

import com.creaturelove.ecommercebackend.db.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, Integer> {

    public ProductEntity findById(String id) throws EntityNotFoundException;
}
