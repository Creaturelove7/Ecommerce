package com.creaturelove.storeservice.externalService;

import com.creaturelove.storeservice.db.entity.ProductEntity;
import com.creaturelove.storeservice.model.request.ProductBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FactoryService", path = "/products")
public interface FactoryClient {
    @PostMapping
    public void addProduct(@RequestBody ProductBody productBody);

    @PutMapping
    public void updateProduct(@RequestBody ProductBody productBody);

    @DeleteMapping
    public void deleteProduct(String productId);

    @GetMapping
    public ProductEntity getProductById(@RequestParam String productId);

    @GetMapping("/hello")
    public String hello();
}
