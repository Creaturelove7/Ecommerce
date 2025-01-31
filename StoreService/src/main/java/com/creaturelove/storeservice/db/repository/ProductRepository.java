package com.creaturelove.storeservice.db.repository;

import com.creaturelove.storeservice.db.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    Optional<ProductEntity> findById(String id);

    ProductEntity findByName(String productName);
}
