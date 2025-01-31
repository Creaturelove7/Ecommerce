package com.creaturelove.storeservice.db.repository;

import com.creaturelove.storeservice.db.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE CartEntity SET totalPrice = :totalPrice WHERE id = :cartId")
    void updateTotalPrice(Long cartId, BigDecimal totalPrice);
}
