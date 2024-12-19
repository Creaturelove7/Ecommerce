package com.creaturelove.ecommercebackend.service;

import com.creaturelove.ecommercebackend.db.entity.CartEntity;
import com.creaturelove.ecommercebackend.db.entity.OrderEntity;
import com.creaturelove.ecommercebackend.db.entity.ProductEntity;
import com.creaturelove.ecommercebackend.db.repository.CartRepository;
import com.creaturelove.ecommercebackend.db.repository.OrderRepository;
import com.creaturelove.ecommercebackend.db.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void addProductToCart(int userId, String productId) {
        CartEntity cart = cartRepository.findByUserId(userId);
        ProductEntity product = productRepository.findById(productId);
        OrderEntity order = orderRepository.findByCartId(cart.getId());


        Integer orderId;
        int quantity;

        if (order == null) {
            orderId = null;
            quantity = 1;
        } else {
            orderId = order.getId();
            quantity = order.getQuantity() + 1;
        }
        OrderEntity newOrder = new OrderEntity();
        newOrder.setId(order.getId());
        newOrder.setProductId(productId);
        newOrder.setCartId(cart.getId());
        newOrder.setPrice(product.price());
        newOrder.setQuantity(quantity);

        cartRepository.updateTotalPrice(cart.getId(), cart.getTotalPrice() + product.price());
    }

//    @Cacheable("cart")
//    public CartDto getCart(Long customerId) {
//        CartEntity cart = cartRepository.getByCustomerId(customerId);
//        List<OrderItemEntity> orderItems = orderItemRepository.getAllByCartId(cart.id());
//        List<OrderItemDto> orderItemDtos = getOrderItemDtos(orderItems);
//        return new CartDto(cart, orderItemDtos);
//    }
//
//    @CacheEvict(cacheNames = "cart")
//    @Transactional
//    public void clearCart(Long customerId) {
//        CartEntity cartEntity = cartRepository.getByCustomerId(customerId);
//        orderItemRepository.deleteByCartId(cartEntity.id());
//        cartRepository.updateTotalPrice(cartEntity.id(), 0.0);
//    }
//
//
//    private List<OrderItemDto> getOrderItemDtos(List<OrderItemEntity> orderItems) {
//        List<OrderItemDto> orderItemDtos = new ArrayList<>();
//        for (OrderItemEntity orderItem : orderItems) {
//            MenuItemEntity menuItem = menuItemRepository.findById(orderItem.menuItemId()).get();
//            OrderItemDto orderItemDto = new OrderItemDto(orderItem, menuItem);
//            orderItemDtos.add(orderItemDto);
//        }
//        return orderItemDtos;
//    }


}
