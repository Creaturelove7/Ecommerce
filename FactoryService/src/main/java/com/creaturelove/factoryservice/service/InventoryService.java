package com.creaturelove.factoryservice.service;

import com.creaturelove.factoryservice.db.entity.InventoryEntity;
import com.creaturelove.factoryservice.db.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void initializeStock(String productId, String productName, int initialStock){
        InventoryEntity inventory = new InventoryEntity(
                productId,
                productName,
                initialStock,
                null,
                null
        );

        inventoryRepository.save(inventory);
    }

    public Integer getStockByProductId(String productId){
        return inventoryRepository.findById(productId).get().totalStock();
    }

    public void updateStock(String productId, int quantity){
        InventoryEntity inventory = inventoryRepository.findById(productId).get();

        if(inventory.totalStock() + quantity < 0){
            throw new IllegalStateException("Insufficient stock for product " + productId);
        }

        InventoryEntity updatedInventory = inventory.withStock(inventory.totalStock() + quantity);

        inventoryRepository.save(updatedInventory);
    }

    public void clearStock(String productId){
        inventoryRepository.deleteById(productId);
    }
}
