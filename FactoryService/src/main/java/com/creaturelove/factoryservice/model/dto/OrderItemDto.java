package com.creaturelove.factoryservice.model.dto;

import com.creaturelove.factoryservice.db.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderItemDto (
        Long orderId,
        String productId,
        String productName,
        BigDecimal productPrice,
        Integer quantity
){}
