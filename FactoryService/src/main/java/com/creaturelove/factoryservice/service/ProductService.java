package com.creaturelove.factoryservice.service;

import com.creaturelove.factoryservice.db.entity.ProductEntity;
import com.creaturelove.factoryservice.db.repository.ProductRepository;
import com.creaturelove.factoryservice.model.request.ProductBody;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;

    public ProductService(ProductRepository productRepository, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
    }

    // add new Product
    public void addProduct(ProductBody productBody){
        ProductEntity newProduct = new ProductEntity(
                null,
                productBody.name(),
                productBody.description(),
                productBody.price(),
                productBody.category(),
                productBody.avatar_url(),
                null,
                null
        );

        productRepository.save(newProduct);

        inventoryService.initializeStock(
                productRepository.findByName(productBody.name()).id(),
                productBody.name(),
                productBody.stock()
        );
    }

    // update product info
    public void updateProduct(ProductBody productBody){
        ProductEntity product = productRepository.findByName(productBody.name());

        ProductEntity newProduct = new ProductEntity(
                product.id(),
                productBody.name(),
                productBody.description(),
                productBody.price(),
                productBody.category(),
                productBody.avatar_url(),
                null,
                null
        );

        productRepository.save(newProduct);

        inventoryService.updateStock(
                productRepository.findByName(productBody.name()).id(),
                productBody.stock()
        );
    }

    // delete by id
    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            inventoryService.clearStock(id);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
    }

    public ProductEntity getProductById(String id){
        return productRepository.findById(id).orElse(null);
    }
}
