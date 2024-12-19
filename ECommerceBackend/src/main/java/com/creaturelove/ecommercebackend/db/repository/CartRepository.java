package com.creaturelove.ecommercebackend.db.repository;

import com.creaturelove.ecommercebackend.db.entity.CartEntity;
import com.creaturelove.ecommercebackend.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    CartEntity findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE CartEntity SET totalPrice = :totalPrice WHERE id = :cartId")
    void updateTotalPrice(Integer cartId, Double totalPrice);
}
