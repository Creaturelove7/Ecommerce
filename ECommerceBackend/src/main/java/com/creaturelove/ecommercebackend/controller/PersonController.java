package com.creaturelove.ecommercebackend.controller;

import com.creaturelove.ecommercebackend.db.entity.Person;
import com.creaturelove.ecommercebackend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    UserService userService;

    PersonController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Person insertPerson() {
        return userService.insertPerson();
    }

}
