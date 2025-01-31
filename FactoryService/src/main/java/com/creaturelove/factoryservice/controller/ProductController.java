package com.creaturelove.factoryservice.controller;

import com.creaturelove.factoryservice.db.entity.ProductEntity;
import com.creaturelove.factoryservice.model.request.ProductBody;
import com.creaturelove.factoryservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody ProductBody productBody) {
        productService.addProduct(productBody);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductBody productBody) {
        productService.updateProduct(productBody);
    }

    @DeleteMapping
    public void deleteProduct(String productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping
    public ProductEntity getProductById(@RequestParam String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
