package com.creaturelove.ecommercebackend.db.repository;

import com.creaturelove.ecommercebackend.db.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByCartId(Integer cartId);
}
