package com.creaturelove.ecommercebackend.controller;

import com.creaturelove.ecommercebackend.db.repository.CartRepository;
import com.creaturelove.ecommercebackend.service.CartService;
import com.creaturelove.ecommercebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    CartController(CartService cartService, UserService userService){
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addProducttoCart(){
        cartService.addProductToCart(3, "676376924b18513a04497be1");
    }
}
