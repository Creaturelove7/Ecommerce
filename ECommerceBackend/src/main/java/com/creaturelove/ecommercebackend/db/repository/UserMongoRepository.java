package com.creaturelove.ecommercebackend.db.repository;

import com.creaturelove.ecommercebackend.db.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<Person, String> {
}
