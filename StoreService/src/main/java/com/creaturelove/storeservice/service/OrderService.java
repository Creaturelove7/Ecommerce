package com.creaturelove.storeservice.service;

import com.creaturelove.storeservice.db.entity.OrderEntity;
import com.creaturelove.storeservice.db.entity.OrderItemEntity;
import com.creaturelove.storeservice.db.repository.OrderItemRepository;
import com.creaturelove.storeservice.db.repository.OrderRepository;
import com.creaturelove.storeservice.model.dto.OrderHistoryDto;
import com.creaturelove.storeservice.model.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderEntity createOrder(Long userId, List<OrderItemEntity> orderItems, BigDecimal totalPrice) {
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setPrice(totalPrice);
        order.setPaymentStatus("Pending");

        // set order constraints
        orderItems.forEach(item -> item.setOrder(order));

        // save order and orderItems
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return order;
    }

    public void updatePaymentStatus(Long orderId, String paymentStatus){
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setPaymentStatus(paymentStatus);
        orderRepository.save(order);
    }

    public List<OrderHistoryDto> getOrderHistory(Long userId) {
        List<OrderEntity> orderItems = orderRepository.findByUserId(userId);

        List<OrderHistoryDto> orderHistories = new ArrayList<>();

        for(OrderEntity order : orderItems) {
            List<OrderItemEntity> curOrderItems = order.getOrderItems();
            List<OrderItemDto> orderItemDtos = new ArrayList<>();
            for(OrderItemEntity orderItem : curOrderItems) {
                OrderItemDto orderItemDto = new OrderItemDto(
                        orderItem.getOrder().getId(),
                        orderItem.getProductId(),
                        orderItem.getProductName(),
                        orderItem.getProductPrice(),
                        orderItem.getQuantity()
                );

                orderItemDtos.add(orderItemDto);
            }

            OrderHistoryDto curOHD = new OrderHistoryDto(
                    order.getId(),
                    order.getPrice(),
                    order.getPaymentStatus(),
                    orderItemDtos
            );

            orderHistories.add(curOHD);
        }

        return orderHistories;
    }


}
