package com.creaturelove.storeservice.controller;

import com.creaturelove.storeservice.model.dto.CartProductDto;
import com.creaturelove.storeservice.service.CartService;
import com.creaturelove.storeservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping()
    public List<CartProductDto> getCart(Long userId) throws Exception {
        return cartService.getCart(userId);
    }

    @PostMapping()
    public void addProductToCart(Long userId, String productId){
        cartService.addProductToCart(userId, productId);
    }

    @DeleteMapping()
    public void removeProductFromCart(Long userId, String productId){
        cartService.deleteProductFromCart(userId, productId);
    }

    @DeleteMapping("/clearcart")
    public void clearCart(Long userId){
        cartService.clearCart(userId);
    }


    @PostMapping("/checkout")
    public void checkout(Long userId) throws Exception {
        cartService.checkout(userId);
    }
}
