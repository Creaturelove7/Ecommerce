package com.creaturelove.factoryservice.db.repository;

import com.creaturelove.factoryservice.db.entity.InventoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryEntity, String> {

}
