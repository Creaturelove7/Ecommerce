package com.creaturelove.storeservice.model.dto;

import java.math.BigDecimal;

public record OrderItemDto (
        Long orderId,
        String productId,
        String productName,
        BigDecimal productPrice,
        Integer quantity
){}
