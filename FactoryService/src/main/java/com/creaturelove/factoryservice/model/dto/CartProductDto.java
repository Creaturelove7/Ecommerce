package com.creaturelove.factoryservice.model.dto;

import java.math.BigDecimal;

public record CartProductDto(
        String productId,
        String productName,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
