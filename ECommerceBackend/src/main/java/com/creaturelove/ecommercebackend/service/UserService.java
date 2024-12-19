package com.creaturelove.ecommercebackend.service;

import com.creaturelove.ecommercebackend.db.entity.Person;
import com.creaturelove.ecommercebackend.db.repository.UserMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMongoRepository userMongoRepository;

    public UserService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    public Person insertPerson() {
        Person name = new Person("name", 33);
        return userMongoRepository.insert(name);
    }

}
