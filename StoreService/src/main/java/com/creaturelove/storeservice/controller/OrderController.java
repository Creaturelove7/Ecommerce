package com.creaturelove.storeservice.controller;

import com.creaturelove.storeservice.externalService.FactoryClient;
import com.creaturelove.storeservice.model.dto.OrderHistoryDto;
import com.creaturelove.storeservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;
    FactoryClient factoryClient;

    public OrderController(OrderService orderService, FactoryClient factoryClient) {
        this.orderService = orderService;
        this.factoryClient = factoryClient;
    }

    @GetMapping()
    public List<OrderHistoryDto> getOrderHistory(Long userId) {
        return orderService.getOrderHistory(userId);
    }

    @GetMapping("/hello")
    public String hello() {
        return factoryClient.hello();
    }
}
