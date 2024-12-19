package com.creaturelove.ecommercebackend.db.repository;

import com.creaturelove.ecommercebackend.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
