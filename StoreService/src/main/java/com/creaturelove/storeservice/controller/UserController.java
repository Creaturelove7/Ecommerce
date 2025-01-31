package com.creaturelove.storeservice.controller;

import com.creaturelove.storeservice.model.request.RegisterBody;
import com.creaturelove.storeservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterBody registerBody) {
        userService.register(registerBody.username(), registerBody.password());
    }
}
