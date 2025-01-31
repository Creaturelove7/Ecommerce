package com.creaturelove.storeservice.db.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "inventories")
public record InventoryEntity(
        @Id
        String productId,
        String productName,
        Integer totalStock,
        @CreatedDate
        Instant createdAt,
        @LastModifiedDate
        Instant updatedAt
)
{
        public InventoryEntity withStock(int newStock){
                return new InventoryEntity(this.productId, this.productName, newStock, this.createdAt, null);
        }
}