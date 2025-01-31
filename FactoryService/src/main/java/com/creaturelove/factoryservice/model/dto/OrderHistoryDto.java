package com.creaturelove.factoryservice.model.dto;

import com.creaturelove.factoryservice.db.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

public record OrderHistoryDto(
        Long orderId,
        BigDecimal totalPrice,
        String paymentStatus,
        List<OrderItemDto> orderItems
) {
}
