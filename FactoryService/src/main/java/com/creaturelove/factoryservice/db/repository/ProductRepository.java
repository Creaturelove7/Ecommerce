package com.creaturelove.factoryservice.db.repository;

import com.creaturelove.factoryservice.db.entity.ProductEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findById(String id);

    ProductEntity findByName(String productName);
}
