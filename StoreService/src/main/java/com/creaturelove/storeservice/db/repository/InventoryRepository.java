package com.creaturelove.storeservice.db.repository;

import com.creaturelove.storeservice.db.entity.InventoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryEntity, String> {

}
